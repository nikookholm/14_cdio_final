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
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
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
	Grid table, subTable;
	
	TextBox pbNoBox		= new TextBox();
	TextBox receptNoBox	= new TextBox();
	Label infoLabel		= new Label("Opret ny produktbatch");
	Label viewInfo		= new Label("Indtast produkbatchens oplysninger: ");
	Label pbNoLabel		= new Label("ProductBatch ID"); 
	Label receptNoLabel	= new Label("Recept ID");
	Button okButton		= new Button("OK");
	Button cancelButton	= new Button("Fortryd");
	FlexTable flex;
	
	boolean pbNoValidity 		= false;
	boolean receptNoValidity	= false;
	
	public CreateProductBatchView(ProductBatchDTO pbDTO, MainController mc)
	{
		this.mc = mc;
		this.pbDTO = pbDTO;
		vPanel = new VerticalPanel();
		
		pbNoBox.setFocus(true);
		
		okButton.setEnabled(false);
		cancelButton.setEnabled(true);
		
		if(pbDTO != null){
			vPanel.add(new Label("ProduktBatchen: " + pbDTO.getPbId() + "blev oprettet"));
			
		}else{
			vPanel.add(new Label("Indtast oplysninger"));
		}

		table = new Grid(3, 2);
		
		vPanel.add(infoLabel);
		infoLabel.setStyleName("caption");
		vPanel.add(viewInfo);
		viewInfo.setStyleName("input-text");
		table.setWidget(0, 0, pbNoLabel);
		pbNoLabel.setStyleName("input-text");
		table.setWidget(0, 1, pbNoBox);
		table.setWidget(1, 0, receptNoLabel);
		receptNoLabel.setStyleName("input-text");
		table.setWidget(1, 1, receptNoBox);
		table.setWidget(2, 1, hPanel = new HorizontalPanel());

		hPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		hPanel.add(okButton);
		hPanel.add(cancelButton);
		
		vPanel.add(table);
		vPanel.add(hPanel);
		initWidget(this.vPanel);
		
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

