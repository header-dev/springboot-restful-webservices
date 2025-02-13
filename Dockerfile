# Build Stage
FROM eclipse-temurin:21-jdk AS development
WORKDIR /app
# ติดตั้ง Maven
RUN apt update && apt install -y maven
# Copy Source Code
COPY . .
# Dependency Cache (เพื่อให้ Build ไวขึ้น)
COPY pom.xml .
RUN mvn dependency:resolve
RUN mvn clean package -DskipTests
# เปิดใช้งาน LiveReload
EXPOSE 8080
# รัน Spring Boot ด้วย Spring DevTools (Hot Reload)
CMD ["mvn", "spring-boot:run"]
# --------------------------------------------------

## Run Stage (Production)
FROM eclipse-temurin:21 AS production
# ตั้งค่า Working Directory
WORKDIR /app
# คัดลอก JAR จาก Build Stage มา
COPY --from=development /app/target/springboot-restful-webservices-0.0.1-SNAPSHOT.jar .
# รันแอป
ENTRYPOINT ["java", "-jar", "springboot-restful-webservices-0.0.1-SNAPSHOT.jar"]


