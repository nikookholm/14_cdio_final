package code.database;

import java.util.ArrayList;
import java.util.List;

public interface IngredientBatchDAO {
	IngredientBatchDTO getIngredientBatch(int rbId) throws DALException;
	ArrayList<IngredientBatchDTO> getIngredientBatchList() throws DALException;
	ArrayList<IngredientBatchDTO> getIngredientBatchList(int ingredientId) throws DALException;
	void createIngredientBatch(IngredientBatchDTO ingredientBatch) throws DALException;
	void updateIngredientBatch(IngredientBatchDTO ingredientBatch) throws DALException;
}

