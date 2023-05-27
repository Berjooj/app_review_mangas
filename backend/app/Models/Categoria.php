<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Relations\HasManyThrough;

class Categoria extends Model {
    use HasFactory;

    /**
     * Os atributos e os tipos nativos (Serão convertidos automaticamente)
     *
     * @var array
     */
    protected $casts = [
        // ID da obra
        'id' => 'integer',

        // Nome da categoria
        'nome' => 'string',

        // Data em que o registro foi criado
        'created_at' => 'datetime',

        // Data em que o registro foi atualizado
        'updated_at' => 'datetime',
    ];

    /**
     * Internamente serão convertidos em data
     *
     * @var array
     */
    protected $dates = [
        'created_at',
        'updated_at'
    ];

    /**
     * Os atributos que podem ser atribuídos em massa.
     *
     * @var array
     */
    protected $fillable = [
        // ID da obra
        'id',

        // Nome da categoria
        'nome',

        // Data em que o registro foi criado
        'created_at',

        // Data em que o registro foi atualizado
        'updated_at',
    ];

    /**
     * Get all of the obras for the Categoria
     *
     * @return \Illuminate\Database\Eloquent\Relations\HasManyThrough
     */
    public function obras(): HasManyThrough
    {
        return $this->hasManyThrough(Obra::class, ObraCategoria::class, 'id_categoria', 'id', 'id', 'id_obra');
    }
}
