FROM openjdk:11-jdk-slim AS build

COPY . /app
WORKDIR /app

RUN ./mvnw install -DskipTests -P server
RUN mkdir -p Alfalab/target/dependency && (cd Alfalab/target/dependency; jar -xf ../Alfalab-1.0.jar)

FROM openjdk:11-jdk-slim

COPY --from=build /app/Alfalab/target/dependency/ /app
EXPOSE 8009

ENTRYPOINT ["java", "-cp", "/app", "nl.iisg.alfalabFrontEnd.AlfalabServerConsole"]
