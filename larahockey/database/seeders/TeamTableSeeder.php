<?php

namespace Database\Seeders;

use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;

class TeamTableSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        DB::table('teams')->insert([
            'id' => '1',
            'teamName' => 'Sportklub Csikszereda',
            'coach_name' => 'Rico Rossi'
        ]);

        DB::table('teams')->insert([
            'id' => '2',
            'teamName' => 'Ferencvaros',
            'coach_name' => 'Fodor Szabolcs'
        ]);
    }
}
