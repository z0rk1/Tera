package sk.tera;

import sk.tera.DAO.AddressDAO;
import sk.tera.ui.LoginBean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

/**
 * Created by leaflock on 14.11.2015.
 */
@SessionScoped
@ManagedBean(name = "tera")
public class Tera {
    @EJB
    private AddressDAO aDAO;

    @EJB
    private LoginBean login;

    @Inject
    private Address address;

    private String text = "";


    public void init() {

    }


    public AddressDAO getaDAO() {
        return aDAO;
    }

    public void setaDAO(AddressDAO aDAO) {
        this.aDAO = aDAO;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LoginBean getLogin() {
        return login;
    }

    public void setLogin(LoginBean login) {
        this.login = login;
    }
}
