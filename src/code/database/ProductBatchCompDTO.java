package code.database;

public class ProductBatchCompDTO 
{
	int pbId; 	  // produktbatchets id
	int rbId;        // i omraadet 1-99999999
	double tara;
	double netto;
	int oprId;					// operatoer-nummer

	
	public ProductBatchCompDTO(int pbId, int rbId, double tara, double netto, int oprId)
	{
		this.pbId = pbId;
		this.rbId = rbId;
		this.tara = tara;
		this.netto = netto;
		this.oprId = oprId;
	}
	
	public ProductBatchCompDTO(ProductBatchCompDTO pbComp)
	{
		this.pbId = pbComp.getPbId();
		this.rbId = pbComp.getRbId();
		this.tara = pbComp.getTara();
		this.netto = pbComp.getNetto();
		this.oprId = pbComp.getOprId();
	}
	
	public int getPbId() { return pbId; }
	public void setPbId(int pbId) { this.pbId = pbId; }
	public int getRbId() { return rbId; }
	public void setRbId(int rbId) { this.rbId = rbId; }
	public double getTara() { return tara; }
	public void setTara(double tara) { this.tara = tara; }
	public double getNetto() { return netto; }
	public void setNetto(double netto) { this.netto = netto; }
	public int getOprId() { return oprId; }
	public void setOprId(int oprId) { this.oprId = oprId; }
	public String toString() { 
		return pbId + "\t" + rbId +"\t" + tara +"\t" + netto + "\t" + oprId ; 
	}
}
