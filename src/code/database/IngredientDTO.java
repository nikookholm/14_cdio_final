package code.database;

import java.io.Serializable;

/**
 * Ingredient Data Objekt
 * 
 * @author mn/sh/tb
 * @version 1.2
 */

public class IngredientDTO implements Serializable
{
    /** i omraadet 1-99999999 vaelges af brugerne */
    int ingredientId;                     
    /** min. 2 max. 20 karakterer */
    String ingredientName;                
    /** min. 2 max. 20 karakterer */
    String leverandoer;         
    
    public IngredientDTO()
    {
    	
    }
	
	public IngredientDTO(int ingredientId, String ingredientName, String leverandoer)
	{
		this.ingredientId = ingredientId;
		this.ingredientName = ingredientName;
		this.leverandoer = leverandoer;
	}
	
    public int getIngredientId() { return ingredientId; }
    public void setIngredientId(int IngredientId) { this.ingredientId = IngredientId; }
    public String getIngredientName() { return ingredientName; }
    public void setIngredientName(String ingredientName) { this.ingredientName = ingredientName; }
    public String getLeverandoer() { return leverandoer; }
    public void setLeverandoer(String leverandoer) { this.leverandoer = leverandoer; }
    public String toString() { 
		return ingredientId + "\t" + ingredientName +"\t" + leverandoer; 
	}
}
