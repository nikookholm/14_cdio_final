package code.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("weight")
public interface WeightService extends RemoteService {
	double getTara();
	double getWeight();
	String rm20(int type, String message);
	boolean printToDisplay(String message);
	void clearDisplay();
	void listenForTarget() throws Exception;
}
