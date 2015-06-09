package code.server;



import code.client.WeightService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class WeightServiceImpl extends RemoteServiceServlet implements
		WeightService {

	@Override
	public double getTara() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getWeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String rm20(int type, String message) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void printToDisplay(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clearDisplay() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void listenForTarget() {
		// TODO Auto-generated method stub
		
	}


	
}
