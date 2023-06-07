<?php

namespace App\Http\Requests\UsuarioRequest;

use App\Utils\APIResponse;
use Illuminate\Contracts\Validation\Validator;
use Illuminate\Foundation\Http\FormRequest;
use Illuminate\Http\Exceptions\HttpResponseException;

class StoreUsuarioRequest extends FormRequest {
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
            'nome' => 'required|string|min:4',
            'email' => 'required|string|email',
            'password' => 'required|string|min:6',
            'id_foto_perfil' => 'sometimes|nullable|integer|min:1',
        ];
    }

    /**
     * Get the error messages for the defined validation rules.
     *
     * @return array
     */
    public function messages() {
        return [
            'nome.required' => 'Nome deve ser informado',
            'email.required' => 'Email deve ser informado',
            'email.email' => 'Email inválido',
            'password.required' => 'Senha deve ser informada',
            'nome.min' => 'Nome deve ter no mínimo 5 caracteres',
            'password.min' => 'Senha deve ter no mínimo 6 caracteres',
            'email.email' => 'Email inválido',
            'id_foto_perfil.min' => 'ID da foto de perfil deve ser maior que 0',
        ];
    }

    protected function failedValidation(Validator $validator) {
        throw new HttpResponseException(APIResponse::error($validator->errors()->all()));
    }
}
