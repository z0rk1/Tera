package sk.tera.DAO.Impl;

import sk.tera.DAO.GroupDAO;
import sk.tera.Group;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by leaflock on 4.3.2016.
 */
@Stateful
@LocalBean
public class GroupDAOImpl implements GroupDAO {

    @PersistenceContext(unitName = "teraDS")
    private EntityManager em;

    @Override
    public Group getGroupById(Long id) throws Exception {
//        String sql = "SELECT g FROM Group g WHERE g.id = :id";
//
//        TypedQuery<Address> query = em.createQuery(sql, Group.class);
//        query.setParameter("id", id);
//
//        Address result = query.getSingleResult();
//
//        if (result == null) {
//            throw new IOException("NENASIEL SA DANY ZAZNAM!");
//        }

        return null;
    }

    @Override
    public Group findGroupById(Long id) throws Exception {
        return null;
    }
}
