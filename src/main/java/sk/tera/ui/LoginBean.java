package sk.tera.ui;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Created by leaflock on 14.11.2015.
 */
@Stateless
@LocalBean
public class LoginBean {

    private String ejb = "EJB stateless";

    public String getEjb() {
        return ejb;
    }

    public void setEjb(String ejb) {
        this.ejb = ejb;
    }
}
