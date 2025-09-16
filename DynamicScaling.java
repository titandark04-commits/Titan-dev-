package com.titan.dynamicscaling;

import org.bukkit.entity.Player;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.HashMap;
import java.util.UUID;

public class DynamicScaling extends JavaPlugin {
    private final HashMap<UUID, HashMap<UUID, Double>> mobHealth = new HashMap<>();

    @Override
    public void onEnable() {
        getLogger().info("DynamicScaling enabled!");
    }

    public void scaleMob(Player player, LivingEntity mob, int level) {
        double scaledHp = 100 + (level * 5);
        mobHealth.computeIfAbsent(player.getUniqueId(), k -> new HashMap<>())
                 .put(mob.getUniqueId(), scaledHp);
        player.sendMessage("This mob has " + scaledHp + " HP for you!");
    }

    public double getMobHp(Player player, LivingEntity mob) {
        return mobHealth.getOrDefault(player.getUniqueId(), new HashMap<>())
                        .getOrDefault(mob.getUniqueId(), mob.getHealth());
    }
}
