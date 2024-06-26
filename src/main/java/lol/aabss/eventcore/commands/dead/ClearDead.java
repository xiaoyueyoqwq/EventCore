package lol.aabss.eventcore.commands.dead;

import lol.aabss.eventcore.util.SimpleCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static lol.aabss.eventcore.EventCore.API;
import static lol.aabss.eventcore.util.Config.msg;

public class ClearDead implements SimpleCommand {

    @Override
    public boolean run(CommandSender sender, Command command, String[] args) {
        for (Player p : API.getDead()){
            p.getInventory().clear();
        }
        sender.sendMessage(msg("cleardead.cleared"));
        return true;
    }
}
