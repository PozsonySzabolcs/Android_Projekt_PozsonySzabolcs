<?php

namespace App\Http\Controllers\API;

use App\Http\Controllers\Controller;
use App\Http\Resources\PlayerResource;
use App\Models\Player;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;

class PlayerController extends Controller
{
    public function index()
    {
        $players = DB::table('players')->select('players.name', 'teamName', 'position', 'jerseyNumber')
            ->join('teams', 'teamId', '=', 'teams.id')
            ->get();
        return $players;
    }

    public function store(Request $request)
    {
        $team_id = DB::table('players')->select('teamId')->where('teamId', '=', $request->get('teamId'))->first();

        if (is_null($team_id)) {
            die (response()->json([
                'success' => 'false',
                'message' => 'Sikertelen adatbeszúrás!'
            ]));
        }

        $player = new Player([
            'teamId' => $request->input('teamId'),
            'name' => $request->input('name'),
            'position' => $request->input('position'),
            'jerseyNumber' => $request->input('jerseyNumber')
        ]);

        $player->save();
        return response()->json([
            'success' => 'true',
            'message' => 'Sikeres adatbeszúrás!'
        ]);
    }

    public function show($id)
    {
        $player = DB::table('players')->select('players.name', 'teamName', 'position', 'jerseyNumber', 'teamId')
            ->join('teams', 'teamId', '=', 'teams.id')
            ->where('players.id', '=', $id)
            ->first();

        if (is_null($player)) {
            return response()->json([
                'success' => 'false',
                'message' => 'Hiba!'
            ]);
        }

        return $player;
    }

    public function update(Request $request, Player $player)
    {
        $player->update($request->all());
        return new PlayerResource($player);
    }

    public function destroy(Player $player)
    {
        $player->delete();
        return new PlayerResource($player);
    }
}
