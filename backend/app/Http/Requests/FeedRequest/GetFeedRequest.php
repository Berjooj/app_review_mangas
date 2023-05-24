<?php

namespace App\Http\Requests\FeedRequest;

use Illuminate\Foundation\Http\FormRequest;

class GetFeedRequest extends FormRequest {
    /**
     * Determine if the user is authorized to make this request.
     *
     * @return bool
     */
    public function authorize() {
        return true;
    }

    /**
     * Get the validation rules that apply to the request.
     *
     * @return array<string, mixed>
     */
    public function rules() {
        return [
            'page' => 'required|integer|min:0',
            'tipoFiltro' => 'sometimes|required|string|in:em_alta,lancamentos,em_breve',
            'filtro' => 'sometimes|required|string'
        ];
    }

    /**
     * Get the error messages for the defined validation rules.
     *
     * @return array<string, mixed>
     */
    public function messages() {
        return [
            'page.required' => 'O campo paginação é obrigatório',
            'page.integer' => 'O campo paginação deve ser um número inteiro',
            'page.min' => 'O campo paginação deve ser maior ou igual a 0',
            'tipoFiltro.required' => 'O campo filtro é obrigatório',
            'tipoFiltro.string' => 'O campo filtro deve ser uma string',
            'tipoFiltro.in' => 'O campo filtro deve ser uma das opções: em_alta, lancamentos, em_breve'
        ];
    }
}
