package sk.tera.db.DAO.Impl;

import sk.tera.db.DAO.DAO;
import sk.tera.db.DAO.GroupDAO;
import sk.tera.db.Entity.Group;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.persistence.TypedQuery;
import java.io.IOException;

/**
 * Implementation of {@link GroupDAO} interface.
 *
 * @author z0rk1
 */
@Stateful
@LocalBean
public class GroupDAOImpl extends DAO implements GroupDAO {

    @Override
    public Long add(Group group) throws Exception {
        getEm().persist(group);
        return  group.getId();
    }

    @Override
    public Long update(Group group) throws Exception {
        getEm().merge(group);
        return group.getId();
    }

    @Override
    public Long delete(Long id) throws Exception {
        Group group = getGroupById(id);

        return delete(group);
    }

    @Override
    public Long delete(Group group) throws Exception {
        getEm().remove(group);
        return group.getId();
    }

    @Override
    public Group getGroupById(Long id) throws Exception {
        String sql = "SELECT a FROM Address a WHERE a.id = :id";

        TypedQuery<Group> query = getEm().createQuery(sql, Group.class);
        query.setParameter("id", id);

        Group result = query.getSingleResult();

        if (result == null) {
            throw new IOException("NENASIEL SA DANY ZAZNAM!");
        }

        return result;
    }

    @Override
    public Group findGroupById(Long id) throws Exception {
        String sql = "SELECT a FROM Address a WHERE a.id = :id";

        TypedQuery<Group> query = getEm().createQuery(sql, Group.class);
        query.setParameter("id", id);

        return query.getSingleResult();
    }
}
