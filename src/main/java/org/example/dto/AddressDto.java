package org.example.dto;

import java.util.List;

public record AddressDto(Long id, String street, String city) {
    public AddressDto(String street, String city) {
        this(null, street, city);
    }

}
