<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Relations\HasMany;
use Illuminate\Database\Eloquent\SoftDeletes;

class Obra extends Model {
    use HasFactory, SoftDeletes;

    /**
     * Os atributos e os tipos nativos (Serão convertidos automaticamente)
     *
     * @var array
     */
    protected $casts = [
        // ID da obra
        'id' => 'integer',

        // ID da obra na API externa
        'id_externo' => 'integer',

        // Tipo da obra
        'id_tipo' => 'integer',

        // JSON com as informações da obra vindas da API externa
        'json_info' => 'object',

        // Data em que o registro foi criado
        'created_at' => 'datetime',

        // Data em que o registro foi atualizado
        'updated_at' => 'datetime',

        // Data que o registro foi excluido lógicamente
        'deleted_at' => 'datetime',
    ];

    /**
     * Internamente serão convertidos em data
     *
     * @var array
     */
    protected $dates = [
        'created_at',
        'updated_at',
        'deleted_at',
    ];

    /**
     * Os atributos que podem ser atribuídos em massa.
     *
     * @var array
     */
    protected $fillable = [
        // ID da obra
        'id',

        // ID da obra na API externa
        'id_externo',

        // Tipo da obra
        'id_tipo',

        // JSON com as informações da obra vindas da API externa
        'json_info',

        // Data em que o registro foi criado
        'created_at',

        // Data em que o registro foi atualizado
        'updated_at',

        // Data que o registro foi excluido lógicamente
        'deleted_at',
    ];


    /**
     * Os atributos que devem ser ocultados para matrizes.
     *
     * @var array
     */
    protected $hidden = [
        'id_externo'
    ];

    /**
     * Get all of the favoritos for the Obra
     *
     * @return \Illuminate\Database\Eloquent\Relations\HasMany
     */
    public function favoritos(): HasMany {
        return $this->hasMany(Favorito::class, 'id_obra', 'id');
    }

    /**
     * Get all of the avaliacoes for the Obra
     *
     * @return \Illuminate\Database\Eloquent\Relations\HasMany
     */
    public function avaliacoes(): HasMany {
        return $this->hasMany(Avaliacao::class, 'id_obra', 'id');
    }
}
