#
#FROM openjdk:8-jdk-alpine
#RUN apk add gradle
##COPY ./build.gradle.kts ./build.gradle.kts
#RUN gradle --version
#COPY ./ ./
#RUN gradle build
#
#
##FROM openjdk:8-jdk-alpine
##VOLUME /tmp
##ARG DEPENDENCY=/build/dependency
##COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
##COPY ${DEPENDENCY}/META-INF /app/META-INF
##COPY ${DEPENDENCY}/BOOT-INF/classes /app
##ENTRYPOINT ["java","-cp","app:app/lib/*","com.anand.industries.KotlinRedis.KotlinRedisApplication"]
#
#
#FROM openjdk:8-jdk-alpine
#VOLUME /tmp
##COPY --from=0 "./build/libs/Kotlin-Redis-0.0.1-SNAPSHOT.jar" .
#COPY "build/libs/Kotlin-Redis-0.0.1-SNAPSHOT.jar" .
##COPY /build/libs/Kotlin-Redis-0.0.1-SNAPSHOT.jar Kotlin-Redis-0.0.1-SNAPSHOT.jar
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/Kotlin-Redis-0.0.1-SNAPSHOT.jar"]
