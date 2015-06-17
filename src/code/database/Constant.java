package code.database;


// erstat konstanterne nedenfor

public abstract class Constant
{
	public static final String
		server					= "localhost",  // database-serveren
		database				=  "cdio",     //"jdbcdatabase", // navnet paa din database = dit studienummer
		username				= "root",     // dit brugernavn = dit studienummer 
		password				= "";        // dit password som du har valgt til din database
	
//	*****************
//	
//			server					= "62.79.16.16",  // database-serveren
//			database				= "grp14",       //"jdbcdatabase", // navnet paa din database = dit studienummer 
//			username				= "grp14",      // dit brugernavn = dit studienummer 
//			password				= "rV5e36us";  // dit password som du har valgt til din database 
//	
	public static final int
		port					= 3306;
}
