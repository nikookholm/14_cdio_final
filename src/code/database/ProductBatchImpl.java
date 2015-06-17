package code.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ProductBatchImpl implements ProductBatchDAO {

	SimpleDateFormat dateTime = new SimpleDateFormat("yyyy/MM/dd - HH:mm");
	String datI = dateTime.format(new Date());
	
	@Override
	public ProductBatchDTO getProductBatch(int pbId) throws DALException {
		ResultSet rs = Connector.doQuery("SELECT * FROM productbatch WHERE pb_id = " + pbId);
	    try {
	    	if (!rs.first()) throw new DALException("productbatch" + pbId + " findes ikke");
	    	return new ProductBatchDTO (rs.getInt("pb_id"), rs.getInt("recept_id"), rs.getInt("status"), rs.getString("date") );
	    }
	    catch (SQLException e) {throw new DALException(e); }
	}
	
	@Override
	public void createProductBatch(ProductBatchDTO productbatch) throws DALException {
		
		
		Connector.doUpdate(
				"INSERT INTO productbatch(pb_id, recept_id, status, date) VALUES " +
				"(" + productbatch.getPbId() + ", '" + productbatch.getReceptId() + "', '" + productbatch.getStatus() + "','" + datI + "')"
			);
	}

	@Override
	public void updateProductBatch(ProductBatchDTO productbatch) throws DALException {
		Connector.doUpdate(
				"UPDATE productbatch SET status = '" + productbatch.getStatus() + "', recept_id =  '" + productbatch.getReceptId() 
					+ "', date = '" + datI + "' WHERE pb_id = " + productbatch.getPbId()
		);
	}
	
	@Override
	public ArrayList<ProductBatchDTO> getProductBatchList() throws DALException {
		ArrayList<ProductBatchDTO> list = new ArrayList<ProductBatchDTO>();
		ResultSet rs = Connector.doQuery("SELECT * FROM productbatch");
		try
		{
			while (rs.next()) 
			{
				list.add(new ProductBatchDTO(rs.getInt("pb_id"), rs.getInt("recept_id"), rs.getInt("status"), rs.getString("date") ));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}

}
