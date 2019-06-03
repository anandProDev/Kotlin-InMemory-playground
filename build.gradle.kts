import com.palantir.gradle.docker.DockerExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.1.5.RELEASE"
	id("io.spring.dependency-management") version "1.0.7.RELEASE"
	kotlin("jvm") version "1.2.71"
	kotlin("plugin.spring") version "1.2.71"
	id("com.palantir.docker") version "0.22.1"
}

group = "com.anand.industries"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
	mavenCentral()
}

dependencies {
	implementation("io.github.microutils:kotlin-logging:1.6.26")

	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("redis.clients:jedis:2.9.0")
	implementation("org.springframework.data:spring-data-redis:2.0.3.RELEASE")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.datastax.cassandra:cassandra-driver-core:4.0.0")
	implementation("org.springframework.kafka:spring-kafka:2.2.6.RELEASE")
	implementation("io.micrometer:micrometer-registry-prometheus:1.1.4")

	implementation("org.testcontainers:testcontainers:1.11.3")
	implementation("io.mockk:mockk:1.9.3")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}

val unpackTask = tasks.register<Copy>("unpack") {
	dependsOn( "bootJar")
	from(zipTree(tasks.findByName("bootJar")!!.outputs.files.singleFile))
	into("build/dependency")
}

configure<DockerExtension> {
	name = "${project.group}/kotlinredis"
	copySpec.from(unpackTask.get().outputs).into("dependency")
	buildArgs(mapOf("DEPENDENCY" to "dependency"))
}