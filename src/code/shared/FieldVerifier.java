package code.shared;


public class FieldVerifier {

	public static boolean isValidName(String name) {
		if (name == null) {
			return false;
		}
		return name.length() > 1 && name.length() <= 20;
	}
	// CPR skal bestå af 10 tegn der alle er cifre. Der er ikke taget hensyn til at dato skal passe
	public static boolean isCPRValid(String cpr){
		if(cpr == null){
			return false;
		}
		try{
			@SuppressWarnings("unused")// verifyCPR findes kun til at lave checket
			int verifyCPR = Integer.parseInt(cpr);
		}
		catch(NumberFormatException e){
			return false;
		}
		return cpr.length() == 10;
	}
	// Initialer skal være mellem 2 og 4 karakterer, der er ikke taget nogen hensyn til karakter type
	public static boolean isInitialsValid(String ini){
		if(ini.equals(null)){
			return false;
		}
		else{
			return (ini.length() >= 2) && (ini.length() <=4);
		}
	}
	// leverandørs text af råvare skal være mellem 2-20 karakterer 
	public static boolean leverandoerValid(String lev){
		if(!lev.equals(null)){
		return false;
		}
		else{
			return (lev.length() >=2) && (lev.length() <=20);
		}
	}
	
	// råvare navn skal være mellem 2-20 karakterer
	public static boolean ingredientName(String ingName){
		if(!ingName.equals(null)){
			return false;
		}
		else{
			return(ingName.length() >= 2) && (ingName.length() <= 20);
		}
	}
	//råvareId skal bestå af tegnerne som tallerne fra 1 til 99999999
	public static boolean ingredientId(String ingId){
		if(ingId.equals(null)){
			return false;
		}
		try{
			int ingrId = Integer.parseInt(ingId);
		}
		catch(NumberFormatException e){
			return false;
			
		}
		return ingId.length() >0 && ingId.length() <= 8;
	}
}



































