package code.client.controllers;

import code.client.DatabaseService;
import code.client.DatabaseServiceAsync;
import code.client.views.LoginView;
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
	private UserDTO user;

	public MainController()
	{
		userController 			   = new UserController(this);
		ingredientController	   = new IngredientController(this);
		productBatchController 	   = new ProductBatchController(this);
		productBatchCompController = new ProductBatchCompController(this);
		receptController		   = new ReceptController(this);
		
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
		if (user == null) widget = new LoginView(this);
		RootPanel.get("main").add(widget);
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
