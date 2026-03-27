FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY . .

RUN chmod +x mvnw && ./mvnw package -DskipTests

EXPOSE 8080

CMD ["sh", "-c", "java -jar target/welcome-0.0.1-SNAPSHOT.jar"]