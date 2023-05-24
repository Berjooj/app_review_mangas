<?php

namespace App\Http\Requests\AvaliacaoCurtidaRequest;

use Illuminate\Foundation\Http\FormRequest;

class StoreAvaliacaoCurtidaRequest extends FormRequest {
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
            'id_avaliacao' => 'required|numeric'
        ];
    }

    /**
     * Get the error messages for the defined validation rules.
     */
    public function messages(): array {
        return [
            'id_avaliacao.required' => 'O campo id_avaliacao é obrigatório',
            'id_avaliacao.numeric' => 'O campo id_avaliacao deve ser numérico'
        ];
    }
}
