package com.ultras.bans.utility;
import net.kyori.adventure.text.Component; import net.kyori.adventure.text.minimessage.MiniMessage;
public final class Text { private static final MiniMessage MM=MiniMessage.miniMessage(); private Text(){} public static Component c(String s){return MM.deserialize(s==null?"":s);} public static String plain(Component c){return net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer.plainText().serialize(c);} }
