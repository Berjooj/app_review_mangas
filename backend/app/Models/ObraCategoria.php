<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Relations\HasMany;

class ObraCategoria extends Model {
    use HasFactory;

    protected $table = 'obra_categoria';

    /**
     * Os atributos e os tipos nativos (Serão convertidos automaticamente)
     *
     * @var array
     */
    protected $casts = [
        // ID
        'id' => 'integer',

        // ID da categoria
        'id_categoria' => 'integer',

        // ID da obra
        'id_obra' => 'integer',
    ];

    /**
     * Os atributos que podem ser atribuídos em massa.
     *
     * @var array
     */
    protected $fillable = [
        // ID
        'id',

        // ID da categoria
        'id_categoria',

        // ID da obra
        'id_obra',
    ];

    /**
     * Get all of the obras for the ObraCategoria
     *
     * @return \Illuminate\Database\Eloquent\Relations\HasMany
     */
    public function obras(): HasMany {
        return $this->hasMany(Obra::class, 'id', 'id_obra');
    }

    /**
     * Get all of the categorias for the ObraCategoria
     *
     * @return \Illuminate\Database\Eloquent\Relations\HasMany
     */
    public function categorias(): HasMany {
        return $this->hasMany(Categoria::class, 'id', 'id_categoria');
    }
}
