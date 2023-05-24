<?php

namespace App\Http\Requests\AvaliacaoRequest;

use Illuminate\Foundation\Http\FormRequest;

class UpdateAvaliacaoRequest extends FormRequest {
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
            'nota' => 'required|numeric|min:0|max:10',
            'comentario' => 'required|string',
            'id_avaliacao' => 'required|numeric'
        ];
    }

    /**
     * Get the error messages for the defined validation rules.
     *
     * @return array<string, string>
     */
    public function messages(): array {
        return [
            'nota.required' => 'A nota é obrigatória',
            'nota.numeric' => 'A nota deve ser um número',
            'nota.min' => 'A nota deve ser maior ou igual a 0',
            'nota.max' => 'A nota deve ser menor ou igual a 10',
            'comentario.required' => 'O comentário é obrigatório',
            'comentario.string' => 'O comentário deve ser uma string',
            'id_avaliacao.required' => 'O id da avaliação é obrigatório',
            'id_avaliacao.numeric' => 'O id da avaliação deve ser um número'
        ];
    }
}
