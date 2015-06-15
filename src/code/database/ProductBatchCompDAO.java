package code.database;

import java.util.ArrayList;
import java.util.List;


public interface ProductBatchCompDAO {
	ProductBatchCompDTO getProductBatchComp(int pbId, int rbId) throws DALException;
	ArrayList<ProductBatchCompDTO> getProductBatchCompList(int pbId) throws DALException;
	ArrayList<ProductBatchCompDTO> getProductBatchCompList() throws DALException;
	void createProductBatchComp(ProductBatchCompDTO produktbatchcomponent) throws DALException;
	void updateProductBatchComp(ProductBatchCompDTO produktbatchcomponent) throws DALException;	
}

