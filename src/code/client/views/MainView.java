package code.client.views;

import code.client.controllers.MainController;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class MainView extends Composite {
	
	private MainController mc;
	
	public MainView(final MainController mc)
	{
		this.mc = mc;
		VerticalPanel panel = new VerticalPanel();
		
		Anchor addUser				= new Anchor("Opret bruger");
		Anchor updateUser			= new Anchor("Se og rediger brugere");
		Anchor addIngredient		= new Anchor("Opret ny råvare");
		Anchor updateIngredient		= new Anchor("Se og rediger råvarer");
		Anchor addProductBatch		= new Anchor("Opret ny produktbatch");
		Anchor listProductBatch		= new Anchor("Se alle produktbatches");
		Anchor listProductBatchComp = new Anchor("Se alle produktbatch komponenter");
		Anchor addRecept			= new Anchor("Opret recept");
		Anchor listRecepts			= new Anchor("Se alle recepter");
		Anchor addIngredientBatch 	= new Anchor("Opret råvarebatch");
		Anchor listIngredientBatch 	= new Anchor("Se alle råvarebatches");
		
		Label userCaption 		  	  = new Label("Brugeradministration");
		Label ingredientCaption	  	  = new Label("Råvare/ingredienser");
		Label productBatchCaption	  = new Label("Produktbatches");
		Label productBatchCompCaption = new Label("Produktbatch komponenter");
		Label receptCaption		  	  = new Label("Recepter");
		Label ingredientBatchCaption  = new Label("Råvarebatches");
		
		ClickHandler addUserHandler = new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				mc.show(mc.getUserController().createUser(null));				
			}
		};
		
		ClickHandler updateUserHandler = new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				mc.show(mc.getUserController().listUsers());				
			}
		};
		
		ClickHandler addIngredientHandler = new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				mc.show(mc.getIngredientController().createIngredient(null));
			}
		};
		
		ClickHandler updateIngredientHandler = new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				mc.show(mc.getIngredientController().listIngredients());				
			}
		};
		
		ClickHandler addProductBatchHandler = new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				mc.show(mc.getProductBatchController().createProductBatch(null));				
			}
		};
		
		ClickHandler listProductBatchHandler = new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				mc.show(mc.getProductBatchController().listProductBatch());				
			}
		};
		
		ClickHandler listProductBatchCompHandler = new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				mc.show(mc.getProductBatchCompController().listProductBatchComp());				
			}
		};
		
		ClickHandler addReceptHandler = new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				mc.show(mc.getReceptController().createRecept(null));				
			}
		};
		
		ClickHandler listReceptsHandler = new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				mc.show(mc.getReceptController().listRecepts());				
			}
		};
		
		ClickHandler addIngredientBatchHandler = new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				mc.show(mc.getIngredientBatchController().createIngredientBatch(null));				
			}
		};
		
		ClickHandler listIngredientBatchHandler = new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				mc.show(mc.getIngredientBatchController().listIngredientBatch());				
			}
		};
		
		int role = mc.getUser().getRole();
		if (role < 5)
		{
			panel.add(productBatchCompCaption);
			productBatchCompCaption.setStyleName("caption");
			listProductBatchComp.addClickHandler(listProductBatchCompHandler);
			panel.add(listProductBatchComp);
			panel.add(new HTML("<br />"));
		}
		
		if (role < 4)
		{
			panel.add(ingredientBatchCaption);
			ingredientBatchCaption.setStyleName("caption");
			addIngredientBatch.addClickHandler(addIngredientBatchHandler);
			panel.add(addIngredientBatch);
			listIngredientBatch.addClickHandler(listIngredientBatchHandler);
			panel.add(listIngredientBatch);
			panel.add(new HTML("<br />"));
			
			panel.add(productBatchCaption);
			productBatchCaption.setStyleName("caption");
			addProductBatch.addClickHandler(addProductBatchHandler);
			panel.add(addProductBatch);
			listProductBatch.addClickHandler(listProductBatchHandler);
			panel.add(listProductBatch);
			panel.add(new HTML("<br />"));		
		}
		
		if (role < 3)
		{
			panel.add(ingredientCaption);
			ingredientCaption.setStyleName("caption");
			addIngredient.addClickHandler(addIngredientHandler);
			panel.add(addIngredient);
			updateIngredient.addClickHandler(updateIngredientHandler);
			panel.add(updateIngredient);
			panel.add(new HTML("<br />"));
			
			panel.add(receptCaption);
			receptCaption.setStyleName("caption");
			addRecept.addClickHandler(addReceptHandler);
			panel.add(addRecept);
			listRecepts.addClickHandler(listReceptsHandler);
			panel.add(listRecepts);
			panel.add(new HTML("<br />"));	
		}
		
		if (role < 2)
		{
			panel.add(userCaption);
			userCaption.setStyleName("caption");
			addUser.addClickHandler(addUserHandler);
			panel.add(addUser);
			updateUser.addClickHandler(updateUserHandler);
			panel.add(updateUser);
			panel.add(new HTML("<br />"));
		}
			

		initWidget(panel);
		
	}
	
	
}
