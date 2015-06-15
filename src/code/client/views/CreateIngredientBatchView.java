package code.client.views;

import code.client.controllers.IngredientBatchController;
import code.client.controllers.MainController;
import code.database.IngredientBatchDTO;
import code.database.ReceptDTO;
import code.shared.FieldVerifier; 

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class CreateIngredientBatchView extends Composite
{
	IngredientBatchController ingrBatch ;
	IngredientBatchDTO ingrBatchDTO;
	MainController mc;

	VerticalPanel vPanel;
	Grid table, subTable;
	boolean rbIdCheck			= false;
	boolean ingredientIdCheck	= false;
	boolean maengdeCheck		= false;
	
	private TextBox rbIdBox 		= new TextBox();    	//rb_id
	private TextBox ingredientIdBox = new TextBox(); 		//ingredient_id
	private TextBox maengdeBox  	= new TextBox();		//maengde
	
	private Label rbIdLabel 		= new Label("råvarebatch_id");
	private Label ingredientIdLabel	= new Label("råvare_id");
	private Label maengdeLabel		= new Label("maengde");
	private Button OkButton    		= new Button("Ok");
	private Button cancelButton 	= new Button("cancel");
	
	public CreateIngredientBatchView(final MainController mc, IngredientBatchDTO ingrBatchDTO)
	{
		this.ingrBatchDTO 	= ingrBatchDTO;
		this.mc				= mc;
		this.vPanel			= new VerticalPanel();
		
		initWidget(vPanel);
		
		if(ingrBatchDTO != null)
		{
			vPanel.add(new Label());
		}
		
		this.table = new Grid(3,3);
		
		table.setWidget(0, 0, rbIdLabel);
		table.setWidget(0, 1, rbIdBox);
		table.setWidget(1, 0, ingredientIdLabel);
		table.setWidget(1, 1, ingredientIdBox);
		table.setWidget(2, 0, maengdeLabel);
		table.setWidget(2, 1, maengdeBox);
		
		this.subTable = new Grid(1,2);
		
		subTable.setWidget(0, 0, cancelButton);
		subTable.setWidget(0, 1, OkButton);
	
		OkButton.setEnabled(false);
		OkButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				IngredientBatchDTO ingrBatchDTO = new IngredientBatchDTO(Integer.parseInt(rbIdBox.getText()), Integer.parseInt(ingredientIdBox.getText()), Double.parseDouble(maengdeBox.getText()));
				mc.show(mc.getIngredientBatchController().createIngredientBatch(ingrBatchDTO));

			}
		});
		
		cancelButton.setEnabled(true);
		cancelButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				mc.show(new MainView(mc));
			}
		});
		
		vPanel.add(table);

		rbIdBox.addKeyUpHandler(new rbId());
		ingredientIdBox.addKeyUpHandler(new ingredientId());
		maengdeBox.addKeyUpHandler(new maengde());

		vPanel.add(subTable);
	}
	
	private class rbId implements KeyUpHandler
	{
		@Override
		public void onKeyUp(KeyUpEvent event)
		{
			if(!FieldVerifier.ispbNoValid(rbIdBox.getText()))
			{
				rbIdBox.setStyleName("gwt-Textbox-invalidEntry");
				rbIdCheck = false;
			}
			else{
				rbIdBox.removeStyleName("gwt-TextBox-invalidEntry");
				rbIdCheck = true;
				okButtonEnabler();
			}
		}
	}
	
	private class ingredientId implements KeyUpHandler
	{
		@Override
		public void onKeyUp(KeyUpEvent event)
		{
			if(!FieldVerifier.ingredientId(ingredientIdBox.getText()))
			{
				ingredientIdBox.setStyleName("gwt-Textbox-invalidEntry");
				ingredientIdCheck = false;
			}
			else{
				ingredientIdBox.removeStyleName("gwt-TextBox-invalidEntry");
				ingredientIdCheck = true;
				okButtonEnabler();
			}
		}
	}
	
	private class maengde implements KeyUpHandler
	{
		@Override
		public void onKeyUp(KeyUpEvent event)
		{
			if(!FieldVerifier.isValidName(maengdeBox.getText()))
			{
				maengdeBox.setStyleName("gwt-Textbox-invalidEntry");
				maengdeCheck = false;
			}
			else{
				maengdeBox.removeStyleName("gwt-TextBox-invalidEntry");
				maengdeCheck = true;
				okButtonEnabler();
			}
		}
	}
	
	public void okButtonEnabler(){
		if(rbIdCheck && ingredientIdCheck && maengdeCheck){
			OkButton.setEnabled(true);
		}
		else OkButton.setEnabled(false);

	}
	public void cancelButtonEnable(){
		if(rbIdCheck && ingredientIdCheck && maengdeCheck){
			OkButton.setEnabled(true);

		}
		else OkButton.setEnabled(true);
	}
}
