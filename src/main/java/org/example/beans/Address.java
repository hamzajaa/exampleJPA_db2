package org.example.beans;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long id;
    @Column(name = "street", nullable = false, length = 60)
    private String street;
    @Column(name = "city", nullable = false, length = 60)
    private String city;

    @OneToMany(mappedBy = "address")
    List<Student> students = new ArrayList<>();



    public Address() {
    }

    public Address(String street, String city) {
        this.street = street;
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


}
