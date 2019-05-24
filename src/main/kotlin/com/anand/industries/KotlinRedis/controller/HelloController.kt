package com.anand.industries.KotlinRedis.controller

import com.anand.industries.KotlinRedis.data.Student
import com.anand.industries.KotlinRedis.data.StudentRepository
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController(private val studentRepository: StudentRepository) {

    @RequestMapping("/hello/{name}")
    fun answer(@PathVariable("name") name: String) : Student {
        val student = Student(id = name, name = name)
        studentRepository.save(student)

        return studentRepository.findById(name).get()
    }
}

