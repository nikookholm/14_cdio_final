package code.client.controllers;

import java.util.ArrayList;

import code.client.views.CreateIngredientBatchView;
import code.client.views.ListIngredientBatchView;
import code.database.IngredientBatchDTO;

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
	
	//Widget, der ved success opretter en råvarebatch. Ved forkert indtastning returneres intet.
	public Widget createIngredientBatch(IngredientBatchDTO ingrBatchDTO) 
	{
		this.ibDTO = ingrBatchDTO;
		
		if(ingrBatchDTO!= null){
			final int checkIngId	= ingrBatchDTO.getIngredientId();
			final int checkIbId		= ingrBatchDTO.getRbId();

			mc.databaseService.ingredientBatch_table_get(checkIngId, new AsyncCallback<IngredientBatchDTO>(){
				@Override
				public void onSuccess(IngredientBatchDTO result) {
					booln = false;
				}

				//Metoden skal fejle, da vi ikke ønsker at finde nogle ID
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
			
			//Hvis der ikke er fundet nogen ingredients og batch-id, der findes i databasen oprettes én
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

	//Widget, der kalder listen fra databaseService og returner en liste af råvarebatches, hvis success.
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
