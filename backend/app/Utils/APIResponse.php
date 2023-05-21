<?php

namespace App\Utils;

class APIResponse {
    public static function success($data = null, $message = null): \Illuminate\Http\JsonResponse {
        return response()->json([
            'status' => 200,
            'data' => $data,
            'message' => $message
        ]);
    }

    public static function error($message = null, $status = 500): \Illuminate\Http\JsonResponse {
        return response()->json([
            'status' => $status,
            'message' => $message
        ]);
    }
}
