package code.database;

import java.io.Serializable;

/**
 * Recept Data Objekt
 * 
 * @author mn/tb
 * @version 1.2
 */

public class ReceptDTO implements Serializable
{
	/** Recept nr i omraadet 1-99999999 */
	int receptId;
	/** Receptnavn min. 2 max. 20 karakterer */
	String receptName;
	/** liste af kompenenter i recepten */
	
	public ReceptDTO()
	{
		
	}
    
	public ReceptDTO(String receptName, int receptId)
	{
        this.receptName = receptName;
        this.receptId = receptId;
	}

    public int getReceptId() { return receptId; }
	public void setReceptId(int receptId) { this.receptId = receptId; }
	public String getReceptName() { return receptName; }
	public void setReceptName(String receptName) { this.receptName = receptName; }
	public String toString() { 
		return receptId + "\t" + receptName; 
	}
}
