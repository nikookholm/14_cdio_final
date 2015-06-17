package code.client.views;

import java.util.ArrayList;
import java.util.List;

import code.database.*;
import code.client.controllers.MainController;
import code.client.controllers.ProductBatchCompController;
import code.database.DALException;
import code.server.DatabaseServiceImpl;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ListProductBatchCompView extends Composite {

	ProductBatchCompController pbCompC;
	MainController mc;
	VerticalPanel vPanel;
	Label 	infoLabel				= new Label("Produkbatch komponenter");
	Label 	productBatchIdLabel		= new Label("Produktbatch ID");
	Label	ingredientBatchIdLabel	= new Label("Råvarebatch ID");
	Label	taraWeightLabel			= new Label("Tara i kg");
	Label	nettoWeightLabel		= new Label("Netto i kg");
	Label	operatorIdLabel			= new Label("Operatør ID");
	Button backButton;
	FlexTable ft;
	FlexTable subTable;

	ArrayList<ProductBatchCompDTO> pbCompDTO;

	public ListProductBatchCompView(ArrayList<ProductBatchCompDTO> pbCompDTO, MainController mc)
	{
		this.mc = mc;
		this.pbCompDTO = pbCompDTO;
		
		vPanel = new VerticalPanel();
		vPanel.setHeight("100px");
		
		ft = new FlexTable();
		
		vPanel.add(infoLabel);
		infoLabel.setStyleName("caption");
		
		ft.setWidget(0, 0, productBatchIdLabel);
		ft.setWidget(0, 1, ingredientBatchIdLabel);
		ft.setWidget(0, 2, taraWeightLabel);
		ft.setWidget(0, 3, nettoWeightLabel);
		ft.setWidget(0, 4, operatorIdLabel);
	
		ft.getCellFormatter().setWidth(0, 0, "150px;");
		ft.getCellFormatter().setWidth(0, 1, "150px;");
		ft.getCellFormatter().setWidth(0, 2, "150px;");
		ft.getCellFormatter().setWidth(0, 3, "150px;");
		ft.getCellFormatter().setWidth(0, 4, "150px;");
		
		productBatchIdLabel.setStyleName("input-text");
		ingredientBatchIdLabel.setStyleName("input-text");
		taraWeightLabel.setStyleName("input-text");
		nettoWeightLabel.setStyleName("input-text");
		operatorIdLabel.setStyleName("input-text");
	
		int i = 0;
		for(ProductBatchCompDTO pbC : pbCompDTO) {
			i++;
			ft.setText(i+1, 0, "" + pbC.getPbId());
			ft.setText(i+1, 1, "" + pbC.getRbId());
			ft.setText(i+1, 2, "" + pbC.getTara());
			ft.setText(i+1, 3, "" + pbC.getNetto());
			ft.setText(i+1, 4, "" + pbC.getOprId());
		}
		
		backButton = new Button("Tilbage");
		backButton.setEnabled(true);
	
		vPanel.add(ft);
		vPanel.add(backButton);
		vPanel.setCellHorizontalAlignment(backButton, HasHorizontalAlignment.ALIGN_CENTER);
		initWidget(this.vPanel);
		
		backButton.addClickHandler(new backClickHandler());
	}

	private class backClickHandler implements ClickHandler
	{
		@Override
		public void onClick(ClickEvent event) 
		{	
			mc.show(new MainView(mc));
		}
	}
}
