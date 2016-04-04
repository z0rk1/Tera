package sk.tera.db.DAO;

import sk.tera.db.DAO.Impl.AddressDAOImpl;
import sk.tera.db.Entity.Address;

import javax.ejb.Local;
import javax.persistence.EntityManager;

/**
 * DAO interface for Address entity.
 *
 * @author z0rk1
 */
@Local(AddressDAOImpl.class)
public interface AddressDAO {
    public static final String TABLE_NAME = "T_ADDRESS";
    public static final String ID = "ID";
    public static final String STREET = "STREET";
    public static final String TOWN = "TOWN";
    public static final String STATE = "STATE";
    public static final String ZIP = "ZIP";

    public Address getAddressById(Long id) throws Exception;
    public Address findAddressById(Long id) throws Exception;
    public Long add(Address address) throws Exception;
    public Long update(Address address) throws Exception;
    public Long delete(Long id) throws Exception;
    public Long delete(Address address) throws Exception;

    public void setEm(EntityManager em);
    public EntityManager getEm();
}
