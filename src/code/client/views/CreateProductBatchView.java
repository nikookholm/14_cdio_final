package code.client.views;

import code.*;
import code.client.controllers.MainController;
import code.client.controllers.ProductBatchController;
import code.database.ProductBatchDTO;
import code.database.UserDTO;
import code.shared.FieldVerifier;

import com.google.gwt.*;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class CreateProductBatchView extends Composite
{
	ProductBatchController pbC;
	MainController mc;
	
	VerticalPanel VPanel;
	Button OKButton, cancelButton;
	Label infoLabel;
	Grid table;
	Label pbNoLabel, receptNoLabel, dateLabel, statLabel;
	TextBox pbNoBox, receptNoBox, dateBox, statBox;
	
	boolean pbNoValidity 		= false;
	boolean receptNoValidity	= false;
	boolean dateValidity 		= false;
	boolean statValidity 		= false;
	
	public CreateProductBatchView(final MainController mc)
	{
		this.mc = mc;
		this.VPanel = new VerticalPanel();
		initWidget(VPanel);
		
		pbNoLabel		= new Label("prodBatchNo");
		receptNoLabel	= new Label("receptNo");
		dateLabel		= new Label("date");
		statLabel		= new Label("status");

		pbNoBox		= new TextBox();
		receptNoBox	= new TextBox();
		dateBox		= new TextBox();
		statBox		= new TextBox();
		
		OKButton = new Button("OK");
		OKButton.setEnabled(false);
		cancelButton = new Button("Cancel");
		cancelButton.setEnabled(true);
		
		infoLabel = new Label("Intast den nye ProduktBatchs oplysninger hér.");
		table = new Grid(4, 2);
		table.setTitle("Create new ProductBatch");
		table.setWidget(0, 0, pbNoLabel);
		table.setWidget(0, 1, pbNoBox);
		table.setWidget(1, 0, receptNoLabel);
		table.setWidget(1, 1, receptNoBox);
		table.setWidget(2, 0, dateLabel);
		table.setWidget(2, 1, dateBox);
		table.setWidget(3, 0, statLabel);
		table.setWidget(3, 1, statBox);
		
		dateBox.setText(pbC.getDate());
		
		pbNoBox.addKeyUpHandler(new PbNoBoxHandler());
		receptNoBox.addKeyUpHandler(new ReceptNoBoxHandler());
		dateBox.addKeyUpHandler(new DateBoxHandler());
		statBox.addKeyUpHandler(new StatBoxHandler());
		
		VPanel.add(infoLabel);
		VPanel.add(table);
		VPanel.add(OKButton);
		VPanel.add(cancelButton);
		
//		OKButton.addClickHandler(new PBClickHandler());
	
	}
	
	private class PbNoBoxHandler implements KeyUpHandler
	{
		@Override
		public void onKeyUp(KeyUpEvent event) 
		{
			if( (FieldVerifier.ispbNoValid(pbNoBox.getText())) == false) {
				pbNoBox.setStyleName("gwt-TextBox-invalidEntry");
				pbNoValidity = false;
			}else{
				pbNoBox.removeStyleName("gwt-TextBox-invalidEntry");
				pbNoValidity = true;
			}
			OKButtonValidity();
		}
	}
	
	private class ReceptNoBoxHandler implements KeyUpHandler
	{
		@Override
		public void onKeyUp(KeyUpEvent event) 
		{
			if(!FieldVerifier.isReceptNoValid(receptNoBox.getText())) {
				receptNoBox.setStyleName("gwt-TextBox-invalidEntry");
				receptNoValidity = false;
			}else{
				receptNoBox.removeStyleName("gwt-TextBox-invalidEntry");
				receptNoValidity = true;
			}
			OKButtonValidity();
		}
	}
	
	private class DateBoxHandler implements KeyUpHandler
	{
		@Override
		public void onKeyUp(KeyUpEvent event) 
		{
			if(!dateBox.getText().isEmpty()){
				dateValidity = true;
			}else{
				dateBox.setStyleName("gwt-TextBox-invalidEntry");
			}
			OKButtonValidity();
		}
	}
	
	private class StatBoxHandler implements KeyUpHandler
	{
		@Override
		public void onKeyUp(KeyUpEvent event) 
		{
			if(!FieldVerifier.isStatusValid(statBox.getText()))	{
				statBox.setStyleName("gwt-TextBox-invalidEntry");
				statValidity = false;
			}else{
				statBox.removeStyleName("gwt-TextBox-invalidEntry");
				statValidity = true;
			}
			OKButtonValidity();
		}
	}
	
	private void OKButtonValidity()
	{
		if(pbNoValidity && receptNoValidity && dateValidity && statValidity)
			OKButton.setEnabled(true);
		else 
			OKButton.setEnabled(false);
	}
/*	
	private class PBClickHandler implements ClickHandler 
	{
		int pbI;
		int statI;
		int recI;
		int datI;
		
	      @Override
	      public void onClick(ClickEvent event) {
				try {
					this.pbI = Integer.parseInt(pbNoBox.getText());
					this.statI = Integer.parseInt(statBox.getText());
					this.recI = Integer.parseInt(receptNoBox.getText());
					this.datI = Integer.parseInt(dateBox.getText());
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
				
				ProductBatchDTO uDTO = new ProductBatchDTO(pbI, statI, recI, datI);
	    	  
				try {
					mc.service.create(create, new AsyncCallback<Void>() {
							@Override
							public void onSuccess(Void result) {
								Window.alert("ProduktBatchen er gemt succefuldt");
							}
	
							@Override
							public void onFailure(Throwable caught) {
								Window.alert("Server fejl!" + caught.getMessage());
							}
						}
					);
				} 
				catch (Exception e) {
					e.printStackTrace();
				}
				OKButton.setEnabled(true);
	      }
	   }
*/
	
}



