package com.abbosidev.domain.exception

class StudentNotFoundWithThisIdException(id: Long) : RuntimeException("Student not found with this id $id")

class FirstnameAndLastnameShouldBeProvidedException() : RuntimeException("Firstname and lastname should be provided")
class SomethingWentWrongException() : RuntimeException("Something went wrong")