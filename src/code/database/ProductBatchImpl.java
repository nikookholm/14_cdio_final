package code.database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProductBatchImpl implements ProductBatchDAO {

	@Override
	public ProductBatchDTO getProductBatch(int pbId) throws DALException {
		ResultSet rs = Connector.doQuery("SELECT * FROM productbatch WHERE pb_id = " + pbId);
	    try {
	    	if (!rs.first()) throw new DALException("productbatch" + pbId + " findes ikke");
	    	return new ProductBatchDTO (rs.getInt("pb_id"), rs.getInt("recept_id"), rs.getInt("status"));
	    }
	    catch (SQLException e) {throw new DALException(e); }
	}

	
	@Override
	public void createProductBatch(ProductBatchDTO productbatch) throws DALException {
		Connector.doUpdate(
				"INSERT INTO productbatch(pb_id, recept_id, status) VALUES " +
				"(" + productbatch.getPbId() + ", '" + productbatch.getReceptId() + "', '" + productbatch.getStatus() + "')"
			);
	}

	@Override
	public void updateProductBatch(ProductBatchDTO productbatch) throws DALException {
		Connector.doUpdate(
				"UPDATE productbatch SET status = '" + productbatch.getStatus() + "', recept_id =  '" + productbatch.getReceptId() + "' WHERE pb_id = " + productbatch.getPbId()
		);
	}
	
	@Override
	public List<ProductBatchDTO> getProductBatchList() throws DALException {
		List<ProductBatchDTO> list = new ArrayList<ProductBatchDTO>();
		ResultSet rs = Connector.doQuery("SELECT * FROM productbatch");
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
