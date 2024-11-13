package com.SmartDataSystems.test.student_info.controllers;

import com.SmartDataSystems.test.student_info.models.DTOs.StudentDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface StudentController {

    @Operation(
            summary = "Get student by ID",
            description = "Returns a student by the provided ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Student found",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = StudentDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Student not found"
                    )
            }
    )
    @GetMapping("/{id}")
    ResponseEntity<StudentDto> findById(@Parameter(description = "Student ID", required = true) @PathVariable Long id);

    @Operation(
            summary = "Get list of students",
            description = "Returns a list of all students",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "List of students returned",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = StudentDto.class)
                            )
                    )
            }
    )
    @GetMapping
    ResponseEntity<List<StudentDto>> findAll();

    @Operation(
            summary = "Create a new student",
            description = "Creates a new student in the system",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Student created"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid student data"
                    )
            }
    )
    @PutMapping
    ResponseEntity<Void> create(@RequestBody @Valid StudentDto student);

    @Operation(
            summary = "Update student information",
            description = "Updates the information of an existing student",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Student updated",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = StudentDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid student data"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Student not found"
                    )
            }
    )
    @PostMapping
    ResponseEntity<StudentDto> update(@RequestBody @Valid StudentDto student);

    @Operation(
            summary = "Archive student by ID",
            description = "Archives the student by the provided ID",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Student archived"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Student not found"
                    )
            }
    )
    @DeleteMapping("/id")
    ResponseEntity<Void> archiving(@RequestParam Long id);

    @Operation(
            summary = "Archive multiple students",
            description = "Archives multiple students by the provided IDs",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Students archived"
                    )
            }
    )
    @DeleteMapping
    ResponseEntity<Void> archiving(@RequestBody Long... ids);
}