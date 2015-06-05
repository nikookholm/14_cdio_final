package code.database;

import java.util.List;

public interface ProductBatchDAO {
	ProductBatchDTO getProductBatch(int pbId) throws DALException;
	List<ProductBatchDTO> getProductBatchList() throws DALException;
	void createProductBatch(ProductBatchDTO productbatch) throws DALException;
	void updateProductBatch(ProductBatchDTO productbatch) throws DALException;
}