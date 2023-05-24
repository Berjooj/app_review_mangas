<?php

namespace App\Http\Controllers;

use App\Http\Requests\FeedRequest\GetFeedRequest;
use App\Services\FeedService;
use App\Utils\APIResponse;

class FeedController extends Controller {

    public function index(GetFeedRequest $request): \Illuminate\Http\JsonResponse {
        try {
            $validado = $request->validated();

            if (empty($validado['tipoFiltro'])) {
                $validado['tipoFiltro'] = 'em_alta';
            }

            $obras = FeedService::getInstance()->getFeed($validado);

            return APIResponse::success($obras, 'Feed carregado com sucesso');
        } catch (\Exception $e) {
            return APIResponse::error($e->getMessage());
        }
    }
}
