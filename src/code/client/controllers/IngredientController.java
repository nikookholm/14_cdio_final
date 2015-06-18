package code.client.controllers;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

import code.client.views.CreateIngredientView;
import code.client.views.InfomationWidget;
import code.client.views.ListIngredientsView;
import code.database.DALException;
import code.database.IngredientDTO;

public class IngredientController {
	
	private MainController			 mc;
	private ArrayList<IngredientDTO> ingls;
	private Widget					 returnInfo = null;
	
	public IngredientController(MainController mc){
		this.mc = mc;
	}
	
	public Widget createIngredient(final Object ingDTO) {
		
		if(ingDTO instanceof IngredientDTO){
			
			mc.databaseService.ingredients_table_create((IngredientDTO)ingDTO, new AsyncCallback<Void>() {		
				@Override
				public void onSuccess(Void result) {
					returnInfo = InfomationWidget.showInfomation("Bruger \"" + ((IngredientDTO)ingDTO).getIngredientName() + "\" blev oprettet!");						
				}
				
				@Override
				public void onFailure(Throwable caught) {
					returnInfo = InfomationWidget.showInfomation(caught);
				}
			});			

		}
		return new CreateIngredientView(mc, returnInfo);
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
	
	public Widget updateIngredient(IngredientDTO ingrDto){
		
		mc.databaseService.ingredients_table_update(ingrDto, new AsyncCallback<Void>() {
			
			@Override
			public void onSuccess(Void result) {
				mc.show(mc.getIngredientController().listIngredients());
			}
			
			@Override
			public void onFailure(Throwable caught) {
			}
		});
		
		return null;
	}

}