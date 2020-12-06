package com.weswaas.hub.listeners;

import com.weswaas.api.utils.BukkitUtil;
import com.weswaas.api.utils.ItemBuilder;
import com.weswaas.hub.Hub;
import com.weswaas.hub.gui.MainGUI;
import com.weswaas.hub.utils.ServerUtils;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.weather.WeatherChangeEvent;

/**
 * Created by Weswas on 10/01/2017.
 */
public class PlayerListener implements Listener{

    @EventHandler
    public void onBreak(BlockBreakEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){

        e.setJoinMessage(null);
        BukkitUtil.clear(e.getPlayer());

        e.getPlayer().teleport(Hub.getInstance().getSpawn());

        e.getPlayer().getInventory().setItem(4, new ItemBuilder(Material.NETHER_STAR).name("§3Server Selector").build());
        e.getPlayer().getInventory().setHeldItemSlot(4);
        e.getPlayer().updateInventory();
        ServerUtils.getPracticePlayerCount(e.getPlayer());

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        e.setQuitMessage(null);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e){

        if(e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK){
            return;
        }

        if(e.getClickedBlock() != null){
            if(e.getClickedBlock().getType() == Material.CHEST){
                if(e.getPlayer().getDisplayName().equalsIgnoreCase("TomTomPlaysGames")){
                    e.getPlayer().sendMessage("§aHey TomTom, Weswas here. Hope you're proud that I fixed a bug that you recommanded me ! <3");
                }
                e.setCancelled(true);
            }
        }

        if(e.getItem() == null){
            return;
        }

        if(e.getItem().getType() != Material.NETHER_STAR){
            return;
        }

        if(e.getPlayer() == null){
            return;
        }

        new MainGUI().open(e.getPlayer());

    }

    @EventHandler
    public void onDamage(EntityDamageEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    public void onWeather(WeatherChangeEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    public void onBucket(PlayerBucketEmptyEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    public void onBucket(PlayerBucketFillEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    public void onEntity(PlayerInteractEntityEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    public void onPickup(PlayerPickupItemEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    public void onInginite(BlockIgniteEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    public void onFood(FoodLevelChangeEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e){
        if(e.getPlayer().getLocation().getBlockY() < 60){
            e.getPlayer().teleport(Hub.getInstance().getSpawn());
        }
    }

}
