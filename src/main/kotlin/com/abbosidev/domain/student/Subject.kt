package com.abbosidev.domain.student

import io.quarkus.hibernate.reactive.panache.kotlin.PanacheCompanion
import io.quarkus.hibernate.reactive.panache.kotlin.PanacheEntity
import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import jakarta.persistence.Table

@Entity
@Table(name = "subjects")
class Subject : PanacheEntity() {

    lateinit var name: String

    @OneToOne(targetEntity = Mark::class, cascade = [CascadeType.ALL])
    @JoinColumn(name = "marks", referencedColumnName = "id")
    lateinit var mark: Mark

    companion object : PanacheCompanion<Subject> {}
}