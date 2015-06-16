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
	Label infoLabel;
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
		initWidget(this.VPanel);
		
		infoLabel = new Label("Liste over produktbatchkomponenter i systemet");
		ft = new FlexTable();
		ft.setText(0, 0, "productBatch id");
		ft.setText(0, 1, "ingredientBatch id");
		ft.setText(0, 2, "taraweight");
		ft.setText(0, 3, "nettoweight");
		ft.setText(0, 4, "operator id");
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
		
		
		VPanel.add(infoLabel);
		VPanel.add(ft);
		VPanel.add(subTable);
		
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
