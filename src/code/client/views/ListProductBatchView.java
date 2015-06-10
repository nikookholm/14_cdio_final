package code.client.views;

import java.util.List;

import code.*;
import code.client.DatabaseServiceAsync;
import code.client.controllers.MainController;
import code.client.controllers.ProductBatchController;
import code.database.ProductBatchDTO;

import com.google.gwt.*;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
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
	FlexTable flex;
	
	List<ProductBatchDTO> ls;

	public ListProductBatchView(ProductBatchDTO pbDTO, MainController mc)
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
		this.flex = new FlexTable();
		flex.setTitle("ProductBatchListView");
		flex.setWidget(0, 0, pbNoLabel);
		flex.setWidget(0, 1, receptNoLabel);
		flex.setWidget(0, 2, dateLabel);
		flex.setWidget(0, 3, statLabel);
		flex.setWidget(1, 3, backButton);
		
		for (int i = 0; i < ls.size(); i++ ) {
			flex.setWidget(i+1, 0, new Label(ls.get(i).getPbId()     + ""));
			flex.setWidget(i+1, 1, new Label(ls.get(i).getReceptId() + ""));
			flex.setWidget(i+1, 2, new Label(ls.get(i).getDate()     + ""));
			flex.setWidget(i+1, 3, new Label(ls.get(i).getStatus()   + ""));
			flex.setWidget(i+1, 4, new Anchor("Redigér"));
		}
		
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
