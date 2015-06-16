package code.client.views;

import java.util.ArrayList;
import java.util.List;

import code.client.DatabaseServiceAsync;
import code.client.DatabaseService;
import code.database.*;
import code.client.controllers.MainController;
import code.client.controllers.ProductBatchCompController;
import code.client.controllers.ProductBatchController;
import code.database.DALException;
import code.server.DatabaseServiceImpl;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ListProductBatchCompView extends Composite {

	ProductBatchCompController pbCompC;
	MainController mc;
	VerticalPanel VPanel;
	Button backButton;
	FlexTable ft;

	ArrayList<ProductBatchCompDTO> pbCompDTO;

	public ListProductBatchCompView(ArrayList<ProductBatchCompDTO> pbCompDTO, MainController mc)
	{
		this.mc = mc;
		this.pbCompDTO = pbCompDTO;
		VPanel = new VerticalPanel();
		VPanel.setHeight("100px");
		initWidget(this.VPanel);

		backButton = new Button("Tilbage");
		backButton.setEnabled(true);

		ft = new FlexTable();
		ft.getCellFormatter().setWidth(0, 0, "100px");
		ft.getCellFormatter().setWidth(0, 1, "100px");
		ft.getCellFormatter().setWidth(0, 2, "100px");
		ft.getCellFormatter().setWidth(0, 3, "100px");
		ft.getCellFormatter().setWidth(0, 4, "100px");

		ft.setTitle("ProductBatchCompListView");
		ft.setText(0, 0, "ProductBatch id");
		ft.setText(0, 1, "IngredientBatch id");
		ft.setText(0, 2, "taravaegt");
		ft.setText(0, 3, "nettovaegt");
		ft.setText(0, 4, "Operator id");
		
		int i = 0;
		for(ProductBatchCompDTO pbC : pbCompDTO) {
			i++;
			ft.setText(i+1, 0, "" + pbC.getPbId());
			ft.setText(i+1, 1, "" + pbC.getRbId());
			ft.setText(i+1, 2, "" + pbC.getTara());
			ft.setText(i+1, 3, "" + pbC.getNetto());
			ft.setText(i+1, 4, "" + pbC.getOprId());
		}
		
		VPanel.add(ft);
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
