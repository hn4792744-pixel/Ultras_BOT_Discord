package com.ultras.bans.model;
import java.time.Instant;
import java.util.UUID;
public record PunishmentRecord(long id, UUID uuid, String username, String ip, PunishmentType type, boolean permanent, long durationSeconds, Instant createdAt, Instant expiresAt, String reason, String operator, UUID operatorUuid, String server, String world, String location, String source, PunishmentStatus status) {}
