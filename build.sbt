ThisBuild / tlBaseVersion := "0.0"

val scala213 = "2.13.8"
ThisBuild / crossScalaVersions := List(scala213, "3.1.2")
ThisBuild / scalaVersion       := scala213

val algebraVersion = "2.7.0"
val refinedVersion = "0.9.29"

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
      "org.typelevel" %%% "discipline-munit"   % "2.0.0-M2"     % Test
    )
  )
