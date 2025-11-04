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

    @Getter private static final HandlerList handlers = new HandlerList();
    private boolean cancelled;

    private double newHealth;
    private int newFoodLevel;

    public PlayerPVPSoupEvent(Player who, double newHealth, int newFoodLevel) {
        super(who);
        this.newHealth = newHealth;
        this.newFoodLevel = newFoodLevel;
    }
}
