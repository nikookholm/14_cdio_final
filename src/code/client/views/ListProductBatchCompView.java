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
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ListProductBatchCompView extends Composite {

	ProductBatchCompController pbCompC;
	MainController mc;
	VerticalPanel VPanel;
	Label 	infoLabel				= new Label("ProduktBatch Komponenter");
	Label 	productBatchIdLabel		= new Label("ProduktBatch ID");
	Label	ingredientBatchIdLabel	= new Label("RåvareBatch ID");
	Label	taraWeightLabel			= new Label("Tara værdi");
	Label	nettoWeightLabel		= new Label("Netto værdi");
	Label	operatorIdLabel			= new Label("Operatør ID");
	Button backButton;
	FlexTable ft;
	Grid subTable;

	ArrayList<ProductBatchCompDTO> pbCompDTO;

	public ListProductBatchCompView(ArrayList<ProductBatchCompDTO> pbCompDTO, MainController mc)
	{
		this.mc = mc;
		this.pbCompDTO = pbCompDTO;
		
		VPanel = new VerticalPanel();
		VPanel.setHeight("100px");
		
		ft = new FlexTable();
		
		VPanel.add(infoLabel);
		infoLabel.setStyleName("caption");
		ft.setWidget(0, 0, productBatchIdLabel);
		productBatchIdLabel.setStyleName("input-text");
		ft.setWidget(0, 1, ingredientBatchIdLabel);
		ingredientBatchIdLabel.setStyleName("input-text");
		ft.setWidget(0, 2, taraWeightLabel);
		taraWeightLabel.setStyleName("input-text");
		ft.setWidget(0, 3, nettoWeightLabel);
		nettoWeightLabel.setStyleName("input-text");
		ft.setWidget(0, 4, operatorIdLabel);
		operatorIdLabel.setStyleName("input-text");
		ft.getCellFormatter().setWidth(0, 0, "150px;");
		ft.getCellFormatter().setWidth(0, 1, "150px;");
		ft.getCellFormatter().setWidth(0, 2, "150px;");
		ft.getCellFormatter().setWidth(0, 3, "150px;");
		ft.getCellFormatter().setWidth(0, 4, "150px;");
		
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
		this.subTable = new Grid (1,2);
		subTable.setWidget(0, 0, backButton);

		VPanel.add(ft);
		VPanel.add(subTable);
		initWidget(this.VPanel);
		
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
