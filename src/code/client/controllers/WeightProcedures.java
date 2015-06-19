package code.client.controllers;

import java.util.ArrayList;

import org.apache.bcel.generic.CPInstruction;
import org.apache.tools.ant.taskdefs.Sleep;

import com.google.gwt.aria.client.MathRole;
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
	WeightServiceImpl ws;
	double tara;
	double actualMass;


	public WeightProcedures(WeightServiceImpl weightService){ 
		try{
			dbs = new DatabaseServiceImpl();
			ws = weightService;
			start();
		}catch(Exception e){
			System.out.println("Databasen kunne ikke oprettes");
		}
	}

	public void start()
	{

		try{
			login();
			confirmOperator();
			chooseProduct();
			updateStatus(1);


			for (ReceptCompDTO ingredient : ingredientsLines)
			{


				System.out.println(ingredient.getIngredientId() + " er id på ingrediensen");
				ingredientLine(ingredient);

			}
			updateStatus(2);

			// if(slutproduktion==true){
			// printer sluttidspunktet 
			//			}
		}catch(WeightException e){

		}catch(DALException e){
			ws.p111("db-fejl kontakt administrator");
		}
	}


	private void login() throws WeightException, DALException
	{
		String message = "nr.";
		String checkOprNr;
		int oprNr;

		checkOprNr = ws.rm20(8, message);


		System.out.println("CheckOprNr er : " + checkOprNr);

		System.out.println(checkOprNr + " er checkOprnr i Login()");

		oprNr = Integer.parseInt(checkOprNr);
		System.out.println("oprNummer er " + oprNr);
		opr = dbs.user_table_get(oprNr);


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
		String valid = "";

		validateOpr = ws.rm20(8, message);
		System.out.println(validateOpr.trim());

		if(!validateOpr.equals(valid)){
			try {
				login();
			} catch (DALException e) {
				ws.p111("db-fejl kontakt administrator");
			}
		}
	}

	private void chooseProduct() 
	{
		System.out.println("VI er nu i enterProdukt!");

		int productNo;
		try {
			productNo = Integer.parseInt(ws.rm20(8, "ProdBNr."));
			pBDTO	  = dbs.productBatch_table_get(productNo);
			receptDTO = dbs.recept_table_get(pBDTO.getReceptId());
			String validateRecept = ws.rm20(8, receptDTO.getReceptName()+"?");

			if(validateRecept.equals("")){
				ingredientsLines = dbs.receptComp_table_get(receptDTO.getReceptId());
				System.out.println(ingredientsLines.size() + " listens størrelse");
			}else {
				chooseProduct();
			}

		} catch (NumberFormatException e1) {
			ws.p111("Der opstod en vægtfejl numberformat");
			chooseProduct();
		} catch (WeightException e1) {
			ws.p111("Der opstod en vægtfejl");
			chooseProduct();
		}catch (DALException e) {
			ws.p111("db-fejl kontakt administrator");
		}

	}

	private void updateStatus(int status)  
	{		
		pBDTO.setStatus(status);
		try {
			dbs.productBatch_table_update(pBDTO);
		} catch (DALException e) {
			ws.p111("db-fejl Kontakt administrator");
		}
	}

	private void clearAndTara()
	{
		System.out.println("<---------- kommer ind i tarermetoden ------------>");

		try{
			String validateClearWeight = ws.rm20(8,"Aflast vaegt, tryk 1 /OK");
			System.out.println("massen på vægten er " + ws.getWeight());
			double checkWeight = ws.getWeight();

			if(!validateClearWeight.equals("")&&(!(checkWeight <= 0.000))){
				clearAndTara();
			}
			else{
				validateClearWeight = null;
				ws.getTara();
				String placeContainer =	ws.rm20(8,"placer box tryk 1 /Ok");
				if(!placeContainer.equals("")&&(ws.getWeight() > 0.000)){
					clearAndTara();
				}
				else{
					tara =	ws.getTara();
				}
			}
		}catch(WeightException e){
			ws.p111("Der opstod en fejl i vægten");
			clearAndTara();
		}

		System.out.println("Afslutter Tarermetoden");

	}

	private void enterIngredientBatchNumber(ReceptCompDTO receptComp) throws WeightException
	{
		System.out.println("<----------- EnterIngredientBatchNumber-------------->");

		int ingredientID = receptComp.getIngredientId();




		String verifyId = ws.rm20(8, "Indtast RaaBatchNr /OK");

		try {
			iDTO = dbs.ingredients_table_get(receptComp.getIngredientId());

			iBDTO = dbs.ingredientBatch_table_get(Integer.parseInt(verifyId));

			if(iBDTO.getIngredientId() ==  receptComp.getIngredientId()){
				ws.p111(iDTO.getIngredientName() + " " + receptComp.getNomNetto() );
				actualMass = ws.doSTcommand();

				double min =  actualMass - Math.round(((receptComp.getNomNetto()*receptComp.getTolerance()*1000)/1000));
				double max = actualMass + Math.round(((receptComp.getNomNetto()*receptComp.getTolerance()*1000)/1000));




				while(actualMass < min){
					ws.p111("Fyld mere i " + iDTO.getIngredientName() + " " + receptComp.getNomNetto() );
					actualMass = ws.doSTcommand();
					System.out.println(actualMass);

				}

				if (actualMass > max) {
					ws.rm20(8, "Over maks.værdi, kasser /ok");
					enterIngredientBatchNumber(receptComp);

				}
			}

			iBDTO.setMaengde(iBDTO.getMaengde() - actualMass);
			pBCDTO = new ProductBatchCompDTO(pBDTO.getPbId(), iBDTO.getRbId() , tara, actualMass, opr.getOprId());
			try {
					dbs.ingredientBatch_table_update(iBDTO);
				   dbs.productBatchComp_table_create(pBCDTO);
			} catch (DALException e) {
				ws.rm20(8, "db-fejl 1 kasser");
				enterIngredientBatchNumber(receptComp);
			}

		} catch (NumberFormatException e) {
			ws.rm20(8, "Fejl 100 - kasser /ok");
			enterIngredientBatchNumber(receptComp);
		} catch (DALException e) {
			ws.rm20(8, "Ukendt raavare /ok");
			enterIngredientBatchNumber(receptComp);
		}

		ws.p111(""); // resetter det sekundære display
	}


	private void ingredientLine(ReceptCompDTO ingredient) throws WeightException
	{			

		clearAndTara();
		enterIngredientBatchNumber(ingredient);
	}
}
