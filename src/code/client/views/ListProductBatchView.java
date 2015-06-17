package code.client.views;

import java.util.ArrayList;

import code.client.controllers.MainController;
import code.client.controllers.ProductBatchController;
import code.database.ProductBatchDTO;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ListProductBatchView extends Composite
{
	MainController mc;
	ProductBatchController pbC;
	ArrayList<ProductBatchDTO> pbLS;
	
	VerticalPanel VPanel;
	HorizontalPanel hPanel;
	Button backButton;
	Label infoLabel, pbNoLabel, receptNoLabel, statLabel, dateLabel;
	FlexTable flex;
	
	public ListProductBatchView(ArrayList<ProductBatchDTO> pbLS, MainController mc)
	{
		this.mc = mc;
		this.pbLS = pbLS;
		
		hPanel = new HorizontalPanel();
		VPanel = new VerticalPanel();
		initWidget(this.VPanel);
		
		infoLabel 		= new Label("Listevisning over ProduktBatches");
		pbNoLabel		= new Label("ProductBatch ID"); 
		receptNoLabel	= new Label("Recept ID");
		statLabel 		= new Label("Status");
		dateLabel 		= new Label("Dato");
		
		backButton = new Button("Tilbage");
		backButton.setEnabled(true);
		
		this.flex = new FlexTable();
		flex.setTitle("ProductBatchListView");
		flex.setWidget(0, 0, pbNoLabel);
		flex.setWidget(0, 1, receptNoLabel);
		flex.setWidget(0, 2, statLabel);
		flex.setWidget(0, 3, dateLabel);
		flex.getCellFormatter().setWidth(0, 0, "250px;");
		flex.getCellFormatter().setWidth(0, 1, "250px;");
		flex.getCellFormatter().setWidth(0, 2, "250px;");
		flex.getCellFormatter().setWidth(0, 3, "250px;");
		
		infoLabel.setStyleName("caption");
		pbNoLabel.setStyleName("input-text");
		receptNoLabel.setStyleName("input-text");
		statLabel.setStyleName("input-text");
		dateLabel.setStyleName("input-text");
		
		int i = 1;
		for (ProductBatchDTO pbDTO : pbLS) {
			flex.setText(i, 0, "" + pbDTO.getPbId() );
			flex.setText(i, 1, "" + pbDTO.getReceptId() );
			flex.setText(i, 2, "" + pbDTO.getStatus() );
			flex.setText(i, 3, "" + pbDTO.getDateTime() );
			
			i++;
		}
		
		VPanel.add(infoLabel);
		VPanel.add(flex);
		VPanel.add(backButton);
		VPanel.setCellHorizontalAlignment(backButton, HasHorizontalAlignment.ALIGN_CENTER);

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
