package org.example.services.facade;

import org.example.dto.AddressDto;

import java.util.List;

public interface AddressService {
    List<AddressDto> findAll();
    AddressDto findById(Long id);
    AddressDto save(AddressDto addressDto);
    AddressDto update(AddressDto addressDto,Long id);
    int delete(Long id);
}
