package code.client.views;

import code.*;
import code.client.DatabaseServiceAsync;
import code.client.controllers.MainController;
import code.client.controllers.ProductBatchController;
import code.database.DALException;
import code.server.DatabaseServiceImpl;

import com.google.gwt.*;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ListProductBatchView extends Composite
{
	ProductBatchController pbC;
	VerticalPanel VPanel;
	
	public ListProductBatchView(MainController mc) throws DALException
	{
		VPanel = new VerticalPanel();
		VPanel.setHeight("100px");
		initWidget(this.VPanel);
		
		final FlexTable ft = new FlexTable();
		ft.getCellFormatter().setWidth(0, 0, "100px");
		ft.getCellFormatter().setWidth(0, 1, "100px");
		ft.getCellFormatter().setWidth(0, 2, "100px");
		
		ft.setTitle("ProductBatchListView");
		
		ft.setText(0, 0, "pbID");
		ft.setText(0, 1, "receptID");
		ft.setText(0, 2, "Status");
		
//		mc.show(widget);
		
	}
	
	
}
