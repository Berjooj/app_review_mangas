<?php

namespace App\Http\Controllers;

use App\Models\Avaliacao;
use App\Http\Requests\AvaliacaoRequest\StoreAvaliacaoRequest;
use App\Http\Requests\AvaliacaoRequest\UpdateAvaliacaoRequest;
use App\Models\Obra;
use App\Utils\APIResponse;

class AvaliacaoController extends Controller {
    public function index(int $idObra): \Illuminate\Http\JsonResponse {
        try {
            $avaliacoes = Avaliacao::where('id_obra', $idObra)
                ->with('curtidas')
                ->orderBy('created_at', 'desc')
                ->get();

            return APIResponse::success($avaliacoes, 'Avaliacoes carregadas com sucesso');
        } catch (\Exception $e) {
            return APIResponse::error($e->getMessage());
        }
    }

    public function store(StoreAvaliacaoRequest $request): \Illuminate\Http\JsonResponse {
        try {
            $obra = Obra::findOrFail($request->validated()['id_obra']);

            $jsonObra = json_decode($obra->json_info);

            // Tá errado, mas fazer o que, a API não retorna as avaliações só a nota :\
            $jsonObra->nota = ($jsonObra->nota + $request->validated()['nota']) / 2;

            $obra->json_info = json_encode($jsonObra);
            $obra->save();

            $avaliacao = Avaliacao::firstOrCreate($request->validated());
            return APIResponse::success($avaliacao, 'Avaliacao criada com sucesso!');
        } catch (\Exception $e) {
            return APIResponse::error($e->getMessage());
        }
    }
}
