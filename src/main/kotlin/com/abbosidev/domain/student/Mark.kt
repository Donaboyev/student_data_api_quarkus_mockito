package com.abbosidev.domain.student

import io.quarkus.hibernate.reactive.panache.kotlin.PanacheEntity
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "marks")
class Mark : PanacheEntity() {

    var mark: Int = 0

}