package code.client.views;

import code.client.controllers.MainController;

import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Menu extends Composite {
	
	FlexTable table = new FlexTable();
	
	Anchor mainMenuAnchor = new Anchor("Hovedmenu");
	Anchor logoutAnchor   = new Anchor("Log ud");
	
	HorizontalPanel logoutPanel = new HorizontalPanel();
	
	public Menu(MainController mc)
	{		
		if (mc.getUser() != null)
		{
			logoutPanel.add(new Label("Du er logget ind som "));
			logoutPanel.add(new Label(mc.getUser().getOprName()));
			logoutPanel.add(new Label(" ("));
			logoutPanel.add(logoutAnchor);
			logoutPanel.add(new Label(")"));
			
			table.setWidget(0, 0, mainMenuAnchor);
			table.setWidget(0,  1, logoutPanel);
		}
		
		initWidget(table);
	}
	
}
