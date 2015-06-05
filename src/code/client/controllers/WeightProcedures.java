package code.client.controllers;

import code.database.IngredientDTO;

public class WeightProcedures {
	
	public void start()
	{
		login();
		confirmOperator();
		chooseProductNumber();
		
		for (IngredientDTO ingredient : ALLE_RAAVARE_LINJER)
		{
			ingredientLine(ingredient);
		}
	}
	
	private void login()
	{
		
	}
	
	private void confirmOperator()
	{
		
	}
	
	private void chooseProductNumber()
	{
		
	}
	
	private void ingredientLine(IngredientDTO ingredient)
	{
		updateStatus();
		clearAndTara();
		enterIngredientBatchNumber();
		updateStatus();
	}
	
	private void clearAndTara()
	{
		
	}
	
	private void updateStatus()
	{
		
	}
	
	private void enterIngredientBatchNumber()
	{
		
	}
	
	
	

}
