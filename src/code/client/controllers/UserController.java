package code.client.controllers;

import java.util.ArrayList;

import code.client.views.CreateUserView;
import code.client.views.ListUsersView;
import code.client.views.MainView;
import code.database.UserDTO;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;

public class UserController {

	private MainController mc;

	ArrayList<UserDTO> users;

	UserDTO userDTO;

	public UserController(MainController mc)
	{
		this.mc = mc;
	}

	public Widget login(String oprNumber)
	{
		mc.setUser(oprNumber);
		return new MainView(mc);
	}

	public Widget createUser(UserDTO user)
	{
		if(user != null){
			mc.databaseService.user_table_create(user, new AsyncCallback<Void>() {

				@Override
				public void onFailure(Throwable caught) {
					// Skal præsentere fejl
				}
				@Override
				public void onSuccess(Void result) {
				}
			});
			return null;	
		}
		else{
			return new CreateUserView(user, mc);
		}
	}

	public Widget updateUser(UserDTO user)
	{
		return new Widget();
	}

	public Widget listUsers()
	{
		return new ListUsersView(mc);
	}

	public Widget deactivateUser()
	{
		return new Widget();
	}

}
