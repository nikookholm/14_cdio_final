package code.client.views;

import java.util.ArrayList;

import code.client.controllers.MainController;
import code.database.IngredientDTO;
import code.shared.FieldVerifier;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ListIngredientsView extends Composite 
{

	MainController mc;
	ArrayList<IngredientDTO> ingls;

	VerticalPanel vPanel;
	Label nameLabel  	 = new Label("Navn");
	Label IdLabel 		 = new Label("Id");
	Label leverandoer	 = new Label("Leverandør");
	Label info			 = new Label("Råvarer");
	Button back			 = new Button("Tilbage");
	Anchor edit 		 = new Anchor("Rediger");
	Anchor cancel		 = new Anchor("Annullér");
	Anchor ok;

	TextBox NameBox, IdBox, leverandoerBox;
	TextBox selectedNameBox     = new TextBox();
	TextBox selectedLeverandoer = new TextBox();

	boolean selectedNameCheck        = false;
	boolean selectedLeverandoerCheck = false;

	FlexTable flexTable;
	Grid subT  = new Grid(1,1);

	public ListIngredientsView(ArrayList<IngredientDTO> ingls, final MainController mc)
	{
		this.mc = mc;
		this.ingls = ingls;
		this.vPanel = new VerticalPanel();

		vPanel.add(info);
		info.setStyleName("caption");

		flexTable = new FlexTable();
		flexTable.setWidget(0, 0, IdLabel);
		flexTable.setWidget(0, 1, nameLabel);
		flexTable.setWidget(0, 2, leverandoer);
		IdLabel.setStyleName("input-text");
		nameLabel.setStyleName("input-text");
		leverandoer.setStyleName("input-text");
		flexTable.getCellFormatter().setWidth(0, 0, "250px;");
		flexTable.getCellFormatter().setWidth(0, 1, "250px;");
		flexTable.getCellFormatter().setWidth(0, 2, "250px;");

		int i = 0;
		for(IngredientDTO igr : ingls){
			i++;
			flexTable.setText(i+1, 0, "" + igr.getIngredientId());
			flexTable.setText(i+1, 1, "" + igr.getIngredientName());
			flexTable.setText(i+1, 2, "" + igr.getLeverandoer());

			Anchor newEdit = new Anchor("Rediger");
			newEdit.addClickHandler(new EditButtonHandler());
			flexTable.setWidget(i+1, 3, newEdit);

		}				

		HorizontalPanel hPanel = new HorizontalPanel();

		hPanel.add(back);

		subT.setWidget(0, 0, back);

		vPanel.add(flexTable);
		vPanel.add(back);
		vPanel.setCellHorizontalAlignment(back, HasHorizontalAlignment.ALIGN_CENTER);
		initWidget(this.vPanel);

		back.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				mc.show(new MainView(mc));

			}
		});
	}

	private class EditButtonHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			if(cancel != null){
				cancel.fireEvent(new ClickEvent(){});}

			final int selectedRow = flexTable.getCellForEvent(event).getRowIndex();
			final Anchor ok =   new Anchor("OK");

			selectedNameBox.setText(flexTable.getText(selectedRow, 1));
			selectedLeverandoer.setText(flexTable.getText(selectedRow, 2));

			flexTable.setWidget(selectedRow, 1, selectedNameBox);
			flexTable.setWidget(selectedRow, 2, selectedLeverandoer);
			flexTable.setWidget(selectedRow, 3, cancel);
			flexTable.setWidget(selectedRow, 4, ok);

			final selName sN = new selName();
			selectedNameBox.addKeyUpHandler(sN);

			final selLeverandoer sL = new selLeverandoer();
			selectedLeverandoer.addKeyUpHandler(sL);

			final String ingName = selectedNameBox.getText();
			final String leverandoer = selectedLeverandoer.getText();
			final int ingredientId = Integer.parseInt(flexTable.getText(selectedRow, 0));

			Anchor newCancel = new Anchor("Annullér");

			newCancel = cancel;
			newCancel.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					IngredientDTO updateIng = new IngredientDTO(ingredientId, ingName, leverandoer);
					mc.getIngredientController().updateIngredient(updateIng);
				}			
			});

			ok.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					if(!selectedNameCheck && !selectedLeverandoerCheck){

						selectedNameBox.setText(ingName);
						selectedLeverandoer.setText(leverandoer);
						sL.onKeyUp(null);
						sN.onKeyUp(null);
					}
					else  {

						IngredientDTO updIng = new IngredientDTO(ingredientId, selectedNameBox.getText(), selectedLeverandoer.getText());
						mc.getIngredientController().updateIngredient(updIng);					
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
				}
			}
		}
	}
}

