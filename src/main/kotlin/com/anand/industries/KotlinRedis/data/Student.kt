package com.anand.industries.KotlinRedis.data

import java.io.Serializable
import org.springframework.data.redis.core.RedisHash

@RedisHash("Student")
data class Student(var id : Int, var name : String,
                   var gender : Gender? = Gender.MALE, var grade : Int? = 1) : Serializable {
    enum class Gender {
        MALE, FEMALE
    }
}