package com.yasuoza.plugin.test

import org.specs2.mutable._
import play.api.test._
import play.api.test.Helpers._
import com.yasuoza.plugin.RedisStore

class Play2RedisSpec extends Specification {

  def testConfiguration = {
    inMemoryDatabase()
  }

  "Play2Redis" should {

    "works with redis using pool" in {

      running(FakeApplication(additionalConfiguration = testConfiguration)) {

        RedisStore.withClient { client =>
          client.set("hello", "world")
        }

        RedisStore.withClient { client =>
          client.get("hello")
        } must beSome[String].which(_ == "world")

        import com.redis.serialization.Parse.Implicits._
        RedisStore.withClient { client =>
          client.pipeline { p =>
            p.set("a", 1)
            p.set("b", 2)
            p.get[Int]("b")
            p.get("c")
          }
        }.get must beEqualTo(List(true, true, Some(2), None))

      }
    }

  }

}
