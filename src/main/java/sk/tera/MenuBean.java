package sk.tera;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.io.Serializable;

/**
 * @author z0rk1
 */
@ManagedBean(name = "menuBean")
@SessionScoped
public class MenuBean implements Serializable
{
	/**
	 * @see java.io.Serializable
	 */
	private static final long serialVersionUID = 3227900457981872721L;

	public static final String REPORT    = "REPORT";
	public static final String LIBRARY   = "LIB";
	public static final String INZERTION = "INZERT";
	public static final String GALERY    = "GALERY";

	private static final String FACES_REDIRECT_TRUE = "?faces-redirect=true";

	private String path;

	@PostConstruct
	public void preInit()
	{
		setPath("/Pages/intro.xhtml");
	}

	public void init()
	{

	}

	public void selectMenu(ActionEvent event)
	{
		String selectedMenu = (String) event.getComponent().getAttributes().get("menu");
		redirectMenuOpt(selectedMenu);
	}

	private void redirectMenuOpt(String selectedMenu)
	{
		StringBuilder path = new StringBuilder("/Pages");

		switch (selectedMenu)
		{
		case REPORT:
			path.append("/").append("pages/page1.xhtml");
			break;
		case LIBRARY:
			path.append("/").append("pages/page2.xhtml");
			break;
		case INZERTION:
			path.append("/").append("pages/page3.xhtml");
			break;
		case GALERY:
			path.append("/").append("pages/page4.xhtml");
			break;
		}

		setPath(path.toString());
	}

	public void redirectToHomePage()
	{
		String path = "/Pages/intro.xhtml";
		redirect(path);
	}

	private void redirect(String path)
	{
		try
		{
			FacesContext.getCurrentInstance().getExternalContext().redirect(path);
		}
		catch (Exception e)
		{
			//
		}
	}

	public String getPath()
	{
		return path;
	}

	public void setPath(String path)
	{
		this.path = path;
	}
}
