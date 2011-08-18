name := "bubble-breakers-II"

version := "1.0"

scalaVersion:="2.9.0"


organization:="com.xdev"

// fork a new JVM for 'run' and 'test:run'
fork := true

// fork a new JVM for 'test:run', but not 'run'
fork in Test := true

// add a JVM option to use when forking a JVM for 'run'
javaOptions += "-Xmx2G"
