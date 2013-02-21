package com.yasuoza.plugin

import play.Plugin
import play.api.Application

class Play2Redis(app: Application) extends Plugin {

  private lazy val host     = app.configuration.getString("redis.host").getOrElse("localhost")
  private lazy val port     = app.configuration.getInt("redis.port").getOrElse(6379)
  private lazy val maxIdle  = app.configuration.getInt("redis.maxidle").getOrElse(8)
  private lazy val database = app.configuration.getInt("redis.database").getOrElse(0)
  private lazy val secret   = app.configuration.getString("redis.password")

  override def onStart() {
    RedisDB(host = host, port = port, maxIdle = maxIdle, database = database, secret = secret)
  }

  override def onStop() {
    RedisDB.close()
  }
}

