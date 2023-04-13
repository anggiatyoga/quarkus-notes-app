## Stage 1 : build with maven builder image with native capabilities
FROM quay.io/quarkus/ubi-quarkus-native-image:22.2-java11 AS build
COPY --chown=quarkus:quarkus mvnw /notes-app/mvnw
COPY --chown=quarkus:quarkus .mvn /notes-app/.mvn
COPY --chown=quarkus:quarkus pom.xml /notes-app/
USER quarkus
WORKDIR /notes-app
RUN ls -latr ./mvnw
RUN chmod u+x ./mvnw
RUN ./mvnw -B org.apache.maven.plugins:maven-dependency-plugin:3.1.2:go-offline
RUN ls -latr ./mvnw
COPY src /notes-app/src
RUN ./mvnw package -Pnative -DskipTests -Dquarkus.native.native-image-xmx=7G

## Stage 2 : create the docker final image
FROM quay.io/quarkus/quarkus-micro-image:1.0
WORKDIR /work/
COPY --from=build /notes-app/target/*-runner /work/application

# set up permissions for user `1001`
RUN chmod 775 /work /work/application \
  && chown -R 1001 /work \
  && chmod -R "g+rwX" /work \
  && chown -R 1001:root /work

EXPOSE 8080
USER 1001

CMD ["./application", "-Dquarkus.http.host=0.0.0.0"]