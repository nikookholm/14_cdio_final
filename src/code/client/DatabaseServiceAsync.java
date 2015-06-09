package code.client;

import java.util.ArrayList;

import code.database.IngredientBatchDTO;
import code.database.IngredientBatchImpl;
import code.database.IngredientDTO;
import code.database.ProductBatchDTO;
import code.database.ProductBatchImpl;
import code.database.ReceptDTO;
import code.database.UserDTO;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface DatabaseServiceAsync {

	void user_table_create(UserDTO user, AsyncCallback<Void> callback);
	void user_table_update(UserDTO user, AsyncCallback<Void> callback);
	void user_table_list(AsyncCallback<ArrayList<UserDTO>> callback);
	
	void ingredients_table_create(IngredientDTO ingredient, AsyncCallback<Void> callback);
	void ingredients_table_update(IngredientDTO ingredient, AsyncCallback<Void> callback);
	void ingredients_table_list(AsyncCallback<ArrayList<IngredientDTO>> callback);
	
	void recept_table_create(ReceptDTO recept, AsyncCallback<Void> callback);
	void recept_table_list(AsyncCallback<ArrayList<ReceptDTO>> callback);
	
	void productBatch_table_create(ProductBatchDTO productBatch, AsyncCallback<Void> callback);
	void productBatch_table_list(AsyncCallback<ArrayList<ProductBatchDTO>> callback);
	
	void ingredientBatch_table_create(IngredientBatchDTO ingredient, AsyncCallback<Void> callback);
	void ingredientBatch_table_list(AsyncCallback<ArrayList<IngredientBatchDTO>> callback);
	
	
}
