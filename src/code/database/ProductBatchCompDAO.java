package code.database;

import java.util.List;


public interface ProductBatchCompDAO {
	ProductBatchCompDTO getProductBatchComp(int pbId, int rbId) throws DALException;
	List<ProductBatchCompDTO> getProductBatchCompList(int pbId) throws DALException;
	List<ProductBatchCompDTO> getProductBatchCompList() throws DALException;
	void createProductBatchComp(ProductBatchCompDTO produktbatchcomponent) throws DALException;
	void updateProductBatchComp(ProductBatchCompDTO produktbatchcomponent) throws DALException;	
}

