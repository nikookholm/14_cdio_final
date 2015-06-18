package code.client.controllers;

import java.util.ArrayList;

import code.client.views.CreateIngredientBatchView;
import code.client.views.ListIngredientBatchView;
import code.database.IngredientBatchDTO;
import code.database.IngredientDTO;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;

public class IngredientBatchController 
{
	MainController mc;
	ArrayList<IngredientBatchDTO> ingrBatchDTO;
	IngredientBatchDTO ibDTO;
	boolean ingredientboolean		= false;
	boolean ingredientbatchboolean 	= false;

	public IngredientBatchController(MainController mc)
	{
		this.mc = mc;
	}

	//Widget, der ved success opretter en råvarebatch. Ved forkert indtastning returneres intet.
	public Widget createIngredientBatch(final IngredientBatchDTO ingrBatchDTO) 
	{
		this.ibDTO = ingrBatchDTO;

		if(ingrBatchDTO != null){
			final int checkIngrId	= ingrBatchDTO.getIngredientId();
			final int checkIbId		= ingrBatchDTO.getRbId();			

			mc.databaseService.ingredients_table_get(checkIngrId, new AsyncCallback<IngredientDTO>(){
				@Override
				public void onFailure(Throwable caught) {
					ingredientboolean = false;
					Window.alert("Der findes ingen ingrediens med det id");
				}

				@Override
				public void onSuccess(IngredientDTO result) {
					ingredientboolean = true;
				}
			});
			
			mc.databaseService.ingredientBatch_table_get(checkIbId, new AsyncCallback<IngredientBatchDTO>() {
				@Override
				public void onFailure(Throwable caught) {
					ingredientbatchboolean = false;
					Window.alert("Der findes ingen råvarebatch med det id");
				}

				@Override
				public void onSuccess(IngredientBatchDTO element) {
					if(element.getRbId() == checkIbId){
					ingredientbatchboolean = true;
					Window.alert("Der findes en råvarebatch med det id");
					}
				}
			});

			//Hvis der er fundet en ingredient, der findes i databasen, og ikke en råvarebatch oprettes én råvarebatch med det angivne id
			if(ingredientboolean == true && ingredientbatchboolean != true){
				mc.databaseService.ingredientBatch_table_create(ingrBatchDTO, new AsyncCallback<Void>() {

					@Override
					public void onSuccess(Void result) {
						Window.alert("Der blev oprettet en ny råvarebatch");
					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Der blev ikke oprettet en ny råvarebatch");
					}
				});	
			}
			return new CreateIngredientBatchView(ingrBatchDTO, mc);
		}
		else{
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