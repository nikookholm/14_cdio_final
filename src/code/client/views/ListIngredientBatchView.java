package code.client.views;

import java.util.ArrayList;

import code.client.controllers.MainController;
import code.database.IngredientBatchDTO;
import code.database.IngredientDTO;
import code.database.ProductBatchDTO;

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
	Label rbIdLabel 		= new Label("råvarebatch_id");
	Label ingredientIdLabel	= new Label("råvare_id");
	Label maengdeLabel		= new Label("maengde");;
	Label infoLabel;
	Button backButton;
	TextBox rbIdBox, ingredientIdBox, maengdeBox;

	FlexTable ft;
	Grid subTable;

	ArrayList<IngredientBatchDTO> ingrBatchDTO;

	public ListIngredientBatchView(ArrayList<IngredientBatchDTO> ingrBatchDTO, final MainController mc)
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
		ft = new FlexTable();
		ft.setTitle("IngredientBatchView");
		ft.setWidget(0, 0, rbIdLabel);
		ft.setWidget(0, 1, ingredientIdLabel);
		ft.setWidget(0, 2, maengdeLabel);
		
		mc.databaseService.ingredientBatch_table_list(new AsyncCallback<ArrayList<IngredientBatchDTO>>(){
		
			@Override
			public void onSuccess(ArrayList<IngredientBatchDTO> result) {
				for (int i = 0; i<result.size(); i++) {
					ft.setText(i+1, 0,"" + result.get(i).getRbId());
					ft.setText(i+1, 1,"" + result.get(i).getIngredientId());
					ft.setText(i+1, 2,"" + result.get(i).getMaengde());
				}	
			}
			
			@Override
			public void onFailure(Throwable caught){
				Window.alert("Fejl ved ListIngredientBatchView" + caught.getMessage());
			}
		});
		
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

