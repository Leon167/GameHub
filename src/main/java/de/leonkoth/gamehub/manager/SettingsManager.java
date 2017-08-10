package de.leonkoth.gamehub.manager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;

import java.io.File;
import java.io.IOException;

/**
 * Created by Leon on 30.05.2015.
 * Project ShopSystem
 * <p/>
 * Copyright (C) 2014 Leon167 { LekoHD
 */

public class SettingsManager {
	static SettingsManager instance = new SettingsManager();
	Plugin p;
	FileConfiguration config;
	File cfile;
	FileConfiguration data;
	File dfile;

    /**
     * Get the instace of this settings manager
     * @return the instance
     */
	public static SettingsManager getInstance() {
		return instance;
	}

    /**
     * To setup the config and date file
     */
	public void setup(Plugin p) {
		this.cfile = new File(p.getDataFolder(), "config.yml");
		this.config = p.getConfig();

		if (!p.getDataFolder().exists()) {
			p.getDataFolder().mkdir();
		}

        config.options().copyDefaults(true);
        try {
            config.save(cfile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.dfile = new File(p.getDataFolder(), "data.yml");

		if (!this.dfile.exists()) {
			try {
				this.dfile.createNewFile();
				//MessageManager.getInstance().log("Data successfully created!");
			} catch (IOException e) {
				Bukkit.getServer().getLogger()
						.severe(ChatColor.RED + "Could not create data.yml!");
			}
		}

		this.data = YamlConfiguration.loadConfiguration(this.dfile);

	}

    /**
     * To get the data file
     * @return the data file. Used in:
     */
	public FileConfiguration getData() {
		return this.data;
	}

    /**
     * To save the current data file
     */
	public void saveData() {
		try {
			this.data.save(this.dfile);
			//MessageManager.getInstance().log("Data successfully saved!");
		} catch (IOException e) {
			Bukkit.getServer().getLogger()
					.severe(ChatColor.RED + "Could not save data.yml!");
		}
	}

    /**
     * To reload the data file
     */
	public void reloadData() {
		this.data = YamlConfiguration.loadConfiguration(this.dfile);
	}

    /**
     * To get the config file
     * @return the config file
     */
	public FileConfiguration getConfig() {
		return this.config;
	}

    /**
     * To save the current config file
     */
	public void saveConfig() {
		try {
			this.config.save(this.cfile);
			//MessageManager.getInstance().log("Config successfully saved!");
		} catch (IOException e) {
			Bukkit.getServer().getLogger()
					.severe(ChatColor.RED + "Could not save config.yml!");
		}
	}

    /**
     * To reload the config file
     */
	public void reloadConfig() {
		this.config = YamlConfiguration.loadConfiguration(this.cfile);
	}

    /**
     * To get the PluginDescriptionFile
     * @return the file
     */
	public PluginDescriptionFile getDesc() {
		return this.p.getDescription();
	}
}