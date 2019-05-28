package com.anand.industries.KotlinRedis

import com.anand.industries.KotlinRedis.data.Student
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.ser.std.JsonValueSerializer
import com.fasterxml.jackson.databind.ser.std.StringSerializer
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.consumer.ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG
import org.apache.kafka.clients.producer.ProducerConfig
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.apache.kafka.clients.producer.ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG
import org.apache.kafka.clients.producer.ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import org.springframework.kafka.support.converter.StringJsonMessageConverter
import org.springframework.kafka.support.serializer.JsonSerializer
import java.io.Serializable
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration as JedisClientConfiguration1

@SpringBootApplication
@Configuration

class KotlinRedisApplication {

    @Value("\${spring.redis.host:localhost}")
    private val databaseName: String = ""

    @Value("\${spring.redis.port:6379}")
    private val port: Int = 0

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            runApplication<KotlinRedisApplication>(*args)
        }
    }

    @Bean
    fun jedisConnectionFactory(): JedisConnectionFactory {
        return JedisConnectionFactory(RedisStandaloneConfiguration(databaseName, port))
    }

    @Bean
    fun redisTemplate(): RedisTemplate<String, Any> {
        return RedisTemplate<String, Any>().apply {
            setConnectionFactory(jedisConnectionFactory())
        }
    }
}

fun main(args: Array<String>) {
    runApplication<KotlinRedisApplication>(*args)
}


@Configuration
class SenderConfig {

    @Value("\${kafka.bootstrap-servers}")
    private val bootstrapServers: String = "BOOTSTRAP URL NOT CONFIGURED"

    @Bean
    fun producerConfigs(): Map<String, Serializable?> {
        return mapOf(
            ProducerConfig.BOOTSTRAP_SERVERS_CONFIG to bootstrapServers,
            KEY_SERIALIZER_CLASS_CONFIG to StringSerializer::class.java,
            VALUE_SERIALIZER_CLASS_CONFIG to JsonSerializer::class.java)
    }

    @Bean
    fun producerFactory(): ProducerFactory<String, Student> {
        return DefaultKafkaProducerFactory(producerConfigs())
    }

    @Bean
    fun kafkaTemplate(): KafkaTemplate<String, Student> {
        return KafkaTemplate(producerFactory())
    }
}

@EnableKafka
@Configuration
class ConsumerConfig {

    @Value("\${kafka.bootstrap-servers}")
    private val bootstrapServers: String? = "BOOTSTRAP URL NOT CONFIGURED"

    fun consumerFactory(groupId: String): ConsumerFactory<String, Student> {
        val props = mapOf(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to bootstrapServers,
            ConsumerConfig.GROUP_ID_CONFIG to StringSerializer::class.java
            //ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to org.springframework.kafka.support.serializer.JsonDeserializer::class.java,
            //ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to org.springframework.kafka.support.serializer.JsonDeserializer::class.java
        )
//        return DefaultKafkaConsumerFactory(props)

        return DefaultKafkaConsumerFactory<String, Student>(
            props,
            StringDeserializer(),
            org.springframework.kafka.support.serializer.JsonDeserializer<Student>(Student::class.java)
        )
    }

    @Bean
    fun kafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, Student> {

        val factory = ConcurrentKafkaListenerContainerFactory<String, Student>()
        factory.consumerFactory = consumerFactory("spring")
        factory.setMessageConverter(StringJsonMessageConverter())
        return factory
    }
}

