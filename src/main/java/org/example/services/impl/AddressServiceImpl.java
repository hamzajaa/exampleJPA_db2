package org.example.services.impl;

import jakarta.transaction.Transactional;
import org.example.beans.Address;
import org.example.beans.Student;
import org.example.converter.impl.AddressConverter;
import org.example.converter.impl.StudentConverter;
import org.example.dao.AddressDao;
import org.example.dao.StudentDao;
import org.example.dto.AddressDto;
import org.example.dto.StudentDto;
import org.example.services.facade.AddressService;
import org.example.services.facade.StudentService;

import java.util.List;

public class AddressServiceImpl implements AddressService {

    private AddressDao addressDao;
    private AddressConverter addressConverter;
    private StudentDao studentDao;
    private StudentConverter studentConverter;


    public AddressServiceImpl(AddressDao addressDao, AddressConverter addressConverter, StudentDao studentDao, StudentConverter studentConverter) {
        this.addressDao = addressDao;
        this.addressConverter = addressConverter;
        this.studentDao = studentDao;
        this.studentConverter = studentConverter;
    }

    @Override
    public List<AddressDto> findAll() {
        List<Address> addresss = addressDao.findAll();
        return addresss.stream().map(addressConverter::toDto).toList();
    }

    @Override
    public AddressDto findById(Long id) {
        Address address = addressDao.findById(id);
        return addressConverter.toDto(address);
    }

    @Override
    public AddressDto save(AddressDto addressDto) {
        Address address = addressConverter.toEntity(addressDto);
        Address savedAddress = addressDao.save(address);
        return addressConverter.toDto(savedAddress);
    }

    @Override
    public AddressDto update(AddressDto addressDto, Long id) {
        Address address = addressConverter.toEntity(addressDto);
        Address updatedAddress = addressDao.update(address, id);
        return addressConverter.toDto(updatedAddress);
    }

    @Override
    public int delete(Long id) {
        AddressDto address = findById(id);
        List<Student> students = studentDao.findByAddress(addressConverter.toEntity(address));
        for (Student student : students) {
            studentDao.delete(student.getId());
        }
        return addressDao.delete(id);
    }


}
