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
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Button;

public class ListIngredientBatchView extends Composite
{
	MainController mc;
	VerticalPanel VPanel;
	Label rbIdLabel 		= new Label("productbatch id");
	Label ingredientIdLabel	= new Label("ingredient id");
	Label maengdeLabel		= new Label("maengde");;
	Label infoLabel;
	Button backButton;
	TextBox rbIdBox, ingredientIdBox, maengdeBox;

	FlexTable ft;
	Grid subTable;

	ArrayList<IngredientBatchDTO> ingrBatchDTO;

	public ListIngredientBatchView(ArrayList<IngredientBatchDTO> ingrBatchDTO, MainController mc)
	{
		this.mc = mc;
		this.ingrBatchDTO = ingrBatchDTO;
		VPanel = new VerticalPanel();
		initWidget(this.VPanel);

		rbIdBox 		= new TextBox();
		ingredientIdBox = new TextBox();
		maengdeBox 		= new TextBox();

		infoLabel = new Label("Liste over råvarebatches i systemet");
		ft = new FlexTable();
		ft.setWidget(0, 0, rbIdLabel);
		ft.setWidget(0, 1, ingredientIdLabel);
		ft.setWidget(0, 2, maengdeLabel);
//		ft.getCellFormatter().setWidth(0, 0, "150px;");
//		ft.getCellFormatter().setWidth(0, 1, "150px;");
//		ft.getCellFormatter().setWidth(0, 2, "150px;");
		
		int i = 0;
		for (IngredientBatchDTO ingrBatch : ingrBatchDTO) {
			i++;
			ft.setText(i+1, 0,"" + ingrBatch.getRbId());
			ft.setText(i+1, 1,"" + ingrBatch.getIngredientId());
			ft.setText(i+1, 2,"" + ingrBatch.getMaengde());
		}	

		backButton = new Button("Tilbage");
		backButton.setEnabled(true);
		this.subTable = new Grid(1,2);
		subTable.setWidget(0, 0, backButton);

		VPanel.add(infoLabel);
		VPanel.add(ft);
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

