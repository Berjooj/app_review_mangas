<?php

use App\Http\Controllers\AuthController;
use App\Http\Controllers\UsuarioController;
use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider and all of them will
| be assigned to the "api" middleware group. Make something great!
|
*/

Route::prefix('auth')->group(function () {
    Route::post('/register', [UsuarioController::class, 'store']);

    Route::post('/login', [AuthController::class, 'login']);
    Route::post('/logout', [AuthController::class, 'logout'])->middleware('auth:api');

    Route::get('/me', [AuthController::class, 'me'])->middleware('auth:api');
});

Route::middleware(['auth:api'])->prefix('usuario')->group(function () {
    Route::get('/', [UsuarioController::class, 'index']);
    Route::get('/{idUsuario}', [UsuarioController::class, 'show']);

    Route::put('/', [UsuarioController::class, 'update']);

    Route::delete('/{idUsuario}', [UsuarioController::class, 'destroy']);
});
