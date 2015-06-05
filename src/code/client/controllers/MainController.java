package code.client.controllers;

import code.client.views.LoginView;

import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class MainController {
	
	private UserController userController;
	private String user;

	public MainController()
	{
		userController = new UserController(this);
		show(new LoginView(this));
	}
	
	public UserController getUserController()
	{
		return userController;
	}
	
	public void show(Widget widget)
	{
		RootPanel.get("main").clear();
		RootPanel.get("main").add(widget);
	}
	
	public void setUser(String user)
	{
		this.user = user;
	}
	
	public String getUser()
	{
		return user;
	}
	
}
