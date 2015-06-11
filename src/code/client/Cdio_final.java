package code.client;

import code.client.controllers.MainController;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Cdio_final implements EntryPoint {

	public void onModuleLoad() {
		WeightServiceAsync weightService = GWT
				.create(WeightService.class);
		
		weightService.start(new AsyncCallback<Void>() {
			
			@Override
			public void onSuccess(Void result) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
		new MainController();		
	}
}