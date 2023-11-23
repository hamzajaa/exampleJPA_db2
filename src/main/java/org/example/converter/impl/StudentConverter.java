package org.example.converter.impl;

import org.example.beans.Student;
import org.example.converter.IConverter;
import org.example.dto.StudentDto;

public class StudentConverter implements IConverter<Student, StudentDto> {

    private AddressConverter addressConverter;

    public StudentConverter(AddressConverter addressConverter) {
        this.addressConverter = addressConverter;
    }

    @Override
    public Student toEntity(StudentDto dto) {
        if (dto == null) {
            return null;
        }
        Student student = new Student();
        student.setId(dto.id());
        student.setFirstName(dto.firstName());
        student.setLastName(dto.lastName());
        student.setEmail(dto.email());
        student.setAddress(addressConverter.toEntity(dto.addressDto()));
        return student;
    }

    @Override
    public StudentDto toDto(Student entity) {
        if (entity == null) {
            return null;
        }
        return new StudentDto(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                addressConverter.toDto(entity.getAddress())
        );
    }
}
