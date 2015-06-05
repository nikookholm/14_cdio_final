package code.client.views;

import code.client.controllers.MainController;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class MainView extends Composite {
	
	private MainController mc;
	
	public MainView(MainController mc)
	{
		this.mc = mc;
		VerticalPanel panel = new VerticalPanel();
		
		panel.add(new Label("Hej " + mc.getUser()));
		
		initWidget(panel);
		
	
	}
	
	
}
