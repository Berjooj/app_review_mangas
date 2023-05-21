<?php

namespace App\Http\Requests\FotoPerfilRequest;

use App\Utils\APIResponse;
use Illuminate\Contracts\Validation\Validator as ValidationValidator;
use Illuminate\Foundation\Http\FormRequest;
use Illuminate\Http\Exceptions\HttpResponseException;

class StoreFotoPerfilRequest extends FormRequest {
    /**
     * Determine if the user is authorized to make this request.
     */
    public function authorize(): bool {
        return false;
    }

    /**
     * Get the validation rules that apply to the request.
     *
     * @return array<string, \Illuminate\Contracts\Validation\ValidationRule|array|string>
     */
    public function rules(): array {
        return [
            'caminho_arquivo' => 'required|string'
        ];
    }

    /**
     * Get the error messages for the defined validation rules.
     *
     * @return array
     */
    public function messages() {
        return [
            'caminho_arquivo.required' => 'Caminho do arquivo deve ser informado'
        ];
    }

    protected function failedValidation(ValidationValidator $validator) {
        throw new HttpResponseException(APIResponse::error($validator->errors()->all()));
    }
}
