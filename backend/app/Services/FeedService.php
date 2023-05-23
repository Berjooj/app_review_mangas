<?php

namespace App\Services;

use App\Models\Categoria;
use App\Models\Obra;

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
        $data['page[limit]'] = 10;
        $data['page[offset]'] = $data['page'] ?? 0;

        unset($data['page']);

        switch ($data['tipoFiltro']) {
            case 'em_alta':
                $data['sort'] = '-averageRating';
                break;
            case 'lancamentos':
                $data['sort'] = '-createdAt';
                break;
            case 'em_breve':
                $data['sort'] = '-startDate';
                break;
        }

        unset($data['tipoFiltro']);

        $data['include'] = 'categories';

        $animes = $this->getAPI('anime', $data);
        $mangas = $this->getAPI('manga', $data);

        $obras = array_merge($animes, $mangas);
        $feed = [];
        $categorias = [];

        foreach ($obras as $obra) {
            $obraLocal = Obra::firstOrCreate(['id_externo' => $obra['id'], 'id_tipo' => $obra['type'] == 'anime' ? 1 : 2]);

            $obraCategorias = $obra['relationships']['categories']['data'] ?? [];

            foreach ($obraCategorias as &$categoria) {
                if (!array_key_exists($categoria['id'], $categorias)) {
                    $categoriaLocal = Categoria::find($categoria['id']);

                    if (!$categoriaLocal) {
                        $categoriasAPI = $this->getAPI(
                            'categories/' . $categoria['id'],
                            ['fieldsHeader' => '[categories]=slug']
                        );

                        $categorias[$categoria['id']] = $categoriasAPI['attributes']['slug'];
                        $categoria['nome'] = ucfirst($categoriasAPI['attributes']['slug']);

                        Categoria::firstOrCreate(['id' => $categoria['id'], 'nome' => $categoria['nome']]);
                    } else {
                        $categoria['nome'] = $categoriaLocal->nome;
                    }
                }
            }

            $obraCategorias = array_column(array_values($obraCategorias), 'nome');

            $feed[] = [
                'id' => $obraLocal->id,
                'id_externo' => $obra['id'],
                'titulo' => $obra['attributes']['titles']['en']
                    ?? $obra['attributes']['titles']['en_jp']
                    ?? $obra['attributes']['titles']['canonicalTitle']
                    ?? null,
                'subtitulo' => $obra['attributes']['titles']['ja_jp']
                    ?? $obra['attributes']['titles']['en_jp']
                    ?? null,
                'qtEpisodios' => $obra['attributes']['episodeCount']
                    ?? null,
                'qtVolumes' => $obra['attributes']['pageCount']
                    ?? rand(90, 400),
                'favCount' => $obra['attributes']['favoritesCount'],
                'nota' => $obra['attributes']['averageRating']
                    ? round((($obra['attributes']['averageRating'] * 5) / 100), 1)
                    : 0,
                'categorias' => count($obraCategorias) > 3
                    ? array_slice($obraCategorias, 0, 3)
                    : $obraCategorias,
                'imagem' => $obra['attributes']['posterImage']['original'],
            ];
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
            $response = $this->client->request('GET', $this->url . '/' . $tipo, [
                'query' => $data
            ]);

            $obra = json_decode($response->getBody()->getContents(), true)['data'];

            return $obra;
        } catch (\Exception $e) {
            throw new \Exception('Erro ao buscar a obra');
        }
    }
}
