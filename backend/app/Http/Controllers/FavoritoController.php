<?php

namespace App\Http\Controllers;

use App\Http\Requests\FavoritoRequest\StoreFavoritoRequest;
use App\Models\Favorito;
use App\Models\Obra;
use App\Utils\APIResponse;

class FavoritoController extends Controller {

    public function index(): \Illuminate\Http\JsonResponse {
        try {
            $obras = Favorito::where('id_usuario', auth()->user()->id)
                ->with('obra')
                ->get();

            return APIResponse::success($obras, 'Favoritos carregados com sucesso');
        } catch (\Exception $e) {
            return APIResponse::error($e->getMessage());
        }
    }

    public function store(StoreFavoritoRequest $request): \Illuminate\Http\JsonResponse {
        try {
            $obra = Obra::findOrFail($request->validated()['id_obra']);

            $obra->qt_favoritos++;

            $obra->save();

            $favorito = Favorito::firstOrCreate([
                'id_usuario' => auth()->user()->id,
                'id_obra' => $obra->id,
            ]);

            return APIResponse::success($favorito, 'Obra favoritada com sucesso!');
        } catch (\Exception $e) {
            return APIResponse::error($e->getMessage());
        }
    }

    public function destroy(int $idFavorito): \Illuminate\Http\JsonResponse {
        try {
            $favorito = Favorito::where('id_usuario', auth()->user()->id)
                ->where('id', $idFavorito)
                ->firstOrFail();

            $obra = Obra::findOrFail($favorito->id_obra);

            $obra->qt_favoritos--;

            $obra->save();

            $favorito->delete();

            return APIResponse::success([], 'Obra desfavoritada com sucesso!');
        } catch (\Exception $e) {
            return APIResponse::error($e->getMessage());
        }
    }
}
