package code.database;

public class IngredientBatchDTO
{
	int rbId;                     // i omraadet 1-99999999
	int IngredientId;             // i omraadet 1-99999999
	double maengde;             // kan vaere negativ 

	public IngredientBatchDTO(int rbId, int IngredientId, double maengde)
	{
		this.rbId = rbId;
		this.IngredientId = IngredientId;
		this.maengde = maengde;
	}
	
	public int getRbId() { return rbId; }
	public void setRbId(int rbId) { this.rbId = rbId; }
	public int getIngredientId() { return IngredientId; }
	public void setIngredientId(int IngredientId) { this.IngredientId = IngredientId; }
	public double getMaengde() { return maengde; }
	public void setMaengde(double maengde) { this.maengde = maengde; }
	public String toString() { 
		return rbId + "\t" + IngredientId +"\t" + maengde; 
	}
}
