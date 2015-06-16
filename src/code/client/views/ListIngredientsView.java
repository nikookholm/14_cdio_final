package code.client.views;

import java.awt.image.TileObserver;
import java.beans.FeatureDescriptor;
import java.lang.reflect.Array;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;

import code.client.controllers.IngredientController;
import code.client.controllers.MainController;
import code.database.DALException;
import code.database.IngredientDTO;
import code.shared.FieldVerifier;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.AttachEvent.Handler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
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
	Label NameLabel  	 = new Label("Name");
	Label IdLabel 		 = new Label("Id");
	Label leverandoer	 = new Label("leverandoer");
	Label back 			 = new Label("Tilbage");
	
	Anchor edit 		 = new Anchor("rediger");
	Anchor cancel		 = new Anchor("cancel");
	Anchor ok			;
	
	TextBox NameBox, IdBox, leverandoerBox;
	TextBox selectedNameBox     = new TextBox();
	TextBox selectedLeverandoer = new TextBox();
	
	boolean selectedNameCheck        = false;
	boolean selectedLeverandoerCheck = false;

	FlexTable flexTabel;

	ArrayList<IngredientDTO> ingls;

	public ListIngredientsView(final MainController mc)
	{
		this.mc = mc;
		this.vPanel = new VerticalPanel();
		initWidget(this.vPanel);

		flexTabel = new FlexTable();
		
		flexTabel.setWidget(0, 0, IdLabel);
		flexTabel.setWidget(0, 1, NameLabel);
		flexTabel.setWidget(0, 2, leverandoer);

		mc.databaseService.ingredients_table_list(new AsyncCallback<ArrayList<IngredientDTO>>() {

			@Override
			public void onSuccess(ArrayList<IngredientDTO> result) {
				for (int i = 0; i<result.size(); i++) {

					flexTabel.setText  (i+1, 0, result.get(i).getIngredientId()  + "");
					flexTabel.setText  (i+1, 1, result.get(i).getIngredientName() + "");
					flexTabel.setText  (i+1, 2, result.get(i).getLeverandoer()    + "");
					Anchor newEdit = new Anchor("Rediger");
					newEdit.addClickHandler(new EditButtonHandler());
					flexTabel.setWidget(i+1, 3, newEdit);
					
				}				
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("fejl");
			}
		});


		vPanel.add(flexTabel);

	}
	
	private class EditButtonHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			
		
			if(cancel != null){
				cancel.fireEvent(new ClickEvent(){});}

			final int selectedRow = flexTabel.getCellForEvent(event).getRowIndex();
			final Anchor ok =   new Anchor("ok");

			selectedNameBox.setText(flexTabel.getText(selectedRow, 1));
			selectedLeverandoer.setText(flexTabel.getText(selectedRow, 2));
			
			flexTabel.setWidget(selectedRow, 1, selectedNameBox);
			flexTabel.setWidget(selectedRow, 2, selectedLeverandoer);
			flexTabel.setWidget(selectedRow, 3, cancel);
			flexTabel.setWidget(selectedRow, 4, ok);
						
			selectedNameBox.addKeyUpHandler(new selName());
			selectedLeverandoer.addKeyUpHandler(new selLeverandoer());
			
			final String ingName = selectedNameBox.getText();
			final String leverandoer = selectedLeverandoer.getText();
			final int ingredientId = Integer.parseInt(flexTabel.getText(selectedRow, 0));
			
			Anchor newCancel = new Anchor("cansel");
			
			newCancel = cancel;
			
			newCancel.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					IngredientDTO updateIng = new IngredientDTO(ingredientId, ingName, leverandoer);
					mc.getIngredientController().updateIngredient(updateIng);

					
				}			
			});

//			ok.setEnabled(false);
			ok.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					if(selectedNameCheck && selectedNameCheck){
						
					IngredientDTO updateIng = new IngredientDTO(ingredientId, selectedNameBox.getText(), selectedLeverandoer.getText());
					mc.getIngredientController().updateIngredient(updateIng);
				}
					else {

						
						selectedNameBox.setText(ingName);
						selectedLeverandoer.setText(leverandoer);
						
						
					}
			}
			});
			
		}
	
		
		private class selName implements KeyUpHandler{

			@Override
			public void onKeyUp(KeyUpEvent event) {
				
				if(!FieldVerifier.ingredientName(selectedNameBox.getText())){
					selectedNameBox.setStyleName("gwt-TextBox-invalidEntry");
					 selectedNameCheck = false;
				}
					else{
						selectedNameBox.removeStyleName("gwt-TextBox-invalidEntry");
						selectedNameCheck = true;
//						okBtnEnabler();
					}
				}	
		}
		
		private class selLeverandoer implements KeyUpHandler{

			@Override
			public void onKeyUp(KeyUpEvent event) {
				if(!FieldVerifier.leverandoerValid(selectedLeverandoer.getText())){
					selectedLeverandoer.setStyleName("gwt-TextBox-invalidEntry");
					selectedLeverandoerCheck = false;
				}
				else{
					selectedLeverandoer.removeStyleName("gwt-TextBox-invalidEntry");
					selectedLeverandoerCheck = true;
//					okBtnEnabler();
				}
			}
		}
		
//		public void linkHandler(){
//
//			if(selectedNameCheck && selectedLeverandoerCheck){
//				
//				ok.setEnabled(true);
//			}
//			else ok.setEnabled(false);
//		}
		
	}
	
	
}



























