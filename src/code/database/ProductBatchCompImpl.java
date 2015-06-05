package code.database;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProductBatchCompImpl implements ProductBatchCompDAO {

	
	public ProductBatchCompDTO getProductBatchComp(int pbId, int rbId)	throws DALException {
		
		ResultSet rs = Connector.doQuery(("SELECT * FROM ProductBatchComponent  WHERE pb_id =" + pbId + "AND rb_Id = " +  rbId));
	    try {
	    	if (!rs.first()) throw new DALException("Operatoeren " + pbId + " findes ikke");
	    	return new ProductBatchCompDTO (rs.getInt("pb_id"), rs.getInt("rb_id"), rs.getDouble("tara"), rs.getDouble("netto"), rs.getInt("opr_id"));
	    }
	    catch (SQLException e) {throw new DALException(e); }	
		
	}

	
	public List<ProductBatchCompDTO> getProductBatchCompList(int pbId)
			throws DALException {
		
		List<ProductBatchCompDTO> list = new ArrayList<ProductBatchCompDTO>();
		ResultSet rs = Connector.doQuery("SELECT * FROM ProductBatchComponent WHERE pb_id= " + pbId);
		try
		{
			while (rs.next()) 
			{
				list.add(new ProductBatchCompDTO(rs.getInt("pb_id"), rs.getInt("rb_id"), rs.getDouble("tara"), rs.getDouble("netto"), rs.getInt("opr_id")));			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
		
	
	}


	public List<ProductBatchCompDTO> getProductBatchCompList()
			throws DALException {
		
		List<ProductBatchCompDTO> list = new ArrayList<ProductBatchCompDTO>();
		ResultSet rs = Connector.doQuery("SELECT * FROM ProductBatchComponent");
		try
		{
			while (rs.next()) 
			{
				list.add(new ProductBatchCompDTO(rs.getInt("pb_id"), rs.getInt("rb_id"), rs.getDouble("tara"), rs.getDouble("netto"), rs.getInt("opr_id")));			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
		
	}

	
	public void createProductBatchComp(ProductBatchCompDTO ProductBatchComponent)
			throws DALException {
		Connector.doUpdate("INSERT INTO ProductBatchComponent (opr_id, netto, tara, rb_id, pb_id) VALUES" + 
			"(" + ProductBatchComponent.getOprId() + ", " + ProductBatchComponent.getNetto() + ", " + ProductBatchComponent.getTara() + ", " + ProductBatchComponent.getRbId() + ", " + ProductBatchComponent.getPbId()+ ")" 
			);
		
	
		
	}

	
	public void updateProductBatchComp(ProductBatchCompDTO ProductBatchComponent)
			throws DALException {
		Connector.doUpdate(
				"UPDATE ProductBatchComponent SET opr_id = '" + ProductBatchComponent.getOprId() + "', netto = '" + ProductBatchComponent.getNetto() + "', tara = '" + ProductBatchComponent.getTara() + "' " + "WHERE rb_id = " + ProductBatchComponent.getRbId() +
				"' " + "WHERE pb_id = " + ProductBatchComponent.getPbId()); 
			
		
		
	}

}
