package org.example.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import org.example.beans.Address;
import org.example.dao.AddressDao;
import org.example.utils.JPAHelper;

import java.util.List;

public class AddressDaoImpl implements AddressDao {


    @Override
    public List<Address> findAll() {
        EntityManager entityManager = JPAHelper.getFactory().createEntityManager();
        entityManager.getTransaction().begin();

        List<Address> resultList = entityManager.createQuery("SELECT s From Address s", Address.class).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();

        return resultList;

    }

    @Override
    public Address findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("The given id must not be null!");
        }
        EntityManager entityManager = JPAHelper.getFactory().createEntityManager();
        entityManager.getTransaction().begin();
        Address address = entityManager.find(Address.class, id);
        entityManager.getTransaction().commit();
        entityManager.close();
        if (address == null) {
            throw new EntityNotFoundException("The address with id: " + id + " not found");
        }
        return address;
    }

    @Override
    public Address save(Address address) {
        if (address == null) throw new IllegalArgumentException("Entity must not be null");
        EntityManager entityManager = JPAHelper.getFactory().createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(address);
        entityManager.getTransaction().commit();
        entityManager.close();
        return address;
    }

    @Override
    public Address update(Address address, Long id) {
        Address foundedAddress = findById(id);
        foundedAddress.setStreet(address.getStreet());
        foundedAddress.setCity(address.getCity());
        EntityManager entityManager = JPAHelper.getFactory().createEntityManager();
        entityManager.getTransaction().begin();
        Address updatedAddress = entityManager.merge(foundedAddress);
        entityManager.getTransaction().commit();
        entityManager.close();
        return updatedAddress;
    }

    @Override
    public int delete(Long id) {
        Address foundedAddress = findById(id);
        if (foundedAddress == null) return -1;
        EntityManager entityManager = JPAHelper.getFactory().createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.contains(foundedAddress) ? foundedAddress : entityManager.merge(foundedAddress));
        entityManager.getTransaction().commit();
        entityManager.close();
        return 1;
    }


}
