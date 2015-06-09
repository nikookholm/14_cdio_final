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

public interface WeightServiceAsync {

	void getTara(AsyncCallback<Double> callback);
	void getWeight(AsyncCallback<Double> callback);
	void rm20(int type, String message, AsyncCallback<String> callback);
	void printToDisplay(String message, AsyncCallback<Boolean> callback);
	void clearDisplay(AsyncCallback<Void> callback);
	void listenForTarget(AsyncCallback<Void> callback);
	
	
}
