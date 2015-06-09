package code.database;

public class ProductBatchDTO 
{
	int pbId;                     // i omraadet 1-99999999
	int status;					// 0: ikke paabegyndt, 1: under produktion, 2: afsluttet
	int receptId;
	int datoId;
	
	public ProductBatchDTO()
	{
		
	}
	
	public ProductBatchDTO(int pbId, int status, int receptId, int datoId)
	{
		this.pbId = pbId;
		this.status = status;
		this.receptId = receptId;
		this.datoId = datoId;
	}
	
	public int getPbId() { return pbId; }
	public void setPbId(int pbId) { this.pbId = pbId; }
	public int getStatus() { return status; }
	public void setStatus(int status) { this.status = status; }
	public int getReceptId() { return receptId; }
	public void setReceptId(int receptId) { this.receptId = receptId; }
	public String toString() { return pbId + "\t" + status + "\t" + receptId; }
}
