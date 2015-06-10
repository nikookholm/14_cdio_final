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
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ListProductBatchView extends Composite
{
	ProductBatchController pbC;
	MainController mc;
	
	VerticalPanel VPanel;
	Button backButton;
	Label infoLabel;
	Label pbNoLabel, receptNoLabel, dateLabel, statLabel;
	
	List<ProductBatchDTO> ls;

	public ListProductBatchView(final ProductBatchDTO pbDTO, final MainController mc)
	{
		this.mc = mc;
		VPanel = new VerticalPanel();
		initWidget(this.VPanel);
		
		pbNoLabel		= new Label("prodBatchNo");
		receptNoLabel	= new Label("receptNo");
		dateLabel		= new Label("dato");
		statLabel		= new Label("status");
		
		backButton = new Button("Tilbage");
		backButton.setEnabled(true);
		
		infoLabel = new Label("Listevisning over ProduktBatchses.");
		final FlexTable flex = new FlexTable();
		flex.setTitle("ProductBatchListView");
		flex.setWidget(0, 0, pbNoLabel);
		flex.setWidget(0, 1, receptNoLabel);
		flex.setWidget(0, 2, dateLabel);
		flex.setWidget(0, 3, statLabel);
		flex.setWidget(1, 3, backButton);
		
		
		
		VPanel.add(infoLabel);
		VPanel.add(flex);
		
		backButton.addClickHandler(new backClickHandler());
		
	}
	
	private class backClickHandler implements ClickHandler
	{
		@Override
		public void onClick(ClickEvent event) {
			
			mc.show(new MainView(mc));
		}
		
	}
}
