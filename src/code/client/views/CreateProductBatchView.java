package code.client.views;

import code.client.controllers.MainController;
import code.client.controllers.ProductBatchController;
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

public class CreateProductBatchView extends Composite
{
	ProductBatchController pbC;
	ProductBatchDTO pbDTO;
	MainController mc;
	
	VerticalPanel vPanel;
	HorizontalPanel hPanel;
	TextBox pbNoBox, receptNoBox;
	Label infoLabel, pbNoLabel, receptNoLabel;
	Button okButton, cancelButton;
	FlexTable flex;
	
	boolean pbNoValidity 	 = false;
	boolean receptNoValidity = false;
	
	public CreateProductBatchView(ProductBatchDTO pbDTO, MainController mc)
	{
		this.mc = mc;
		this.pbDTO = pbDTO;
		
		hPanel = new HorizontalPanel();
		vPanel = new VerticalPanel();
		initWidget(this.vPanel);
		
		infoLabel		= new Label("Opret ny produktbatch");
		pbNoLabel		= new Label("ProductBatch ID"); 
		receptNoLabel	= new Label("Recept ID");
		okButton		= new Button("OK");
		cancelButton	= new Button("Annullér");
		pbNoBox		= new TextBox();
		receptNoBox	= new TextBox();
		
		pbNoBox.setFocus(true);
		okButton.setEnabled(false);
		cancelButton.setEnabled(true);
		
		if(pbDTO != null){
			vPanel.add(new Label("Produkt Batchen: " + pbDTO.getPbId() + " blev oprettet"));
		}
		this.flex = new FlexTable();
		flex.setTitle("Lav ny ProduktBatch");
		flex.setWidget(0, 0, pbNoLabel);
		flex.setWidget(0, 1, pbNoBox);
		flex.setWidget(1, 0, receptNoLabel);
		flex.setWidget(1, 1, receptNoBox);
		flex.setWidget(2, 0, hPanel);
		flex.getFlexCellFormatter().setColSpan(2, 0, 2);
		flex.getCellFormatter().setAlignment(2, 0, HasHorizontalAlignment.ALIGN_RIGHT, HasVerticalAlignment.ALIGN_MIDDLE);
		
		infoLabel.setStyleName("caption");
		pbNoLabel.setStyleName("input-text");
		receptNoLabel.setStyleName("input-text");
		
		hPanel.add(cancelButton);
		hPanel.add(okButton);
		
		vPanel.add(infoLabel);
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

