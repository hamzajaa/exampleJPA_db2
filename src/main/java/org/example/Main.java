package org.example;

import org.example.converter.impl.AddressConverter;
import org.example.converter.impl.StudentConverter;
import org.example.dao.AddressDao;
import org.example.dao.StudentDao;
import org.example.dao.impl.AddressDaoImpl;
import org.example.dao.impl.StudentDaoImpl;
import org.example.dto.AddressDto;
import org.example.dto.StudentDto;
import org.example.services.facade.AddressService;
import org.example.services.facade.StudentService;
import org.example.services.impl.AddressServiceImpl;
import org.example.services.impl.StudentServiceImpl;

import java.util.List;

public class Main {


    public static void main(String[] args) {

        AddressDao addressDao = new AddressDaoImpl();
        StudentDao studentDao = new StudentDaoImpl();


        AddressConverter addressConverter = new AddressConverter();
        StudentConverter studentConverter = new StudentConverter(addressConverter);

        AddressService addressService = new AddressServiceImpl(addressDao, addressConverter, studentDao, studentConverter);
        StudentService studentService = new StudentServiceImpl(studentDao, studentConverter, addressService, addressConverter);

        AddressDto addressDto1 = new AddressDto("street1", "Rabat");
        AddressDto addressDto2 = new AddressDto("street2", "Rabat");
        AddressDto addressDto3 = new AddressDto("street", "Marrakech");

        AddressDto savedAddress1 = addressService.save(addressDto1);
        AddressDto savedAddress2 = addressService.save(addressDto2);
        AddressDto savedAddress3 = addressService.save(addressDto3);

        StudentDto studentDto1 = new StudentDto("hamza", "jaa", "hamzajaa@gmail.com", savedAddress3);
        StudentDto studentDto2 = new StudentDto("ayoub", "ben", "ayoubben@gmail.com", savedAddress1);
        StudentDto studentDto3 = new StudentDto("salah", "salah", "salah@gmail.com", savedAddress2);

        StudentDto saved1 = studentService.save(studentDto1);
        StudentDto saved2 = studentService.save(studentDto2);
        StudentDto saved3 = studentService.save(studentDto3);

        StudentDto update = studentService.update(new StudentDto("hamza", "jaa", "updatedemail@gnail.com", savedAddress2), 1L);
        List<StudentDto> studentsList = studentService.findAll();
        StudentDto studentById = studentService.findById(1L);
//        int res = studentService.delete(1L);

        addressService.delete(2L);

        System.out.println("Operations Successes");
    }
}