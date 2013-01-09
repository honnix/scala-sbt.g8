import sbt._
import Keys._

object Resolvers {
  val typesafeReleases = "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"
  val mavenLocal = "Local Maven Repository" at "file://" + Path.userHome + "/.m2/repository"

  val myResolvers = Seq(typesafeReleases, mavenLocal)
}

object BuildSettings {

  import Resolvers._

  val buildOrganization = "$organization$"
  val buildVersion = "$version$"
  val buildScalaVersion = "$scala_version$"

  val buildSettings = Defaults.defaultSettings ++ Seq(
    organization := buildOrganization,
    version := buildVersion,
    scalaVersion := buildScalaVersion,
    shellPrompt := ShellPrompt.buildShellPrompt,
    // publish to maven repository
    // publishTo := Some(Resolver.file("file", new File(Path.userHome.absolutePath + "/.m2/repository"))),
    resolvers ++= myResolvers
  )
}

object ShellPrompt {

  object devnull extends ProcessLogger {
    def info(s: => String) {}

    def error(s: => String) {}

    def buffer[T](f: => T): T = f
  }

  def currBranch = (
    ("git status -sb" lines_! devnull headOption)
      getOrElse "-" stripPrefix "## "
    )

  val buildShellPrompt = {
    (state: State) => {
      val currProject = Project.extract(state).currentProject.id
      "%s:%s:%s> ".format(
        currProject, currBranch, BuildSettings.buildVersion
      )
    }
  }
}

object Dependencies {
  // private val crossMappedDowngrade = CrossVersion.fullMapped {
  //   case "2.10.0" => "2.9.2"
  //   case x => x
  // }

  // val akka = "com.typesafe.akka" %% "akka-actor" % "2.1.0"
}

object $name;format="Camel"$Build extends Build {

  import Dependencies._
  import BuildSettings._

  val commonDeps = Seq(
    // akka
  )

  lazy val $name;format="camel"$ = Project(
    id = "$name;format="norm"$",
    base = file("."),
    settings = buildSettings ++ Seq(
      libraryDependencies ++= commonDeps
    ))
}
