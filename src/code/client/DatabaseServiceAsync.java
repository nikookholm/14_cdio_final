package code.client;

import java.util.ArrayList;

import code.database.IngredientBatchDTO;
import code.database.IngredientBatchImpl;
import code.database.IngredientDTO;
import code.database.ProductBatchCompDTO;
import code.database.ProductBatchDTO;
import code.database.ProductBatchImpl;
import code.database.ReceptCompDTO;
import code.database.ReceptDTO;
import code.database.UserDTO;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface DatabaseServiceAsync {

	void user_table_create(UserDTO user, AsyncCallback<Void> callback);
	void user_table_update(UserDTO user, AsyncCallback<Void> callback);
	void user_table_list(AsyncCallback<ArrayList<UserDTO>> callback);
	void user_table_get(int id, AsyncCallback<UserDTO> callback);
	
	void ingredients_table_create(IngredientDTO ingredient, AsyncCallback<Void> callback);
	void ingredients_table_update(IngredientDTO ingredient, AsyncCallback<Void> callback);
	void ingredients_table_list(AsyncCallback<ArrayList<IngredientDTO>> callback);
	void ingredients_table_get(int ingredientId, AsyncCallback<IngredientDTO> callback);
	
	void recept_table_create(ReceptDTO recept, AsyncCallback<Void> callback);
	void recept_table_list(AsyncCallback<ArrayList<ReceptDTO>> callback);
	void recept_table_get(int id, AsyncCallback<ReceptDTO> callback);
	
	void productBatch_table_create(ProductBatchDTO productBatch, AsyncCallback<Void> callback);
	void productBatch_table_list(AsyncCallback<ArrayList<ProductBatchDTO>> callback);
	void productBatch_table_get(int id, AsyncCallback<ProductBatchDTO> callback); 
	void productBatch_table_update(ProductBatchDTO productBatchDTO, AsyncCallback<Void> callback);
	
	void productBatchComp_table_list(AsyncCallback<ArrayList<ProductBatchCompDTO>> callback);
	
	void ingredientBatch_table_create(IngredientBatchDTO ingredient, AsyncCallback<Void> callback);
	void ingredientBatch_table_list(AsyncCallback<ArrayList<IngredientBatchDTO>> callback);
	void ingredientBatch_table_get(int id, AsyncCallback<IngredientBatchDTO> callback);
	void ingredientBatch_table_update(IngredientBatchDTO ingredientBatch, AsyncCallback<Void> callback);
	
	void receptComp_table_get(int id, AsyncCallback<ArrayList<ReceptCompDTO>> callback);
	void productBatchComp_table_create(ProductBatchCompDTO pBCompDTO, AsyncCallback<Void> callback);
	
}
