package com.SmartDataSystems.test.student_info.services;

import com.SmartDataSystems.test.exceptions.StudentNotFoundException;
import com.SmartDataSystems.test.student_info.models.mappers.StudentMapper;
import com.SmartDataSystems.test.student_info.models.DTOs.StudentDto;
import com.SmartDataSystems.test.student_info.models.Student;
import com.SmartDataSystems.test.student_info.repositories.StudentRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentCRUDService {

    final StudentRepository studentRepository;
    final StudentMapper studentMapper;

    public StudentDto findById(Long id) {
        log.info("Find student by id: {}", id);
        return studentRepository.findById(id)
                .map(studentMapper::toDto)
                .orElseThrow(() -> new StudentNotFoundException(id));
    }

    public List<StudentDto> findAll() {
        log.info("Find all students");
        return studentRepository.findAll()
                .stream()
                .map(studentMapper::toDto)
                .toList();
    }

    @Transactional
    public StudentDto saveOrUpdate(StudentDto studentDto) {
        log.info("Save or update student: {}", studentDto);
        Student student = studentMapper.toEntity(studentDto);
        if (student.getId() == null) {
            return save(student).orElseThrow(() -> new IllegalArgumentException("Failed to save student"));
        } else {
            return update(studentDto).orElseThrow(() -> new IllegalArgumentException("Failed to update student"));
        }
    }

    @Transactional
    public void archiving(Long... id) {
        int countOfUpdate = studentRepository.archivingByIds(id);

        int unsuccessfullyUpdate = id.length - countOfUpdate;
        if (unsuccessfullyUpdate > 0) {
            throw new IllegalArgumentException("Failed to archive " + unsuccessfullyUpdate + " student(s)");
        }
    }

    private Optional<StudentDto> save(Student student) {
        student = studentRepository.save(student);
        return Optional.ofNullable(studentMapper.toDto(student));
    }

    private Optional<StudentDto> update(StudentDto studentDto) {
        Student studentForUpdate = studentRepository.getReferenceById(studentDto.getId());

        studentForUpdate = studentMapper.partialUpdate(studentDto, studentForUpdate);

        return save(studentForUpdate);
    }
}
