package com.anand.industries.KotlinRedis.controller

import com.anand.industries.KotlinRedis.data.Student
import com.anand.industries.KotlinRedis.data.StudentRepository
import com.anand.industries.KotlinRedis.kafka.KafkaMessageConsumer
import com.anand.industries.KotlinRedis.kafka.KafkaMessageProducer
import mu.KLogging
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.awt.image.ImageProducer
import java.util.concurrent.atomic.AtomicInteger



@RestController
class HelloController(private val studentRepository: StudentRepository,
                      private val kafkaMessageConsumer: KafkaMessageConsumer,
                      private val kafkaMesageProducer: KafkaMessageProducer) {

    companion object : KLogging()

    private val counter = AtomicInteger(0)

    @RequestMapping("/hello/{name}")
    fun answer(@PathVariable("name") name: String) : Student {

        logger.info { "Received request for name $name" }
        val id = counter.incrementAndGet()
        val student = Student(id = id, name = name)

        logger.info { "Created student pojo" }

        kafkaMesageProducer.send(student)

        logger.info { "sent message" }

        Thread.sleep(5000)

        val redisStudent = studentRepository.findById(id).get()

        logger.info { "Student fetched from db $redisStudent" }

        return redisStudent
    }

}

