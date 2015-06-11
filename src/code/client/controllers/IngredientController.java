package code.client.controllers;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;

import code.client.views.CreateIngredientView;
import code.client.views.ListIngredientsView;
import code.database.DALException;
import code.database.IngredientDTO;

public class IngredientController {
	

	MainController mc;
	ArrayList<IngredientDTO> ingls;
	
	
	public IngredientController(MainController mc){
		this.mc = mc;
		
		
	}
	
	public Widget createIngredient(IngredientDTO ingDTO) {

		if(ingDTO!= null){
			
			// opret dto i database
			mc.databaseService.ingredients_table_create(ingDTO, new AsyncCallback<Void>() {
				
				@Override
				public void onSuccess(Void result) {
					
								
				}
				
				@Override
				public void onFailure(Throwable caught) {
					
					
				}
			});			
			return new CreateIngredientView(mc, ingDTO);
		}else{
			
			return new CreateIngredientView(mc, null);
		}
	}

	public Widget  listIngredients(){
		
		
		
		mc.databaseService.ingredients_table_list(new AsyncCallback<ArrayList<IngredientDTO>>() {
			
			@Override
			public void onSuccess(ArrayList<IngredientDTO> result) {
				ingls = result;
			}
			
			@Override
			public void onFailure(Throwable caught) {
				ingls = null;
			}
		});
		
		
			return new ListIngredientsView(mc);
	
		
	}
	
	public Widget updateIngredient(){
		
		return null;
		
	}
	
	

}
