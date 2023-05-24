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

            // Calcula a média das notas
            $jsonObra->nota = (($jsonObra->nota * $jsonObra->qtAvaliacoes) + $request->validated()['nota']) / ($jsonObra->qtAvaliacoes + 1);
            $jsonObra->qtAvaliacoes++;

            $obra->json_info = json_encode($jsonObra);
            $obra->save();

            $avaliacao = Avaliacao::firstOrCreate(array_merge($request->validated(), ['id_usuario' => auth()->user()->id]));
            return APIResponse::success($avaliacao, 'Avaliacao criada com sucesso!');
        } catch (\Exception $e) {
            return APIResponse::error($e->getMessage());
        }
    }

    public function update(UpdateAvaliacaoRequest $request): \Illuminate\Http\JsonResponse {
        try {
            $avaliacao = Avaliacao::where('id_usuario', auth()->user()->id)
                ->where('id', $request->validated()['id'])
                ->firstOrFail();

            $obra = Obra::findOrFail($avaliacao->id_obra);

            $jsonObra = json_decode($obra->json_info);

            // Calcula a média das notas
            $jsonObra->nota = (($jsonObra->nota * $jsonObra->qtAvaliacoes) + $request->validated()['nota']) / ($jsonObra->qtAvaliacoes + 1);

            $obra->json_info = json_encode($jsonObra);
            $obra->save();

            $avaliacao->update($request->validated());
            return APIResponse::success($avaliacao, 'Avaliacao atualizada com sucesso!');
        } catch (\Exception $e) {
            return APIResponse::error($e->getMessage());
        }
    }
}
