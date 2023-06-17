<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Relations\BelongsTo;
use Illuminate\Database\Eloquent\Relations\HasMany;

class Avaliacao extends Model {
    use HasFactory;

    /**
     * Os atributos e os tipos nativos (Serão convertidos automaticamente)
     *
     * @var array
     */
    protected $casts = [
        // ID da avaliação
        'id' => 'integer',

        // ID do usuário
        'id_usuario' => 'integer',

        // ID da obra
        'id_obra' => 'integer',

        // Comentário da obra
        'comentario' => 'string',

        // Nota da avaliação
        'nota' => 'integer',

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
        // ID da avaliação
        'id',

        // ID do usuário
        'id_usuario',

        // ID da obra
        'id_obra',

        // Comentário
        'comentario',

        // Nota da avaliação
        'nota'
    ];

    /**
     * Get the usuario that owns the Avaliacao
     *
     * @return \Illuminate\Database\Eloquent\Relations\BelongsTo
     */
    public function usuario(): BelongsTo {
        return $this->belongsTo(User::class, 'id_usuario', 'id');
    }

    /**
     * Get the obra that owns the Avaliacao
     *
     * @return \Illuminate\Database\Eloquent\Relations\BelongsTo
     */
    public function obra(): BelongsTo {
        return $this->belongsTo(Obra::class, 'id_obra', 'id');
    }

    /**
     * Get all of the curtidas for the Avaliacao
     *
     * @return \Illuminate\Database\Eloquent\Relations\HasMany
     */
    public function curtidas(): HasMany {
        return $this->hasMany(AvaliacaoCurtida::class, 'id_avaliacao', 'id');
    }
}
