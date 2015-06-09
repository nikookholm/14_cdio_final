package code.client.controllers;

import java.util.List;

import code.database.Connector;
import code.database.IngredientDAO;
import code.database.IngredientDTO;

public class IngredientController {
	
	IngredientDAO ingDAO;
	IngredientDAO ingDTO;
	
	
	public void createIngredient(IngredientDTO ingDTO) throws Exception {
		
		Connector con = new Connector();
		ingDAO.createIngredient(ingDTO);
		
		printIngredient();
	}
	
	private void printIngredient() {
		// TODO Auto-generated method stub
		
	}

	public List<IngredientDTO> ingredientList() throws Exception{
		
		Connector con = new Connector();
		List<IngredientDTO> ingLs = ingDAO.getIngredientList();
		
		return ingLs;

		
	}
	
	public void updateIngredient(){
		
	}
	
	

}
