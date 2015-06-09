package code.client.views;

import code.client.controllers.MainController;
import code.shared.FieldVerifier;

import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;


public class CreateIngredientView extends Composite{

	private VerticalPanel vPanel;

	boolean leverandoerCheck	 = false;
	boolean ingredientNameCheck  = false;
	boolean ingredientIdCheck    = false;

	private MainController mc;
	private TextBox leverandoerBox       = new TextBox();    //leverandoer,
	private TextBox ingredientNameBox = new TextBox();   	//raavare_navn,
	TextBox ingredientIdBox;   							   //raavare_Id;

	private Label leverandoerLabel       = new Label("leverandoer");
	private Label ingredientNameLabel = new Label("ingredient name");
	private Label ingredientIdLabel   = new Label("ingredient Id");

	private Button OkButton     = new Button("Ok");
	private Button cancelButton = new Button("cancel");
	private Button clearButton  = new Button("clear");

	Grid tabel, subTable;



	public  CreateIngredientView(final MainController mc){

		this.mc = mc;
		this.vPanel = new VerticalPanel();
		initWidget(vPanel);

		this.tabel = new Grid(4,2);

		tabel.setWidget(0, 0, leverandoerLabel);
		tabel.setWidget(0, 1, leverandoerBox);
		tabel.setWidget(1, 0, ingredientNameLabel);
		tabel.setWidget(1, 1, ingredientNameBox);
		tabel.setWidget(2, 0, ingredientIdLabel);
		tabel.setWidget(2, 1, ingredientIdBox   = new TextBox());
		tabel.setWidget(3, 0, cancelButton);
		tabel.setWidget(3, 1, OkButton);
		OkButton.setEnabled(false);

		this.subTable = new Grid(1,3);

		subTable.setWidget(0, 0, cancelButton);
		subTable.setWidget(0, 1, clearButton);
		subTable.setWidget(0, 2, OkButton);


		vPanel.add(tabel);
		vPanel.add(subTable);

		ingredientIdBox.addKeyUpHandler(new ingredientId());
		ingredientNameBox.addKeyUpHandler(new ingredientName());
		leverandoerBox.addKeyUpHandler(new leverandoer());

		vPanel.add(OkButton);
	}

	private class ingredientId implements KeyUpHandler{

		@Override
		public void onKeyUp(KeyUpEvent event) {
			if(!FieldVerifier.ingredientId(ingredientIdBox.getText())){
				ingredientIdBox.setStyleName("gwt-TextBox-invalidEntry");
				ingredientIdCheck = false;

			}
			else{
				ingredientIdBox.removeStyleName("gwt-TextBox-invalidEntry");
				ingredientIdCheck = true;
			}

		}

		


	}
	private class ingredientName implements KeyUpHandler{

		@Override
		public void onKeyUp(KeyUpEvent event) {
			if(!FieldVerifier.ingredientName(ingredientNameBox.getText())){
				ingredientNameBox.setStyleName("gwt-TextBox-invalidEntry");
				ingredientNameCheck = false;

			}
			else{
				ingredientNameBox.setStyleName("gwt-TextBox-invalidEntry");
				ingredientNameCheck = true;

			}
		}

	}
	private class leverandoer implements KeyUpHandler{

		@Override
		public void onKeyUp(KeyUpEvent event) {
			if(!FieldVerifier.leverandoerValid(leverandoerBox.getText())){
				leverandoerBox.setStyleName("gwt-TextBox-invalidEntry");
				leverandoerCheck = false;
			}
			else{
				leverandoerBox.setStyleName("gwt-TextBox-invalidEnrtry");
				leverandoerCheck = true;
			}
		}

	}
	public void okButtonEnabler(){
		if(ingredientIdCheck && ingredientNameCheck && leverandoerCheck){
			OkButton.setEnabled(true);
		}
		else OkButton.setEnabled(false);

	}


}









































