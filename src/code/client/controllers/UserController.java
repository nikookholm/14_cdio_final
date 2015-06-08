package code.client.controllers;

import code.client.views.CreateUserView;
import code.client.views.MainView;
import code.database.UserDTO;

import com.google.gwt.user.client.ui.Widget;

public class UserController {
	
	private MainController mc;
	
	public UserController(MainController mc)
	{
		this.mc = mc;
	}
	
	public Widget login(String oprNumber)
	{
		mc.setUser(oprNumber);
		return new MainView(mc);
	}
	
	public Widget createUser()
	{
		return new CreateUserView(mc);
	}
	
	public Widget updateUser(UserDTO user)
	{
		return new Widget();
	}
	
	public Widget listUsers()
	{
		return new Widget();
	}
	
	public Widget deactivateUser()
	{
		return new Widget();
	}

}
