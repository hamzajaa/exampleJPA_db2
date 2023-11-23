package org.example.services.facade;

import org.example.beans.Student;
import org.example.dto.AddressDto;
import org.example.dto.StudentDto;

import java.util.List;

public interface StudentService {
    List<StudentDto> findAll();
    StudentDto findById(Long id);
    List<StudentDto> findByAddress(AddressDto addressDto);
    StudentDto save(StudentDto studentDto);
    StudentDto update(StudentDto studentDto,Long id);
    int delete(Long id);
}
