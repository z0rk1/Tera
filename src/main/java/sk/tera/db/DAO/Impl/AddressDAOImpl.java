package sk.tera.db.DAO.Impl;

import sk.tera.db.DAO.AddressDAO;
import sk.tera.db.DAO.DAO;
import sk.tera.db.Entity.Address;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Implementation of {@link AddressDAO} interface.
 *
 * @author z0rk1
 */
@Stateful
@LocalBean
public class AddressDAOImpl extends DAO implements AddressDAO {

//    @PersistenceContext(unitName = "teraDS")
//    private EntityManager em;


    public Address getAddressById(Long id) throws Exception {
        String sql = "SELECT a FROM Address a WHERE a.id = :id";

        TypedQuery<Address> query = getEm().createQuery(sql, Address.class);
        query.setParameter("id", id);

        List<Address> result = query.getResultList();

        if (result.isEmpty()) {
            throw new IOException("NENASIEL SA DANY ZAZNAM!");
        }

        return result.get(0);
    }

    @Override
    public Address findAddressById(Long id) throws Exception {
        String sql = "SELECT a FROM Address a WHERE a.id = :id";

        TypedQuery<Address> query = getEm().createQuery(sql, Address.class);
        query.setParameter("id", id);

        return query.getSingleResult();
    }

    public LinkedList<Address> findAddress() throws Exception {
        Query query = getEm().createNamedQuery("FIND_ALL");
        return new LinkedList<Address>(query.getResultList());
    }

    @Override
    public Long add(Address address) throws Exception {
        getEm().persist(address);
        return address.getId();
    }

    @Override
    public Long update(Address address) throws Exception {
        getEm().merge(address);
        return address.getId();
    }

    @Override
    public Long delete(Long id) throws Exception {
        Address address = getAddressById(id);

        return delete(address);
    }

    @Override
    public Long delete(Address address) throws Exception {
        getEm().remove(address);
        return address.getId();
    }
}
