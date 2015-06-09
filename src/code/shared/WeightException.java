package code.shared;

public class WeightException extends Exception 
{
	private static final long serialVersionUID = -5490114628089339323L;
	public WeightException(String message) { super(message); }    
	public WeightException(Exception e) { super(e); }	
}
