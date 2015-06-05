package code.database;

/**
 * Ingredient Data Objekt
 * 
 * @author mn/sh/tb
 * @version 1.2
 */

public class IngredientDTO 
{
    /** i omraadet 1-99999999 vaelges af brugerne */
    int IngredientId;                     
    /** min. 2 max. 20 karakterer */
    String IngredientNavn;                
    /** min. 2 max. 20 karakterer */
    String leverandoer;         
	
	public IngredientDTO(int IngredientId, String IngredientNavn, String leverandoer)
	{
		this.IngredientId = IngredientId;
		this.IngredientNavn = IngredientNavn;
		this.leverandoer = leverandoer;
	}
	
    public int getIngredientId() { return IngredientId; }
    public void setIngredientId(int IngredientId) { this.IngredientId = IngredientId; }
    public String getIngredientNavn() { return IngredientNavn; }
    public void setIngredientNavn(String IngredientNavn) { this.IngredientNavn = IngredientNavn; }
    public String getLeverandoer() { return leverandoer; }
    public void setLeverandoer(String leverandoer) { this.leverandoer = leverandoer; }
    public String toString() { 
		return IngredientId + "\t" + IngredientNavn +"\t" + leverandoer; 
	}
}
