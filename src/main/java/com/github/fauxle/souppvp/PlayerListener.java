package com.github.fauxle.souppvp;

import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

@RequiredArgsConstructor
public class PlayerListener implements Listener {

    private final SoupPVP plugin;

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (event.getItem() == null) return;
        if (event.getItem().getType() != Material.MUSHROOM_SOUP) return;
        if (!(event.getAction() == Action.RIGHT_CLICK_AIR
                || event.getAction() == Action.RIGHT_CLICK_BLOCK)) return;

        event.setUseInteractedBlock(Event.Result.DENY);

        Player p = event.getPlayer();

        double newHealth = p.getHealth();
        int newFoodLevel = p.getFoodLevel();

        if (p.getHealth() < p.getMaxHealth()) {
            newHealth = Math.min(p.getMaxHealth(), newHealth + plugin.soupHealthRestores);
        } else if (p.getFoodLevel() < 20) {
            newFoodLevel = Math.min(20, (int) (newFoodLevel + plugin.soupFoodRestores));
        } else {
            event.setCancelled(true);
            return;
        }

        PlayerPVPSoupEvent soupEvent = new PlayerPVPSoupEvent(p, newHealth, newFoodLevel);
        plugin.getServer().getPluginManager().callEvent(soupEvent);

        if (soupEvent.isCancelled()) {
            event.setCancelled(true);
            return;
        }

        p.setHealth(soupEvent.getNewHealth());
        p.setFoodLevel(soupEvent.getNewFoodLevel());

        ItemStack soup = event.getItem();
        if (soup.getAmount() > 1) {
            soup.setAmount(soup.getAmount() - 1);
            p.getInventory().addItem(new ItemStack(Material.BOWL));
        } else {
            soup.setType(Material.BOWL);
        }
    }
}
