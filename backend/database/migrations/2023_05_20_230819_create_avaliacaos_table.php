<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration {
    /**
     * Run the migrations.
     */
    public function up(): void {
        Schema::create('avaliacaos', function (Blueprint $table) {
            $table->id();
            $table->bigInteger('id_usuario');
            $table->bigInteger('id_obra');
            $table->integer('nota');
            $table->string('comentario')->nullable();
            $table->timestamps();

            $table->foreign('id_usuario')->references('id')->on('users');
            $table->foreign('id_obra')->references('id')->on('obras');
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void {
        Schema::dropIfExists('avaliacaos');
    }
};
