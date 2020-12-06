package com.weswaas.hub.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Weswas on 29/04/2020.
 */
public class FlyCommand implements CommandExecutor{

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){

        if(sender instanceof Player){
            Player p = (Player) sender;

            if(p.hasPermission("oniziac.oniziac")){
                if(args.length == 0){
                    if(!p.isFlying()){
                        p.sendMessage("§3You are now in fly mode.");
                        p.setAllowFlight(true);
                    }else{
                        p.setFlying(false);
                        p.sendMessage("§3You are not in fly mode anymore !");
                        p.setAllowFlight(false);
                    }
                }else{
                    p.sendMessage("§cInvalid synthax. Please try with /fly");
                }
            }

        }

        return false;
    }

}
