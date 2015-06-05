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
		
		// aktøren logger ind i systemet. checker for dens ID nr. 
		
		
	}
	
	private void confirmOperator()
	{
		
		// som skal godtage operatøren før han kan afveje
		
	}
	
	private void chooseProductNumber()
	{
		
		// indtastning af produktnummer, som allerede findes i databasen, hvis ikke afviser den
		
	}
	
	private void ingredientLine(IngredientDTO ingredient)
	{
		
		// udfører de nedstående metoder
		updateStatus();
		clearAndTara();
		enterIngredientBatchNumber();
		updateStatus();
	}
	
	private void clearAndTara()
	{
		// clear vægten så man kan tarere
		
	}
	
	private void updateStatus()
	{
		// updatere listen af de indtastede værdier, fx. de afvejde ingredienser
		
	}
	
	private void enterIngredientBatchNumber()
	{
		// indtastning af ingredientsbatch nummeret
		// henter batch nummerets ingredienser
		
		
	}

}
