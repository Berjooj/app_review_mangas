<?php

namespace App\Http\Controllers;

use App\Models\AvaliacaoCurtida;
use App\Http\Requests\AvaliacaoCurtidaRequest\StoreAvaliacaoCurtidaRequest;
use App\Utils\APIResponse;

class AvaliacaoCurtidaController extends Controller {
    public function store(StoreAvaliacaoCurtidaRequest $request): \Illuminate\Http\JsonResponse {
        try {
            $avaliacaoCurtida = AvaliacaoCurtida::firstOrCreate(array_merge($request->validated(), ['id_usuario' => auth()->user()->id]));
            return APIResponse::success($avaliacaoCurtida, 'AvaliacaoCurtida criada com sucesso!');
        } catch (\Exception $e) {
            return APIResponse::error($e->getMessage());
        }
    }

    public function destroy(int $idCurtida): \Illuminate\Http\JsonResponse {
        try {
            $avaliacaoCurtida = AvaliacaoCurtida::findOrFail($idCurtida);
            $avaliacaoCurtida->delete();

            return APIResponse::success(null, 'AvaliacaoCurtida removida com sucesso!');
        } catch (\Exception $e) {
            return APIResponse::error($e->getMessage());
        }
    }
}
