package code.database;

import java.io.Serializable;

public class ProductBatchDTO implements Serializable
{
	int pbId;                     // i omraadet 1-99999999
	int status;					// 0: ikke paabegyndt, 1: under produktion, 2: afsluttet
	int receptId;
	
	public ProductBatchDTO(){
		
	}
	
	public ProductBatchDTO(int pbId, int status, int receptId)
	{
		this.pbId = pbId;
		this.status = status;
		this.receptId = receptId;
	}
	
	public int getPbId() { return pbId; }
	public void setPbId(int pbId) { this.pbId = pbId; }
	public int getStatus() { return status; }
	public void setStatus(int status) { this.status = status; }
	public int getReceptId() { return receptId; }
	public void setReceptId(int receptId) { this.receptId = receptId; }
	public String toString() { return pbId + "\t" + status + "\t" + receptId; }
}

