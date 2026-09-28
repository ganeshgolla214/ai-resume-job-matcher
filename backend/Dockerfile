FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
RUN apk add --no-cache maven
COPY pom.xml .
RUN mvn dependency:go-offline -q
COPY src ./src
RUN mvn package -DskipTests -q
EXPOSE 8080
ENTRYPOINT ["java","-jar","target/resume-matcher-1.0.0.jar"]