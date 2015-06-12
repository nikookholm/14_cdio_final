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
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

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
	TextBox pbNoBox, receptNoBox;
	
	int selectedRow;
	
	public ListProductBatchView(ArrayList<ProductBatchDTO> pbLS, MainController mc)
	{
		this.mc = mc;
		this.pbLS = pbLS;
		VPanel = new VerticalPanel();
		initWidget(this.VPanel);
		
		pbNoBox		= new TextBox();
		receptNoBox	= new TextBox();
		
		backButton = new Button("Tilbage");
		backButton.setEnabled(true);
		
		infoLabel = new Label("Listevisning over ProduktBatches");
		this.flex = new FlexTable();
		flex.setTitle("ProductBatchListView");
		flex.setText(0, 0, "prodBatchNo");
		flex.setText(0, 1, "receptNo");
		flex.setText(0, 2, "dato");
		flex.setText(0, 3, "status");
		
		for ( int i=0 ; i<pbLS.size() ; i++ ) {
			flex.setText(i+1, 0, "" + this.pbLS.get(i).getPbId() );
			flex.setText(i+1, 1, "" + this.pbLS.get(i).getReceptId() );
			flex.setText(i+1, 2, "" + this.pbLS.get(i).getDateTime() );
			flex.setText(i+1, 3, "" + this.pbLS.get(i).getStatus() );
			
			flex.setWidget(i+1, 5, edit = new Anchor("Redigér"));
			edit.addClickHandler(new editClickHandler());
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
	
	private class editClickHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			if(prevCancel != null){
				prevCancel.fireEvent(new ClickEvent(){});
			}
			selectedRow = flex.getCellForEvent(event).getRowIndex();

			pbNoBox.setText(flex.getText(selectedRow, 0));
			receptNoBox.setText(flex.getText(selectedRow, 1));
			
			flex.setWidget(selectedRow, 0, pbNoBox);
			flex.setWidget(selectedRow, 1, receptNoBox);
			
		}

	}
}
