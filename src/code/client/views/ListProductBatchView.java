package code.client.views;

import java.util.List;

import code.*;
import code.client.DatabaseServiceAsync;
import code.client.controllers.MainController;
import code.client.controllers.ProductBatchController;
import code.database.DALException;
import code.database.ProductBatchDTO;
import code.server.DatabaseServiceImpl;

import com.google.gwt.*;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ListProductBatchView extends Composite
{
	ProductBatchController pbC;
	MainController mc;
	VerticalPanel VPanel;
	Label pbNoLabel, receptNoLabel, dateLabel, statLabel;
	Label infoLabel;
	
	List<ProductBatchDTO> ls;

	public ListProductBatchView(final MainController mc) throws DALException
	{
		this.mc = mc;
		VPanel = new VerticalPanel();
		initWidget(this.VPanel);
		
		pbNoLabel		= new Label("prodBatchNo");
		receptNoLabel	= new Label("receptNo");
		dateLabel		= new Label("date");
		statLabel		= new Label("status");
		
		infoLabel = new Label("Intast den nye ProduktBatchs oplysninger hér.");
		final FlexTable flex = new FlexTable();
		flex.setTitle("ProductBatchListView");
		flex.setWidget(0, 0, pbNoLabel);
		flex.setWidget(0, 1, receptNoLabel);
		flex.setWidget(0, 2, dateLabel);
		flex.setWidget(0, 3, statLabel);
		
		VPanel.add(flex);
		
		
		
	}
	
	
}
