package sk.tera.DAO.Impl;

import sk.tera.DAO.PersonDAO;
import sk.tera.Person;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by leaflock on 30.3.2016.
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
