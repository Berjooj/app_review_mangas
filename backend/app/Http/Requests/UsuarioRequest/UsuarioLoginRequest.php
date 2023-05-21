<?php

namespace App\Http\Requests\UsuarioRequest;

use App\Utils\APIResponse;
use Illuminate\Foundation\Http\FormRequest;
use Illuminate\Contracts\Validation\Validator;
use Illuminate\Http\Exceptions\HttpResponseException;

class UsuarioLoginRequest extends FormRequest {
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
            'email' => 'required|email|string',
            'password' => 'required|string|min:6',
        ];
    }

    /**
     * Get the error messages for the defined validation rules.
     *
     * @return array
     */
    public function messages() {
        return [
            'email.required' => 'Credenciais inválidas',
            'email.email' => 'Credenciais inválidas',
            'password.required' => 'Credenciais inválidas',
            'password.min' => 'Credenciais inválidas',
        ];
    }

    protected function failedValidation(Validator $validator) {
        throw new HttpResponseException(APIResponse::error('Credenciais inválidas'));
    }
}
