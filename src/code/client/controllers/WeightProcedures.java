package code.client.controllers;

import java.util.ArrayList;

import org.apache.bcel.generic.CPInstruction;
import org.apache.tools.ant.taskdefs.Sleep;

import com.google.gwt.i18n.client.NumberFormat;
import com.ibm.icu.text.DecimalFormat;

import code.client.DatabaseService;
import code.client.WeightService;
import code.database.DALException;
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

	DatabaseService dbs;
	WeightService ws;


	public WeightProcedures(WeightServiceImpl weightService) throws Exception{
		dbs = new DatabaseServiceImpl();
		ws = weightService;
		try {
			start();
		} catch (WeightException e) {

		}
	}

	public void start() throws WeightException, DALException
	{


		ingredientsLines = dbs.receptComp_table_get(0);
		login();
		confirmOperator();
		chooseProduct();
		updateStatus(1);

		for (ReceptCompDTO ingredient : ingredientsLines)
		{
			try {
				ingredientLine(ingredient);
			} catch (InterruptedException e) {

			}

		}
		updateStatus(2);

		// if(slutproduktion==true){
		// printer sluttidspunktet 
		//			}
	}


	private void login() throws WeightException, DALException
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


	private void confirmOperator() throws WeightException
	{
		System.out.println("Vi er nu i confirmOperator");
		String validateOpr;
		String message = opr.getOprName() + "?";
		String valid = "1";

		validateOpr = ws.rm20(8, message);
		System.out.println(validateOpr.trim());

		if(!validateOpr.equals(valid)){
			try {
				login();
			} catch (DALException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void chooseProduct() throws WeightException
	{
		System.out.println("VI er nu i enterProdukt!");

		int productNo = Integer.parseInt(ws.rm20(8, "ProdBNr.."));

		String verifyrReceptBalancing;
		String valid = "1";

		try {
			pBDTO	  = dbs.productBatch_table_get(productNo);
			receptDTO = dbs.recept_table_get(pBDTO.getReceptId());
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	

		String validateRecept = ws.rm20(8, receptDTO.getReceptName()+"?");

		if(validateRecept.equals(valid)){
			try {
				ingredientsLines = dbs.receptComp_table_get(receptDTO.getReceptId());
			} catch (DALException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			chooseProduct();
		}

	}

	private void updateStatus(int status)
	{		
		pBDTO.setStatus(status);
		try {
			dbs.productBatch_table_update(pBDTO);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void clearAndTara() throws WeightException, InterruptedException
	{


		String validateClearWeight = ws.rm20(8,"Aflast vaegt, tryk 1 /OK");
		System.out.println("massen på vægten er " + ws.getWeight());
		double checkWeight = ws.getWeight();
	
		if(validateClearWeight.equals("1")&&(checkWeight == 0.000)){
			validateClearWeight = null;
			ws.getTara();
			String placeContainer =	ws.rm20(8,"S\u00E6t box tryk 1 /Ok");
			if(placeContainer.equals("1")&&(ws.getWeight() > 0.000)){
				ws.getTara();
			}
			else{
				clearAndTara();
			}
		}
		else{
			clearAndTara();
		}

	}

	private void enterIngredientBatchNumber() throws WeightException
	{
		double weightValue = 0;
		int ingredientID = -1; // sat til minus 1, er initialiseret og sikker

		String verifyId = ws.rm20(8, "Indtast RaaBatchNr /OK");
		try {
			iBDTO = dbs.ingredientBatch_table_get(Integer.parseInt(verifyId));
			
		} catch (NumberFormatException e) {
		
			e.printStackTrace();
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(iBDTO==null){
			enterIngredientBatchNumber();
		}
		else{
			//			String ingredientMass = ws.rm20(8, "")
			iBDTO.setMaengde(weightValue);
			try {
				dbs.ingredientBatch_table_update(iBDTO);
			} catch (DALException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		}			
	}
	//I4 A "3154307"
	//		IngredientBatchDTO iBDTO = null;
	//		ingredientID = iBDTO.getIngredientId();
	//		iBDTO.setMaengde(savedValue);
	//}
	private void ingredientLine(ReceptCompDTO ingredient) throws WeightException, InterruptedException
	{			
		try {
			ingredientsLines = dbs.receptComp_table_get(0);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		clearAndTara();
		enterIngredientBatchNumber();
	}
}
