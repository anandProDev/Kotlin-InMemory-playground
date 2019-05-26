package com.anand.industries.KotlinRedis

import org.apache.commons.pool2.impl.GenericObjectPoolConfig
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration.DefaultJedisClientConfigurationBuilder
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import java.time.Duration
import java.util.Optional
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLParameters
import javax.net.ssl.SSLSocketFactory
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


