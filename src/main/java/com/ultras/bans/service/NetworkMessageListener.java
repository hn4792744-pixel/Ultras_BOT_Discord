package com.ultras.bans.service;

import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import com.ultras.bans.ULTRASBansPlugin;

public final class NetworkMessageListener implements PluginMessageListener {

    private final ULTRASBansPlugin plugin;

    public NetworkMessageListener(ULTRASBansPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        if (!NetworkChannel.CHANNEL.equals(channel)) {
            return;
        }

        plugin.getLogger().info("Received network message from " + player.getName());
    }
}
