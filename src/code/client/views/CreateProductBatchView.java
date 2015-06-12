package code.client.views;

import java.text.SimpleDateFormat;
import java.util.Date;

import code.client.controllers.MainController;
import code.client.controllers.ProductBatchController;
import code.database.ProductBatchDTO;
import code.shared.FieldVerifier;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class CreateProductBatchView extends Composite
{
	ProductBatchController pbC;
	MainController mc;
	
	VerticalPanel VPanel;
	HorizontalPanel HPanel;
	Button OKButton, cancelButton;
	Label infoLabel;
	Grid table, subTable;
	FlexTable flex;
	Label pbNoLabel, receptNoLabel;
	TextBox pbNoBox, receptNoBox;
	
	boolean pbNoValidity 		= false;
	boolean receptNoValidity	= false;
//	SimpleDateFormat dateTime 	= new SimpleDateFormat("yyyy/MM/dd-HH:mm");
	
	public CreateProductBatchView(ProductBatchDTO pbDTO, MainController mc)
	{
		this.mc = mc;
		this.VPanel = new VerticalPanel();
		initWidget(VPanel);
		
		pbNoLabel		= new Label("prodBatchNo");
		receptNoLabel	= new Label("receptNo");
		pbNoBox		= new TextBox();
		receptNoBox	= new TextBox();
		pbNoBox.setFocus(true);
		
		OKButton = new Button("OK");
		cancelButton = new Button("Cancel");
		OKButton.setEnabled(false);
		cancelButton.setEnabled(true);
		
		if(pbDTO != null){
			infoLabel = new Label("Dette er det nyligt oprettet ProduktBatch");
			this.flex = new FlexTable();
			
		}else{
			infoLabel = new Label("Intast den nye ProduktBatchs oplysninger hér");
			this.table = new Grid(3, 2);
			table.setTitle("Create new ProductBatch");
			table.setWidget(0, 0, pbNoLabel);
			table.setWidget(0, 1, pbNoBox);
			table.setWidget(1, 0, receptNoLabel);
			table.setWidget(1, 1, receptNoBox);
		}
		this.subTable = new Grid(1, 1);
		
		HPanel = new HorizontalPanel();
		subTable.setWidget(0, 0, HPanel );
		
		HPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		HPanel.add(OKButton);
		HPanel.add(cancelButton);
		
		pbNoBox.addKeyUpHandler(new PbNoBoxHandler());
		receptNoBox.addKeyUpHandler(new ReceptNoBoxHandler());
		
		VPanel.add(infoLabel);
		VPanel.add(table);
		VPanel.add(subTable);
		
		OKButton.addClickHandler(new OKClickHandler());
		cancelButton.addClickHandler(new cancelClickHandler());
		
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
			OKButton.setEnabled(true);
		else 
			OKButton.setEnabled(false);
	}
	
	private class OKClickHandler implements ClickHandler 
	{
	    @Override
	    public void onClick(ClickEvent event) {
			
			int pbI = Integer.parseInt(pbNoBox.getText());
			int recI = Integer.parseInt(receptNoBox.getText());
			int statI = 0;
			String datI = "";
//			datI = dateTime.format(new Date());
			
			ProductBatchDTO pbDTO = new ProductBatchDTO(pbI, statI, recI, datI);
			
			mc.getProductBatchController().createProductBatch(pbDTO);
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

