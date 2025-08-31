# Start from an official OpenJDK image
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copy application jar
COPY target/metrics-api-*.jar app.jar
# Copy Prometheus config if needed
COPY src/main/resources/prometheus.yml /app/prometheus.yml

EXPOSE 8080

# Optionally use JAVA_OPTS for custom config
ENV JAVA_OPTS=""

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
