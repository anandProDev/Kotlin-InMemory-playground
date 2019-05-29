package com.anand.industries.KotlinRedis.kafka

import com.anand.industries.KotlinRedis.data.Student
import com.anand.industries.KotlinRedis.data.StudentRepository
import mu.KLogging
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component

@Component
class KafkaMessageConsumer(private val studentRepository : StudentRepository) {

    companion object : KLogging()
    @KafkaListener(topics = ["student-saver-topic"], groupId = "student-consumer-group")
    fun listen(@Payload student: Student) {

        logger.info { "Received message $student" }

        studentRepository.save(student)

        logger.info { "Saved message successfully $student" }
    }
}
