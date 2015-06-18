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
	
	public DatabaseServiceImpl() throws Exception {
		new Connector();
	}

	@Override
	public void user_table_create(UserDTO user) throws DALException {
		new UserImpl().createUser(user);		
	}

	@Override
	public ArrayList<UserDTO> user_table_list() throws DALException {
		return user.getUserList();
	}

	@Override
	public void user_table_update(UserDTO user) throws DALException {
		this.user.updateUser(user);	
	}
	
	@Override
	public UserDTO user_table_get(int id)  throws DALException {
		return user.getUser(id);
	}

	@Override
	public void ingredients_table_create(IngredientDTO ingredient) throws DALException {
		this.ingredient.createIngredient(ingredient);
	}

	@Override
	public void ingredients_table_update(IngredientDTO ingredient) throws DALException {
		this.ingredient.updateIngredient(ingredient);		
	}

	@Override
	public ArrayList<IngredientDTO> ingredients_table_list() throws DALException {
		return ingredient.getIngredientList();
	}

	@Override
	public void recept_table_create(ReceptDTO recept) throws DALException {
		this.recept.createRecept(recept);
	}

	@Override
	public ArrayList<ReceptDTO> recept_table_list() throws DALException {
		return recept.getReceptList();
	}

	@Override
	public void productBatch_table_create(ProductBatchDTO productBatch) throws DALException {
		this.productBatch.createProductBatch(productBatch);
	}

	@Override
	public ArrayList<ProductBatchDTO> productBatch_table_list() throws DALException {
		return productBatch.getProductBatchList();
	}

	@Override
	public void ingredientBatch_table_create(IngredientBatchDTO ingredientBatch) throws DALException {
		this.ingredientBatch.createIngredientBatch(ingredientBatch);
	}

	@Override
	public ArrayList<IngredientBatchDTO> ingredientBatch_table_list() throws DALException {
		return ingredientBatch.getIngredientBatchList();
	}

	@Override
	public ArrayList<ProductBatchCompDTO> productBatchComp_table_list() throws DALException {
		return productBatchComp.getProductBatchCompList();
	}

	@Override
	public ReceptDTO recept_table_get(int id) throws DALException {
		return recept.getRecept(id);
	}

	@Override
	public ProductBatchDTO productBatch_table_get(int id) throws DALException {
		return productBatch.getProductBatch(id);
	}

	@Override
	public IngredientBatchDTO ingredientBatch_table_get(int id) throws DALException {
		return ingredientBatch.getIngredientBatch(id);
	}

	@Override
	public ArrayList<ReceptCompDTO> receptComp_table_get(int id) throws DALException {
		return receptComp.getReceptCompList(id);
	}

	@Override
	public void ingredientBatch_table_update(IngredientBatchDTO ingredientBatch) throws DALException {
		this.ingredientBatch.updateIngredientBatch(ingredientBatch);
	}

	@Override
	public void productBatch_table_update(ProductBatchDTO productBatchDTO) throws DALException {
		productBatch.updateProductBatch(productBatchDTO);
	}

	@Override
	public IngredientDTO ingredients_table_get(int ingredientId) throws DALException {
		return ingredient.getIngredient(ingredientId);
	}

}
