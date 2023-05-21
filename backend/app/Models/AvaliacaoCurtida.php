<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Relations\BelongsTo;

class AvaliacaoCurtida extends Model {
    use HasFactory;

    /**
     * Os atributos e os tipos nativos (Serão convertidos automaticamente)
     *
     * @var array
     */
    protected $casts = [
        // ID da avaliação curtida
        'id' => 'integer',

        // ID da avaliação
        'id_avaliacao' => 'integer',

        // ID do usuário
        'id_usuario' => 'integer',

        // Data em que o registro foi criado
        'created_at' => 'datetime',

        // Data em que o registro foi atualizado
        'updated_at' => 'datetime'
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
        // ID da avaliação curtida
        'id',

        // ID da avaliação
        'id_avaliacao',

        // ID do usuário
        'id_usuario',

        // Data em que o registro foi criado
        'created_at',

        // Data em que o registro foi atualizado
        'updated_at'
    ];

    /**
     * Get the usuario that owns the AvaliacaoCurtida
     *
     * @return \Illuminate\Database\Eloquent\Relations\BelongsTo
     */
    public function usuario(): BelongsTo {
        return $this->belongsTo(User::class, 'id_usuario');
    }

    /**
     * Get the avaliacao that owns the AvaliacaoCurtida
     *
     * @return \Illuminate\Database\Eloquent\Relations\BelongsTo
     */
    public function avaliacao(): BelongsTo {
        return $this->belongsTo(Avaliacao::class, 'id_avaliacao');
    }
}
