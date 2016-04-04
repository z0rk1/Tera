package sk.tera;

import sk.tera.DAO.AddressDAO;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by leaflock on 14.11.2015.
 */
@Entity
@Table(name = AddressDAO.TABLE_NAME)
public class Address implements Serializable {

    /**
     * @see Serializable
     */
    private static final long serialVersionUID = 335691160224963642L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = AddressDAO.ID, nullable = false, unique = true, updatable = false)
    private Long id;

    @Column(name = AddressDAO.STREET, length = 255, nullable = false)
    private String street;

    @Column(name = AddressDAO.TOWN, length = 255, nullable = false)
    private String town;

    @Column(name = AddressDAO.STATE, length = 255, nullable = false)
    private String state;

    @Column(name = AddressDAO.ZIP, length = 20, nullable = false)
    private String zip;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;

        Address address = (Address) o;

        if (!getId().equals(address.getId())) return false;
        if (getStreet() != null ? !getStreet().equals(address.getStreet()) : address.getStreet() != null) return false;
        if (getTown() != null ? !getTown().equals(address.getTown()) : address.getTown() != null) return false;
        if (getState() != null ? !getState().equals(address.getState()) : address.getState() != null) return false;
        return !(getZip() != null ? !getZip().equals(address.getZip()) : address.getZip() != null);

    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + (getStreet() != null ? getStreet().hashCode() : 0);
        result = 31 * result + (getTown() != null ? getTown().hashCode() : 0);
        result = 31 * result + (getState() != null ? getState().hashCode() : 0);
        result = 31 * result + (getZip() != null ? getZip().hashCode() : 0);
        return result;
    }
}
