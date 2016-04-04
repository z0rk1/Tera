package sk.tera.db.DAO.Impl;

import sk.tera.db.DAO.PersonDAO;
import sk.tera.db.Entity.Person;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Implementation of {@link PersonDAO} interface.
 *
 * @author z0rk1
 */
@Stateful
@LocalBean
public class PersonDAOImpl implements PersonDAO {

    @PersistenceContext(unitName = "teraDS")
    private EntityManager em;

    @Override
    public Person getPersonById(Long id) throws Exception {
        return null;
    }

    @Override
    public Person findPersonById(Long id) {
        return null;
    }
}
