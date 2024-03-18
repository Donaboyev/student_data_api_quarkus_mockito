package com.abbosidev.domain.student

import jakarta.validation.Valid
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.GET
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType.APPLICATION_JSON

@Path("/api/v1/students")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
class StudentResource(private val studentService: StudentService) {

    @GET
    @Path("/{id}")
    fun getStudent(@PathParam("id") id: Long) = studentService.getStudentById(id)

    @POST
    fun createStudent( student: StudentDto) = studentService.createStudent(student)
}