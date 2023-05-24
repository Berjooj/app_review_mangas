<?php

namespace App\Models;

use Illuminate\Foundation\Auth\User as Authenticatable;
use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Relations\HasMany;
use Illuminate\Database\Eloquent\Relations\HasOne;
use Illuminate\Database\Eloquent\SoftDeletes;
use Laravel\Passport\HasApiTokens;

class User extends Authenticatable {
    use HasApiTokens, HasFactory, SoftDeletes;

    /**
     * Os atributos e os tipos nativos (Serão convertidos automaticamente)
     *
     * @var array
     */
    protected $casts = [
        // ID do usuário
        'id' => 'integer',

        // Nome do usuário
        'nome' => 'string',

        // E-mail do usuário
        'email' => 'string',

        // Senha do usuário
        'password' => 'string',

        // ID foto de perfil
        'id_foto_perfil' => 'integer',

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
        // Nome do usuário
        'nome',

        // E-mail do usuário
        'email',

        // Senha do usuário
        'password',

        // ID foto de perfil
        'id_foto_perfil',

        // Data em que o registro foi atualizado
        'updated_at',
    ];


    /**
     * Os atributos que devem ser ocultados para matrizes.
     *
     * @var array
     */
    protected $hidden = [
        'password',
        'updated_at'
    ];

    /**
     * Get the foto_perfil associated with the Usuario
     *
     * @return \Illuminate\Database\Eloquent\Relations\HasOne
     */
    public function fotoPerfil(): HasOne {
        return $this->hasOne(FotoPerfil::class, 'id', 'id_foto_perfil');
    }

    /**
     * Get all of the favoritos for the Usuario
     *
     * @return \Illuminate\Database\Eloquent\Relations\HasMany
     */
    public function favoritos(): HasMany {
        return $this->hasMany(Favorito::class, 'id_usuario', 'id');
    }

    /**
     * Get all of the avaliacoes for the Usuario
     *
     * @return \Illuminate\Database\Eloquent\Relations\HasMany
     */
    public function avaliacoes(): HasMany {
        return $this->hasMany(Avaliacao::class, 'id_usuario', 'id');
    }

    /**
     * Get all of the curtidas for the Usuario
     *
     * @return \Illuminate\Database\Eloquent\Relations\HasMany
     */
    public function curtidas(): HasMany {
        return $this->hasMany(AvaliacaoCurtida::class, 'id_usuario', 'id');
    }
}
