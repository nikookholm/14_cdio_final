package code.database;

import java.io.Serializable;
import java.sql.Date;

import org.apache.james.mime4j.field.datetime.DateTime;

public class ProductBatchDTO implements Serializable 
{
	int pbId;                     // i omraadet 1-99999999
	int status;					// 0: ikke paabegyndt, 1: under produktion, 2: afsluttet
	int receptId;
	Date date;
	
	public ProductBatchDTO()
	{
		
	}
	
	public ProductBatchDTO(int pbId, int status, int receptId, long date)
	{
		this.pbId = pbId;
		this.status = status;
		this.receptId = receptId;
		this.date = new Date(date);
	}
	
	public int getPbId() { return pbId; }
	public void setPbId(int pbId) { this.pbId = pbId; }
	public int getStatus() { return status; }
	public void setStatus(int status) { this.status = status; }
	public int getReceptId() { return receptId; }
	public void setReceptId(int receptId) { this.receptId = receptId; }
	public Date getDate() { return date; }
	public String toString() { return pbId + "\t" + status + "\t" + receptId +"\t" +date; }
}
