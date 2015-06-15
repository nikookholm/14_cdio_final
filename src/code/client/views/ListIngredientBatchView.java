package code.client.views;

import java.util.ArrayList;

import code.client.controllers.MainController;
import code.database.IngredientBatchDTO;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ListIngredientBatchView extends Composite
{
	MainController mc;
	VerticalPanel vPanel;
	Label rbIdLabel 		= new Label("råvarebatch_id");
	Label ingredientIdLabel	= new Label("råvare_id");
	Label maengdeLabel		= new Label("maengde");;
	Anchor edit;

	TextBox rbIdBox, ingredientIdBox, maengdeBox;

	FlexTable flexTable;

	ArrayList<IngredientBatchDTO> ingrBatchDTO;

	public ListIngredientBatchView(MainController mc)
	{
		this.mc = mc;
		this.vPanel = new VerticalPanel();
		initWidget(this.vPanel);
		this.ingrBatchDTO = ingrBatchDTO;

		flexTable = new FlexTable();

		flexTable.setWidget(0, 0, rbIdLabel);
		flexTable.setWidget(0, 1, ingredientIdLabel);
		flexTable.setWidget(0, 2, maengdeLabel);
		flexTable.setWidget(0, 3, new Label(";)"));

		mc.databaseService.ingredientBatch_table_list(new AsyncCallback<ArrayList<IngredientBatchDTO>>() {

			@Override
			public void onFailure(Throwable caught)
			{
				Window.alert("Der skete en fejl, da listen over råvarebatches prøvede at hentes"+caught.getMessage());
			}

			@Override
			public void onSuccess(ArrayList<IngredientBatchDTO> result) {
				for (int i = 0; i<result.size(); i++) {

					flexTable.setText(i+1, 0, result.get(i).getRbId()  + "");
					flexTable.setText(i+1, 1, result.get(i).getIngredientId() + "");
					flexTable.setText(i+1, 2, result.get(i).getMaengde()    + "");
				}	
			}
		});

		vPanel.add(flexTable);

	}
}
