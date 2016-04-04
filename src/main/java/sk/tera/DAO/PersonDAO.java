package sk.tera.DAO;

import sk.tera.Person;
import sk.tera.DAO.Impl.PersonDAOImpl;

import javax.ejb.Local;

/**
 * Created by leaflock on 30.3.2016.
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
