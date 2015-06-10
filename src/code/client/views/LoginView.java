package code.client.views;

import code.client.controllers.MainController;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class LoginView extends Composite {
	
	private MainController mc;
	private TextBox oprNumber   = new TextBox();
	
	public LoginView(MainController mc)
	{
		this.mc = mc;
		VerticalPanel panel = new VerticalPanel();
		
		Button okBtn    = new Button("Login");
		Button clearBtn = new Button("Ryd");
		okBtn.addClickHandler(new obBtnClick());
		clearBtn.addClickHandler(new clearBtnClick());
		
		panel.add(oprNumber);
		panel.add(okBtn);
		
		initWidget(panel);
		
	}
	
	private class obBtnClick implements ClickHandler
	{

		@Override
		public void onClick(ClickEvent event) {
			mc.show(mc.getUserController().login(oprNumber.getText()));			
		}
		
	}

	private class clearBtnClick implements ClickHandler
	{

		@Override
		public void onClick(ClickEvent event) {
			
		}
		
	}

}
