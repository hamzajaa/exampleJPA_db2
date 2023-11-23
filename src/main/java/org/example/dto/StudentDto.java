package org.example.dto;

public record StudentDto(Long id, String firstName, String lastName, String email, AddressDto addressDto) {
    public StudentDto(String firstName, String lastName, String email, AddressDto addressDto) {
        this(null, firstName, lastName, email, addressDto);
    }
}
