package com.weswaas.hub;

import com.weswaas.hub.commands.FlyCommand;
import com.weswaas.hub.listeners.PlayerListener;
import com.weswaas.hub.utils.ServerUtils;
import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

/**
 * Created by Weswas on 10/01/2017.
 */
public class Hub extends JavaPlugin{

    private static Hub instance;

    private Location spawn;

    @Override
    public void onEnable() {

        instances();

        spawn = new Location(Bukkit.getWorld("world"), 4.5, 101, -0.5);
        Vector playerLookDirection = new Vector(-4, 0, 0);
        spawn.setDirection(playerLookDirection.normalize());
        gameRules(spawn.getWorld());
        ServerUtils se = new ServerUtils();

        registerListeners(new PlayerListener());
        Bukkit.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        Bukkit.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", se);

        getCommand("fly").setExecutor(new FlyCommand());

    }

    private void registerListeners(Listener...listeners){
        for(Listener list : listeners){
            Bukkit.getPluginManager().registerEvents(list, this);
        }
    }

    private void gameRules(World w){

        w.setGameRuleValue("doDaylightCycle", "true");
        w.setGameRuleValue("doMobSpawning", "false");
        w.setPVP(false);
        w.setDifficulty(Difficulty.PEACEFUL);
        w.setThundering(false);
        w.setStorm(false);
        w.setSpawnLocation(spawn.getBlockX(), spawn.getBlockY(), spawn.getBlockZ());

    }

    private void instances(){
        instance = this;
    }

    public static Hub getInstance(){
        return instance;
    }

    public Location getSpawn(){
        return this.spawn;
    }

}
