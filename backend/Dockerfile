FROM maven:3.9.6-eclipse-temurin-21

#-------------- Production Build --------------
# FROM maven:3.9.6-eclipse-temurin-21 AS builder
#-----------------------------------------------

WORKDIR /app

COPY . .

#-------------- Production Build --------------
# RUN mvn clean package -DskipTests

# FROM eclipse-temurin:21-jre-alpine

# WORKDIR /app

# COPY --from=builder /app/target/app.jar app.jar
#-----------------------------------------------

EXPOSE 8080

#-------------- Production Build --------------
# ENTRYPOINT ["sh", "-c", "sleep 15 && java -jar app.jar"]
#-----------------------------------------------

CMD ["./mvnw", "spring-boot:run"]