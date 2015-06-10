package code.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

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

	String contentOfFile;
	String[] adressArray;
	TCPConnector tcp;
	
	public WeightServiceImpl() throws WeightException{
		try {
			fileRead();
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
	public boolean printToDisplay(String message) throws WeightException {
		tcp.send("D " + message + "\r\n");
		return ("D A".equals(tcp.receive())) ? true : false;
	}

	@Override
	public void clearDisplay() throws WeightException {
		String result;
		String request = "DW\r\n";
		tcp.send(request);
		result = tcp.receive();
		
		if(result.equals("DW A"));
		{
			getWeight();
		}
		{throw new WeightException("Der opstod en fejl, da vægten prøvede at skifte til vægt-visning");}
			
	}

	@Override
	public void listenForTarget() throws Exception{
		TCPConnector tcp;
		
		boolean noTarget = false;
		String host = null;
		int port = 0;
		while(!noTarget)
		{
			for(String ip : adressArray) 
			{
				host = ip.substring(0, ip.indexOf(":"));
				try
				{
					port = Integer.parseInt(ip.substring(ip.indexOf(":")+1));
				}
				catch(NumberFormatException e)
				{
					e.printStackTrace();
				}
				
				tcp = new TCPConnector(host, port);
				if(tcp.connect())
				{
					new WeightProcedures(this).start();
				}
			}
		}
		
		
		//Lytter efter vægt-terminaler på IP'er. Hvis den finder en ip, køres WeightProcedures
	}
	
	public void fileRead() throws IOException
	{	
		contentOfFile = FileUtils.readFileToString(new File("ip_port.txt"));
		adressArray = ArrayUtils.toArray(contentOfFile.replaceAll("\\r", "").split("\n"));
	}
}