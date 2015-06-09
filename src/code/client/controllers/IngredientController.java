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
	
	IngredientDAO ingDAO;
	
	public IngredientController(MainController mc){
		this.mc = mc;
		
		
	}
	public Widget createIngredient(IngredientDTO ingDTO) {
		
//		ingDAO.createIngredient(ingDTO);
		if(ingDTO!= null){
			
			
			// opret dto i database
			mc.databaseService.ingredients_table_create(ingDTO, new AsyncCallback<Void>() {
				
				@Override
				public void onSuccess(Void result) {
					
				}
				
				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					
				}
			});			
			return null;
		}else{
			return new CreateIngredientView(mc);
		}
	}

	public List<IngredientDTO> ingredientList(){
		
		
	
		List<IngredientDTO> ingLs = ingDAO.getIngredientList();
		
		return ingLs;

		
	}
	
	public void updateIngredient(){
		
	}
	
	

}
