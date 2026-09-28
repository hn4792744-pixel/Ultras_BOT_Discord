package com.ultras.bans.service;

import com.ultras.bans.ULTRASBansPlugin;
import com.ultras.bans.model.PunishmentRecord;
import org.bukkit.entity.Player;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.nio.charset.StandardCharsets;

public final class NetworkMessageSender {

    private final ULTRASBansPlugin plugin;

    public NetworkMessageSender(ULTRASBansPlugin plugin) {
        this.plugin = plugin;
    }

    public void sendPunishment(PunishmentRecord record) {
        send("APPLY",
                record.uuid().toString(),
                record.username(),
                record.ip(),
                record.type().name(),
                String.valueOf(record.durationSeconds()),
                record.reason(),
                record.server());
    }

    public void sendRemove(String uuid, String type, String server) {
        send("REMOVE", uuid, "", "", type, "0", "", server);
    }

    private void send(String action, String uuid, String username, String ip,
                      String type, String duration, String reason, String server) {

        Player player = plugin.getServer().getOnlinePlayers().stream().findFirst().orElse(null);

        if (player == null) {
            return;
        }

        try {
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(bytes);

            write(out, action);
            write(out, uuid);
            write(out, username);
            write(out, ip);
            write(out, type);
            write(out, duration);
            write(out, reason);
            write(out, server);

            player.sendPluginMessage(plugin, NetworkChannel.CHANNEL, bytes.toByteArray());

        } catch (Exception e) {
            plugin.getLogger().warning("Failed to send network message: " + e.getMessage());
        }
    }

    private void write(DataOutputStream out, String value) throws Exception {
        byte[] data = value.getBytes(StandardCharsets.UTF_8);
        out.writeInt(data.length);
        out.write(data);
    }
}
