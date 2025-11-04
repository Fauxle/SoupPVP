package com.github.fauxle.souppvp;

import org.bukkit.plugin.java.JavaPlugin;

public class SoupPVP extends JavaPlugin {

    protected double soupHealthRestores, soupFoodRestores;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        reloadConfig();
        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
    }

    @Override
    public void reloadConfig() {
        super.reloadConfig();
        soupHealthRestores = getConfig().getDouble("soup-health-restores", 0);
        soupFoodRestores = getConfig().getDouble("soup-food-level-restores", 0);
    }
}
