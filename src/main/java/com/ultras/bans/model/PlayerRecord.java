package com.ultras.bans.model;
import java.time.Instant; import java.util.UUID;
public record PlayerRecord(UUID uuid,String username,String ip,String platform,String rank,boolean op,String server,String world,String location,Instant firstJoin,Instant lastJoin,Instant lastQuit) {}
