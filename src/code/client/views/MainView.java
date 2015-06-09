package code.client.views;

import code.client.controllers.MainController;

import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class MainView extends Composite {
	
	private MainController mc;
	
	public MainView(MainController mc)
	{
		this.mc = mc;
		VerticalPanel panel = new VerticalPanel();
		
		//panel.add(new Label("Hej " + mc.getUser()));
		
		panel.add(new Label("Brugeradministration"));
		panel.add(new Anchor("Opret bruger"));
		panel.add(new Anchor("Se og rediger brugere"));
		
		panel.add(new Label("Råvare/ingredienser"));
		panel.add(new Anchor("Opret ny ingrediens"));
		panel.add(new Anchor("Se og rediger ingredienser"));
		
		initWidget(panel);
		
	
	}
	
	
}
