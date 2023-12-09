package lol.aabss.eventcore.commands.revives;

import lol.aabss.eventcore.Config;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GiveRevive implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        String permmessage = Config.getString("permission-message");
        String prefix = Config.getString("prefix");
        if (sender.hasPermission("eventcore.giverevive")){
            if (args.length == 0){
                sender.sendMessage(Config.color(prefix + " &cPlease specify a player!"));
            }
            else{
                if (args.length == 1){
                    sender.sendMessage(Config.color(prefix + " &cPlease specify an amount!"));
                }
                else{
                    if (Bukkit.getPlayer(args[0]) == null){
                        sender.sendMessage(Config.color(prefix + " &cPlease input a valid player"));
                    }
                    else{
                        Player e = Bukkit.getPlayer(args[0]);
                        assert e != null;
                        Config.giveRevives(e, Integer.valueOf(args[1]));
                        sender.sendMessage(Config.color(prefix + " &eYou gave " + args[0] + args[1] + " revives!"));
                        e.sendMessage(Config.color(prefix + " &eYou received " + args[1] + " revives!"));
                    }
                }
            }
        }
        else{
            sender.sendMessage(Config.color(prefix + " " + permmessage));
        }
        return true;
    }
}