package code.client.controllers;

import java.util.ArrayList;

import org.apache.bcel.generic.ReturnInstruction;

import code.client.views.CreateUserView;
import code.client.views.InfomationWidget;
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
	private Widget returnInfomation;

	public UserController(MainController mc)
	{
		this.mc = mc;
	}

	public Widget login(final String oprNumber, final String password)
	{
		mc.databaseService.user_table_get(Integer.parseInt(oprNumber), new AsyncCallback<UserDTO>() {

			@Override
			public void onFailure(Throwable caught) {
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

	public Widget createUser(final Object user)
	{
		if(user instanceof UserDTO){

			int userId = ((UserDTO)user).getOprId();
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

			if(bool != true){
				mc.databaseService.user_table_create((UserDTO)user, new AsyncCallback<Void>() {

					@Override
					public void onFailure(Throwable caught) {
						returnInfomation = new InfomationWidget().showInfomation(caught);
						mc.show(new CreateUserView(mc, returnInfomation));
					}
					
					@Override
					public void onSuccess(Void result) {
						returnInfomation = new InfomationWidget().showInfomation("Bruger \"" + ((UserDTO)user).getOprName() + "\" oprettet!");
						mc.show(new CreateUserView(mc, returnInfomation));
					}
				});
			
		
			
			}
	
		}
		return new CreateUserView(mc, null);
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
				mc.show(new ListUsersView(result, mc));

			}
		});
		return returnView;
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


