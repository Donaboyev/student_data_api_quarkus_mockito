package com.abbosidev.domain.student

import com.abbosidev.domain.exception.StudentNotFoundWithThisIdException
import com.abbosidev.infrastructure.config.ifNullFailWith
import io.quarkus.hibernate.reactive.panache.common.WithSession
import io.quarkus.hibernate.reactive.panache.common.WithTransaction
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class StudentService {

    @WithSession
    fun getStudentById(id: Long) = Student.findStudentById(id)
        .ifNullFailWith { StudentNotFoundWithThisIdException(id) }

    @WithTransaction
    fun createStudent(student: StudentDto) = Student.createStudent(student)

}