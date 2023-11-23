package org.example.dao;

import org.example.beans.Address;
import org.example.beans.Student;

import java.util.List;

public interface StudentDao {

    List<Student> findAll();
    Student findById(Long id);
    List<Student> findByAddress(Address address);

    Student save(Student student);
    Student update(Student student,Long id);
    int delete(Long id);

}
