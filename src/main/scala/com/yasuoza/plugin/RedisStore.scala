package com.yasuoza.plugin

import com.redis._

object RedisStore {
  private var _host: String        = _
  private var _port: Int           = _
  private var _maxIdle: Int        = _
  private var _database: Int       = _
  private var _secret: Option[Any] = _

  private lazy val redisClientPool =
    new RedisClientPool(
      host = _host,
      port = _port,
      maxIdle = _maxIdle,
      database = _database,
      secret = _secret
    )

  def apply(host: String, port: Int, maxIdle: Int, database: Int, secret: Option[Any]) = {
    _host = host
    _port = port
    _maxIdle = maxIdle
    _database = database
    _secret = secret

    redisClientPool
  }

  def close() {
    redisClientPool.close
  }

  def withClient[T](body: RedisClient => T) = {
    redisClientPool.withClient {
      body
    }
  }
}
