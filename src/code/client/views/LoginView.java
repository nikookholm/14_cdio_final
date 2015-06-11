package code.client.views;

import code.client.controllers.MainController;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class LoginView extends Composite {
	
	private MainController mc;

	
	public LoginView(MainController mc)
	{
		this.mc = mc;
		VerticalPanel panel = new VerticalPanel();
		
		FlexTable table = new FlexTable();

		TextBox oprNumber   = new TextBox();
		TextBox password    = new TextBox();
		Button okBtn    	= new Button("Login");
		Button clearBtn		= new Button("Ryd");
		okBtn.addClickHandler(new obBtnClick());
		clearBtn.addClickHandler(new clearBtnClick());
		
		HorizontalPanel buttonPanel = new HorizontalPanel();
		buttonPanel.add(oprNumber);
		buttonPanel.add(okBtn);
		
				
		panel.add(new Label("Login"));
		table.setText(0, 0, "Operatør ID:");
		table.setWidget(0, 1, oprNumber);
		table.setText(1, 0, "Adgangskode:");
		table.setWidget(1, 1, password);
		table.setWidget(2,  0, buttonPanel);
		table.getFlexCellFormatter().setColSpan(2, 0, 2);
		
		panel.add(table);
		initWidget(panel);
		
	}
	
	private class obBtnClick implements ClickHandler
	{

		@Override
		public void onClick(ClickEvent event) {
			mc.show(mc.getUserController().login("23"));			
		}
		
	}

	private class clearBtnClick implements ClickHandler
	{

		@Override
		public void onClick(ClickEvent event) {
			
		}
		
	}

}
