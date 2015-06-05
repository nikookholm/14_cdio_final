package code.database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProductBatchImpl implements ProductBatchDAO {

	@Override
	public ProductBatchDTO getProductBatch(int pbId) throws DALException {
		ResultSet rs = Connector.doQuery("SELECT * FROM ProductBatch WHERE pb_id = " + pbId);
	    try {
	    	if (!rs.first()) throw new DALException("ProductBatch " + pbId + " findes ikke");
	    	return new ProductBatchDTO (rs.getInt("pb_id"), rs.getInt("recept_id"), rs.getInt("status"));
	    }
	    catch (SQLException e) {throw new DALException(e); }
	}

	
	@Override
	public void createProductBatch(ProductBatchDTO ProductBatch) throws DALException {
		Connector.doUpdate(
				"INSERT INTO ProductBatch(pb_id, recept_id, status) VALUES " +
				"(" + ProductBatch.getPbId() + ", '" + ProductBatch.getReceptId() + "', '" + ProductBatch.getStatus() + "')"
			);
	}

	@Override
	public void updateProductBatch(ProductBatchDTO ProductBatch) throws DALException {
		Connector.doUpdate(
				"UPDATE ProductBatch SET status = '" + ProductBatch.getStatus() + "', recept_id =  '" + ProductBatch.getReceptId() + "' WHERE pb_id = " + ProductBatch.getPbId()
		);
	}
	
	@Override
	public List<ProductBatchDTO> getProductBatchList() throws DALException {
		List<ProductBatchDTO> list = new ArrayList<ProductBatchDTO>();
		ResultSet rs = Connector.doQuery("SELECT * FROM ProductBatch");
		try
		{
			while (rs.next()) 
			{
				list.add(new ProductBatchDTO(rs.getInt("pb_id"), rs.getInt("recept_id"), rs.getInt("status")));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}

}
