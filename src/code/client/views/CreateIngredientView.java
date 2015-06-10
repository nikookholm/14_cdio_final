package code.client.views;

import code.client.controllers.MainController;
import code.database.IngredientDTO;
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
	IngredientDTO createdIng;



	public  CreateIngredientView(final MainController mc, IngredientDTO createdIng){
		
		
		
		this.createdIng = createdIng;
		this.mc = mc;
		this.vPanel = new VerticalPanel();
		initWidget(vPanel);
		
		if(createdIng!= null){
			vPanel.add(new Label(createdIng.getIngredientName() + "er oprettet"));
			
		}

		this.tabel = new Grid(4,2);
		
//		tabel.setWidget(0, 0, new Label("Status")); // ud fra tabelen og skal vises nå iralevant
//		tabel.setWidget(0, 1, new TextBox());
		tabel.setWidget(1, 0, leverandoerLabel);
		tabel.setWidget(1, 1, leverandoerBox);
		tabel.setWidget(2, 0, ingredientNameLabel);
		tabel.setWidget(2, 1, ingredientNameBox);
		tabel.setWidget(3, 0, ingredientIdLabel);
		tabel.setWidget(3, 1, ingredientIdBox   = new TextBox());


		this.subTable = new Grid(1,2);

		subTable.setWidget(0, 0, cancelButton);
		subTable.setWidget(0, 1, OkButton);

		OkButton.setEnabled(false);
		cancelButton.setEnabled(true);

		vPanel.add(tabel);

		ingredientIdBox.addKeyUpHandler(new ingredientId());
		ingredientNameBox.addKeyUpHandler(new ingredientName());
		leverandoerBox.addKeyUpHandler(new leverandoer());

		vPanel.add(subTable);

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
				okButtonEnabler();
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
				ingredientNameBox.removeStyleName("gwt-TextBox-invalidEntry");
				ingredientNameCheck = true;
				okButtonEnabler();

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
				okButtonEnabler();
			}
		}

	}
	
	public void okButtonEnabler(){
		if(ingredientIdCheck && ingredientNameCheck && leverandoerCheck){
			OkButton.setEnabled(true);
		}
		else OkButton.setEnabled(false);

	}
	public void cancelButtonEnable(){
		
		mc.show(new MainView(mc));
		
	}


}









































