package code.client.views;

import java.util.ArrayList;

import code.client.controllers.MainController;
import code.database.IngredientBatchDTO;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Button;

public class ListIngredientBatchView extends Composite
{
	MainController mc;
	VerticalPanel vPanel;
	Label ibIdLabel 		= new Label("Råvarebatch ID");
	Label ingredientIdLabel	= new Label("Råvare ID");
	Label maengdeLabel		= new Label("Mængde i kg");;
	Label infoLabel			= new Label("Råvarebatch");	
	Button backButton;

	FlexTable ft;
	Grid subTable;

	ArrayList<IngredientBatchDTO> ingrBatchDTO;

	public ListIngredientBatchView(ArrayList<IngredientBatchDTO> ingrBatchDTO, MainController mc)
	{
		this.mc = mc;
		this.ingrBatchDTO = ingrBatchDTO;
		
		vPanel = new VerticalPanel();
	
		ft = new FlexTable();
		
		vPanel.add(infoLabel);
		infoLabel.setStyleName("caption");
		
		ft.setWidget(0, 0, ibIdLabel);
		ft.setWidget(0, 1, ingredientIdLabel);
		ft.setWidget(0, 2, maengdeLabel);
		
		ibIdLabel.setStyleName("input-text");
		ingredientIdLabel.setStyleName("input-text");
		maengdeLabel.setStyleName("input-text");
		
		ft.getCellFormatter().setWidth(0, 0, "150px;");
		ft.getCellFormatter().setWidth(0, 1, "150px;");
		ft.getCellFormatter().setWidth(0, 2, "150px;");
		
		int i = 0;
		for (IngredientBatchDTO ingrBatch : ingrBatchDTO) {
			i++;
			ft.setText(i+1, 0,"" + ingrBatch.getRbId());
			ft.setText(i+1, 1,"" + ingrBatch.getIngredientId());
			ft.setText(i+1, 2,"" + ingrBatch.getMaengde());
		}	

		backButton = new Button("Tilbage");
		backButton.setEnabled(true);

		vPanel.add(ft);
		vPanel.add(backButton);
		vPanel.setCellHorizontalAlignment(backButton, HasHorizontalAlignment.ALIGN_CENTER);
		initWidget(this.vPanel);

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

