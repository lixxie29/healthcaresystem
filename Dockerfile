
FROM eclipse-temurin:22-alpine

ENTRYPOINT java $JAVA_OPTS -jar /app/poc-0.0.1-SNAPSHOT.jar $APP_ARGS
EXPOSE 8080