<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration {
    /**
     * Run the migrations.
     */
    public function up(): void {
        Schema::create('obras', function (Blueprint $table) {
            $table->id();
            $table->bigInteger('id_externo')->unique();

            // 1 - Mangá, 2 - Anime
            $table->integer('id_tipo')->default(1);

            $table->string('titulo')->nullable();
            $table->string('subtitulo')->nullable();
            $table->integer('qt_episodios')->nullable();
            $table->integer('qt_volumes')->nullable();
            $table->integer('qt_avaliacoes')->nullable();
            $table->bigInteger('qt_favoritos')->nullable();
            $table->float('nota')->nullable();
            $table->string('url_imagem')->nullable();

            $table->dateTime('data_lancamento')->nullable();

            $table->timestamps();
            $table->softDeletes();
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void {
        Schema::dropIfExists('obras');
    }
};
