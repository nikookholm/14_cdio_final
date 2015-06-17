package code.client.views;

import code.client.controllers.MainController;
import code.database.IngredientDTO;
import code.shared.FieldVerifier;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class CreateIngredientView extends Composite{

	private VerticalPanel vPanel;

	boolean leverandoerCheck	 = false;
	boolean ingredientNameCheck  = false;
	boolean ingredientIdCheck    = false;

	private MainController mc;
	
	private TextBox leverandoerBox    = new TextBox();      
	private TextBox ingredientNameBox = new TextBox();    
	private TextBox ingredientIdBox   = new TextBox(); 	  

	private Label createIng			  = new Label("Opret ny råvare");
	private Label leverandoerLabel    = new Label("Leverandør");
	private Label ingredientNameLabel = new Label("Navn");
	private Label ingredientIdLabel   = new Label("Id");

	private Button OkButton     = new Button("OK");
	private Button cancelButton = new Button("Annullér");

	FlexTable ft = new FlexTable();

	IngredientDTO createdIng;

	public  CreateIngredientView(final MainController mc, IngredientDTO createdIng){

		this.createdIng = createdIng;
		this.mc = mc;
		this.vPanel = new VerticalPanel();

		initWidget(vPanel);
		
		vPanel.add(createIng);
		createIng.setStyleName("caption");

		if(createdIng!= null){
			vPanel.add(new Label(createdIng.getIngredientName() + " blev oprettet"));
		}

		ft.setWidget(0, 0, ingredientIdLabel);
		ingredientIdLabel.setStyleName("input-text");
		ft.setWidget(0, 1, ingredientIdBox);
		
		ft.setWidget(1, 0, ingredientNameLabel);
		ingredientNameLabel.setStyleName("input-text");
		ft.setWidget(1, 1, ingredientNameBox);
		
		ft.setWidget(2, 0, leverandoerLabel);
		leverandoerLabel.setStyleName("input-text");
		ft.setWidget(2, 1, leverandoerBox);

		HorizontalPanel hBtnPanle = new HorizontalPanel();
		hBtnPanle.add(cancelButton);
		hBtnPanle.add(OkButton);
		ft.setWidget(3, 0, hBtnPanle);
		ft.getFlexCellFormatter().setColSpan(3, 0, 2);
		ft.getFlexCellFormatter().setAlignment(3, 0, HasHorizontalAlignment.ALIGN_RIGHT, HasVerticalAlignment.ALIGN_MIDDLE);
		
		OkButton.setEnabled(false);
		OkButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				IngredientDTO ingredDTO = new IngredientDTO(Integer.parseInt( ingredientIdBox.getText()), ingredientNameBox.getText(), leverandoerBox.getText());
				mc.show(mc.getIngredientController().createIngredient(ingredDTO));
			}
		});

		cancelButton.setEnabled(true);
		cancelButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				mc.show(new MainView(mc));
			}
		});
		
		vPanel.add(ft);

		ingredientIdBox.addKeyUpHandler(new ingredientId());
		ingredientNameBox.addKeyUpHandler(new ingredientName());
		leverandoerBox.addKeyUpHandler(new leverandoer());
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
	
}
