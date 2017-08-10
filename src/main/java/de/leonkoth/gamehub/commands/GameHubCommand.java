package de.leonkoth.gamehub.commands;

import de.leonkoth.gamehub.GameHub;
import de.leonkoth.gamehub.interfaces.Game;
import de.leonkoth.gamehub.manager.MessageManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Leon on 07.08.2017.
 * Project GameHub
 * © 2016 - Leon Koth
 */
public class GameHubCommand implements CommandExecutor{

    private GameHub gameHub;

    public GameHubCommand(GameHub gameHub)
    {
        this.gameHub = gameHub;
    }

    String commands[] = {
            "/gamehub info",
            "/gamehub setlobbyspawn",
            "/gamehub <game>",
            "/gamehub <game> setplayerspawn [number]",
            "/gamehub <game> setspectatorspawn [number]"
    };

    private void sendHelp(Player p)
    {
        MessageManager.getInstance().msg(p, MessageManager.MessageType.INFO, "Commands:");
        for(String cmd : commands){
            p.sendMessage(ChatColor.GRAY + cmd);
        }
    }

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(commandSender instanceof Player)
        {
            Player sender = (Player) commandSender;
            if(args.length == 0)
            {
                this.sendHelp(sender);
                return true;
            }
            else if(args.length == 1)
            {
                if(args[0].equalsIgnoreCase("info"))
                {
                    this.sendHelp(sender);
                    return true;
                } else if(args[0].equalsIgnoreCase("setlobbyspawn"))
                {

                    return true;
                } else
                {
                    for(Game game : this.gameHub.getGames())
                    {
                        if(game.getClass().getSimpleName().equalsIgnoreCase(args[0]))
                        {
                            sender.sendMessage("success");
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

}
