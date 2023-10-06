lazy val checkReport = taskKey[Unit]("Check the test report")

lazy val repro = project.in(file("repro")).settings(
  libraryDependencies += "dev.zio" %% "zio-test" % "2.0.18" % Test,
  libraryDependencies += "dev.zio" %% "zio-test-sbt" % "2.0.18" % Test,
  testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework"),

  checkReport := {
      val _ = (Test / test).value
      val reportsDirectory = (Test / testReportsDirectory).value
      val report = reportsDirectory / "TEST-com.example.ReproSpec.xml"
      val document = scala.xml.parsing.ConstructingParser.fromFile(report, false).document
      val cases = document \ "testcase"
      val length = cases.length
      assert(
        length == 2,
        s"Expected 2 test cases, found $length in the report. Report: $report"
      )
  }

)
