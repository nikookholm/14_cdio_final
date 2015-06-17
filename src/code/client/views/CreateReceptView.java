	package code.client.views;

import code.client.controllers.MainController;
import code.client.controllers.ReceptController;
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
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class CreateReceptView extends Composite
{
	VerticalPanel vPanel;
	FlexTable table;
	HorizontalPanel hPanel;
	
	TextBox receptNameBox	= new TextBox();
	TextBox	receptIdBox		= new TextBox();	
	Label infoLabel			= new Label("Opret ny recept");
	Label viewInfo			= new Label("Indtast receptens oplysninger: ");
	Label receptNameLabel 	= new Label("Recept navn");
	Label receptIdLabel  	= new Label("Recept ID");
	Button okButton    		= new Button("OK");
	Button cancelButton 	= new Button("Annullér");
	
//	ReceptController receptC;
	ReceptDTO receptDTO;
	MainController mc;
	
	boolean receptNameCheck	= false;
	boolean receptIdCheck	= false;
	
	public CreateReceptView(ReceptDTO receptDTO, final MainController mc)
	{
		this.receptDTO 	= receptDTO;
		this.mc 		= mc;
		vPanel			= new VerticalPanel();
		
		receptNameBox.setFocus(true);
		cancelButton.setEnabled(true);
		okButton.setEnabled(false);
		
		if(receptDTO != null)
		{
			vPanel.add(new Label(receptDTO.getReceptName() + " blev oprettet"));
		}
		
		table = new FlexTable();
		
		vPanel.add(infoLabel);
		vPanel.add(viewInfo);
		infoLabel.setStyleName("caption");
		viewInfo.setStyleName("input-text");
		
		table.setWidget(0, 0, receptNameLabel);
		table.setWidget(0, 1, receptNameBox);
		table.setWidget(1, 0, receptIdLabel);
		table.setWidget(1, 1, receptIdBox);
		receptIdLabel.setStyleName("input-text");
		receptNameLabel.setStyleName("input-text");
		table.setWidget(2, 0, hPanel = new HorizontalPanel());
		//table.getFlexCellFormatter().setColSpan(2, 0, );
		
		hPanel.add(okButton);
		hPanel.add(cancelButton);
		hPanel.setCellHorizontalAlignment(hPanel, HasHorizontalAlignment.ALIGN_RIGHT);
		
		vPanel.add(table);
		vPanel.add(hPanel);
		initWidget(this.vPanel);
		
		receptIdBox.addKeyUpHandler(new receptId());
		receptNameBox.addKeyUpHandler(new receptName());

		okButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				ReceptDTO receptDTO = new ReceptDTO(receptNameBox.getText(), Integer.parseInt(receptIdBox.getText()));
				mc.show(mc.getReceptController().createRecept(receptDTO));
			}
		});
	
		cancelButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				mc.show(new MainView(mc));
			}
		});
	}
	
	private class receptId implements KeyUpHandler
	{
		@Override
		public void onKeyUp(KeyUpEvent event)
		{
			if(!FieldVerifier.isReceptNoValid(receptIdBox.getText()))
			{
				receptIdBox.setStyleName("gwt-Textbox-invalidEntry");
				receptIdCheck = false;
			}
			else{
				receptIdBox.removeStyleName("gwt-TextBox-invalidEntry");
				receptIdCheck = true;
				okButtonEnabler();
			}
		}
	}
	
	private class receptName implements KeyUpHandler
	{
		@Override
		public void onKeyUp(KeyUpEvent event)
		{
			if(!FieldVerifier.isValidName(receptNameBox.getText()))
			{
				receptNameBox.setStyleName("gwt-Textbox-invalidEntry");
				receptNameCheck = false;
			}
			else{
				receptNameBox.removeStyleName("gwt-TextBox-invalidEntry");
				receptNameCheck = true;
				okButtonEnabler();
			}
		}
	}
	
	public void okButtonEnabler(){
		if(receptIdCheck && receptNameCheck){
			okButton.setEnabled(true);
		}
		else okButton.setEnabled(false);

	}
	public void cancelButtonEnable(){
		if(receptIdCheck && receptNameCheck){
			okButton.setEnabled(true);

		}
		else okButton.setEnabled(true);
	}
}
