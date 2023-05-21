<?php

namespace Database\Seeders;

use Illuminate\Database\Seeder;

class UsuarioSeeder extends Seeder {
    /**
     * Run the database seeds.
     */
    public function run(): void {
        \App\Models\User::factory(10)->create();
    }
}
