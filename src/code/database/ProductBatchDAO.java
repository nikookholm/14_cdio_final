package code.database;

import java.util.ArrayList;
import java.util.List;

public interface ProductBatchDAO {
	ProductBatchDTO getProductBatch(int pbId) throws DALException;
	ArrayList<ProductBatchDTO> getProductBatchList() throws DALException;
	void createProductBatch(ProductBatchDTO productbatch) throws DALException;
	void updateProductBatch(ProductBatchDTO productbatch) throws DALException;
}