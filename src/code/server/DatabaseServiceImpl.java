package code.server;

import java.util.ArrayList;

import code.client.DatabaseService;
import code.database.IngredientBatchDTO;
import code.database.IngredientDTO;
import code.database.ProductBatchDTO;
import code.database.ReceptDTO;
import code.database.UserDTO;
import code.shared.FieldVerifier;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class DatabaseServiceImpl extends RemoteServiceServlet implements
		DatabaseService {

	@Override
	public void user_table_create(UserDTO user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<UserDTO> user_table_list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void user_table_update(UserDTO user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ingredients_table_create(IngredientDTO ingredient) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ingredients_table_update(IngredientDTO ingredient) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<IngredientDTO> ingredients_table_list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void recept_table_create(ReceptDTO recept) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<ReceptDTO> recept_table_list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void productBatch_table_create(ProductBatchDTO productBatch) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<ProductBatchDTO> productBatch_table_list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void ingredientBatch_table_create(IngredientBatchDTO ingredientBatch) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<IngredientBatchDTO> ingredientBatch_table_list() {
		// TODO Auto-generated method stub
		return null;
	}



	
}
