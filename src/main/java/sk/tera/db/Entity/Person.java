package sk.tera.db.Entity;

import sk.tera.db.DAO.PersonDAO;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Entity which represent table T_PERSON.
 *
 * @author z0rk1
 */
@Entity
@Table(name = PersonDAO.TABLE_NAME)
public class Person implements Serializable {

    /**
     * @see java.io.Serializable
     */
    private static final long serialVersionUID = -3110732065365780493L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = PersonDAO.ID, nullable = false, updatable = false, unique = true)
    private Long id;

    @Column(name = PersonDAO.NICK, nullable = false, unique = true, length = 50)
    private String nick;

    @Column(name = PersonDAO.PASSWORD, nullable = false, length = 255)
    private String password;

//    @OneToOne
    @Transient
    @Column(name = PersonDAO.ADDRESS)
    private Address address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;

        Person person = (Person) o;

        if (!getId().equals(person.getId())) return false;
        if (!getNick().equals(person.getNick())) return false;
        if (!getPassword().equals(person.getPassword())) return false;
        return !(getAddress() != null ? !getAddress().equals(person.getAddress()) : person.getAddress() != null);

    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getNick().hashCode();
        result = 31 * result + getPassword().hashCode();
        result = 31 * result + (getAddress() != null ? getAddress().hashCode() : 0);
        return result;
    }
}
