<?php

namespace App\Http\Requests\UsuarioRequest;

use App\Utils\APIResponse;
use Illuminate\Foundation\Http\FormRequest;
use Illuminate\Contracts\Validation\Validator;
use Illuminate\Http\Exceptions\HttpResponseException;

class UpdateUsuarioRequest extends FormRequest {
    /**
     * Determine if the user is authorized to make this request.
     */
    public function authorize(): bool {
        return true;
    }

    /**
     * Get the validation rules that apply to the request.
     *
     * @return array<string, \Illuminate\Contracts\Validation\ValidationRule|array|string>
     */
    public function rules(): array {
        return [
            'id' => 'required|integer',
            'nome' => 'required|string',
            'password' => 'sometimes|required|string|min:6',
            'id_foto_perfil' => 'sometimes|nullable|integer|min:1'
        ];
    }

    /**
     * Get the error messages for the defined validation rules.
     *
     * @return array
     */
    public function messages() {
        return [
            'id.required' => 'ID deve ser informado',
            'nome.required' => 'Nome deve ser informado',
            'password.required' => 'Senha deve ser informada',
            'password.min' => 'Senha deve ter no mínimo 6 caracteres',
            'id_foto_perfil.min' => 'ID da foto de perfil deve ser maior que 0',
        ];
    }

    protected function failedValidation(Validator $validator) {
        throw new HttpResponseException(APIResponse::error($validator->errors()->all()));
    }
}
