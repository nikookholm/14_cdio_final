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
		try {
			new UserImpl().createUser(user);
		} catch (DALException e) {}
		
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
		try {
			this.user.updateUser(user);
		} catch (DALException e) {}	
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
	public void ingredients_table_create(IngredientDTO ingredient) {
		try {
			this.ingredient.createIngredient(ingredient);
		} catch (DALException e) {
			// ERROR :'(
		}
		
	}

	@Override
	public void ingredients_table_update(IngredientDTO ingredient) {
		try {
			this.ingredient.updateIngredient(ingredient);
		} catch (DALException e) {}
		
	}

	@Override
	public ArrayList<IngredientDTO> ingredients_table_list() {
		try {
			return ingredient.getIngredientList();
		} catch (DALException e) {
			return null;
		}
	}

	@Override
	public void recept_table_create(ReceptDTO recept) {
		try {
			this.recept.createRecept(recept);
		} catch (DALException e) {}
		
	}

	@Override
	public ArrayList<ReceptDTO> recept_table_list() {
		try {
			return recept.getReceptList();
		} catch (DALException e) {
			return null;
		}
	}

	@Override
	public void productBatch_table_create(ProductBatchDTO productBatch) {
		try {
			this.productBatch.createProductBatch(productBatch);
		} catch (DALException e) {}
	}

	@Override
	public ArrayList<ProductBatchDTO> productBatch_table_list() {
		try {
			return productBatch.getProductBatchList();
		} catch (DALException e) {
			return null;
		}
	}

	@Override
	public void ingredientBatch_table_create(IngredientBatchDTO ingredientBatch) {
		try {
			this.ingredientBatch.createIngredientBatch(ingredientBatch);
		} catch (DALException e) {}
		
	}

	@Override
	public ArrayList<IngredientBatchDTO> ingredientBatch_table_list() {
		try {
			return ingredientBatch.getIngredientBatchList();
		} catch (DALException e) {
			return null;
		}
	}

	@Override
	public ArrayList<ProductBatchCompDTO> productBatchComp_table_list() {
		try {
			return productBatchComp.getProductBatchCompList();
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

	@Override
	public IngredientBatchDTO ingredientBatch_table_get(int id) {
		try {
			return ingredientBatch.getIngredientBatch(id);
		} catch (DALException e) {
			return null;
		}
	}

	@Override
	public ArrayList<ReceptCompDTO> receptComp_table_get(int id) {
		try {
			return receptComp.getReceptCompList(id);
		} catch (DALException e) {
			return null;
		}
	}

	@Override
	public void ingredientBatch_table_update(IngredientBatchDTO ingredientBatch) {
		try {
			this.ingredientBatch.updateIngredientBatch(ingredientBatch);
		} catch (DALException e) {}
	}

	@Override
	public void productBatch_table_update(ProductBatchDTO productBatchDTO) {
		try {
			productBatch.updateProductBatch(productBatchDTO);
		} catch (DALException e) {}
	}

	@Override
	public IngredientDTO ingredients_table_get(int ingredientId) throws DALException {

		return ingredient.getIngredient(ingredientId);

	}



	
}
