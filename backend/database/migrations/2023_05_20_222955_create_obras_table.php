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

            $table->unique(['id_externo', 'id_tipo']);

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
