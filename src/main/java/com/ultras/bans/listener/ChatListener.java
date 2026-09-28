package com.ultras.bans.listener;
import com.ultras.bans.ULTRASBansPlugin; import com.ultras.bans.model.PunishmentType; import org.bukkit.event.*; import org.bukkit.event.player.AsyncPlayerChatEvent;
public final class ChatListener implements Listener {private final ULTRASBansPlugin p;public ChatListener(ULTRASBansPlugin p){this.p=p;}@EventHandler public void chat(AsyncPlayerChatEvent e){p.database().active(e.getPlayer().getUniqueId(),PunishmentType.MUTE).thenAccept(a->{if(a.isPresent())e.setCancelled(true);});}}
