package code.client.views;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import code.client.controllers.IngredientController;
import code.client.controllers.MainController;
import code.database.DALException;
import code.database.IngredientDTO;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class ListIngredientsView extends Composite 
{

//	IngredientController ingCon;
	MainController mc;
	VerticalPanel vPanel;
	Label NameLabel  	 = new Label("ingredient Name");
	Label IdLabel 		 = new Label("ingredint Id");
	Label leverandoer	 = new Label("leverandoer");
	Anchor edit;
	
	TextBox NameBox, IdBox, leverandoerBox;

	FlexTable flexTabel;

	ArrayList<IngredientDTO> ingls;

	public ListIngredientsView(MainController mc)
	{
		this.mc = mc;
		this.vPanel = new VerticalPanel();
		initWidget(this.vPanel);
		this.ingls = ingls;
		
		flexTabel = new FlexTable();
		

		flexTabel.setWidget(0, 0, NameLabel);
		flexTabel.setWidget(0, 1, IdLabel);
		flexTabel.setWidget(0, 2, leverandoer);
		flexTabel.setWidget(0, 3, new Label(";)"));

		mc.databaseService.ingredients_table_list(new AsyncCallback<ArrayList<IngredientDTO>>() {
			
			@Override
			public void onSuccess(ArrayList<IngredientDTO> result) {
				for (int i = 0; i<result.size(); i++) {

					flexTabel.setText(i+1, 0, result.get(i).getIngredientId()  + "");
					flexTabel.setText(i+1, 1, result.get(i).getIngredientName() + "");
					flexTabel.setText(i+1, 2, result.get(i).getLeverandoer()    + "");
					flexTabel.setWidget(i+1, 3, edit = new Anchor("rediger"));
				}				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("fejl");
				
				
			}
		});
		
		
		vPanel.add(flexTabel);
	}

}




























