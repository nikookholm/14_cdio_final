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
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class CreateReceptView extends Composite
{
	VerticalPanel vPanel;
	TextBox receptNameBox, receptIdBox;;//receptnavn og receptid
	Button okButton     = new Button("OK");
	Button cancelButton = new Button("Cancel");
	Grid table;
	Label viewInfo, presentSuccess;
	Label receptNameLabel = new Label("recept name");
	Label receptIdLabel   = new Label("recept id");
	HorizontalPanel hPanel;
	
//	ReceptController receptC;
	ReceptDTO receptDTO;
	MainController mc;
	
	boolean receptNameCheck	= false;
	boolean receptIdCheck	= false;
	
	public CreateReceptView(final ReceptDTO receptDTO, final MainController mc)
	{
		this.receptDTO 	= receptDTO;
		this.mc 		= mc;
		this.vPanel		= new VerticalPanel();
		
		initWidget(vPanel);
		
		if(receptDTO != null)
		{
			vPanel.add(new Label(receptDTO.getReceptName() + " blev oprettet"));
		}
		
		okButton.setEnabled(false);
		viewInfo = new Label("Indtast den nye recepts oplysninger");
		
		vPanel.add(viewInfo);
		
		table = new Grid(3,2);
		
		table.setWidget(0, 0, receptNameLabel);
		table.setWidget(0, 1, receptNameBox = new TextBox());
		table.setWidget(1, 0, receptIdLabel);
		table.setWidget(1, 1, receptIdBox 	= new TextBox());
		table.setWidget(2, 1, hPanel = new HorizontalPanel());;
		
		hPanel.add(okButton);
		hPanel.add(cancelButton);
		
		vPanel.add(table);
		
		receptIdBox.addKeyUpHandler(new receptId());
		receptNameBox.addKeyUpHandler(new receptName());

		okButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				ReceptDTO receptDTO = new ReceptDTO(receptNameBox.getText(), Integer.parseInt(receptIdBox.getText()));
				mc.show(mc.getReceptController().createRecept(receptDTO));

			}
		});
	
		cancelButton.setEnabled(true);
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
