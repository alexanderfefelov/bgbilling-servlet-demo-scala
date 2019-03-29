name := "bgbilling-servlet-demo-scala"
organization := "com.github.alexanderfefelov"
version := "0.1.0-SNAPSHOT"

scalaVersion := "2.12.8"

lazy val root = project in file(".")

libraryDependencies ++= Seq(
  "javax.servlet" % "javax.servlet-api" % "4.0.1" % "provided",
  "log4j" % "log4j" % "1.2.17"
)

assemblyExcludedJars in assembly := {
  val classPath = (fullClasspath in assembly).value
  val jarsToDrop = Seq(
    "bgbilling-kernel-7.1_1076.jar",
    "log4j-1.2.17.jar"
  )
  classPath filter { x =>  jarsToDrop.contains(x.data.getName) }
}

assemblyJarName in assembly := s"${name.value}-${version.value}.jar"
