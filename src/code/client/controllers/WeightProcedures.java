package code.client.controllers;


import java.util.ArrayList;

import org.apache.tools.ant.taskdefs.Sleep;

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
	IngredientBatchDTO iBDTO;
	IngredientDTO iDTO;

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
			opr = dbs.user_table_get(oprNr);
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
		ReceptDTO recept = null;
		ProductBatchDTO pbDTO;
		@SuppressWarnings("null")
		String message1 = "vil du afveje" + recept.getReceptName();
		String verifyrReceptBalancing;
		String valid = "1";
		ArrayList<IngredientDTO> allIngredient;

		produktNummer = ws.rm20(4, message);

		if(produktNummer.matches("\\D")){
			produktNr = Integer.parseInt(produktNummer);
			pbDTO = dbs.productBatch_table_get(produktNr);
			recept = dbs.recept_table_get(pbDTO.getReceptId());
			recept.getReceptName();
			verifyrReceptBalancing = ws.rm20(4, message1);
			if(verifyrReceptBalancing.equals(valid)){
				allIngredient = dbs.ingredients_table_list();

				ingredientLine(ingredient);
			}
		}else{
			chooseProductNumber();
		}

	}

	private void ingredientLine(IngredientDTO ingredient) throws WeightException, InterruptedException
	{		
		clearAndTara();
		updateStatus();
		enterIngredientBatchNumber();
		updateStatus();
	}

	private void clearAndTara() throws WeightException, InterruptedException
	{

		//		double checkWeight = ws.getWeight();
		String message = "Sæt tarabeholder på vægten og tryk OK";
		String message2 = "Sæt ingrediens på vægten, afvej og tryk OK";
	

		ws.clearDisplay();
		while(ws.getWeight() > 0){
			ws.rm20(4,"Aflast vægten, tak");
		}
		ws.getTara();
		ws.printToDisplay(message);
		ws.getTara();
		
		ws.rm20(4, message2);
		ws.getWeight();


	}

	private void updateStatus()
	{
		
		
		
		// opdatere listen af de indtastede værdier, fx. de afvejde ingredienser

	}

	private void enterIngredientBatchNumber() throws WeightException
	{
		
		double savedValue = 0;
		int ingredientID;
		String verifyId;
		String message1 = "indtast ingredientsBatch nummer på første ingredients";
		
		
		verifyId = ws.rm20(4, message1);
			
			if(verifyId.matches("\\D")){
				ingredientID = Integer.parseInt(verifyId);
				iBDTO = dbs.ingredientBatch_table_get(ingredientID);
				iBDTO.setMaengde(ingredientID);
				
					if(iBDTO==null){
						enterIngredientBatchNumber();
					}			
			}
				
//		IngredientBatchDTO iBDTO = null;
//		ingredientID = iBDTO.getIngredientId();
//		iBDTO.setMaengde(savedValue);


	}
}
