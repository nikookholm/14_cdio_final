package code.database;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProductBatchCompImpl implements ProductBatchCompDAO {

	
	public ProductBatchCompDTO getProductBatchComp(int pbId, int rbId)	throws DALException {
		
		ResultSet rs = Connector.doQuery(("SELECT * FROM productbatchcomponent  WHERE pb_id =" + pbId + "AND rb_id = " +  rbId));
	    try {
	    	if (!rs.first()) throw new DALException("Operatoeren " + pbId + " findes ikke");
	    	return new ProductBatchCompDTO (rs.getInt("pb_id"), rs.getInt("rb_id"), rs.getDouble("tara"), rs.getDouble("netto"), rs.getInt("opr_id"));
	    }
	    catch (SQLException e) {throw new DALException(e); }	
		
	}

	
	public ArrayList<ProductBatchCompDTO> getProductBatchCompList(int pbId)
			throws DALException {
		
		ArrayList<ProductBatchCompDTO> list = new ArrayList<ProductBatchCompDTO>();
		ResultSet rs = Connector.doQuery("SELECT * FROM productbatchcomponent WHERE pb_id= " + pbId);
		try
		{
			while (rs.next()) 
			{
				list.add(new ProductBatchCompDTO(rs.getInt("pb_id"), rs.getInt("rb_id"), rs.getDouble("tara"), rs.getDouble("netto"), rs.getInt("opr_id")));			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
		
	
	}


	public ArrayList<ProductBatchCompDTO> getProductBatchCompList()
			throws DALException {
		
		ArrayList<ProductBatchCompDTO> list = new ArrayList<ProductBatchCompDTO>();
		ResultSet rs = Connector.doQuery("SELECT * FROM productbatchcomponent");
		try
		{
			while (rs.next()) 
			{
				list.add(new ProductBatchCompDTO(rs.getInt("pb_id"), rs.getInt("rb_id"), rs.getDouble("tara"), rs.getDouble("netto"), rs.getInt("opr_id")));			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
		
	}

	
	public void createProductBatchComp(ProductBatchCompDTO productbatchcomponent)
			throws DALException {
		Connector.doUpdate("INSERT INTO productbatchcomponent (opr_id, netto, tara, rb_id, pb_id) VALUES" + 
			"(" + productbatchcomponent.getOprId() + ", " + productbatchcomponent.getNetto() + ", " + productbatchcomponent.getTara() + ", " + productbatchcomponent.getRbId() + ", " + productbatchcomponent.getPbId()+ ")" 
			);
		
	
		
	}

	
	public void updateProductBatchComp(ProductBatchCompDTO productbatchcomponent)
			throws DALException {
		Connector.doUpdate(
				"UPDATE produktbatchcomponent SET opr_id = '" + productbatchcomponent.getOprId() + "', netto = '" + productbatchcomponent.getNetto() + "', tara = '" + productbatchcomponent.getTara() + "' " + "WHERE rb_id = " + productbatchcomponent.getRbId() +
				"' " + "WHERE pb_id = " + productbatchcomponent.getPbId()); 
			
		
		
	}

}
