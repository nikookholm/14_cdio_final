package code.database;

import java.util.List;

public interface IngredientBatchDAO {
	IngredientBatchDTO getIngredientBatch(int rbId) throws DALException;
	List<IngredientBatchDTO> getIngredientBatchList() throws DALException;
	List<IngredientBatchDTO> getIngredientBatchList(int IngredientId) throws DALException;
	void createIngredientBatch(IngredientBatchDTO Ingredientbatch) throws DALException;
	void updateIngredientBatch(IngredientBatchDTO Ingredientbatch) throws DALException;
}

