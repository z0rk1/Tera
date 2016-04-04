package sk.tera.db.Entity;

import sk.tera.db.DAO.GroupDAO;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Entity which represent table T_GROUP.
 *
 * @author z0rk1
 */
@Entity
@Table(name = GroupDAO.TABLE_NAME)
public class Group implements Serializable {

    /**
     * @see Serializable
     */
    private static final long serialVersionUID = -7014095292292549060L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = GroupDAO.ID, nullable = false, unique = true, updatable = false)
    private Long id;

    @Column(name = GroupDAO.NAME, nullable = false, unique = true, length = 255)
    private String name;

    @Column(name = GroupDAO.VALUE, nullable = false, unique = true, length = 255)
    private String value;

    @Column(name = GroupDAO.DESCRIPTION, length = 1000)
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Group)) return false;

        Group group = (Group) o;

        if (!getId().equals(group.getId())) return false;
        if (!getName().equals(group.getName())) return false;
        if (!getValue().equals(group.getValue())) return false;
        return !(getDescription() != null ? !getDescription().equals(group.getDescription()) : group.getDescription() != null);

    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getName().hashCode();
        result = 31 * result + getValue().hashCode();
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        return result;
    }
}
