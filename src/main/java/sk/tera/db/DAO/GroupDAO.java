package sk.tera.db.DAO;

import sk.tera.db.DAO.Impl.GroupDAOImpl;
import sk.tera.db.Entity.Group;

import javax.ejb.Local;

/**
 * DAO interface for Group entity.
 *
 * @author z0rk1
 */
@Local(GroupDAOImpl.class)
public interface GroupDAO {
    public static final String TABLE_NAME = "T_GROUP";
    public static final String ID = "ID";
    public static final String NAME = "NAME";
    public static final String VALUE = "VALUE";
    public static final String DESCRIPTION = "DESCRIPTION";

    public Group getGroupById(Long id) throws Exception;
    public Group findGroupById(Long id) throws Exception;
}
