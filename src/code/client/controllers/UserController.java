package code.client.controllers;

import java.util.ArrayList;

import code.client.views.CreateUserView;
import code.client.views.ListUsersView;
import code.client.views.LoginView;
import code.client.views.MainView;
import code.database.UserDTO;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;

public class UserController {

	private MainController mc;
	ArrayList<UserDTO> users;
	private UserDTO currentUser;
	private Widget returnView;
	private boolean bool = false;

	public UserController(MainController mc)
	{
		this.mc = mc;
	}

	public Widget login(final String oprNumber, final String password)
	{
		mc.databaseService.user_table_get(Integer.parseInt(oprNumber), new AsyncCallback<UserDTO>() {

			@Override
			public void onFailure(Throwable caught) {
				//Window.alert("FEEEEEEEEEEEEEEJL");
				mc.show(new LoginView(mc));
			}

			@Override
			public void onSuccess(UserDTO result) {

				parseUserDTO(result);

				if (password.equals(currentUser.getPassword()) ){

					mc.setUser(currentUser);
					mc.show(new MainView(mc));
				}
			}
		});

		return returnView;
	}

	public Widget createUser(final UserDTO user)
	{
		if(user != null){

			int userId = user.getOprId();
			mc.databaseService.user_table_get(userId, new AsyncCallback<UserDTO>() {
				@Override
				public void onFailure(Throwable caught) {
					bool = false;
				}
				@Override
				public void onSuccess(UserDTO result) {
					bool = true;
				}
			});

			if(bool == true){
				mc.databaseService.user_table_create(user, new AsyncCallback<Void>() {

					@Override
					public void onFailure(Throwable caught) { }
					@Override
					public void onSuccess(Void result) { }
				});
			}else{
//				Window.alert("Det ønskede bruger ID eksistere allerede!");
				return new CreateUserView(user, mc);
			}
			return new CreateUserView(user, mc);
		}
		else{
			return new CreateUserView(null, mc);
		}
	}	

	public Widget updateUser(UserDTO user)
	{
		mc.databaseService.user_table_update(user, new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) { }

			@Override
			public void onSuccess(Void result) {
				mc.show(mc.getUserController().listUsers());
			}
		});
		return null;
	}

	public Widget listUsers()
	{
		mc.databaseService.user_table_list(new AsyncCallback<ArrayList<UserDTO>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Der skete en fejl, da systemet skulle have adgang til databasen");
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

	private void parseUserDTO(UserDTO result){
		this.currentUser = result;

	}
}


