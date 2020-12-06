package com.weswaas.hub.utils;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.weswaas.hub.Hub;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by Weswas on 28/04/2020.
 */
public class ServerUtils implements PluginMessageListener{

    public static int trainingCount = 2;

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        if (!channel.equals("BungeeCord")) {
            return;
        }
        ByteArrayDataInput in = ByteStreams.newDataInput(message);
        String subchannel = in.readUTF();
        if (subchannel.equals("PlayerCount")) {
            String server = in.readUTF();
            trainingCount = in.readInt();
        }
    }

    public static void getPracticePlayerCount(final Player p){
        new BukkitRunnable(){
            @Override
            public void run() {
                ByteArrayDataOutput out = ByteStreams.newDataOutput();
                out.writeUTF("PlayerCount");
                out.writeUTF("practice");

                p.sendPluginMessage(Hub.getInstance(), "BungeeCord", out.toByteArray());
            }
        }.runTaskLater(Hub.getInstance(), 20);
    }

}
