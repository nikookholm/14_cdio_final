package code.database;

import java.io.Serializable;

/**
 * User Data Access Objekt
 * 
 * @author mn/tb
 * @version 1.2
 */

public class UserDTO implements Serializable
{
	/** User-identifikationsnummer (opr_id) i omraadet 1-99999999. Vaelges af brugerne */
	int oprId;                     
	/** Usernavn (opr_navn) min. 2 max. 20 karakterer */
	String oprName;                
	/** User-initialer min. 2 max. 3 karakterer */
	String ini;                 
	/** User cpr-nr 10 karakterer */
	String cpr;                 
	/** User password min. 7 max. 8 karakterer */
	String password;  
	/** User role: Administrator, Farmaceut, Værkfører eller Operator */
	int role;
	/**User status: boolean for true or false if active */
	boolean active;
	
	public UserDTO()
	{
		
	}
	
	public UserDTO(int oprId, String oprName, String ini, String cpr, String password, int role, boolean active)
	{
		this.oprId = oprId;
		this.oprName = oprName;
		this.ini = ini;
		this.cpr = cpr;
		this.password = password;
		this.role = role;
		this.active = active;
	}
	
	public UserDTO(String oprName, String ini, String cpr, int role){
		this.oprName = oprName;
		this.ini = ini;
		this.cpr = cpr;
		this.role = role;
	}

    
    public int getOprId() { return oprId; }
	public void setOprId(int oprId) { this.oprId = oprId; }
	public String getOprName() { return oprName; }
	public void setOprName(String oprName) { this.oprName = oprName; }
	public String getIni() { return ini; }
	public void setIni(String ini) { this.ini = ini; }
	public String getCpr() { return cpr; }
	public void setCpr(String cpr) { this.cpr = cpr; }
	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }
	public int getRole() { return role; }
	public void setRole(int role) { this.role = role;}
	public boolean getActive() { return active; }
	public void setActive(boolean active) {	this.active = active;}
	public String toString() { return oprId + "\t" + oprName + "\t" + ini + "\t" + cpr + "\t" + password; }
}
