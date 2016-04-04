package sk.tera.db.DAO.Impl;

import sk.tera.db.DAO.AddressDAO;
import sk.tera.db.Entity.Address;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.io.IOException;

/**
 * Implementation of {@link AddressDAO} interface.
 *
 * @author z0rk1
 */
@Stateful
@LocalBean
public class AddressDAOImpl implements AddressDAO {

    @PersistenceContext(unitName = "teraDS")
    private EntityManager em;


    public Address getAddressById(Long id) throws Exception {
        String sql = "SELECT a FROM Address a WHERE a.id = :id";

        TypedQuery<Address> query = em.createQuery(sql, Address.class);
        query.setParameter("id", id);

        Address result = query.getSingleResult();

        if (result == null) {
            throw new IOException("NENASIEL SA DANY ZAZNAM!");
        }

        return result;
    }

    @Override
    public Address findAddressById(Long id) throws Exception {
        String sql = "SELECT a FROM Address a WHERE a.id = :id";

        TypedQuery<Address> query = em.createQuery(sql, Address.class);
        query.setParameter("id", id);

        return query.getSingleResult();
    }

    @Override
    public Long add(Address address) throws Exception {
        em.persist(address);
        return address.getId();
    }

    @Override
    public Long update(Address address) throws Exception {
        em.merge(address);
        return address.getId();
    }

    @Override
    public Long delete(Long id) throws Exception {
        Address address = getAddressById(id);

        return delete(address);
    }

    @Override
    public Long delete(Address address) throws Exception {
        em.remove(address);
        return address.getId();
    }

    @Override
    public EntityManager getEm() {
        return this.em;
    }

    @Override
    public void setEm(EntityManager em) {
        this.em = em;
    }
}
