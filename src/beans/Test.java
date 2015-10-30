/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.Bailleurs;

/**
 *
 * @author Dell
 */
public class Test {

    private static final String PERSISTENCE_UNIT_NAME = "ESDeskAppPU";
    private static EntityManagerFactory factory;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();

        // read the existing entries and write to console
        Query q = em.createNamedQuery("Bailleurs.findAll");
        List<Bailleurs> todoList = q.getResultList();
        for (Bailleurs todo : todoList) {
            System.out.println(todo.getNom());
        }
        System.out.println("Size: " + todoList.size());

    }

}
