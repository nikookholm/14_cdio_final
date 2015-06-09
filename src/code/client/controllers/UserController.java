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
	private UserDTO userDTO;

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
		mc.databaseService.user_table_list(new AsyncCallback<ArrayList<UserDTO>>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}
			@Override
			public void onSuccess(ArrayList<UserDTO> result) {
				sortedUserList(result);

			}
		});
		return new ListUsersView(users, mc);
	}

	public Widget deactivateUser()
	{
		return new Widget();
	}

	//Metode der omgår at man skal bruge en final i onSucces i ListUsers(), sætter aktive brugere ind i users
	public void sortedUserList(ArrayList<UserDTO> result) {
		users = new ArrayList<UserDTO>();

		for (int i = 0; i < result.size(); i++) {
			if(result.get(i).getActive()){
				users.add(result.get(i));
			}

		}

	}
}


