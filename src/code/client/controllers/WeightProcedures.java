package code.client.controllers;


import java.util.ArrayList;

import code.client.DatabaseService;
import code.database.IngredientBatchDTO;
import code.database.IngredientDTO;
import code.database.ProductBatchCompDTO;
import code.database.ProductBatchDTO;
import code.database.ReceptDTO;
import code.database.UserDTO;
import code.server.DatabaseServiceImpl;
import code.server.WeightServiceImpl;

public class WeightProcedures {
	
	private int oprNr;
	DatabaseService dbs = new DatabaseServiceImpl();
	
	public WeightProcedures(WeightServiceImpl weightService){
		start();
	}
	
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
		
		// skal tjekke login igennem rm20
//		brug weight service
//		return string name of operator
//				parse string til int
//		gemmes i oprNr
		
//		skal kigge på den returnerede rm20 besked hvis den siger "ok" Så skal operatøren
		//have mulighed for at bruge de forskellige kommandoer i vægten.
//		if(dbs.getOperatorId()>null){
//			dbs.getOperatorId;
//		}else{
//			
//		}
	}
	
	private void confirmOperator()
	{
		
	}
		
	private void chooseProductNumber()
	{
		
//		if(dbs.ProductBatch()>null){
//			dbs.getRecept();
//		}else{
//		adla,sdlasd?
//		}
		
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
		
//		weightService.clearWeight();
//		weightService.taraWeight();
		
	}
	
	private void updateStatus()
	{

		
	}
		// opdatere listen af de indtastede værdier, fx. de afvejde ingredienser
		
	
	private void enterIngredientBatchNumber()
	{
		
//		if(dbs.ingredient_table_list>null){
//			dbs.ingredientBatch_table_list();
//			dbs.ingredientBatchDTO();
//		}	
		// indtastning af ingredientsbatch nummeret
		// henter batch nummerets ingredienser	
	}

}
