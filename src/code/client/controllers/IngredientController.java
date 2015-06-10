package code.client.controllers;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;

import code.client.views.CreateIngredientView;
import code.client.views.ListIngredientsView;
import code.database.IngredientDTO;

public class IngredientController {
	

	MainController mc;
	
	
	public IngredientController(MainController mc){
		this.mc = mc;
		
		
	}
	
	public Widget createIngredient(IngredientDTO ingDTO) {

		if(ingDTO!= null){
			
			// opret dto i database
			mc.databaseService.ingredients_table_create(ingDTO, new AsyncCallback<Void>() {
				
				@Override
				public void onSuccess(Void result) {
					
					//ingDTO = ;				
				}
				
				@Override
				public void onFailure(Throwable caught) {
					
				}
			});			
			return null;
		}else{
			
			return new CreateIngredientView(mc, ingDTO);
		}
	}

	public Widget  listIngredients(){
		
		mc.databaseService.ingredients_table_list(new AsyncCallback<ArrayList<IngredientDTO>>() {
			
			@Override
			public void onSuccess(ArrayList<IngredientDTO> result) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
	
		
		return null ;//new ListIngredientsView(mc, lsIngDTO);

	}
	
	public Widget updateIngredient(){
		
		return null;
		
	}
	
	

}
