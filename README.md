# atm-simulation

How to build and run this application
1. Clone project to your machine.
2. Go to root directory of this application.
3. Checkout develop branch.
4. Issue 'sbt run' in your prefer CLI tools.

This application uses following libraries
1. "com.google.inject" % "guice" % "4.1.0" : for dependency injection purpose.
2. "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test : for testing purpose.
3. "org.mockito" % "mockito-core" % "2.13.0" : for unit test purpose.
4. "com.typesafe.akka" %% "akka-http"   % "10.1.0-RC1" : for HTTP standard informations such as code and message.