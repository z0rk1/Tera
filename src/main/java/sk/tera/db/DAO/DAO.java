package sk.tera.db.DAO;

import javax.persistence.EntityManager;

/**
 * Interface for access to EntityManager of database.
 *
 * @author z0rk1
 */
public interface DAO {
    public EntityManager getEm();
    public void setEm(EntityManager em);
}
