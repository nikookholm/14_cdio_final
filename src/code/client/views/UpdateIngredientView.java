package code.client.views;

import code.client.controllers.MainController;
import code.database.IngredientDTO;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class UpdateIngredientView {
	
	MainController mc;
	IngredientDTO createdIng;
	VerticalPanel vPanel;
	
	public UpdateIngredientView(MainController mc, IngredientDTO createdIng){
		
		this.createdIng = createdIng;
		this.mc = mc;
		this.vPanel = new VerticalPanel();
		
		FlexTable ft = new FlexTable();
		
		ft.getFlexCellFormatter().setColSpan(1, 0, 3);
		
		
		ft.setText(0, 0, "ingredients Id");
		ft.setText(0, 1, "ingredients navn");
		ft.setText(0, 2, "leverandoer");
		
		
		RootPanel.get().add(ft);
		
		
	}


}
