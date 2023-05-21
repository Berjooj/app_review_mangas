<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration {
    /**
     * Run the migrations.
     */
    public function up(): void {
        Schema::create('favoritos', function (Blueprint $table) {
            $table->id();
            $table->bigInteger('id_usuario');
            $table->bigInteger('id_obra');

            $table->foreign('id_usuario')->references('id')->on('users')->onDelete('cascade');
            $table->foreign('id_obra')->references('id')->on('obras')->onDelete('cascade');

            $table->unique(['id_usuario', 'id_obra']);

            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void {
        Schema::dropIfExists('favoritos');
    }
};
