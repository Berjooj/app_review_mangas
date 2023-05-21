<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Relations\BelongsTo;
use Illuminate\Database\Eloquent\Relations\HasOne;

class Favorito extends Model {
    use HasFactory;

    /**
     * Os atributos e os tipos nativos (Serão convertidos automaticamente)
     *
     * @var array
     */
    protected $casts = [
        // ID do favorito
        'id' => 'integer',

        // ID do usuário
        'id_usuario' => 'integer',

        // ID da obra
        'id_obra' => 'integer',

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
        // ID do favorito
        'id',

        // ID do usuário
        'id_usuario',

        // ID da obra
        'id_obra',

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
     * Get the usuario that owns the Favorito
     *
     * @return \Illuminate\Database\Eloquent\Relations\BelongsTo
     */
    public function usuario(): BelongsTo {
        return $this->belongsTo(User::class, 'id_usuario', 'id');
    }

    /**
     * Get the obra associated with the Favorito
     *
     * @return \Illuminate\Database\Eloquent\Relations\HasOne
     */
    public function obra(): HasOne {
        return $this->hasOne(Obra::class, 'id', 'id_obra');
    }
}
