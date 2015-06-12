package code.client.views;

import java.util.ArrayList;
import java.util.List;

import code.*;
import code.client.DatabaseServiceAsync;
import code.client.controllers.MainController;
import code.client.controllers.ProductBatchController;
import code.database.ProductBatchDTO;
import code.database.UserDTO;

import com.google.gwt.*;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ListProductBatchView extends Composite
{
	MainController mc;
	ProductBatchController pbC;
	ArrayList<ProductBatchDTO> pbLS;
	
	VerticalPanel VPanel;
	Button backButton;
	Label infoLabel;
	Label pbNoLabel, receptNoLabel, dateLabel, statLabel;
	FlexTable flex;
	Grid subTable;
	
	public ListProductBatchView(ArrayList<ProductBatchDTO> pbLS, MainController mc)
	{
		this.mc = mc;
		this.pbLS = pbLS;
		VPanel = new VerticalPanel();
		initWidget(this.VPanel);
		
		pbNoLabel		= new Label("prodBatchNo");
		receptNoLabel	= new Label("receptNo");
		dateLabel		= new Label("dato");
		statLabel		= new Label("status");
		
		backButton = new Button("Tilbage");
		backButton.setEnabled(true);
		
		infoLabel = new Label("Listevisning over ProduktBatches");
		this.flex = new FlexTable();
		flex.setTitle("ProductBatchListView");
		flex.setWidget(0, 0, pbNoLabel);
		flex.setWidget(0, 1, receptNoLabel);
		flex.setWidget(0, 2, dateLabel);
		flex.setWidget(0, 3, statLabel);
		
		for (int i = 0; i<pbLS.size(); i++ ) {
			flex.setText(i+1, 0, "" + pbLS.get(i).getPbId() );
			flex.setText(i+1, 1, "" + pbLS.get(i).getReceptId() );
			flex.setText(i+1, 2, "" + pbLS.get(i).getDateTime() );
			flex.setText(i+1, 3, "" + pbLS.get(i).getStatus() );
			flex.setWidget(i+1, 4, new Anchor("Redigér"));
		}
		
		this.subTable = new Grid(1, 2);
		subTable.setWidget(0, 0, backButton);
		
		VPanel.add(infoLabel);
		VPanel.add(flex);
		VPanel.add(subTable);
		
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
