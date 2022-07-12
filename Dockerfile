FROM adoptopenjdk/openjdk11

EXPOSE 3000

ADD /target/forum-0.0.1-SNAPSHOT.jar forum.jar

ENTRYPOINT ["java", "-jar", "forum.jar"]
ENTRYPOINT ["java", "$JAVA_OPTS --XX:+UseContainerSupport",  "-Xnx300m -Xss512k -XX:CICompilerCount=2",
"-Dserver.port=$PORT", "-Dspring-boot.profiles.active=prod",
"-jar", "forum.jar"]