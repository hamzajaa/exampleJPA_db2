package org.example.dao;

import org.example.beans.Address;

import java.util.List;

public interface AddressDao {

    List<Address> findAll();
    Address findById(Long id);
    Address save(Address student);
    Address update(Address student,Long id);
    int delete(Long id);

}
