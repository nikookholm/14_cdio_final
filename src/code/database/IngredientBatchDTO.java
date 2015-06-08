package code.database;

public class IngredientBatchDTO
{
	int rbId;                     // i omraadet 1-99999999
	int ingredientId;             // i omraadet 1-99999999
	double maengde;             // kan vaere negativ 

	public IngredientBatchDTO(int rbId, int ingredientId, double maengde)
	{
		this.rbId = rbId;
		this.ingredientId = ingredientId;
		this.maengde = maengde;
	}
	
	public IngredientBatchDTO(IngredientBatchDTO ingrBatch)
	{
		this.rbId = ingrBatch.getRbId();
		this.ingredientId = ingrBatch.getIngredientId();
		this.maengde = ingrBatch.getMaengde();
	}
	
	public int getRbId() { return rbId; }
	public void setRbId(int rbId) { this.rbId = rbId; }
	public int getIngredientId() { return ingredientId; }
	public void setIngredientId(int ingredientId) { this.ingredientId = ingredientId; }
	public double getMaengde() { return maengde; }
	public void setMaengde(double maengde) { this.maengde = maengde; }
	public String toString() { 
		return rbId + "\t" + ingredientId +"\t" + maengde; 
	}
}
