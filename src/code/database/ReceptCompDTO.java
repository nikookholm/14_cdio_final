package code.database;

public class ReceptCompDTO
{
	int receptId;                  // auto genereres fra 1..n   
	int ingredientId;             // i omraadet 1-99999999
	double nomNetto;            // skal vaere positiv og passende stor
	double tolerance;           // skal vaere positiv og passende stor
	
	public ReceptCompDTO()
	{
		
	}
	
	public ReceptCompDTO(int receptId, int ingredientId, double nomNetto, double tolerance)
	{
		this.receptId = receptId;
		this.ingredientId = ingredientId;
		this.nomNetto = nomNetto;
		this.tolerance = tolerance;
	}

	public int getReceptId() { return receptId; }
	public void setReceptId(int receptId) { this.receptId = receptId; }
	public int getIngredientId() { return ingredientId; }
	public void setIngredientId(int raavareId) { this.ingredientId = raavareId; }
	public double getNomNetto() { return nomNetto; }
	public void setNomNetto(double nomNetto) { this.nomNetto = nomNetto; }
	public double getTolerance() { return tolerance; }
	public void setTolerance(double tolerance) { this.tolerance = tolerance; }
	public String toString() { 
		return receptId + "\t" + ingredientId + "\t" + nomNetto + "\t" + tolerance; 
	}
}
