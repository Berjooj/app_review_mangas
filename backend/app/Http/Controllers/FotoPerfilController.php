<?php

namespace App\Http\Controllers;

use App\Models\FotoPerfil;
use App\Http\Requests\FotoPerfilRequest\StoreFotoPerfilRequest;
use App\Http\Requests\FotoPerfilRequest\UpdateFotoPerfilRequest;
use App\Utils\APIResponse;

class FotoPerfilController extends Controller {
    /**
     * Display a listing of the resource.
     */
    public function index() {
        try {
            $fotosPerfil = FotoPerfil::all();

            return APIResponse::success($fotosPerfil);
        } catch (\Exception $e) {
            return APIResponse::error($e->getMessage());
        }
    }

    /**
     * Store a newly created resource in storage.
     */
    public function store(StoreFotoPerfilRequest $request) {
        // TODO: upload da foto
    }

    /**
     * Display the specified resource.
     */
    public function show(int $idFotoPerfil) {
        try {
            $fotoPerfil = FotoPerfil::findOrFail($idFotoPerfil);

            return APIResponse::success($fotoPerfil);
        } catch (\Exception $e) {
            return APIResponse::error($e->getMessage());
        }
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(UpdateFotoPerfilRequest $request, FotoPerfil $fotoPerfil) {
        try {
            $fotoPerfil->update($request->validated());

            return APIResponse::success($fotoPerfil, 'Foto de perfil atualizada com sucesso!');
        } catch (\Exception $e) {
            return APIResponse::error($e->getMessage());
        }
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy(FotoPerfil $fotoPerfil) {
        try {
            $fotoPerfil->delete();

            return APIResponse::success(null, 'Foto de perfil removida com sucesso!');
        } catch (\Exception $e) {
            return APIResponse::error($e->getMessage());
        }
    }
}
