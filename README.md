# Player Behavior Record

[![License](https://shields.io/github/license/AnzhiZhang/PlayerBehaviorRecord?label=License)](https://github.com/AnzhiZhang/PlayerBehaviorRecord/blob/master/LICENSE)
[![Modrith](https://img.shields.io/modrinth/v/ciA0JPWg?logo=modrinth&label=Modrinth&color=%2300AF5C)](https://modrinth.com/plugin/player-behavior-record)
[![Release](https://shields.io/github/v/release/AnzhiZhang/PlayerBehaviorRecord?display_name=tag&include_prereleases&label=Release)](https://github.com/AnzhiZhang/PlayerBehaviorRecord/releases/latest)

> Record player behavior and send to [kafka](https://kafka.apache.org/).

## Configuration

- `threadPoolSize`: thread pool size, default: `10`
- `serverName`: server name, if you need install this mod on multiple servers, this could be used to identify which server the record is from, default: `server`
- `playerLocationDataPointIntervalMs`: interval of player location data point, default: `500`
- `kafkaBootstrapServers`: kafka bootstrap servers, default: `localhost:9092`
- `kafkaReconnectBackoffMaxMs`: kafka reconnect backoff max ms, default: `5000`
- `kafkaTopic`: kafka topic name, default: `minecraft`

## Features

### Player Location

Record player location with fixed interval.

Key: `player_location`

Value:

```json
{
  "time": "2024-01-01T00:00:00.000",
  "serverName": "server",
  "playerUUID": "00000000-0000-0000-0000-000000000000",
  "playerName": "player",
  "worldName": "world",
  "x": 0.0,
  "y": 0.0,
  "z": 0.0
}
```

### Player Activity

Record player login/logout.and online time.

Key: `player_activity`

Value:

```json
{
  "time": "2024-01-01T00:00:00.000",
  "serverName": "server",
  "playerUUID": "00000000-0000-0000-0000-000000000000",
  "playerName": "player",
  "loginAt": "2024-01-01T00:00:00.000",
  "logoutAt": "2024-01-01T00:00:00.000",
  "onlineTimeSeconds": 0
}
```
