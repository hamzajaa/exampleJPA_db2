package org.example.services.impl;

import org.example.beans.Address;
import org.example.beans.Student;
import org.example.converter.impl.AddressConverter;
import org.example.converter.impl.StudentConverter;
import org.example.dao.StudentDao;
import org.example.dto.AddressDto;
import org.example.dto.StudentDto;
import org.example.services.facade.AddressService;
import org.example.services.facade.StudentService;

import java.util.List;

public class StudentServiceImpl implements StudentService {

    private StudentDao studentDao;
    private StudentConverter studentConverter;
    private AddressService addressService;
    private AddressConverter addressConverter;

    public StudentServiceImpl(StudentDao studentDao, StudentConverter studentConverter, AddressService addressService, AddressConverter addressConverter) {
        this.studentDao = studentDao;
        this.studentConverter = studentConverter;
        this.addressService = addressService;
        this.addressConverter = addressConverter;
    }

    @Override
    public List<StudentDto> findAll() {
        List<Student> students = studentDao.findAll();
        return students.stream().map(studentConverter::toDto).toList();
    }

    @Override
    public StudentDto findById(Long id) {
        Student student = studentDao.findById(id);
        return studentConverter.toDto(student);
    }

    @Override
    public List<StudentDto> findByAddress(AddressDto addressDto) {
        List<Student> students = studentDao.findByAddress(addressConverter.toEntity(addressDto));
        return students.stream().map(studentConverter::toDto).toList();
    }

    @Override
    public StudentDto save(StudentDto studentDto) {
        Student student = studentConverter.toEntity(studentDto);
        AddressDto address = addressService.findById(studentDto.addressDto().id());
        student.setAddress(addressConverter.toEntity(address));
        Student savedStudent = studentDao.save(student);
        return studentConverter.toDto(savedStudent);
    }

    @Override
    public StudentDto update(StudentDto studentDto, Long id) {
        StudentDto foundedStudent = findById(id);
        Student student = studentConverter.toEntity(foundedStudent);
        AddressDto address = addressService.findById(studentDto.addressDto().id());
        Address updatedAddress = addressConverter.toEntity(address);
        student.setAddress(updatedAddress);
        Student updatedStudent = studentDao.update(student, id);
        return studentConverter.toDto(updatedStudent);
    }

    @Override
    public int delete(Long id) {
        return studentDao.delete(id);
    }


}
