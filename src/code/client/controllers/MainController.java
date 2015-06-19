package code.client.controllers;

import code.client.DatabaseService;
import code.client.DatabaseServiceAsync;
import code.client.views.LoginView;
import code.client.views.Menu;
import code.database.UserDTO;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class MainController {
	
	public final DatabaseServiceAsync databaseService = GWT
			.create(DatabaseService.class);
	
	private UserController userController;
	private IngredientController ingredientController;
	private ProductBatchController productBatchController;
	private ProductBatchCompController productBatchCompController;
	private ReceptController receptController;
	private IngredientBatchController ingredientBatchController;
	private UserDTO user;

	public MainController()
	{
		userController 			   = new UserController(this);
		ingredientController	   = new IngredientController(this);
		productBatchController 	   = new ProductBatchController(this);
		productBatchCompController = new ProductBatchCompController(this);
		receptController		   = new ReceptController(this);
		ingredientBatchController  = new IngredientBatchController(this);
		
		show(new LoginView(this, null));
	}
	
	public UserController getUserController()
	{
		return userController;
	}
	
	public IngredientController getIngredientController()
	{
		return ingredientController;
	}
	
	public IngredientBatchController getIngredientBatchController()
	{
		return ingredientBatchController;
	}
	
	public ProductBatchController getProductBatchController()
	{
		return productBatchController;
	}
	
	public ProductBatchCompController getProductBatchCompController()
	{
		return productBatchCompController;
	}
	
	public ReceptController getReceptController()
	{
		return receptController;
	}
	
	public void show(Widget widget)
	{
		RootPanel.get("main").clear();
		RootPanel.get("menu").clear();
		
		RootPanel.get("menu").add(new Menu(this));
		if (user != null)
		{
			RootPanel.get("main").add(widget);
		}
		else
		{
			RootPanel.get("main").add(new LoginView(this, null));
		}
	}
	
	public void setUser(UserDTO user)
	{
		this.user = user;
	}
	
	public UserDTO getUser()
	{
		return user;
	}
	
}
