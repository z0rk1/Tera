package sk.tera;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Testovaci DAO objekt ktory sa napaja na databazu projektu.
 *
 * @author Marek Rebo
 */
public class TestDAO {

    private EntityManagerFactory emf;
    private EntityManager em;

    public TestDAO() {
        emf= Persistence.createEntityManagerFactory("testDS");
        em = emf.createEntityManager();
    }

    public EntityManagerFactory getEmf() {
        return this.emf;
    }

    public EntityManager getEm() {
        return this.em;
    }
}
