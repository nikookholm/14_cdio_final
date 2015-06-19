package code.client.views;

import code.client.controllers.MainController;
import code.database.ProductBatchDTO;
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
import com.google.gwt.user.client.ui.Widget;

public class CreateProductBatchView extends Composite
{
	ProductBatchDTO pbDTO;
	MainController mc;
	
	VerticalPanel vPanel;
	HorizontalPanel hPanel;
	TextBox pbNoBox, receptNoBox;
	Label headLabel, pbNoLabel, receptNoLabel;
	Button okButton, cancelButton;
	FlexTable flex;
	
	boolean pbNoValidity 	 = false;
	boolean receptNoValidity = false;
	
	public CreateProductBatchView(MainController mc, Widget infomation)
	{
		this.mc = mc;
		this.pbDTO = pbDTO;
		
		hPanel = new HorizontalPanel();
		vPanel = new VerticalPanel();
		initWidget(this.vPanel);
		
		headLabel		= new Label("Opret ny produkt batch");
		pbNoLabel		= new Label("Product batch ID"); 
		receptNoLabel	= new Label("Recept ID");
		okButton		= new Button("OK");
		cancelButton	= new Button("Annullér");
		pbNoBox		= new TextBox();
		receptNoBox	= new TextBox();
		
		pbNoBox.setFocus(true);
		okButton.setEnabled(false);
		cancelButton.setEnabled(true);
		
		if (infomation != null)
		{
			vPanel.add(infomation);
		}
		
		this.flex = new FlexTable();
		flex.setTitle("Lav ny produkt batch");
		flex.setWidget(0, 0, pbNoLabel);
		flex.setWidget(0, 1, pbNoBox);
		flex.setWidget(1, 0, receptNoLabel);
		flex.setWidget(1, 1, receptNoBox);
		flex.setWidget(2, 0, hPanel);
		flex.getFlexCellFormatter().setColSpan(2, 0, 2);
		flex.getCellFormatter().setAlignment(2, 0, HasHorizontalAlignment.ALIGN_RIGHT, HasVerticalAlignment.ALIGN_MIDDLE);
		
		headLabel.setStyleName("caption");
		pbNoLabel.setStyleName("input-text");
		receptNoLabel.setStyleName("input-text");
		
		hPanel.add(cancelButton);
		hPanel.add(okButton);
		
		vPanel.add(headLabel);
		vPanel.add(flex);
		
		okButton.addClickHandler(new OKClickHandler());
		cancelButton.addClickHandler(new cancelClickHandler());

		pbNoBox.addKeyUpHandler(new PbNoBoxHandler());
		receptNoBox.addKeyUpHandler(new ReceptNoBoxHandler());
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
	
	private void OKButtonValidity()
	{
		if(pbNoValidity && receptNoValidity)
			okButton.setEnabled(true);
		else 
			okButton.setEnabled(false);
	}
	
	private class OKClickHandler implements ClickHandler 
	{
	    @Override
	    public void onClick(ClickEvent event) {
	    	int statI = 0;
			String datI = "";			
			ProductBatchDTO pbDTO = new ProductBatchDTO(Integer.parseInt(pbNoBox.getText()), Integer.parseInt(receptNoBox.getText()), statI, datI);
			mc.show(mc.getProductBatchController().createProductBatch(pbDTO));
      }
   }
	
	private class cancelClickHandler implements ClickHandler
	{
		@Override
		public void onClick(ClickEvent event) {
			mc.show(new MainView(mc));
		}
	}
	
}
