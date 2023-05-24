<?php

use App\Http\Controllers\AuthController;
use App\Http\Controllers\AvaliacaoController;
use App\Http\Controllers\AvaliacaoCurtidaController;
use App\Http\Controllers\FavoritoController;
use App\Http\Controllers\FeedController;
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

Route::middleware(['auth:api'])->prefix('feed')->group(function () {
    Route::get('/', [FeedController::class, 'index']);
});

Route::middleware(['auth:api'])->prefix('favoritos')->group(function () {
    Route::get('/', [FavoritoController::class, 'index']);
    Route::post('/', [FavoritoController::class, 'store']);
    Route::delete('/{idFavorito}', [FavoritoController::class, 'destroy']);
});

Route::middleware(['auth:api'])->prefix('avaliar')->group(function () {
    Route::get('/{idObra}', [AvaliacaoController::class, 'index']);
    Route::post('/', [AvaliacaoController::class, 'store']);
});

// TODO
Route::middleware(['auth:api'])->prefix('curtir')->group(function () {
    Route::post('/', [AvaliacaoCurtidaController::class, 'store']);
    Route::destroy('/', [AvaliacaoCurtidaController::class, 'destroy']);
});
