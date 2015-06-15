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

	public IngredientBatchController(MainController mc)
	{
		this.mc = mc;
	}
	
	public Widget createIngredientBatch(IngredientBatchDTO ingrBatchDTO) {

		if(ingrBatchDTO!= null){
			
			// opret dto i database
			mc.databaseService.ingredientBatch_table_create(ingrBatchDTO, new AsyncCallback<Void>() {
				
				@Override
				public void onSuccess(Void result) {
						
				}
				
				@Override
				public void onFailure(Throwable caught) {
					
				}
			});			
			return new CreateIngredientBatchView(mc, ingrBatchDTO);
		}else{
			
			return new CreateIngredientBatchView(mc, null);
		}
	}
	
	public Widget listIngredientBatch()
	{
		mc.databaseService.ingredientBatch_table_list(new AsyncCallback<ArrayList<IngredientBatchDTO>>(){
			
			@Override
			public void onFailure(Throwable caught)
			{
				ingrBatchDTO = null;
				Window.alert("Kunne ikke hente liste fra server" + caught.getMessage());
			}
			@Override
			public void onSuccess(ArrayList<IngredientBatchDTO> list)
			{
				ingrBatchDTO = list;
			}
		});
		return new ListIngredientBatchView(mc);
	}
}
