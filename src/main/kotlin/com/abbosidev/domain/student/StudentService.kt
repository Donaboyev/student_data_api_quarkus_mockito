package com.abbosidev.domain.student

import com.abbosidev.domain.exception.FirstnameAndLastnameShouldBeProvidedException
import com.abbosidev.domain.exception.SomethingWentWrongException
import com.abbosidev.domain.exception.StudentNotFoundWithThisIdException
import com.abbosidev.infrastructure.config.ifNullFailWith
import io.quarkus.hibernate.reactive.panache.common.WithSession
import io.quarkus.hibernate.reactive.panache.common.WithTransaction
import io.smallrye.mutiny.Uni
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class StudentService {

    @WithSession
    fun getStudentById(id: Long) = Student.findById(id)
        .ifNullFailWith { StudentNotFoundWithThisIdException(id) }

    @WithTransaction
    fun createStudent(student: StudentDto) =
        if (student.firstname.isNullOrBlank() || student.lastname.isNullOrBlank()) {
            throw FirstnameAndLastnameShouldBeProvidedException()
        } else {
            Student.createStudent(student)
        }

    @WithTransaction
    fun updateStudent(id: Long, student: StudentDto): Uni<Boolean?> =
        Student.updateStudent(id, student).ifNullFailWith { StudentNotFoundWithThisIdException(id) }

    @WithTransaction
    fun deleteStudent(id: Long) = Student.deleteById(id)

}