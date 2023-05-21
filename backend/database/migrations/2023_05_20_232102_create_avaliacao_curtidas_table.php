<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration {
    /**
     * Run the migrations.
     */
    public function up(): void {
        Schema::create('avaliacao_curtidas', function (Blueprint $table) {
            $table->id();
            $table->bigInteger('id_avaliacao');
            $table->bigInteger('id_usuario');

            $table->foreign('id_avaliacao')->references('id')->on('avaliacaos');
            $table->foreign('id_usuario')->references('id')->on('users');

            $table->unique(['id_avaliacao', 'id_usuario']);
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void {
        Schema::dropIfExists('avaliacao_curtidas');
    }
};
