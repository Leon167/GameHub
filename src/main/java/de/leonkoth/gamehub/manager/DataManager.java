package de.leonkoth.gamehub.manager;

import de.leonkoth.gamehub.GameHub;
import de.leonkoth.gamehub.interfaces.Game;
import org.bukkit.configuration.file.FileConfiguration;

/**
 * Created by Leon on 08.08.2017.
 * Project GameHub
 * © 2016 - Leon Koth
 */
public class DataManager {

    private FileConfiguration data;

    public DataManager()
    {
        data = SettingsManager.getInstance().getData();
    }

    public Game getGame(Game game)
    {

        return game;
    }

    public void saveGame(Game game)
    {
        String name = game.getClass().getSimpleName();

        //int px = game.g.getBlockX();
        //int py = loc.getBlockY();
        //int pz = loc.getBlockZ();
        //String pworld = loc.getWorld().getName();

        this.data.set(name + ".teamAmount", game.getTeamAmount());
        this.data.set(name + ".teamAmount", game.getPlayerSpawns());
    }

}
