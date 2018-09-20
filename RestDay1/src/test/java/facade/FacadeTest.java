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
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Rasmus
 */
public class FacadeTest {
    
    Facade facade = new Facade(Persistence.createEntityManagerFactory("puTEST"));
    
    public FacadeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void AddPersonGetPerson() {
        System.out.println("AddPersonGetPerson");
        facade.addPerson(new Person("John", "Winter", 34567));
        
        List<PersonDTO> persons = facade.getAllPersons();
        System.out.println(persons);
        assertTrue(persons.contains(new PersonDTO("John", "Winter", 34567)));
    }
}
