package code.client.controllers;

import java.util.ArrayList;

import code.client.views.CreateUserView;
import code.client.views.MainView;
import code.database.UserDTO;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
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
		
		mc.databaseService.user_table_list(new AsyncCallback<ArrayList<UserDTO>>() {
			ArrayList<UserDTO> list;
			@Override
			public void onSuccess(ArrayList<UserDTO> result) {
				list = result;
				
			}
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fejl i hentning af brugere");
				
			}
		});
		return new Widget();
	}
	
	public Widget deactivateUser()
	{
		return new Widget();
	}

}
