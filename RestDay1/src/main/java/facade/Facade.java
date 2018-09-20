/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Person;
import entity.PersonDTO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author Rasmus
 */
public class Facade {
    private EntityManagerFactory emf;

    public Facade(EntityManagerFactory emf) {
        this.emf = emf;
    }

    EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Person addPerson(Person person) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();
            return person;
        }
        finally {
            em.close();
        }
    }
    
    public Person deletePerson(long id) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Person p = em.find(Person.class, id);
            em.remove(p);
            em.getTransaction().commit();
            return p;
        }
        finally {
            em.close();
        }
    }

    public Person getPerson(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Person.class, id);
        }
        finally {
            em.close();
        }
    }

    public List<PersonDTO> getAllPersons() {
        EntityManager em = getEntityManager();
        try {
//            TypedQuery<PersonDTO> tq = em.createQuery("Select p from Person p", PersonDTO.class);
            TypedQuery<PersonDTO> tq = em.createQuery("select new entity.PersonDTO(p.firstName, p.lastName, p.phonenumber) from Person p", PersonDTO.class);
            return tq.getResultList();
        }
        finally {
            em.close();
        }
    }

    public Person editPerson(Person p) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(p);
            em.getTransaction().commit();
            return p;
        }
        finally {
            em.close();
        }
    }
}
