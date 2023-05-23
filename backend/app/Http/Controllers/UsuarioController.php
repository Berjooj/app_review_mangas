<?php

namespace App\Http\Controllers;

use App\Models\User;
use App\Http\Requests\UsuarioRequest\StoreUsuarioRequest;
use App\Http\Requests\UsuarioRequest\UpdateUsuarioRequest;
use App\Utils\APIResponse;
use Illuminate\Support\Facades\Hash;

class UsuarioController extends Controller {
    /**
     * Display a listing of the resource.
     */
    public function index(): \Illuminate\Http\JsonResponse {
        try {
            $usuarios = User::all();

            return APIResponse::success($usuarios);
        } catch (\Exception $e) {
            return APIResponse::error($e->getMessage());
        }
    }

    /**
     * Store a newly created resource in storage.
     */
    public function store(StoreUsuarioRequest $request): \Illuminate\Http\JsonResponse {
        try {
            $requestValidatedFields = $request->validated();
            $requestValidatedFields['password'] = bcrypt($requestValidatedFields['password']);

            $usuario = User::create($requestValidatedFields);

            return APIResponse::success($usuario, 'Usuário criado com sucesso!');
        } catch (\Exception $e) {
            return APIResponse::error($e->getMessage());
        }
    }

    /**
     * Display the specified resource.
     */
    public function show(int $idUsuario): \Illuminate\Http\JsonResponse {
        try {
            $usuario = User::findOrFail($idUsuario);

            return APIResponse::success($usuario);
        } catch (\Exception $e) {
            return APIResponse::error($e->getMessage());
        }
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(UpdateUsuarioRequest $request): \Illuminate\Http\JsonResponse {
        try {
            $usuario = User::findOrFail($request->validated()['id']);

            $usuario->update($request->validated());

            return APIResponse::success($usuario, 'Usuário atualizado com sucesso!');
        } catch (\Exception $e) {
            return APIResponse::error($e->getMessage());
        }
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy(int $idUsuario): \Illuminate\Http\JsonResponse {
        try {
            $usuario = User::findOrFail($idUsuario);

            $usuario->delete();

            return APIResponse::success(null, 'Usuário deletado com sucesso!');
        } catch (\Exception $e) {
            return APIResponse::error($e->getMessage());
        }
    }
}
