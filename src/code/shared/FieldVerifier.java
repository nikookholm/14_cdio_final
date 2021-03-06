package code.shared;


public class FieldVerifier {
	
	public static boolean isValidName(String name) {
		if ((!FieldVerifier.isLettersOnly(name)) && (name == null)) {
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
		if((!FieldVerifier.isLettersOnly(ini)) && (ini.equals(null))){
			return false;
		}
		else{
			return (ini.length() >= 2) && (ini.length() <=4);
		}
	}
	
	// Kodeordet skal bestå af 5-8 karaktere
	public static boolean isPswdValid(String pswd)
	{
		if(pswd == null) return false;
		else if(pswd.length()<5) return false;
		else if(pswd.length()>8) return false;
		else return true;
	}

	// leverandørs text af råvare skal være mellem 2-20 karakterer 
	public static boolean leverandoerValid(String lev){
		if((!FieldVerifier.isLettersOnly(lev)) && (lev.equals(null))){
		return false;
		}
		
		else{
			return (lev.length() >=2) && (lev.length() <=20);
		}
	}
	
	// råvare navn skal være mellem 2-20 karakterer
	public static boolean ingredientName(String ingName){
		if((!FieldVerifier.isLettersOnly(ingName)) && (ingName.equals(null))){
			return false;
		}
		else{
			return ingName.length() >= 2 && ingName.length() <= 20;
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
	
	//ProduktBatchID skal være imellem 1 og 99999999 
	public static boolean ispbNoValid(String str)
	{
		int i;
		try{
			i = Integer.parseInt(str);
		}catch(NumberFormatException e){
			return false;
		}
		
		if(1<=i && i<=99999999){
			return true;
		}else{
			return false;
		}
	}
	
	//rb skal være mellem 1 og 99999999
	public static boolean isrbNoValid(String str)
	{
		int i;
		try{
			i = Integer.parseInt(str);
		}catch(NumberFormatException e){
			return false;
		}
		
		if(1<=i && i<=99999999){
			return true;
		}else{
			return false;
		}
	}
	
	//

	//ReceptID skal være imellem 1 og 99999999 
	public static boolean isReceptNoValid(String str)
	{
		int i;
		try{
			i = Integer.parseInt(str);
		}catch(NumberFormatException e){
			return false;
		}
		
		if(1<=i && i<=99999999){
			return true;
		}else{
			return false;
		}
	}
	
	//Status kan være 0,1,2
	public static boolean isStatusValid(String str)
	{
		int i;
		try{
			i = Integer.parseInt(str);
		}catch(NumberFormatException e){
			return false;
		}
		
		if(0<=i && i<=2){
			return true;
		}else{
			return false;
		}
	}
	
	//Generelt check til at afgøre om String-inputs kun er bogstaver.
	public static boolean isLettersOnly(String str){
		char[] chars = str.toCharArray();
		for(char c : chars){
			if(!Character.isLetter(c)){
				return false;
			}
		}
		return true;
	}
}



































