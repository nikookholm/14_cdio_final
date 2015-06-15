package code.client.views;

import java.awt.image.TileObserver;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import code.client.controllers.IngredientController;
import code.client.controllers.MainController;
import code.database.DALException;
import code.database.IngredientDTO;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.AttachEvent.Handler;
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
	Label back 			 = new Label("Tilbage");
	
	Anchor edit 		 = new Anchor("rediger");
	Anchor cancel		 = new Anchor("cancel");
	Anchor ok			 = new Anchor("ok");
	
	TextBox NameBox, IdBox, leverandoerBox;

	FlexTable flexTabel;

	ArrayList<IngredientDTO> ingls;

	public ListIngredientsView(final MainController mc)
	{
		this.mc = mc;
		this.vPanel = new VerticalPanel();
		initWidget(this.vPanel);

		flexTabel = new FlexTable();

		flexTabel.setWidget(0, 0, NameLabel);
		flexTabel.setWidget(0, 1, IdLabel);
		flexTabel.setWidget(0, 2, leverandoer);
		flexTabel.setWidget(0, 3, back);

		mc.databaseService.ingredients_table_list(new AsyncCallback<ArrayList<IngredientDTO>>() {

			@Override
			public void onSuccess(ArrayList<IngredientDTO> result) {
				for (int i = 0; i<result.size(); i++) {

					flexTabel.setText(i+1, 0, result.get(i).getIngredientId()  + "");
					flexTabel.setText(i+1, 1, result.get(i).getIngredientName() + "");
					flexTabel.setText(i+1, 2, result.get(i).getLeverandoer()    + "");
					flexTabel.setWidget(i+1, 3, edit);
					
					edit.addClickHandler(new EditButtonHandler());
				}				
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("fejl");
			}
		});

		back.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				mc.show(new MainView(mc));
			}
		});

		vPanel.add(flexTabel);

	}
	
	public class EditButtonHandler implements ClickHandler{

		TextBox selectedIdBox 	     = new TextBox();
		TextBox selectedNameBox     = new TextBox();
		TextBox selectedLeverandoer = new TextBox();

		@Override
		public void onClick(ClickEvent event) {

			final int selectedRow = flexTabel.getCellForEvent(event).getRowIndex();

			selectedNameBox.setText(flexTabel.getText(selectedRow, 1));
			selectedLeverandoer.setText(flexTabel.getText(selectedRow, 2));
			
			flexTabel.setWidget(selectedRow, 1, selectedNameBox);
			flexTabel.setWidget(selectedRow, 2, selectedLeverandoer);
			flexTabel.setWidget(selectedRow, 3, cancel);
			flexTabel.setWidget(selectedRow, 4, ok);
			
			final String ingName = selectedNameBox.getText();
			final String leverandoer = selectedLeverandoer.getText();
			
			cancel.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					
				flexTabel.setText(selectedRow, 1, ingName);	
				flexTabel.setText(selectedRow, 2, leverandoer);
				}
			});
			
			
		}
		
	


	}
}



























