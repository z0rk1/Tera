package sk.tera.DAO;

import sk.tera.DAO.Impl.GroupDAOImpl;
import sk.tera.Group;

import javax.ejb.Local;

/**
 * Created by leaflock on 4.3.2016.
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
