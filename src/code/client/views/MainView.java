package code.client.views;

import code.client.controllers.MainController;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
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
		Anchor addIngredient		= new Anchor("Opret ny ingrediens");
		Anchor updateIngredient		= new Anchor("Se og rediger ingredienser");
		Anchor addProductBatch		= new Anchor("Opret ny produkt batch");
		Anchor listProductBatch		= new Anchor("Se alle produkt batches");
		Anchor listProductBatchComp = new Anchor("Se alle produkt batch komponenter");
		Anchor addRecept			= new Anchor("Opret recept");
		Anchor listRecepts			= new Anchor("Se alle recepter");
		
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
				//mc.show(mc.getReceptController().listRecepts());				
			}
		};
		
		panel.add(new Label("Brugeradministration"));
		addUser.addClickHandler(addUserHandler);
		panel.add(addUser);
		updateUser.addClickHandler(updateUserHandler);
		panel.add(updateUser);
		
		panel.add(new Label("Råvare/ingredienser"));
		addIngredient.addClickHandler(addIngredientHandler);
		panel.add(addIngredient);
		updateIngredient.addClickHandler(updateIngredientHandler);
		panel.add(updateIngredient);
		
		panel.add(new Label("Produkt batches"));
		addProductBatch.addClickHandler(addProductBatchHandler);
		panel.add(addProductBatch);
		listProductBatch.addClickHandler(listProductBatchHandler);
		panel.add(listProductBatch);
		
		panel.add(new Label("Produkt batch komponenter"));
		listProductBatchComp.addClickHandler(listProductBatchCompHandler);
		panel.add(listProductBatchComp);
		
		panel.add(new Label("Recepter"));
		addRecept.addClickHandler(addReceptHandler);
		panel.add(addProductBatch);
		listRecepts.addClickHandler(listReceptsHandler);
		panel.add(listProductBatch);
		
		initWidget(panel);
		
	}
	
	
}
