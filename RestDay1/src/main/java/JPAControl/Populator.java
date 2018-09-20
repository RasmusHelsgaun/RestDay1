/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPAControl;

import entity.Address;
import entity.Person;
import facade.Facade;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Rasmus
 */
public class Populator {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        
        Address a1 = new Address("Some Road 1", "Berlin");
        Address a2 = new Address("Some Road 2", "New York");

        Person p1 = new Person("Lars", "Henriksen", 1234);
        p1.addAddress(a1);
        p1.addAddress(a2);
        a1.addPerson(p1);
        a2.addPerson(p1);

        Person p2 = new Person("Lotte", "Larsen", 5678);
        p2.addAddress(a1);
        p2.addAddress(a2);
        a1.addPerson(p2);
        a2.addPerson(p2);
        
        try {
            em.getTransaction().begin();
            em.persist(p1);
            em.persist(p2);
            em.getTransaction().commit();
        }
        finally {
            em.close();
        }

    }
}
