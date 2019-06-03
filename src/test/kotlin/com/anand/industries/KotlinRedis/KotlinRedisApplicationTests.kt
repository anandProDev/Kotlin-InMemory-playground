//package com.anand.industries.KotlinRedis
//
//import com.anand.industries.KotlinRedis.controller.HelloController
//import com.anand.industries.KotlinRedis.data.Student
//import com.anand.industries.KotlinRedis.data.StudentRepository
//import com.anand.industries.KotlinRedis.kafka.KafkaMessageProducer
//import junit.framework.Assert.assertEquals
//import junit.framework.Assert.assertNotNull
//import org.junit.Before
//import org.junit.Rule
//import org.junit.Test
//import org.junit.runner.RunWith
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.beans.factory.annotation.Value
//import org.springframework.boot.test.context.SpringBootTest
//import org.springframework.context.annotation.Profile
//import org.springframework.test.context.ActiveProfiles
//import org.springframework.test.context.TestPropertySource
//import org.springframework.test.context.junit4.SpringRunner
//import org.testcontainers.containers.GenericContainer
//import org.junit.ClassRule
//import org.springframework.boot.test.web.client.TestRestTemplate
//import org.springframework.boot.test.web.client.getForObject
//import org.springframework.core.env.Environment
//import java.time.Duration
//import org.testcontainers.containers.DockerComposeContainer
//import java.io.File
//
//@ActiveProfiles("test")
//@RunWith(SpringRunner::class)
//@SpringBootTest
//class KotlinRedisApplicationTests {
//
//    @Autowired
//    private lateinit var studentRepository: StudentRepository
//
//    @Autowired
//    private lateinit var helloController: HelloController
//
//    @Value("\${spring.redis.host}")
//    private val databaseName: String = ""
//
//    @Rule
//    var compose = KDockerComposeContainer(
//        listOf(File("src/test/resources/test-compose.yml"))
//    )
//        .withExposedService("anandindustries_1", 80)
//
//
//    var restTemplate = TestRestTemplate()
//
////    @ClassRule
////    var redis = KGenericContainer("redis:3.0.2")
////        .withExposedPorts(6379)
//
////    var app = KGenericContainer("anandindustries/kotlinredis:latest")
////        .withExposedPorts(8080)
////        .withStartupTimeout(Duration.ofMillis(10000))
//
//
//    @Before
//    @Throws(Exception::class)
//    fun setUp() {
////        redis.start()
////        redis.withExposedPorts(redis.firstMappedPort)
//
//    }
//
//    @Test
//    fun givenSimpleWebServerContainer_whenGetReuqest_thenReturnsResponse() {
//
//        val address = "http://" + compose.getServiceHost(
//            "simpleWebServer_1",
//            80
//        ) + ":" + compose.getServicePort("anandindustries_1", 80)
//        val response = address + "/hello/onlydb/anand"
//
//        assertEquals(response, "Hello World")
//    }
//
////    @Test
////    fun name() {
////        val entity = restTemplate.getForEntity("http://localhost:8080/hello/onlydb/anand", Student::class.java)
////
////        val student = entity.body
////
////        assertNotNull(student)
////    }
//
////    @Test
////    fun name1() {
////
////        println(redis.firstMappedPort)
////
////        val student = helloController.getUserWithOnlyDbInteraction("James")
////
////        assertEquals(student.name, "James")
////
////    }
//}
//
//class KGenericContainer(imageName: String) : GenericContainer<KGenericContainer>(imageName)
//
//class KDockerComposeContainer(files: List<File>) : DockerComposeContainer<KDockerComposeContainer>(files)
