# ULTRAS_bans_v1

Production-oriented Paper punishment and administration plugin by **UC_Hussein**.

## Build

Requirements: Java 21 for the plugin build; modern Paper 1.21.x through current 26.x servers can run the resulting Java 21 bytecode. Paper 26.2 itself requires Java 25 to run the server.

```bash
mvn clean package -DskipTests
```

Output: `target/ULTRAS_bans_v1-1.0.0.jar`

## Core features

- Central punishment engine: Ban, IP Ban, Mute, Warn, Kick, Freeze, Jail, Vanish.
- Permanent and temporary state with ACTIVE/EXPIRED/REMOVED/REVOKED.
- UUID-first storage and offline player records.
- SQLite by default; MySQL/MariaDB configuration available.
- Async database executor and expiry worker.
- Paper GUI player browser, punishment panel, duration selector and history.
- Geyser/Floodgate detection by reflection so the plugin does not hard-depend on Floodgate.
- Shared database architecture for network-wide punishment state behind Velocity/Bungee-style proxy networks.
- Discord webhook integration with rate limiting and no token hard-coded in Java.
- Security threshold for mass staff actions.
- Arabic/English resource layout.
- GitHub Actions build workflow.

## Important proxy note

For authoritative cross-server state, configure the same MySQL/MariaDB database on every backend. Velocity modern forwarding should be configured separately on the proxy and Paper servers. The plugin does not pretend that a backend `Player` object exists on another server.

## Discord

Edit `plugins/ULTRAS_bans_v1/discord/config.yml` and set `enabled: true` and a webhook URL. Keep secrets out of Git.

## Configuration

Main files are generated under `plugins/ULTRAS_bans_v1/`: `config.yml`, `database.yml`, `permissions.yml`, `security.yml`, `punishment.yml`, `proxy.yml`, `lang/`, `gui/`, and `discord/`.

## Commands

`/ban`, `/unban`, `/ipban`, `/ip_ban`, `/unipban`, `/mute`, `/unmute`, `/warn`, `/kick`, `/freeze`, `/frz`, `/jail`, `/unjail`, `/setjail`, `/vn`, `/nv`, `/admin`, `/tam`, `/game0`-`/game3`, `/games`, `/tp`, `/tphere`, `/ip_list`, `/ultrasbans reload`, `/ub reload`.

## Permissions

`ultrasbans.admin`, `ultrasbans.ban`, `ultrasbans.ipban`, `ultrasbans.mute`, `ultrasbans.warn`, `ultrasbans.kick`, `ultrasbans.freeze`, `ultrasbans.jail`, `ultrasbans.vanish`, `ultrasbans.teleport`, `ultrasbans.gamemode`, `ultrasbans.logs`, `ultrasbans.permissions`, `ultrasbans.reload`.

## Compatibility

The project uses the Paper API and a compatibility-first approach. Paper's current documentation says API versions through 26.2 are valid, and current Velocity documentation says modern forwarding is supported for Paper 1.13.2+. See the official Paper/Velocity documentation before production deployment.
