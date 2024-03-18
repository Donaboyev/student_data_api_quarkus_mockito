package com.abbosidev.domain.student

import com.abbosidev.domain.exception.StudentNotFoundWithThisIdException
import com.abbosidev.infrastructure.config.ifNullFailWith
import io.quarkus.hibernate.reactive.panache.common.WithSession
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class StudentService {

    @WithSession
    fun getStudentById(id: Long) = Student.findStudentById(id)
        .ifNullFailWith { StudentNotFoundWithThisIdException(id) }

}