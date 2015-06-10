package code.client.views;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import code.client.controllers.IngredientController;
import code.client.controllers.MainController;
import code.database.DALException;
import code.database.IngredientDTO;

import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ListIngredientsView extends Composite 
{

	IngredientController ingCon;
	MainController mc;
	VerticalPanel vPanel;
	Label NameLabel  	 = new Label("ingredient Name");
	Label IdLabel 		 = new Label("ingredint Id");
	Label leverandoer	 = new Label("leverandoer");

	TextBox NameBox, IdBox, leverandoerBox;

	Grid tabel;
	FlexTable flexTabel;

	ArrayList<IngredientDTO> ingls;

	public ListIngredientsView(MainController mc, ArrayList<IngredientDTO> ingls)
	{
		this.mc = mc;
		this.vPanel = new VerticalPanel();
		initWidget(this.vPanel);
		this.ingls = ingls;

		this.tabel = new Grid(1, 4);
		
		flexTabel = new FlexTable();


		tabel.setWidget(0, 0, NameLabel);
		tabel.setWidget(0, 1, IdLabel);
		tabel.setWidget(0, 2, leverandoer);
		tabel.setWidget(0, 3, new Label());
		
		
		vPanel.add(tabel);


//
//		for (int i = 0; i < ingls.size(); i++ ) {
//
//			flexTabel.setWidget(i+1, 0, new Label(ingls.get(i).getIngredientId()   + ""));
//			flexTabel.setWidget(i+1, 1, new Label(ingls.get(i).getIngredientName() + ""));
//			flexTabel.setWidget(i+1, 2, new Label(ingls.get(i).getLeverandoer()    + ""));
//			flexTabel.setWidget(i+1, 3, new Anchor("rediger"));
//			
//		}
//		
//		
//		vPanel.add(flexTabel);
	}

}
