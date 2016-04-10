package sk.tera.db.DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

/**
 * Interface for access to EntityManager of database.
 *
 * @author z0rk1
 */
public class DAO {

    @PersistenceContext(unitName = "teraDS")
    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public EntityTransaction getTransaction() {
        return em.getTransaction();
    }
}
