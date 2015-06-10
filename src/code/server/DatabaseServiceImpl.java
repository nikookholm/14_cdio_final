package code.server;

import java.sql.SQLException;
import java.util.ArrayList;

import code.client.DatabaseService;
import code.database.*;
import code.shared.FieldVerifier;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class DatabaseServiceImpl extends RemoteServiceServlet implements
		DatabaseService {
	
	private IngredientBatchDAO 	ingredientBatch  = new IngredientBatchImpl();
	private IngredientDAO	   	ingredient	   	 = new IngredientImpl();
	private ProductBatchCompDAO productBatchComp = new ProductBatchCompImpl();
	private ProductBatchDAO 	productBatch	 = new ProductBatchImpl();
	private ReceptCompDAO		receptComp		 = new ReceptCompImpl();
	private ReceptDAO			recept			 = new ReceptImpl();
	private UserDAO				user			 = new UserImpl();
	
	public DatabaseServiceImpl() {
		try {
			new Connector();
		} catch (Exception e) {
		}
	}

	@Override
	public void user_table_create(UserDTO user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<UserDTO> user_table_list() {
		
		try {
			return user.getUserList();
		} catch (DALException e) {
			return null;
		}
		
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

	@Override
	public void productBatchComp_table_create(ProductBatchCompDTO pbComp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<ProductBatchCompDTO> productBatchComp_table_list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDTO user_table_get(int id) {
		
		try {
			return user.getUser(id);
		} catch (DALException e) {
			return null;
		}
		
	}

	@Override
	public ReceptDTO recept_table_get(int id) {
		try {
			return recept.getRecept(id);
		} catch (DALException e) {
			return null;
		}
	}

	@Override
	public ProductBatchDTO productBatch_table_get(int id) {
		try {
			return productBatch.getProductBatch(id);
		} catch (DALException e) {
			return null;
		}
	}



	
}
