package code.client.controllers;


import java.util.ArrayList;

import code.client.DatabaseService;
import code.client.WeightService;
import code.database.IngredientBatchDTO;
import code.database.IngredientDTO;
import code.database.ProductBatchCompDTO;
import code.database.ProductBatchDTO;
import code.database.ReceptDTO;
import code.database.UserDTO;
import code.server.DatabaseServiceImpl;
import code.server.WeightServiceImpl;
import code.shared.WeightException;

public class WeightProcedures {

	UserDTO opr;
	
	DatabaseService dbs = new DatabaseServiceImpl();
	WeightService ws;


	public WeightProcedures(WeightServiceImpl weightService){
		ws = weightService;
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

	private void login() throws WeightException
	{

		String message = "Indtast dit operatør nr.";
		String checkOprNr;
		int oprNr;

		checkOprNr = ws.rm20(4, message);
		if(checkOprNr.matches("\\D")){
			oprNr = Integer.parseInt(checkOprNr);
			opr = (dbs.user_table_get(oprNr));
			if(opr==null){
				login();
			}
			confirmOperator();
		}else{
			login();
		}
	}

	private void confirmOperator() throws WeightException
	{
		
		String validateOpr;
		String message = "Er du" + opr;
		String valid = "1";
		
		validateOpr = ws.rm20(4, message);
		if(validateOpr.equals(valid)){
			chooseProductNumber();
		}else{
			login();	
		}
		
	}

	private void chooseProductNumber() throws WeightException
	{
		
		String message = "indtast produktnummer";
		String produktNummer;
		int produktNr;
		ReceptDTO recept;
		ProductBatchDTO pbDTO;
		
		produktNummer = ws.rm20(4, message);
		
		if(produktNummer.matches("\\D")){
			produktNr = Integer.parseInt(produktNummer);
			pbDTO = dbs.produ
			recept = dbs.recept_table_get(produktNr);
			
		}

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
