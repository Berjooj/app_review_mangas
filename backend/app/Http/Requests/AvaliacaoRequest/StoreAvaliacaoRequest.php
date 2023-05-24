<?php

namespace App\Http\Requests\AvaliacaoRequest;

use Illuminate\Foundation\Http\FormRequest;

class StoreAvaliacaoRequest extends FormRequest {
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
            'id_obra' => 'required|integer',
            'nota' => 'required|integer|min:0|max:5',
            'comentario' => 'sometimes|required|string'
        ];
    }

    /**
     * Get the error messages for the defined validation rules.
     */
    public function messages(): array {
        return [
            'id_obra.required' => 'O campo id_obra é obrigatório',
            'id_obra.integer' => 'O campo id_obra deve ser um inteiro',
            'nota.required' => 'O campo nota é obrigatório',
            'nota.integer' => 'O campo nota deve ser um inteiro',
            'nota.min' => 'O campo nota deve ser maior ou igual a 0',
            'nota.max' => 'O campo nota deve ser menor ou igual a 5',
            'comentario.required' => 'O campo comentario é obrigatório',
            'comentario.string' => 'O campo comentario deve ser uma string'
        ];
    }
}
