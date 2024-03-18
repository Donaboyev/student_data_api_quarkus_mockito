package com.abbosidev.domain.student

import com.abbosidev.infrastructure.config.ifOnNotNull
import io.smallrye.mutiny.Uni
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.GET
import jakarta.ws.rs.POST
import jakarta.ws.rs.PUT
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType.APPLICATION_JSON
import jakarta.ws.rs.core.Response

@Path("/api/v1/students")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
class StudentResource(private val studentService: StudentService) {

    @GET
    @Path("/{id}")
    fun getStudent(@PathParam("id") id: Long) = studentService.getStudentById(id)

    @POST
    fun createStudent(student: StudentDto) = studentService.createStudent(student)

    @PUT
    @Path("/{id}")
    fun updateStudent(@PathParam("id") id: Long, student: StudentDto): Uni<Response> =
        studentService
            .updateStudent(id, student)
            .ifOnNotNull()
            .transform {
                val message = HashMap<String, String>().apply {
                    put("message", "Successfully updated")
                }
                Response.ok(message).build()
            }
}