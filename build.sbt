ThisBuild / tlBaseVersion := "0.1"

val scala213 = "2.13.10"
ThisBuild / crossScalaVersions := List(scala213, "3.3.0")
ThisBuild / scalaVersion       := scala213

ThisBuild / tlCiReleaseBranches := Seq("main")

val algebraVersion = "2.9.0"
val refinedVersion = "0.10.3"

lazy val root = tlCrossRootProject.aggregate(refinedAlgebra)

lazy val refinedAlgebra = crossProject(JVMPlatform, JSPlatform)
  .crossType(CrossType.Pure)
  .in(file("refined-algebra"))
  .settings(
    name := "refined-algebra",
    libraryDependencies ++= Seq(
      "org.typelevel" %%% "algebra"            % algebraVersion,
      "eu.timepit"    %%% "refined"            % refinedVersion,
      "org.typelevel" %%% "algebra-laws"       % algebraVersion % Test,
      "eu.timepit"    %%% "refined-cats"       % refinedVersion % Test,
      "eu.timepit"    %%% "refined-scalacheck" % refinedVersion % Test,
      "org.typelevel" %%% "discipline-munit"   % "2.0.0-M3"     % Test
    )
  )
