package de.leonkoth.gamehub.interfaces;

import org.bukkit.Location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/**
 * Created by Leon on 07.08.2017.
 * Project GameHub
 * © 2016 - Leon Koth
 */
public interface Game {

    ArrayList<Location> getPlayerSpawns();

    ArrayList<Location> getSpectatorSpawns();

    ArrayList<UUID> getPlayers();

    ArrayList<UUID> getSpectators();

    HashMap<String, ArrayList<UUID>> getTeams();

    HashMap<UUID, Integer> getPoints();

    HashMap<UUID, Integer> getLives();

    Integer getTeamAmount();

    void setPlayers(ArrayList<UUID> players);

    void setSpectators(ArrayList<UUID> spectators);

    void sendToGame();

    void initialize();

}
