package code.client.views;

import code.client.controllers.MainController;
import code.shared.FieldVerifier;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class LoginView extends Composite {
	
	private MainController mc;
	TextBox oprNumber     = new TextBox();
	TextBox password      = new TextBox();
	Button  okBtn    	  = new Button("Login");
	Button  clearBtn	  = new Button("Ryd");
	Label   loginLabel    = new Label("Login");
	Label   oprLabel	  = new Label("Operatør ID:");
	Label   passwordLabel = new Label("Adgangskode:");
	
	boolean IdCheck       = false;
//	boolean passwordCheck = false;
	
	public LoginView(MainController mc, Widget infomation)
	{
		this.mc = mc;
		VerticalPanel panel = new VerticalPanel();
		
		FlexTable table = new FlexTable();

		okBtn.setEnabled(false);
		okBtn.addClickHandler(new okBtnClick());
		clearBtn.addClickHandler(new clearBtnClick());
		
		HorizontalPanel buttonPanel = new HorizontalPanel();
		buttonPanel.add(clearBtn);
		buttonPanel.add(okBtn);
		
		if (infomation != null)
		{
			panel.add(infomation);
		}

		panel.add(loginLabel);
		loginLabel.setStyleName("caption");
		table.setWidget(0, 0, oprLabel);
		oprLabel.setStyleName("input-text");
		table.setWidget(0, 1, oprNumber);
		table.setWidget(1, 0, passwordLabel);
		passwordLabel.setStyleName("input-text");
		table.setWidget(1, 1, password);
		table.setWidget(2, 0, buttonPanel);
		table.getFlexCellFormatter().setColSpan(2, 0, 2);
		table.getFlexCellFormatter().setAlignment(2, 0, HasHorizontalAlignment.ALIGN_RIGHT, HasVerticalAlignment.ALIGN_MIDDLE);
		
		panel.add(table);
		initWidget(panel);
		
		oprNumber.addKeyUpHandler(new id());
//		password.addKeyUpHandler(new passWord());
		
	}
	
	private class okBtnClick implements ClickHandler
	{

		@Override
		public void onClick(ClickEvent event) {
			mc.show(mc.getUserController().login(oprNumber.getText(), password.getText()));
		}
		
	}

	private class clearBtnClick implements ClickHandler
	{

		@Override
		public void onClick(ClickEvent event) {
			oprNumber.setText("");
			password.setText("");
		}
		
	}
	
	public void oKButEnabler(){
		
		if(IdCheck){
			okBtn.setEnabled(true);
		}
		else okBtn.setEnabled(false);
			
		
	}
	
	private class id implements KeyUpHandler{

		@Override
		public void onKeyUp(KeyUpEvent event) {
			if(!FieldVerifier.ispbNoValid(oprNumber.getText())){
				oprNumber.setStyleName("gwt-TextBox-invalidEntry");
				IdCheck = false;
			}
			else{
				oprNumber.removeStyleName("gwt-TextBox-invalidEntry");
				IdCheck = true;
				oKButEnabler();
			}
		}
		
	}
	
}
