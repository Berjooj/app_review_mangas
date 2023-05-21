<?php

namespace App\Http\Controllers;

use App\Http\Requests\UsuarioRequest\UsuarioLoginRequest;
use App\Models\User;
use App\Utils\APIResponse;
use Illuminate\Http\Request;
use Illuminate\Support\Carbon;
use Illuminate\Support\Facades\Auth;

class AuthController extends Controller {
    public function login(UsuarioLoginRequest $request): \Illuminate\Http\JsonResponse {
        try {
            if (!auth()->attempt(['email' => $request->email, 'password' => $request->password])) {
                throw new \Exception('Credenciais inválidas', 401);
            }

            /** @var User $usuario */
            $usuario = auth()->user();

            $token = $usuario->createToken('Personal Access Token')->accessToken;

            return APIResponse::success([
                'user' => auth()->user(),
                'token_type' => 'Bearer',
                'access_token' => $token,
            ], 'Usuário autenticado com sucesso!');
        } catch (\Exception $e) {
            return APIResponse::error($e->getMessage(), $e->getCode());
        }
    }

    public function logout(Request $request): \Illuminate\Http\JsonResponse {
        try {
            $request->user()->tokens()->delete();

            return APIResponse::success([], 'Usuário deslogado com sucesso!');
        } catch (\Exception $e) {
            return APIResponse::error($e->getMessage());
        }
    }

    public function me(Request $request): \Illuminate\Http\JsonResponse {
        try {
            return APIResponse::success($request->user());
        } catch (\Exception $e) {
            return APIResponse::error($e->getMessage());
        }
    }
}
