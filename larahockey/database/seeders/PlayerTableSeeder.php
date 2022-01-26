<?php

namespace Database\Seeders;

use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;

class PlayerTableSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        DB::table('players')->insert([
            'id' => '1',
            'teamId' => '1',
            'name' => 'Becze Tihamer',
            'position' => 'RW',
            'jerseyNumber' => '20'
        ]);

        DB::table('players')->insert([
            'id' => '2',
            'teamId' => '1',
            'name' => 'Sofron Istvan',
            'position' => 'RW',
            'jerseyNumber' => '17'
        ]);

        DB::table('players')->insert([
            'id' => '3',
            'teamId' => '2',
            'name' => 'Komaromy Botond',
            'position' => 'F',
            'jerseyNumber' => '17'
        ]);

        DB::table('players')->insert([
            'id' => '4',
            'teamId' => '2',
            'name' => 'Nagy Gergo',
            'position' => 'C',
            'jerseyNumber' => '16'
        ]);
    }
}
