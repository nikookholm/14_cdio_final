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
	void	  				 user_table_create(UserDTO user) throws DALException;
	ArrayList<UserDTO>		 user_table_list() throws DALException;
	void 					 user_table_update(UserDTO user) throws DALException;
	UserDTO					 user_table_get(int id) throws DALException;
	
	void	 				 ingredients_table_create(IngredientDTO ingredient) throws DALException;
	void 					 ingredients_table_update(IngredientDTO ingredient) throws DALException;
	ArrayList<IngredientDTO> ingredients_table_list() throws DALException;
	IngredientDTO			 ingredients_table_get(int ingredientId) throws DALException;
	
	void 				 	 recept_table_create(ReceptDTO recept) throws DALException;
	ArrayList<ReceptDTO> 	 recept_table_list() throws DALException;
	ReceptDTO				 recept_table_get(int id) throws DALException;
	
	void 					   productBatch_table_create(ProductBatchDTO productBatch) throws DALException;
	ArrayList<ProductBatchDTO> productBatch_table_list() throws DALException;
	ProductBatchDTO			   productBatch_table_get(int id) throws DALException;
	void					   productBatch_table_update(ProductBatchDTO productBatchDTO) throws DALException;
	
	ArrayList<ProductBatchCompDTO> productBatchComp_table_list() throws DALException;
	
	void ingredientBatch_table_create(IngredientBatchDTO ingredientBatch) throws DALException;
	ArrayList<IngredientBatchDTO> ingredientBatch_table_list() throws DALException;
	IngredientBatchDTO			ingredientBatch_table_get(int id) throws DALException;
	void						ingredientBatch_table_update(IngredientBatchDTO ingredientBatch) throws DALException;
	
	ArrayList<ReceptCompDTO> receptComp_table_get(int id) throws DALException;
	void 						productBatchComp_table_create(ProductBatchCompDTO pBCompDTO) throws DALException;
}
