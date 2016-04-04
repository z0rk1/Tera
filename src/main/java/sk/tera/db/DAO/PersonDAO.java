package sk.tera.db.DAO;

import sk.tera.db.DAO.Impl.PersonDAOImpl;
import sk.tera.db.Entity.Person;

import javax.ejb.Local;

/**
 * DAO interface for Person entity.
 *
 * @author z0rk1
 */
@Local(PersonDAOImpl.class)
public interface PersonDAO {
    public static final String TABLE_NAME = "T_PERSON";
    public static final String ID = "ID";
    public static final String NICK = "NICK";
    public static final String PASSWORD = "PASSWORD";
    public static final String ADDRESS = "ADDRESS";

    public Person getPersonById(Long id) throws Exception;
    public Person findPersonById(Long id) throws Exception;
}
