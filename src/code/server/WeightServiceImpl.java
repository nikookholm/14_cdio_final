package code.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import code.client.WeightService;
import code.client.controllers.WeightProcedures;
import code.shared.WeightException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class WeightServiceImpl extends RemoteServiceServlet implements
WeightService {

	//globale variabler
	String filename = "ip_port.txt";
	String[] addressArray;
	TCPConnector tcp;

	//Konstruktøren, der starter weightProcedures hvis fileRead() og listenForTarget() kører.
	//	public WeightServiceImpl() throws WeightException, IOException{
	//		
	//	}

	public void start()
	{
		try {
			//fileRead();
			listenForTarget();

			tcp.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("***********Start() i WS er stoppen**************");
	}

	//Metoden, der tarerer vægten
	@Override
	public double getTara() throws WeightException {
		String temp;
		tcp.send("T\r\n");
		temp = tcp.receive();

		while(!temp.startsWith("T S")){
			temp = tcp.receive();
		}
		try{
			return Double.parseDouble(temp.substring(9, 14));
		}
		catch(NumberFormatException e)
		{
			System.out.println("Hej Hej");
			throw new WeightException("Der skete en fejl, da tara prøvede at hentes");
		}
	}

	//metoden, der henter vægten
	@Override
	public double getWeight() throws WeightException{
		{
			String temp;
			tcp.send("S\r\n");
			temp = tcp.receive();



			if ("S".equals(temp.split(" ")[0]))
			{
				return Double.parseDouble(temp.substring(9, 14));
			}
			else
			{
				throw new WeightException("Der skete en fejl, da vægten forsøgte at aflæses");	
			}
		}
	}

	//rm20-kommandoen, der sender en besked til vægten og får en respons tilbage
	@Override
	public String rm20(int type, String message) throws WeightException {
		String result = "";

		String request = "RM20 8 \""+message+"\" \"\" \"&3\"\r\n";

		tcp.send(request);

		result = tcp.receive();
		System.out.println("Før while " + result);
		while(!result.startsWith("RM20 B")){

			result = tcp.receive();

		}
		while (!result.startsWith("RM20 A"))
		{
			result = tcp.receive();

		}

		result = result.substring(8,result.length()-1);
		return result;
	}



	//Metoden, der printer til displayet
	@Override
	public boolean printToDisplay(String message) throws WeightException {
		tcp.send("D \"" + message + "\r\n");
		return ("D A".equals(tcp.receive())) ? true : false;
	}

	//Metoden, der fjerner printdisplayet og viser vægt-displayet igen
	@Override
	public void clearDisplay() throws WeightException {
		String result;
		String request = "DW\r\n";
		tcp.send(request);
		result = tcp.receive();

		if(result.equals("DW A"))
		{
			getWeight();
		}
		{throw new WeightException("Der opstod en fejl, da vægten prøvede at skifte til vægt-visning");}

	}

	//Metoden der lytter på .txt-filen fra fileRead(), lavet et array og finder om ip:port kan bruges til at forbinde
	//Hvis det lykkes, startes weightProcedures.start()
	@Override
	public void listenForTarget() throws Exception{
		boolean noTarget = false;
		String host = null;
		int port = 0;
		//		while(!noTarget)
		//		{
		//			for(String ip : addressArray) 
		//			{
		//				host = ip.substring(0, ip.indexOf(":"));
		//				try
		//				{
		//					port = Integer.parseInt(ip.substring(ip.indexOf(":")+1));
		//				}
		//				catch(NumberFormatException e)
		//				{
		//					e.printStackTrace();
		//				}
		//				
		tcp = new TCPConnector("169.254.2.2", 8000);
		//				tcp = new TCPConnector("localhost", 4567);
		//				tcp = new TCPConnector("10.16.97.77", 8000);
		if(tcp.connect())
		{
			new WeightProcedures(this);
		}
		//			}
		//		}


		//Lytter efter vægt-terminaler på IP'er. Hvis den finder en ip, køres WeightProcedures
	}
	public double doSTcommand(double wantedMass){
		tcp.send("ST 1\r\n");
		
		String result = tcp.receive();
		System.out.println(result); // Burde være ST A
		while(!result.startsWith("S S")){
			tcp.send("ST 1\r\n");
			result = tcp.receive();
			System.out.println(result);
		}
		
		System.out.println(result);
		
		tcp.send("ST 0\r\n");
		System.out.println(tcp.receive());
		return Double.parseDouble(result.substring(9, 14));
		
	}

	public String p111(String message){

		tcp.send("P111 \"" + message + "\"\r\n");
		String result = tcp.receive();
		while(!result.startsWith("P111")){
			result = tcp.receive();
		}

		return tcp.receive();
	}

	//metoden bruger Apache Commons FileUtils og ArrayUtils og tager input-filen som en lang String
	//derpå splitter den på linebreak og laver til array.
	public String[] fileRead() throws IOException
	{
		FileInputStream fileInput = new FileInputStream(filename);
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInput));
		List<String> lines = new ArrayList<String>();
		String line = null;
		while ((line = bufferedReader.readLine()) != null) {
			lines.add(line);
		}
		bufferedReader.close();
		addressArray = lines.toArray(new String[lines.size()]);
		return addressArray;
	}

}