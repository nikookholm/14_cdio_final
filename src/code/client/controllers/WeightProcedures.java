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
	WeightServiceImpl ws;


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


		login();
		confirmOperator();
		chooseProduct();
		updateStatus(1);


		for (ReceptCompDTO ingredient : ingredientsLines)
		{
			System.out.println("Jeg er i for-loopet");
			try {
				System.out.println(ingredient.getIngredientId() + " er id på ingrediensen");
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void chooseProduct() throws WeightException
	{
		System.out.println("VI er nu i enterProdukt!");

		int productNo = Integer.parseInt(ws.rm20(8, "ProdBNr.."));

		try {
			pBDTO	  = dbs.productBatch_table_get(productNo);
			receptDTO = dbs.recept_table_get(pBDTO.getReceptId());
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		String validateRecept = ws.rm20(8, receptDTO.getReceptName()+"?");

		if(validateRecept.equals("")){
			try {
				ingredientsLines = dbs.receptComp_table_get(receptDTO.getReceptId());
				System.out.println(ingredientsLines.size() + " listens størrelse");
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
		System.out.println("<---------- kommer ind i tarermetoden ------------>");


		String validateClearWeight = ws.rm20(8,"Aflast vaegt, tryk 1 /OK");
		System.out.println("massen på vægten er " + ws.getWeight());
		double checkWeight = ws.getWeight();

		if(!validateClearWeight.equals("")&&(!(checkWeight <= 0.000))){
			clearAndTara();
		}
		else{
			validateClearWeight = null;
			ws.getTara();
			String placeContainer =	ws.rm20(8,"S\u00E6t box tryk 1 /Ok");
			if(!placeContainer.equals("1")&&(ws.getWeight() > 0.000)){
				clearAndTara();
			}
			else{
				ws.getTara();

			}
		}




		System.out.println("Afslutter Tarermetoden");

	}

	private void enterIngredientBatchNumber(ReceptCompDTO receptComp) throws WeightException
	{
		System.out.println("<----------- EnterIngredientBatchNumber-------------->");

		int ingredientID = -1; // sat til minus 1, er initialiseret og sikker

		System.out.println(receptComp.getIngredientId());
		String verifyId = ws.rm20(8, "Indtast RaaBatchNr /OK");
		try {
			iBDTO = dbs.ingredientBatch_table_get(Integer.parseInt(verifyId));

			if(iBDTO.getIngredientId() ==  receptComp.getIngredientId()){
				ws.p111("AfVej " + receptComp.getNomNetto() + " " + dbs.ingredients_table_get(receptComp.getIngredientId()));
				ws.doSTcommand(receptComp.getNomNetto()-(receptComp.getNomNetto()*receptComp.getTolerance()));
				double mass = 0;
				boolean notThereYet = true;
				double min = mass - (receptComp.getNomNetto()*receptComp.getTolerance());
				double max = mass + (receptComp.getNomNetto()*receptComp.getTolerance());



				//			while(!notThereYet){
				//				mass = ws.getWeight();
				//				if(mass >= min&&mass <= max){
				//					notThereYet = false;
				//				}
			}


			//	if(confirmMass.equals("1")&&(mass >= min&&mass <= max)){
			//					iBDTO.setMaengde(iBDTO.getMaengde() - mass);
			//				pBCDTO = new ProductBatchCompDTO(pBDTO.getPbId(), ingredientID, ws.getTara(), mass, opr.getOprId());
			try {
				dbs.ingredientBatch_table_update(iBDTO);
			} catch (DALException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	} catch (NumberFormatException e) {

		e.printStackTrace();
	} catch (DALException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}


}


private void ingredientLine(ReceptCompDTO ingredient) throws WeightException, InterruptedException
{			

	clearAndTara();
	enterIngredientBatchNumber(ingredient);
}
}
