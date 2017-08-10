package de.leonkoth.gamehub.games;

import de.leonkoth.gamehub.interfaces.Game;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/**
 * Created by Leon on 07.08.2017.
 * Project GameHub
 * © 2016 - Leon Koth
 */
public class TeamDeathmatch implements Game {
    private ArrayList<Location> playerSpawns, spectatorSpawns;
    private ArrayList<UUID> players, spectators;
    private HashMap<String, ArrayList<UUID>> teams;
    private HashMap<UUID, Integer> points, lives;
    private int teamAmount;

    @Override
    public ArrayList<Location> getPlayerSpawns() {
        return playerSpawns;
    }

    @Override
    public ArrayList<Location> getSpectatorSpawns() {
        return spectatorSpawns;
    }

    @Override
    public ArrayList<UUID> getPlayers() {
        return players;
    }

    @Override
    public ArrayList<UUID> getSpectators() {
        return spectators;
    }

    @Override
    public HashMap<String, ArrayList<UUID>> getTeams() {
        return teams;
    }

    @Override
    public HashMap<UUID, Integer> getPoints() {
        return points;
    }

    @Override
    public HashMap<UUID, Integer> getLives() {
        return lives;
    }

    @Override
    public Integer getTeamAmount() {
        return this.teamAmount;
    }

    @Override
    public void setPlayers(ArrayList<UUID> players) {
        this.players = players;
    }

    @Override
    public void setSpectators(ArrayList<UUID> spectators) {
        this.spectators = spectators;
    }

    @Override
    public void sendToGame() {
        for(UUID uuid : this.players)
        {
            Bukkit.getPlayer(uuid).teleport(this.playerSpawns.get(0));
        }
    }

    @Override
    public void initialize() {
        Bukkit.broadcastMessage("Success");
    }
}
