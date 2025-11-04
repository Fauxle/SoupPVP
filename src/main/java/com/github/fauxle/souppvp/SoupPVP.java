package com.github.fauxle.souppvp;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;
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
        if (getConfig().getBoolean("add-chocolate-milk-recipe", false)) {
            addChocolateMilk();
        }
        if (getConfig().getBoolean("add-cactus-soup-recipe", false)) {
            addCactusJuice();
        }
    }

    private void addChocolateMilk() {
        ItemStack chocolateMilk = new ItemStack(Material.MUSHROOM_SOUP);
        ItemMeta meta = chocolateMilk.getItemMeta();
        meta.setDisplayName("Chocolate Milk");
        chocolateMilk.setItemMeta(meta);

        ShapelessRecipe recipe = new ShapelessRecipe(chocolateMilk);
        recipe.addIngredient(Material.BOWL);
        recipe.addIngredient(Material.COCOA);

        getServer().addRecipe(recipe);
    }

    private void addCactusJuice() {
        ItemStack cactusJuice = new ItemStack(Material.MUSHROOM_SOUP);
        ItemMeta meta = cactusJuice.getItemMeta();
        meta.setDisplayName("Cactus Soup");
        cactusJuice.setItemMeta(meta);

        ShapelessRecipe recipe = new ShapelessRecipe(cactusJuice);
        recipe.addIngredient(Material.BOWL);
        recipe.addIngredient(2, Material.CACTUS);

        getServer().addRecipe(recipe);
    }
}
