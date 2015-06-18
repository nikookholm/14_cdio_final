package code.client;

import java.util.ArrayList;

import code.database.DALException;
import code.database.IngredientBatchDTO;
import code.database.IngredientDTO;
import code.database.ProductBatchCompDTO;
import code.database.ProductBatchDTO;
import code.database.ReceptCompDTO;
import code.database.ReceptDTO;
import code.database.UserDTO;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("database")
public interface DatabaseService extends RemoteService {
	void	  				 user_table_create(UserDTO user);
	ArrayList<UserDTO>		 user_table_list();
	void 					 user_table_update(UserDTO user);
	UserDTO					 user_table_get(int id);
	
	void	 				 ingredients_table_create(IngredientDTO ingredient);
	void 					 ingredients_table_update(IngredientDTO ingredient);
	ArrayList<IngredientDTO> ingredients_table_list();
	IngredientDTO			 ingredients_table_get(int ingredientId) throws DALException;
	
	void 				 	 recept_table_create(ReceptDTO recept);
	ArrayList<ReceptDTO> 	 recept_table_list();
	ReceptDTO				 recept_table_get(int id);
	
	void 					   productBatch_table_create(ProductBatchDTO productBatch);
	ArrayList<ProductBatchDTO> productBatch_table_list();
	ProductBatchDTO			   productBatch_table_get(int id);
	void					   productBatch_table_update(ProductBatchDTO productBatchDTO);
	
	ArrayList<ProductBatchCompDTO> productBatchComp_table_list();
	
	void ingredientBatch_table_create(IngredientBatchDTO ingredientBatch);
	ArrayList<IngredientBatchDTO> ingredientBatch_table_list();
	IngredientBatchDTO			ingredientBatch_table_get(int id);
	void						ingredientBatch_table_update(IngredientBatchDTO ingredientBatch);
	
	ArrayList<ReceptCompDTO> receptComp_table_get(int id);
}
