<?php

namespace App\Services;

use App\Models\Categoria;
use App\Models\Obra;
use Illuminate\Support\Facades\DB;

class FeedService {
    private static $instance;

    private $client;

    private $url = 'https://kitsu.io/api/edge';

    private function __construct() {
        $this->client = new \GuzzleHttp\Client();
    }

    /**
     * @var \App\Services\FeedService
     */
    public static function getInstance(): \App\Services\FeedService {
        if (!self::$instance) {
            self::$instance = new self();
        }

        return self::$instance;
    }

    /**
     * @param array $data
     * @return array
     * @throws \GuzzleHttp\Exception\GuzzleException
     */
    public function getFeed(array $data): array {
        $data['page[limit]'] = 5;
        $data['page[offset]'] = $data['page'] ? ($data['page'] * 10) : 0;

        $obras = Obra::with('categorias')
            ->offset($data['page[offset]'])
            ->limit(10);

        switch ($data['tipoFiltro']) {
            case 'em_alta':
                $data['sort'] = '-averageRating';
                $obras->orderBy('nota', 'desc')
                    ->orderBy('qt_favoritos', 'desc');
                break;
            case 'lancamentos':
                $data['sort'] = '-createdAt';
                $obras->orderBy('data_lancamento', 'desc');
                break;
            case 'em_breve':
                $data['sort'] = '-startDate';
                $obras->where('data_lancamento', '>', DB::raw('NOW()'))
                    ->orderBy('id', 'desc');
                break;
        }

        if (!empty($data['filtro'])) {
            $obras->where('lower(titulo)', 'like', '%' . mb_strtolower($data['filtro']) . '%');
        }

        if ($obras->count() == 10) {
            return $obras->toArray();
        }

        $data['filter[text]'] = $data['filtro'] ?? '';

        $data['include'] = 'categories';

        unset($data['tipoFiltro']);
        unset($data['page']);

        $animes = $this->getAPI('anime', $data);
        $mangas = $this->getAPI('manga', $data);

        $obras = array_filter(array_merge($animes['data'] ?? [], $mangas['data'] ?? []));
        $categoriasIncluidas = array_filter(array_merge($animes['included'] ?? [], $mangas['included'] ?? []));

        $feed = [];
        $categorias = [];

        foreach ($obras as $obra) {
            /** @var \App\Models\Obra */
            $obraLocal = Obra::firstOrCreate(['id_externo' => $obra['id'], 'id_tipo' => $obra['type'] == 'anime' ? 1 : 2]);

            $jsonLocal = json_decode($obraLocal->json_info);

            /** @var array */
            $obraCategorias = $obra['relationships']['categories']['data'] ?? [];

            foreach ($obraCategorias as $categoria) {
                if (!in_array($categoria['id'], $categorias)) {
                    $categoriaLocal = Categoria::find($categoria['id']);

                    if (!$categoriaLocal) {
                        $categoriasAPI = array_filter($categoriasIncluidas, function ($item) use ($categoria) {
                            return $item['id'] == $categoria['id'];
                        });

                        if (count($categoriasAPI)) {
                            $categoriaLocal = Categoria::create([
                                'id' => $categoria['id'],
                                'nome' => ucfirst(head($categoriasAPI)['attributes']['title'])
                            ]);
                        }
                    }

                    $categorias[] = $categoria['id'];
                    $obraLocal->categorias[] = $categoriaLocal;
                }
            }

            $obraCategorias = array_column(array_values($obraCategorias), 'nome');

            $obraLocal->id = $obraLocal->id;

            $obraLocal->id_externo = $obra['id'];

            $obraLocal->titulo = $obra['attributes']['titles']['en']
                ?? $obra['attributes']['titles']['en_jp']
                ?? $obra['attributes']['titles']['canonicalTitle']
                ?? null;

            $obraLocal->subtitulo = $obra['attributes']['titles']['ja_jp']
                ?? $obra['attributes']['titles']['en_jp']
                ?? null;

            $obraLocal->data_lancamento = $obra['attributes']['startDate']
                ?? $obra['attributes']['startDate']
                ?? null;

            $obraLocal->qt_episodios = $obra['attributes']['episodeCount']
                ?? null;

            $obraLocal->qt_volumes = $obra['attributes']['pageCount']
                ?? rand(90, 400);

            $obraLocal->qt_favoritos = $jsonLocal->favCount
                ?? $obra['attributes']['favoritesCount'];

            $obraLocal->nota = $jsonLocal->nota
                ?? ($obra['attributes']['averageRating']
                    ? round((($obra['attributes']['averageRating'] * 5) / 100), 2)
                    : 0
                );

            $obraLocal->qt_avaliacoes = $jsonLocal->qtAvaliacoes
                ?? rand(1, 1000);

            $obraLocal->url_imagem = $obra['attributes']['posterImage']['original'];

            $obraLocal->update();

            $feed[] = $obraLocal;
        }

        return $feed;
    }

    /**
     * @var string $tipo
     * @var array $data
     * @throws \GuzzleHttp\Exception\GuzzleException
     * @return array
     */
    private function getAPI(string $tipo, array $data): array {
        try {
            $data = array_filter($data);

            $response = $this->client->request('GET', $this->url . '/' . $tipo, [
                'query' => $data
            ]);

            $obra = json_decode($response->getBody()->getContents(), true);

            return $obra;
        } catch (\Exception $e) {
            dd($e->getMessage());
            throw new \Exception('Erro ao buscar a obra');
        }
    }
}
