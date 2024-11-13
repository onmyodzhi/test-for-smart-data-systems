package com.SmartDataSystems.test.student_info.controllers.impls;

import com.SmartDataSystems.test.student_info.controllers.StudentController;
import com.SmartDataSystems.test.student_info.models.DTOs.StudentDto;
import com.SmartDataSystems.test.student_info.services.StudentCRUDService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/students")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StudentControllerImpl implements StudentController {

    StudentCRUDService studentCRUDService;

    @Override
    public ResponseEntity<StudentDto> findById(@PathVariable Long id) {
        StudentDto foundStudent = studentCRUDService.findById(id);

        return ResponseEntity.ok(foundStudent);
    }

    @Override
    public ResponseEntity<List<StudentDto>> findAll() {
        List<StudentDto> students = studentCRUDService.findAll();

        return ResponseEntity.ok(students);
    }

    @Override
    public ResponseEntity<Void> create(@RequestBody @Valid StudentDto student) {

        StudentDto savedStudent = studentCRUDService.saveOrUpdate(student);

        return ResponseEntity.created(URI.create("v1/students/" + savedStudent.getId())).build();
    }

    @Override
    public ResponseEntity<StudentDto> update(@RequestBody @Valid StudentDto student) {
        StudentDto updatedStudent = studentCRUDService.saveOrUpdate(student);

        return ResponseEntity.ok(updatedStudent);
    }

    @Override
    public ResponseEntity<Void> archiving(@RequestParam Long id) {
        studentCRUDService.archiving(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> archiving(@RequestBody Long... ids) {
        studentCRUDService.archiving(ids);
        return ResponseEntity.noContent().build();
    }
}
