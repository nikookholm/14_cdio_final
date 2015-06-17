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
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class CreateIngredientBatchView extends Composite
{
	IngredientBatchController ingrBatch ;
	IngredientBatchDTO ingrBatchDTO;
	MainController mc;

	VerticalPanel vPanel;
	FlexTable table;
	HorizontalPanel hPanel;
	
	TextBox rbIdBox 		= new TextBox();    	//rb_id
	TextBox ingredientIdBox = new TextBox(); 		//ingredient_id
	TextBox maengdeBox  	= new TextBox();		//maengde
	Label infoLabel			= new Label("Opret ny råvarebatch");
	Label viewInfo			= new Label("Indtast råvarebatchens oplysninger: ");
	Label ibIdLabel 		= new Label("RåvareBatch ID");
	Label ingredientIdLabel	= new Label("Råvare ID");
	Label maengdeLabel		= new Label("Mængde i kg");
	Button okButton    		= new Button("OK");
	Button cancelButton 	= new Button("Annullér");
	
	boolean rbIdCheck			= false;
	boolean ingredientIdCheck	= false;
	boolean maengdeCheck		= false;
	
	public CreateIngredientBatchView(IngredientBatchDTO ingrBatchDTO, final MainController mc)
	{
		this.ingrBatchDTO 	= ingrBatchDTO;
		this.mc				= mc;
		vPanel				= new VerticalPanel();
		
		rbIdBox.setFocus(true);
		cancelButton.setEnabled(true);
		okButton.setEnabled(false);
		
		if(ingrBatchDTO != null)
		{
			vPanel.add(new Label("Råvarebatchen med råvare ID'en: "+ ingrBatchDTO.getRbId() + "blev oprettet."));
		}
		
		table = new FlexTable();
		
		vPanel.add(infoLabel);
		vPanel.add(viewInfo);
		infoLabel.setStyleName("caption");
		viewInfo.setStyleName("input-text");
		
		table.setWidget(0, 0, ibIdLabel);
		table.setWidget(0, 1, rbIdBox);
		table.setWidget(1, 0, ingredientIdLabel);
		table.setWidget(1, 1, ingredientIdBox);
		table.setWidget(2, 0, maengdeLabel);
		table.setWidget(2, 1, maengdeBox);
		ibIdLabel.setStyleName("input-text");
		ingredientIdLabel.setStyleName("input-text");
		maengdeLabel.setStyleName("input-text");
		table.setWidget(3, 1, hPanel = new HorizontalPanel());
		table.getFlexCellFormatter().setColSpan(2, 0, 2);
		table.getCellFormatter().setAlignment(2, 0, HasHorizontalAlignment.ALIGN_RIGHT, HasVerticalAlignment.ALIGN_MIDDLE);
		
		hPanel.add(okButton);
		hPanel.add(cancelButton);
		hPanel.setCellHorizontalAlignment(hPanel, HasHorizontalAlignment.ALIGN_RIGHT);
		
		vPanel.add(table);
		vPanel.add(hPanel);
		initWidget(this.vPanel);
	
		rbIdBox.addKeyUpHandler(new rbId());
		ingredientIdBox.addKeyUpHandler(new ingredientId());
		maengdeBox.addKeyUpHandler(new maengde());
		
		okButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				IngredientBatchDTO ingrBatchDTO = new IngredientBatchDTO(Integer.parseInt(rbIdBox.getText()), Integer.parseInt(ingredientIdBox.getText()), Double.parseDouble(maengdeBox.getText()));
				mc.show(mc.getIngredientBatchController().createIngredientBatch(ingrBatchDTO));

			}
		});
		
		cancelButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				mc.show(new MainView(mc));
			}
		});
	}
	
	private class rbId implements KeyUpHandler
	{
		@Override
		public void onKeyUp(KeyUpEvent event)
		{
			if(!FieldVerifier.isrbNoValid(rbIdBox.getText()))
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
			okButton.setEnabled(true);
		}
		else okButton.setEnabled(false);

	}
	public void cancelButtonEnable(){
		if(rbIdCheck && ingredientIdCheck && maengdeCheck){
			okButton.setEnabled(true);

		}
		else okButton.setEnabled(true);
	}
}
