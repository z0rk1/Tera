package sk.tera.ui;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * LoginBean
 *
 * @author z0rk1
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
