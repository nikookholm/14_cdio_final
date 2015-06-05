package code.client;

import code.client.controllers.MainController;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Cdio_final implements EntryPoint {

	public void onModuleLoad() {
		new MainController();		
	}
}
