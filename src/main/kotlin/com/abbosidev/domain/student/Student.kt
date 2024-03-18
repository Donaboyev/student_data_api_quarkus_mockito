package com.abbosidev.domain.student

import io.quarkus.hibernate.reactive.panache.kotlin.PanacheCompanion
import io.quarkus.hibernate.reactive.panache.kotlin.PanacheEntity
import io.smallrye.mutiny.Uni
import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
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

    @OneToMany(targetEntity = Subject::class, cascade = [CascadeType.ALL])
    var subjects: MutableList<Subject> = mutableListOf()

    companion object : PanacheCompanion<Student> {

        fun findStudentById(id: Long) = findById(id)

    }
}