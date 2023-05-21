<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Relations\HasMany;
use Illuminate\Database\Eloquent\SoftDeletes;

class FotoPerfil extends Model {
    use HasFactory, SoftDeletes;

    /**
     * Os atributos e os tipos nativos (Serão convertidos automaticamente)
     *
     * @var array
     */
    protected $casts = [
        // ID da foto de perfil
        'id_foto_perfil' => 'integer',

        // Caminho do arquivo
        'caminho_arquivo' => 'string',

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
        'updated_at',
    ];

    /**
     * Os atributos que podem ser atribuídos em massa.
     *
     * @var array
     */
    protected $fillable = [
        // ID da foto de perfil
        'id_foto_perfil',

        // Caminho do arquivo
        'caminho_arquivo',

        // Data em que o registro foi criado
        'created_at',

        // Data em que o registro foi atualizado
        'updated_at',
    ];


    /**
     * Os atributos que devem ser ocultados para matrizes.
     *
     * @var array
     */
    protected $hidden = [];

    /**
     * Get all of the usuarios for the FotoPerfil
     *
     * @return \Illuminate\Database\Eloquent\Relations\HasMany
     */
    public function usuarios(): HasMany {
        return $this->hasMany(User::class, 'id_foto_perfil', 'id');
    }
}
