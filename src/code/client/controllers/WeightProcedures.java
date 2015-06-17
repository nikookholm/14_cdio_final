package code.client.controllers;

import java.util.ArrayList;

import org.apache.bcel.generic.CPInstruction;
import org.apache.tools.ant.taskdefs.Sleep;

import code.client.DatabaseService;
import code.client.WeightService;
import code.database.IngredientBatchDTO;
import code.database.IngredientDTO;
import code.database.ProductBatchCompDTO;
import code.database.ProductBatchDTO;
import code.database.ReceptCompDTO;
import code.database.ReceptDTO;
import code.database.UserDTO;
import code.server.DatabaseServiceImpl;
import code.server.WeightServiceImpl;
import code.shared.WeightException;

public class WeightProcedures {

	UserDTO opr;
	IngredientBatchDTO iBDTO;
	IngredientDTO iDTO;
	ReceptDTO receptDTO;
	ReceptCompDTO receptCompDTO;
	ProductBatchDTO pBDTO;
	ProductBatchCompDTO pBCDTO;
	ArrayList<ReceptCompDTO> ingredientsLines;

	DatabaseService dbs = new DatabaseServiceImpl();
	WeightService ws;

	public WeightProcedures(WeightServiceImpl weightService){
		ws = weightService;
		try {
			start();
		} catch (WeightException e) {

		}
	}

	public void start() throws WeightException
	{
		login();
		confirmOperator();
		enterProductNumber();
		//		updateStatus();
		//
		//		for (ReceptCompDTO ingredient : ingredientsLines)
		//		{
		//			try {
		//				ingredientLine(ingredient);
		//			} catch (InterruptedException e) {
		//
		//			}
		//
		//		}
		//		updateStatus();

		// if(slutproduktion==true){
		// printer sluttidspunktet 
		//			}
	}


	private void login() throws WeightException
	{
		String message = "nr.";
		String checkOprNr;
		int oprNr;

		checkOprNr = ws.rm20(8, message);

		//		if(!checkOprNr.startsWith("RM20 A")){
		//			login();
		//		}
		//		else{
		//			
		//		}
		System.out.println("CheckOprNr er : " + checkOprNr);

		System.out.println(checkOprNr + " er checkOprnr i Login()");

		oprNr = Integer.parseInt(checkOprNr);
		System.out.println("oprNummer er " + oprNr);
		opr = dbs.user_table_get(oprNr);
		// System.out.println("Dit oprNr er" + opr.getOprName());

		if(opr==null){
			//	login();
			System.out.println("login er null");
		}

	}

	//	} 
	//	}
	//	}

	private void confirmOperator() throws WeightException
	{
		System.out.println("Vi er nu i confirmOperator");
		String validateOpr;
		String message = opr.getOprName() + "?";
		String valid = "1";

		validateOpr = ws.rm20(8, message);
		System.out.println(validateOpr.trim());

		if(!validateOpr.equals(valid)){
			login();
		}
	}

	private void enterProductNumber() throws WeightException
	{
		System.out.println("VI er nu i enterProdukt!");
		//
		
		int productNo = Integer.parseInt(ws.rm20(8, "ProdBNr.."));
		//String message1 = "vil du afveje" + receptDTO.getReceptName();
		String verifyrReceptBalancing;
		String valid = "1";
	receptDTO = 


	

//			produktNr = Integer.parseInt(produktNummer);
//
//			pBDTO = dbs.productBatch_table_get(produktNr);
//			receptId = pBDTO.getReceptId();
//			receptDTO = dbs.recept_table_get(receptId);
//			receptDTO.getReceptName();
//
//			verifyrReceptBalancing = ws.rm20(4, message1);
//			if(verifyrReceptBalancing.equals(valid)){
//				ingredientsLines = dbs.receptComp_table_get(receptId);
//
//				//				asda.startAfvejningsprocedure();
//			}
//		}else{
//			enterProductNumber();
//		}
	}

	private void updateStatus()
	{		

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

	private void enterIngredientBatchNumber() throws WeightException
	{
		double weightValue = 0;
		int ingredientID;
		String verifyId;
		String message1 = "indtast ingredientsBatch nummeret på den første ingredients du vil afveje";

		verifyId = ws.rm20(4, message1);
		if(verifyId.matches("\\D")){
			ingredientID = Integer.parseInt(verifyId);
			iBDTO = dbs.ingredientBatch_table_get(ingredientID);
			iBDTO.setMaengde(weightValue);
			dbs.ingredientBatch_table_update(iBDTO);
			if(iBDTO==null){
				enterIngredientBatchNumber();
			}			
		}
		//I4 A "3154307"
		//		IngredientBatchDTO iBDTO = null;
		//		ingredientID = iBDTO.getIngredientId();
		//		iBDTO.setMaengde(savedValue);
	}
	private void ingredientLine(ReceptCompDTO ingredient) throws WeightException, InterruptedException
	{			
		clearAndTara();
		enterIngredientBatchNumber();
	}
}
