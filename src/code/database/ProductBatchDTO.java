package code.database;

import java.io.Serializable;

public class ProductBatchDTO implements Serializable 
{
	int pbId;                     // i omraadet 1-99999999
	int receptId;
	int status;					// 0: ikke paabegyndt, 1: under produktion, 2: afsluttet
	String dateTime;
	
	public ProductBatchDTO()
	{
		
	}
	
	public ProductBatchDTO(int pbId, int receptId, int status, String dateTime)
	{
		this.pbId = pbId;
		this.receptId = receptId;
		this.status = status;
		this.dateTime = dateTime;
	}
	
	public int getPbId() { return pbId; }
	public void setPbId(int pbId) { this.pbId = pbId; }
	public int getReceptId() { return receptId; }
	public void setReceptId(int receptId) { this.receptId = receptId; }
	public int getStatus() { return status; }
	public void setStatus(int status) { this.status = status; }
	public String getDateTime() { return dateTime; }
	public void setDateTime(String dateTime) { this.dateTime = dateTime; }
	public String toString() { return pbId + "\t" + status + "\t" + receptId +"\t" + dateTime; }
}
