package org.example.converter.impl;

import org.example.beans.Address;
import org.example.converter.IConverter;
import org.example.dto.AddressDto;

public class AddressConverter implements IConverter<Address, AddressDto> {


    @Override
    public Address toEntity(AddressDto dto) {
        if (dto == null) {
            return null;
        }
        Address address = new Address();
        address.setId(dto.id());
        address.setStreet(dto.street());
        address.setCity(dto.city());
        return address;
    }

    @Override
    public AddressDto toDto(Address entity) {
        if (entity == null) {
            return null;
        }
        return new AddressDto(
                entity.getId(),
                entity.getStreet(),
                entity.getCity()
        );
    }
}
