package com.abbosidev.infrastructure.config

import io.smallrye.mutiny.Uni
import io.smallrye.mutiny.groups.UniOnNotNull


fun <T> Uni<T>.ifNullFailWith(supplier: () -> Throwable): Uni<T> = this.onItem().ifNull().failWith(supplier)

fun <T> Uni<T>.ifOnNotNull(): UniOnNotNull<T> = this.onItem().ifNotNull()
