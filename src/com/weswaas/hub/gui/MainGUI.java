package com.weswaas.hub.gui;

import com.weswaas.api.functions.GUI;
import com.weswaas.api.utils.ItemBuilder;
import com.weswaas.api.utils.ServerUtils;
import com.weswaas.api.utils.server_pinging.ServerPinger;
import com.weswaas.api.utils.server_pinging.StatusResponse;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;

/**
 * Created by Weswas on 10/01/2017.
 */
public class MainGUI extends GUI {

    public MainGUI() {
        super(null, 9*3, "§3Server Selector");
    }

    @Override
    public void update() {

        com.weswaas.hub.utils.ServerUtils.getPracticePlayerCount(getPlayer());

        StatusResponse response = ServerPinger.POST_NETTY_REWRITE.fetchData("localhost", 4050);

        String motd;
        String[] data = null;

        if(response == null){
            motd = null;
            data = null;
        }else{
            motd = response.description;
            data = motd.split("}");
        }

        String host;
        String scenarios;
        String gameState;
        String teams;
        String borderSize;
        String players;
        String max;

        if(data != null && data.length > 1){
            host = data == null ? "None" : data[0];
            scenarios = data == null ? "None" : data[2];
            gameState = data == null ? "Whitelisted" : data[1];
            teams = data == null ? "FFA" : data[3];
            borderSize = data == null ? "1500x1500" : data[4];
            players = data == null ? "0" : data[5];
            max = data == null ? "120" : data[6];
        }else{
            host = "None";
            scenarios = "None";
            gameState = "Whitelisted";
            teams = "FFA";
            borderSize = "1500x1500";
            players = "0";
            max = "120";
        }

        setItem(12, new ItemBuilder(Material.GOLDEN_APPLE).name("§3Hosted UHC - §b#1").amount(1)
                .lore("§7§m---------------------")
                .lore("§3Host §8» §b" + (host.equalsIgnoreCase("null") ? "Undefined" : host))
                .lore("§3Current Gamestate §8» §b" + StringUtils.capitalize(gameState))
                .lore("§3Scenarios §8» §b" + scenarios)
                .lore("§3Type §8» §b" + teams)
                .lore("§3Border Size §8» §b" + borderSize)
                .lore("§3Players §8» §b" + players + "/" + max)
                .lore("§7§m---------------------").build());

        setItem(14, new ItemBuilder(Material.DIAMOND_SWORD).name("§3[BETA] §bTraining Server").amount(1)
                .lore("§7§m---------------------")
                .lore("§3Current Status §8» §cMaintenance")
                .lore("§3Online Players §8» §b" + com.weswaas.hub.utils.ServerUtils.trainingCount + "/150")
                .lore("§7§m---------------------")
                .lore("§8» §7We are currently looking for")
                .lore("§8» §bbeta testers §7to avoid bugs on")
                .lore("§8» §7our future §btraining server§7.")
                .lore("§8» §7If you are interested in it,")
                .lore("§8» §7please contact us on Twitter:")
                .lore("§8» §b@Oniziac.")
                .build());

    }

    @Override
    public void onClick(InventoryClickEvent e) {
        e.setCancelled(true);
        Player p = (Player)e.getWhoClicked();

        p.closeInventory();

        if(e.getCurrentItem().getType() == Material.GOLDEN_APPLE){
            ServerUtils.sendToServer(p, "uhc");
        }

        else if(e.getCurrentItem().getType() == Material.DIAMOND_SWORD){
            ServerUtils.sendToServer(p, "practice");
        }

    }

    @Override
    public void onClose(InventoryCloseEvent e) {

    }

    @Override
    public void onOpen(InventoryOpenEvent e) {

    }

    @Override
    public void onDrag(InventoryDragEvent e) {

    }

}
