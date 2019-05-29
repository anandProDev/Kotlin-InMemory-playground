package com.anand.industries.KotlinRedis.kafka

import com.anand.industries.KotlinRedis.data.Student
import mu.KLogging
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam


@Component
class KafkaMessageProducer(val kafkaTemplate: KafkaTemplate<String, Student>) {

    companion object : KLogging()

    @PostMapping("/kafka/send")
    fun send(@RequestParam("greeting") student: Student) {
        kafkaTemplate.send("student-saver-topic", student)
        logger.info { "Message sent successfully to topic student-saver-topic" }
    }
}
