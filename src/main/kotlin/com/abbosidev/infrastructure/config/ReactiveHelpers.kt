package com.abbosidev.infrastructure.config

import io.smallrye.mutiny.Uni
import io.smallrye.mutiny.groups.UniOnNotNull
import io.smallrye.mutiny.unchecked.Unchecked


fun <T> Uni<T>.ifNullFailWith(supplier: () -> Throwable): Uni<T> = this.onItem().ifNull().failWith(supplier)

fun <T> Uni<T>.ifOnNotNull(): UniOnNotNull<T> = this.onItem().ifNotNull()

fun Uni<Boolean>.ifFalseFailWith(supplier: () -> Throwable): Uni<Boolean> =
    this.onItem().invoke(
        Unchecked.consumer { v ->
            if (!v) throw supplier.invoke()
        },
    )
