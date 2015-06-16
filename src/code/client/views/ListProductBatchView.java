package code.client.views;

import java.util.ArrayList;

import code.client.controllers.MainController;
import code.client.controllers.ProductBatchController;
import code.database.ProductBatchDTO;
import code.shared.FieldVerifier;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.sun.java.swing.plaf.windows.resources.windows;

public class ListProductBatchView extends Composite
{
	MainController mc;
	ProductBatchController pbC;
	ArrayList<ProductBatchDTO> pbLS;
	
	VerticalPanel VPanel;
	Button backButton, OKButton;
	Anchor edit, ok, cancel, prevCancel;
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
		
		backButton = new Button("Tilbage");
		backButton.setEnabled(true);
		
		infoLabel = new Label("Listevisning over ProduktBatches");
		this.flex = new FlexTable();
		flex.setTitle("ProductBatchListView");
		flex.setText(0, 0, "ProductBatch ID");
		flex.setText(0, 1, "Recept ID");
		flex.setText(0, 2, "Status");
		flex.setText(0, 3, "Dato");
		
		int i = 1;
		for (ProductBatchDTO pbDTO : pbLS) {
			flex.setText(i, 0, "" + pbDTO.getPbId() );
			flex.setText(i, 1, "" + pbDTO.getReceptId() );
			flex.setText(i, 2, "" + pbDTO.getStatus() );
			flex.setText(i, 3, "" + pbDTO.getDateTime() );
			
			i++;
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
