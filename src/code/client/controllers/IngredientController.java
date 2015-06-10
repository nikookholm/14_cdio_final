package code.client.controllers;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;

import code.client.views.CreateIngredientView;
import code.database.Connector;
import code.database.IngredientDAO;
import code.database.IngredientDTO;

public class IngredientController {
	

	MainController mc;
	
	
	public IngredientController(MainController mc){
		this.mc = mc;
		
		
	}
	public Widget createIngredient(IngredientDTO ingDTO) {
		
//		IngredientDTO ingDTO;

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

	public List<IngredientDTO> ingredientList(){
		
		
	
		//List<IngredientDTO> ingLs = ingDAO.getIngredientList();
		
		return null;

		
	}
	
	public void updateIngredient(){
		
	}
	
	

}
