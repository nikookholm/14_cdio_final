package code.client.views;

import code.client.controllers.MainController;

import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Menu extends Composite {
	
	public Menu(MainController mc)
	{
		HorizontalPanel panel = new HorizontalPanel();
		
		Anchor mainMenuAnchor = new Anchor("Hovedmenu");
		Anchor logoutAnchor   = new Anchor("Log ud");
		
		if (mc.getUser() != null)
		{
			panel.add(mainMenuAnchor);
			panel.add(new Label("Du er logget ind som " + mc.getUser().getOprName() + " - "));
			panel.add(logoutAnchor);
		}
		
		initWidget(panel);
	}
	
}
