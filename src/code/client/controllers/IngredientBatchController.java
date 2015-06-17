package code.client.controllers;

import java.util.ArrayList;

import code.client.views.CreateIngredientBatchView;
import code.client.views.ListIngredientBatchView;
import code.database.IngredientBatchDTO;
import code.database.ReceptDTO;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;

public class IngredientBatchController 
{
	MainController mc;
	ArrayList<IngredientBatchDTO> ingrBatchDTO;
	IngredientBatchDTO ibDTO;
	boolean booln = false;

	public IngredientBatchController(MainController mc)
	{
		this.mc = mc;
	}

	public Widget createIngredientBatch(IngredientBatchDTO ingrBatchDTO) 
	{
		this.ibDTO = ingrBatchDTO;
		if(ingrBatchDTO!= null){
			int checkIngId 		= ingrBatchDTO.getIngredientId();
			final int checkIbId		= ingrBatchDTO.getRbId();
			// opretter dto i database
			mc.databaseService.ingredientBatch_table_get(checkIngId, new AsyncCallback<IngredientBatchDTO>(){
				@Override
				public void onSuccess(IngredientBatchDTO result) {
					booln = false;
				}

				@Override
				public void onFailure(Throwable caught) {
					mc.databaseService.ingredientBatch_table_get(checkIbId, new AsyncCallback<IngredientBatchDTO>(){
						@Override
						public void onSuccess(IngredientBatchDTO result) {
							booln = false;
						}
						@Override
						public void onFailure(Throwable caught) {
							booln = true;
						}
					});
				}		
			});

			if(booln == true){
				mc.databaseService.ingredientBatch_table_create(ingrBatchDTO, new AsyncCallback<Void>() {

					@Override
					public void onSuccess(Void result) {

					}

					@Override
					public void onFailure(Throwable caught) {

					}
				});	
			}
			return new CreateIngredientBatchView (ingrBatchDTO, mc);
		}else{
			return new CreateIngredientBatchView(null, mc);
		}
	}

	public Widget listIngredientBatch()
	{
		mc.databaseService.ingredientBatch_table_list(new AsyncCallback<ArrayList<IngredientBatchDTO>>(){

			@Override
			public void onSuccess(ArrayList<IngredientBatchDTO> list)
			{
				ingrBatchDTO = list;
			}

			@Override
			public void onFailure(Throwable caught)
			{
				ingrBatchDTO = null;
				Window.alert("Kunne ikke hente liste fra server" + caught.getMessage());
			}
		});
		return new ListIngredientBatchView(ingrBatchDTO, mc);
	}
}
