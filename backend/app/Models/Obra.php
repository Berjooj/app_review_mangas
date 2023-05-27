<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Relations\HasMany;
use Illuminate\Database\Eloquent\Relations\HasManyThrough;
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

        // Título da obra
        'titulo' => 'string',

        // Subtítulo da obra
        'subtitulo' => 'string',

        // Quantidade de episódios da obra
        'qt_episodios' => 'integer',

        // Quantidade de volumes da obra
        'qt_volumes' => 'integer',

        // Quantidade de favoritos da obra
        'qt_favoritos' => 'float',

        // Nota da obra
        'nota' => 'float',

        // Quantidade de avaliações da obra
        'qt_avaliacoes' => 'integer',

        // URL da imagem da obra
        'url_imagem' => 'string',

        // Data de lançamento
        'data_lancamento' => 'datetime',

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
        'data_lancamento'
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

        // Título da obra
        'titulo',

        // Subtítulo da obra
        'subtitulo',

        // Quantidade de episódios da obra
        'qt_episodios',

        // Quantidade de volumes da obra
        'qt_volumes',

        // Quantidade de avaliações da obra
        'qt_avaliacoes',

        // Quantidade de favoritos da obra
        'qt_favoritos',

        // Nota da obra
        'nota',

        // Data de lançamento
        'data_lancamento',

        // URL da imagem da obra
        'url_imagem',

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

    /**
     * Get all of the categorias for the Obra
     *
     * @return \Illuminate\Database\Eloquent\Relations\HasManyThrough
     */
    public function categorias(): HasManyThrough {
        return $this->hasManyThrough(Categoria::class, ObraCategoria::class, 'id_obra', 'id', 'id', 'id_categoria');
    }
}
