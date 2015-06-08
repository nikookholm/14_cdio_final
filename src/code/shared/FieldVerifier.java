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
	
}
