# atm-simulation

How to build and run this application
1. Clone project to your machine.
2. Go to root directory of this application.
3. Checkout develop branch.
4. Issue 'sbt run' in your prefer CLI tools.

This application uses following libraries
1. "com.google.inject" % "guice" % "4.1.0" : for dependency injection purpose which helps project more loosely coupled and makes unit test easier.
2. "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test : for testing purpose.
3. "org.mockito" % "mockito-core" % "2.13.0" : for unit test purpose which makes object mocking easier.
4. "com.typesafe.akka" %% "akka-http"   % "10.0.11" : for HTTP standard information such as code and message.
5. "com.typesafe" % "config" % "1.3.2" : for flexibility of the application by providing configuration way via application.conf file.