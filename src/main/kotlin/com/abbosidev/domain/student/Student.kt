package com.abbosidev.domain.student

import com.abbosidev.domain.exception.StudentNotFoundWithThisIdException
import com.abbosidev.infrastructure.config.ifNullFailWith
import io.quarkus.hibernate.reactive.panache.kotlin.PanacheCompanion
import io.quarkus.hibernate.reactive.panache.kotlin.PanacheEntity
import io.smallrye.mutiny.Uni
import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank

@Entity
@Table(name = "students")
class Student : PanacheEntity() {

    @NotBlank
    lateinit var firstname: String

    @NotBlank
    lateinit var lastname: String

    var age: Int = 0
    var address: String = ""

    @OneToMany(targetEntity = Subject::class, cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JoinColumn(name = "students", referencedColumnName = "id")
    var subjects: MutableList<Subject> = mutableListOf()

    companion object : PanacheCompanion<Student> {

        fun createStudent(student: StudentDto) = Student().apply {
            firstname = student.firstname!!
            lastname = student.lastname!!
        }.persist<Student>()

        fun updateStudent(id: Long, student: StudentDto): Uni<Boolean?> =
            findById(id)
                .ifNullFailWith { StudentNotFoundWithThisIdException(id) }
                .flatMap { saved ->
                    update(
                        "firstname = ?1, lastname = ?2 , age = ?3, address = ?4 where id = ?5",
                        student.firstname ?: saved!!.firstname,
                        student.lastname ?: saved!!.lastname,
                        student.age ?: saved!!.age,
                        student.address ?: saved!!.address,
                        id
                    ).map {
                        it > 0
                    }
                }
    }
}