package com.ultras.bans.velocity;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.PluginMessageEvent;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.ServerConnection;
import com.velocitypowered.api.proxy.server.RegisteredServer;
import com.velocitypowered.api.proxy.messages.MinecraftChannelIdentifier;
import org.slf4j.Logger;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.nio.charset.StandardCharsets;

@Plugin(
        id = "ultras_bans_velocity",
        name = "ULTRAS BANS Velocity",
        version = "1.0.0",
        description = "ULTRAS BANS Velocity network bridge",
        authors = {"UC_Hussein"}
)
public final class ULTRASBansVelocity {

    private static final MinecraftChannelIdentifier CHANNEL =
            MinecraftChannelIdentifier.from("ultras:bans");

    private final ProxyServer server;
    private final Logger logger;

    @Inject
    public ULTRASBansVelocity(ProxyServer server, Logger logger) {
        this.server = server;
        this.logger = logger;
    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        server.getChannelRegistrar().register(CHANNEL);
        logger.info("ULTRAS BANS Velocity enabled successfully.");
    }

    @Subscribe
    public void onPluginMessage(PluginMessageEvent event) {
        if (!CHANNEL.equals(event.getIdentifier())) {
            return;
        }

        try {
            DataInputStream in = new DataInputStream(
                    new ByteArrayInputStream(event.getData())
            );

            String action = read(in);
            String uuid = read(in);
            String username = read(in);
            String ip = read(in);
            String type = read(in);
            String duration = read(in);
            String reason = read(in);
            String sourceServer = read(in);

            logger.info(
                    "Network punishment received: action={}, player={}, type={}, server={}",
                    action, username, type, sourceServer
            );

            if (event.getSource() instanceof ServerConnection source) {
                event.setResult(PluginMessageEvent.ForwardResult.handled());

                for (RegisteredServer target : server.getAllServers()) {
                    if (!target.equals(source.getServer())) {
                        target.sendPluginMessage(CHANNEL, event.getData());
                    }
                }
            }

        } catch (Exception e) {
            logger.warn("Failed to read ULTRAS BANS network message.", e);
        }
    }

    private String read(DataInputStream in) throws Exception {
        int length = in.readInt();

        if (length < 0 || length > 1_000_000) {
            throw new IllegalArgumentException("Invalid message field length: " + length);
        }

        byte[] data = new byte[length];
        in.readFully(data);
        return new String(data, StandardCharsets.UTF_8);
    }
}
