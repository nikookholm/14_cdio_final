package code.client.views;

import code.client.controllers.MainController;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.HTMLTable.CellFormatter;

public class Menu extends Composite {
	
	FlexTable table = new FlexTable();
	
	Anchor mainMenuAnchor = new Anchor("Hovedmenu");
	Anchor logoutAnchor   = new Anchor("Log ud");
	MainController mc;
	
	HorizontalPanel logoutPanel = new HorizontalPanel();
	
	public Menu(MainController mc)
	{		
		this.mc = mc;
		
		if (mc.getUser() != null)
		{
			logoutPanel.add(new HTML("Du er logget ind som&nbsp;"));
			logoutPanel.add(new Label(mc.getUser().getOprName()));
			logoutPanel.add(new HTML("&nbsp;("));
			logoutPanel.add(logoutAnchor);
			logoutPanel.add(new Label(")"));
			
			mainMenuAnchor.addClickHandler(new mainMenuHandler());
			
			table.setWidget(0, 0, mainMenuAnchor);
			table.setWidget(0, 1, logoutPanel);
			
			CellFormatter cf =  table.getCellFormatter();
			cf.setWidth(0, 1, "100%;");
			cf.setHorizontalAlignment(0, 1,  HasHorizontalAlignment.ALIGN_RIGHT);
			
			logoutAnchor.addClickHandler(new logoutHandler());
		}
		
		initWidget(table);
	}
	
	private class mainMenuHandler implements ClickHandler
	{

		@Override
		public void onClick(ClickEvent event) {
			mc.show(new MainView(mc));
		}
		
	}
	
	private class logoutHandler implements ClickHandler
	{

		@Override
		public void onClick(ClickEvent event) {
			mc.setUser(null);
			mc.show(new LoginView(mc, null));
		}
		
	}
	
}
