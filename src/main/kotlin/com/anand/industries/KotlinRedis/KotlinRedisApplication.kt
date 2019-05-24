package com.anand.industries.KotlinRedis

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate

@SpringBootApplication
@Configuration
class KotlinRedisApplication {


	@Bean
	fun jedisConnectionFactory(): JedisConnectionFactory {
		return JedisConnectionFactory().apply { hostName = "127.0.0.1"
			port = 6379
			timeout = 30000
			usePool = true}
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

