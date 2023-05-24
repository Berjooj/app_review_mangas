<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration {
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up() {
        Schema::table('foto_perfis', function (Blueprint $table) {
            $table->integer('ordem')->after('caminho_arquivo')->default(0);
            $table->integer('grupo')->after('ordem')->default(0);
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down() {
        Schema::table('foto_perfis', function (Blueprint $table) {
            //
        });
    }
};
