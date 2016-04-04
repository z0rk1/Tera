package sk.tera.DAO;

import javax.persistence.EntityManager;

/**
 * Rozhranie pre pristup EntityManagera pre pracu s databazou.
 *
 * @author Marek Rebo
 */
public interface DAO {
    public EntityManager getEm();
    public void setEm(EntityManager em);
}
