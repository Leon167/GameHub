package de.leonkoth.gamehub;

import de.leonkoth.gamehub.commands.GameHubCommand;
import de.leonkoth.gamehub.games.TeamDeathmatch;
import de.leonkoth.gamehub.interfaces.Game;
import de.leonkoth.gamehub.manager.MessageManager;
import de.leonkoth.gamehub.manager.SettingsManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Created by Leon on 07.08.2017.
 * Project de.leonkoth.gamehub.GameHub
 * © 2016 - Leon Koth
 */
public class GameHub extends JavaPlugin {

    private static GameHub instance;
    private static SettingsManager settingsManager;
    private ArrayList<Game> games;

    private int loadedGames = 0;

    public static GameHub getInstance() {
        return instance;
    }

    @Override
    public void onEnable()
    {
        instance = this;

        games = new ArrayList<Game>();

        settingsManager = new SettingsManager();
        settingsManager.setup(instance);

        PluginManager pm = Bukkit.getServer().getPluginManager();

        this.loadLocale();

        this.getCommand("gamehub").setExecutor(new GameHubCommand(this));

        this.registerGame(new TeamDeathmatch());

        ConsoleCommandSender clogger = this.getServer().getConsoleSender();
        clogger.sendMessage(ChatColor.DARK_GRAY + "*******************************");
        clogger.sendMessage(ChatColor.DARK_GRAY + "*           " + ChatColor.AQUA + "GameHub" + ChatColor.DARK_GRAY + "           *");
        clogger.sendMessage(ChatColor.DARK_GRAY + "*  " + ChatColor.GOLD + "Author: " + this.getDescription().getAuthors() + ChatColor.DARK_GRAY + "  *");
        clogger.sendMessage(ChatColor.DARK_GRAY + "*        " + ChatColor.GOLD + "Version: " + this.getDescription().getVersion() + ChatColor.DARK_GRAY + "         *");
        clogger.sendMessage(ChatColor.DARK_GRAY + "*       " + ChatColor.GOLD + "Games Loaded: " + this.loadedGames + "       *");
        clogger.sendMessage(ChatColor.DARK_GRAY + "*******************************");
    }

    @Override
    public void onDisable()
    {

    }

    public ArrayList<Game> getGames()
    {
        return games;
    }

    private void registerGame(Game game)
    {
        this.games.add(game);
        this.loadedGames++;
        //getServer().getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "*******************************");
    }

    private void loadLocale(){
        FileConfiguration locale;
        File lfile;

        lfile = new File(this.getDataFolder(), "locale.yml");
        if (!lfile.exists()) {
            try {
                lfile.createNewFile();
                MessageManager.getInstance().log("Locale file successfully created!");
            } catch (IOException e) {
                Bukkit.getServer().getLogger()
                        .severe(ChatColor.RED + "Could not create locale.yml!");
            }
        }

        locale = YamlConfiguration.loadConfiguration(lfile);
        try {
            for(Field field : Locale.class.getDeclaredFields())
            {
                if(field.getType() == String.class)
                {
                    String name = field.getName();
                    String content = (String) field.get(null);
                    if(!locale.contains("Messages." + name))
                        locale.set("Messages." + name, content.replace("§", "&"));
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        try {
            locale.save(lfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(!locale.isConfigurationSection("Messages")) return;
        try {
            for (String name : locale.getConfigurationSection("Messages").getKeys(false)) {
                String content = locale.getString("Messages." + name).replace("&", "§");
                Field field = Locale.class.getDeclaredField(name);
                field.set(null, content);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
