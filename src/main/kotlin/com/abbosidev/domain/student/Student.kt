package com.abbosidev.domain.student

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

    var age: Int? = null
    var address: String? = null

    @OneToMany(targetEntity = Subject::class, cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JoinColumn(name = "students", referencedColumnName = "id")
    var subjects: MutableList<Subject> = mutableListOf()

    companion object : PanacheCompanion<Student> {

        fun findStudentById(id: Long) = findById(id)

        fun createStudent(student: StudentDto) = Student().apply {
            firstname = student.firstname
            lastname = student.lastname
        }.persist<Student>()

    }
}