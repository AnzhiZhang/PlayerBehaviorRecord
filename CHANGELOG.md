# Changelog

## [1.3.0](https://github.com/AnzhiZhang/PlayerBehaviorRecord/compare/v1.2.2...v1.3.0) (2024-06-02)


### Features

* ✨ update PlayerLocationData structure ([d6c5638](https://github.com/AnzhiZhang/PlayerBehaviorRecord/commit/d6c5638b7d54292861ea5b612e5576d73e6ff5be))

## [1.2.2](https://github.com/AnzhiZhang/PlayerBehaviorRecord/compare/v1.2.1...v1.2.2) (2024-02-04)


### Bug Fixes

* ✏️ fix onlineDurationSeconds name ([707cd57](https://github.com/AnzhiZhang/PlayerBehaviorRecord/commit/707cd57a9ddf34098aaed5d65c0d020849c71721))

## [1.2.1](https://github.com/AnzhiZhang/PlayerBehaviorRecord/compare/v1.2.0...v1.2.1) (2024-02-02)


### Bug Fixes

* 🐛 use Instant to replace LocalDateTime ([f9dd4ba](https://github.com/AnzhiZhang/PlayerBehaviorRecord/commit/f9dd4ba17dcb6758157fa72fdeab4b48394a0fbb))

## [1.2.0](https://github.com/AnzhiZhang/PlayerBehaviorRecord/compare/v1.1.1...v1.2.0) (2024-02-02)


### Features

* ✨ add loginIP in player_activity ([25f7125](https://github.com/AnzhiZhang/PlayerBehaviorRecord/commit/25f71259b39b9eb7804e00c55c57c46435b7f62d))
* ✨ set argument of send method to Object in KafkaManager ([c16026f](https://github.com/AnzhiZhang/PlayerBehaviorRecord/commit/c16026f97a7c3c1c40caa27f8d4e5b1fc162873e))


### Bug Fixes

* 🐛 check isBot in PlayerActivityBehaviorManager ([a971634](https://github.com/AnzhiZhang/PlayerBehaviorRecord/commit/a97163421e0e83159029800b15f3fc90516a6269))
* 🐛 fix player login IP source ([12c4f3e](https://github.com/AnzhiZhang/PlayerBehaviorRecord/commit/12c4f3e6adf84f02e53f1636ccc0232689a4c424))

## [1.1.1](https://github.com/AnzhiZhang/PlayerBehaviorRecord/compare/v1.1.0...v1.1.1) (2024-01-21)


### Bug Fixes

* 🐛 fix server stop lifecycle listener ([e320e75](https://github.com/AnzhiZhang/PlayerBehaviorRecord/commit/e320e7582d8010f7c652cdce59f2df16c3d40b32))
* 🐛 shutdown executorService in BehaviorManager on server stopping ([7a51615](https://github.com/AnzhiZhang/PlayerBehaviorRecord/commit/7a5161522395768b3c86136fc12e6e07668d82bf))

## [1.1.0](https://github.com/AnzhiZhang/PlayerBehaviorRecord/compare/v1.0.0...v1.1.0) (2024-01-21)


### Features

* ✨ record player activity ([cb45918](https://github.com/AnzhiZhang/PlayerBehaviorRecord/commit/cb459186c26deaaa5a916cb33367ae9daea72e21))
* ✨ rename config playerLocationDataPointIntervalMs to dataPointIntervalMs ([3957421](https://github.com/AnzhiZhang/PlayerBehaviorRecord/commit/3957421e0f7504b8fd80705f2491db0a487328b8))
* ✨ update KafkaManager ([4c12c7a](https://github.com/AnzhiZhang/PlayerBehaviorRecord/commit/4c12c7aaa5f835404be71ae100b8d1520149823e))
* 🔧 set required fabricloader version to &gt;=0.14.0 ([080da6b](https://github.com/AnzhiZhang/PlayerBehaviorRecord/commit/080da6bb74f176270d3b5fb5ea56421508bfd024))

## 1.0.0 (2024-01-20)


### Features

* ✨ add kafkaReconnectBackoffMaxMs config ([7483f8f](https://github.com/AnzhiZhang/PlayerBehaviorRecord/commit/7483f8fa927d413572751b298054497bbf646021))
* ✨ collect player location ([abd482b](https://github.com/AnzhiZhang/PlayerBehaviorRecord/commit/abd482b68f7e1c269bba230357d1d41e5ac0c1c6))
* ✨ send player location to kafka ([de5ac0c](https://github.com/AnzhiZhang/PlayerBehaviorRecord/commit/de5ac0cd6668bd6436184db960a4359f8b980649))
* ✨ set kafka producer close timeout to 5 seconds ([b84d915](https://github.com/AnzhiZhang/PlayerBehaviorRecord/commit/b84d9153989d8db88b8a64917d110494d9896cce))
* 🍱 update icon ([4e610fe](https://github.com/AnzhiZhang/PlayerBehaviorRecord/commit/4e610fefeec9ab1e334c9e40e1e164c48379990c))
* 🎉 initial commit ([ddb4d2d](https://github.com/AnzhiZhang/PlayerBehaviorRecord/commit/ddb4d2d105b4fec3ec2d26f0eced10cc072fb94d))
* 🔊 log error when sending player location ([a9ef4f6](https://github.com/AnzhiZhang/PlayerBehaviorRecord/commit/a9ef4f65eaaef489f4b5a0c93c1f72c0cb0bb8e3))


### Bug Fixes

* 🚨 fix kafka require slf4j warning ([1225ebd](https://github.com/AnzhiZhang/PlayerBehaviorRecord/commit/1225ebde46c6785c88098b16e49d9ff6612fc0ee))
