package com.example

import zio.test.{ZIOSpecDefault, _}
object ReproSpec extends ZIOSpecDefault{
    def spec = suite("int and string")(
        suite("string suite")(
            test("concat")(assertTrue("a" + "b" == "ab")),
            test("length")(assertTrue("abc".length == 3))
        )
    )
}

