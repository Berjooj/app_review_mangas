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
            $fotosPerfil = FotoPerfil::orderBy('grupo')
                ->orderBy('ordem')
                ->get();

            return APIResponse::success($fotosPerfil);
        } catch (\Exception $e) {
            return APIResponse::error($e->getMessage());
        }
    }
}
