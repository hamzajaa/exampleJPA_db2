package org.example.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.TypedQuery;
import org.example.beans.Address;
import org.example.beans.Student;
import org.example.dao.StudentDao;
import org.example.utils.JPAHelper;

import java.util.List;

public class StudentDaoImpl implements StudentDao {


    @Override
    public List<Student> findAll() {
        EntityManager entityManager = JPAHelper.getFactory().createEntityManager();
        entityManager.getTransaction().begin();

        List<Student> resultList = entityManager.createQuery("SELECT s From Student s", Student.class).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();

        return resultList;

    }

    @Override
    public Student findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("The given id must not be null!");
        }
        EntityManager entityManager = JPAHelper.getFactory().createEntityManager();
        entityManager.getTransaction().begin();
        Student student = entityManager.find(Student.class, id);
        entityManager.getTransaction().commit();
        entityManager.close();
        if (student == null) {
            throw new EntityNotFoundException("The student with id: " + id + " not found");
        }
        return student;
    }

    @Override
    public List<Student> findByAddress(Address address) {
        if (address == null) {
            throw new IllegalArgumentException("The given entity must not be null!");
        }
        EntityManager entityManager = JPAHelper.getFactory().createEntityManager();
        entityManager.getTransaction().begin();

        TypedQuery<Student> query = entityManager.createQuery("SELECT s FROM Student s WHERE s.address.id = :id", Student.class);
        query.setParameter("id", address.getId());

        entityManager.getTransaction().commit();
        List<Student> resultList = query.getResultList();
        entityManager.close();
        if (resultList == null) {
            throw new EntityNotFoundException("The students with address_id: " + address.getId() + " not found");
        }
        return resultList;
    }

    @Override
    public Student save(Student student) {
        if (student == null) throw new IllegalArgumentException("Entity must not be null");
        EntityManager entityManager = JPAHelper.getFactory().createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(student);
        entityManager.getTransaction().commit();
        entityManager.close();
        return student;
    }

    @Override
    public Student update(Student student, Long id) {
        Student foundedStudent = findById(id);
        foundedStudent.setFirstName(student.getFirstName());
        foundedStudent.setLastName(student.getLastName());
        foundedStudent.setEmail(student.getEmail());
        foundedStudent.setAddress(student.getAddress());
        EntityManager entityManager = JPAHelper.getFactory().createEntityManager();
        entityManager.getTransaction().begin();
        Student updatedStudent = entityManager.merge(foundedStudent);
        entityManager.getTransaction().commit();
        entityManager.close();
        return updatedStudent;
    }

    @Override
    public int delete(Long id) {
        Student foundedStudent = findById(id);
        if (foundedStudent == null) return -1;
        EntityManager entityManager = JPAHelper.getFactory().createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.contains(foundedStudent) ? foundedStudent : entityManager.merge(foundedStudent));
        entityManager.getTransaction().commit();
        entityManager.close();
        return 1;
    }


}
