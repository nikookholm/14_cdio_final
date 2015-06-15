package code.client.views;

import java.util.ArrayList;

import code.client.controllers.MainController;
import code.database.IngredientBatchDTO;
import code.database.ProductBatchDTO;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
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
	Label rbIdLabel 		= new Label("råvarebatch_id");
	Label ingredientIdLabel	= new Label("råvare_id");
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

		backButton = new Button("Tilbage");
		backButton.setEnabled(true);

		infoLabel = new Label("Liste over råvarebatches");
		this.ft = new FlexTable();
		ft.setTitle("IngredientBatchView");
		ft.setWidget(0, 0, rbIdLabel);
		ft.setWidget(0, 1, ingredientIdLabel);
		ft.setWidget(0, 2, maengdeLabel);

		for (int i = 0; i<ingrBatchDTO.size(); i++) {
			ft.setText(i+1, 0,"" + this.ingrBatchDTO.get(i).getRbId());
			ft.setText(i+1, 1,"" + this.ingrBatchDTO.get(i).getIngredientId());
			ft.setText(i+1, 2,"" + this.ingrBatchDTO.get(i).getMaengde());
		}	

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

