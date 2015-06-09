package code.server;

import code.client.WeightService;
import code.client.controllers.WeightProcedures;
import code.shared.WeightException;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class WeightServiceImpl extends RemoteServiceServlet implements
		WeightService {
	
	String 	host = "169.254.2.2";
	int 	port = 8000;

	TCPConnector tcp;
	
	public WeightServiceImpl() throws WeightException{
		try {
			listenForTarget();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public double getTara() throws WeightException {
		String temp;
		tcp.send("T\r\n");
		temp = tcp.receive();
		System.out.println(temp);
		if ("T".equals(temp.split(" ")[0]))
		{
			return Double.parseDouble(temp.split("\\s+")[2]);
		}
		else
		{
			throw new WeightException("Der skete en fejl, da tara prøvede at hentes");
		}
	}

	@Override
	public double getWeight() throws WeightException{
		{
			String temp;
			tcp.send("S\r\n");
			temp = tcp.receive();
			if ("S".equals(temp.split(" ")[0]))
			{
				return Double.parseDouble(temp.split("\\s+")[2]);
			}
			else
			{
				throw new WeightException("Der skete en fejl, da vægten forsøgte at aflæses");	
			}
		}
	}

	@Override
	public String rm20(int type, String message) throws WeightException {
		String result;
		type = 8;
		String request = "RM20 " + type + " \"" + message + "\" \" \" \" \"\r\n";
		tcp.send(request);
		result = tcp.receive();
		System.out.println(result);
		
		if (result.startsWith("RM20 B"))
		{
			return tcp.receive();
		}
		else
		{
			return result;
		}
	}
	
	@Override
	public boolean printToDisplay(String message) {
		tcp.send("D " + message + "\r\n");
		return ("D A".equals(tcp.receive())) ? true : false;
	}

	@Override
	public void clearDisplay() {
		//DW-kommando
	}

	@Override
	public void listenForTarget() throws Exception{
		tcp = new TCPConnector(host, port);
		tcp.connect();
		//Lytter efter vægt-terminaler på IP'er. Hvis den finder en ip, køres WeightProcedures
	}


	
}
