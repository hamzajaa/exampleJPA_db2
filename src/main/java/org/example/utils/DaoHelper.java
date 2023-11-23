package org.example.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import org.example.beans.Student;

import java.util.List;

public class DaoHelper {

    public <T> List<T> findAll(Class<T> entityClass) {
        EntityManager entityManager = JPAHelper.getFactory().createEntityManager();
        entityManager.getTransaction().begin();

        List<T> resultList = entityManager.createQuery("SELECT s From " + entityClass.getSimpleName() + " s", entityClass).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();

        return resultList;

    }

    public static <T> T findById(Class<T> entityClass, Long id) {
        if (id == null) {
            throw new IllegalArgumentException("The given id must not be null!");
        }
        EntityManager entityManager = JPAHelper.getFactory().createEntityManager();
        entityManager.getTransaction().begin();
        T entity = entityManager.find(entityClass, id);
        entityManager.getTransaction().commit();
        entityManager.close();
        if (entity == null) {
            throw new EntityNotFoundException("The student with id: " + id + " not found");
        }
        return entity;
    }

    public static <T> T save(T entity) {
        if (entity == null) throw new IllegalArgumentException("Entity must not be null");
        EntityManager entityManager = JPAHelper.getFactory().createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
        entityManager.close();
        return entity;
    }

    public static <T> int delete(Class<T> entityClass, Long id) {
        T foundedEntity = findById(entityClass, id);
        if (foundedEntity == null) return -1;
        EntityManager entityManager = JPAHelper.getFactory().createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.contains(foundedEntity) ? foundedEntity : entityManager.merge(foundedEntity));
        entityManager.getTransaction().commit();
        entityManager.close();
        return 1;
    }

    public <T> T update(Class<T> entityClass, Long id) {
        T foundedEntity = findById(entityClass,id);
        EntityManager entityManager = JPAHelper.getFactory().createEntityManager();
        entityManager.getTransaction().begin();

        // Update the entity properties
        // Assuming setters and getters are appropriately defined in the entity classes
        // For example, if you have setFirstName, getFirstName methods in your entity class
        // You can update the properties individually without modifying the ID field
        // For simplicity, you can update each property manually
        // Example: ((Student) foundEntity).setFirstName(((Student) entity).getFirstName());

        // Merge the updated entity back to the persistence context
        entityManager.detach(foundedEntity); // Detach the entity from the persistence context
        entityManager.merge(entityClass); // Merge the updated entity back to the persistence context

        entityManager.getTransaction().commit();
        entityManager.close();
        return foundedEntity;
    }


}
