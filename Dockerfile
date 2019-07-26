FROM openjdk:8-alpine as build
WORKDIR /app
COPY gradlew build.gradle settings.gradle /app/
COPY gradle /app/gradle
RUN ./gradlew build || return 0
COPY . /app
RUN ./gradlew build -x test

FROM openjdk:8-alpine

ENV VERSION 0.0.1

RUN apk add --no-cache \
    libreoffice-writer \
    && rm -rf /tmp/*

COPY --from=build /app/build/libs/reporter-$VERSION.jar /reporter.jar
RUN mkdir /reports
VOLUME /reports
EXPOSE 8080

RUN addgroup -S reporter && adduser -S reporter -G reporter
USER reporter

CMD java -jar /reporter.jar --reporter.path=/reports
