package code.client;

import code.shared.WeightException;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("weight")
public interface WeightService extends RemoteService {
	double getTara() throws WeightException;
	double getWeight() throws WeightException;
	String rm20(int type, String message) throws WeightException;
	boolean printToDisplay(String message) throws WeightException;
	void clearDisplay() throws WeightException;
	void listenForTarget() throws Exception;
	void start();
}
