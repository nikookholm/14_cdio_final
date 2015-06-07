package code.client.controllers;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;

import code.database.*;

public class ProductBatchController 
{
	ProductBatchDAO pbDAO;
	ProductBatchDTO pbDTO;
	
	public void createProductBatch(ProductBatchDTO pbDTO) throws Exception
	{
		Connector con = new Connector();
		pbDAO.createProductBatch(pbDTO);
		
		printProductBatch();
	}
	
	public List<ProductBatchDTO> listProductBatch() throws Exception
	{
		Connector con = new Connector();
		List<ProductBatchDTO> ls = pbDAO.getProductBatchList();
		
		return ls;
	}
	
	private void printProductBatch()
	{
		
	}
	
	public String getDate()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/M/dd");
		String currentDate = sdf.format(new Date()); 
		
		return currentDate;
	}
}
