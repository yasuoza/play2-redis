# play2-redis

Thin redis store client wrapper for play2.1 framework.

## Features

* Based on [scala-redis](https://github.com/debasishg/scala-redis/).
* Create client pool on application start.
* Anorm like syntax.

## Install

Add dependency to your `project/Build.scala`

```scala
"com.yasuoza.plugin" % "play2-redis" % "0.1.0"
```

Starting project, it will be like this

```scala
val appDependencies = Seq(
  "com.yasuoza.plugin" % "play2-redis" % "0.1.0",
  jdbc,
  anorm
)
```

## Usage

You can use like this.

```scala
import com.yasuoza.plugin.RedisDB

object Application extends Controller {
  def index = Action {
    RedisDB.withClient { client =>
        client.set("hello", "world")
      }

      val helloMsg: String = RedisDB.withClient { client =>
        client.get("hello")
      }.getOrElse("bye")

      Ok(views.html.index(helloMsg))
  }
}
```

If you want to use pipelined operation, it will be done like this.

```scala
import com.yasuoza.plugin.RedisDB
import com.redis.serialization.Parse.Implicits._

val pipelined =  RedisDB.withClient { client =>
  client.pipeline { p =>
    p.set("a", 1)
    p.set("b", 2)
    p.get[Int]("b")
    p.get("c")
  }
}.get //=> List(true, true, Some(2), None)
```

For more usage, please reffer to [scala-redis#usage](https://github.com/debasishg/scala-redis/#usage)


## Configuration

You can change following redis server configuration in `conf/application.conf`.

```text
redis.host=localhost
redis.port=6879
redis.maxIdle=8
redis.database=0
redis.secret=
```

## LICENSE
This software is licensed under the Apache 2 license, quoted below.

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at


    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
