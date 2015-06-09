package code.client.controllers;

import code.client.DatabaseService;
import code.client.DatabaseServiceAsync;
import code.client.views.LoginView;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class MainController {
	
	public final DatabaseServiceAsync databaseService = GWT
			.create(DatabaseService.class);
	
	private UserController userController;
	private IngredientController ingredientController;
	private ProductBatchController productBatchController;
	private String user;

	public MainController()
	{
		userController = new UserController(this);
		ingredientController = new IngredientController(this);
		//productBatchController = new ProductBatchController(this);
		show(new LoginView(this));
	}
	
	public UserController getUserController()
	{
		return userController;
	}
	
	public IngredientController getIngredientController()
	{
		return ingredientController;
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
