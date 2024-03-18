package com.abbosidev.domain.exception

class StudentNotFoundWithThisIdException(id: Long) : RuntimeException("Student not found with this id $id")