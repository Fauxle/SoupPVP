package com.github.fauxle.souppvp;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

@Getter
@Setter
public class PlayerPVPSoupEvent extends PlayerEvent implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled;

    /** The new health the player will be at after consuming the soup */
    private double newHealth;

    /** The new food level the player will be at after consuming the soup */
    private int newFoodLevel;

    public PlayerPVPSoupEvent(Player who, double newHealth, int newFoodLevel) {
        super(who);
        this.newHealth = newHealth;
        this.newFoodLevel = newFoodLevel;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}
