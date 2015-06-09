package code.client.views;

import code.client.controllers.MainController;
import code.database.UserDTO;
import code.shared.FieldVerifier;

import com.gargoylesoftware.htmlunit.javascript.host.html.HTMLHeadElement;
import com.google.gwt.dom.builder.shared.HtmlHeadBuilder;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class CreateUserView extends Composite {
	VerticalPanel vPanel;

	TextBox nameBox, iniBox, cprBox;
	ListBox roleList;
	Button okBtn;
	Grid table;
	Label viewInfo, presentSuccess;
	MainController mc;


	// Flag der skal afgøre om ok knappen skal gøres tilgænglig
	boolean nameCheck = false; 
	boolean iniCheck  = false;
	boolean cprCheck  = false;



	public CreateUserView(final UserDTO user, final MainController mc){


		this.vPanel = new VerticalPanel();
		initWidget(vPanel);


		okBtn = new Button("Ok");
		okBtn.setEnabled(false);
		viewInfo = new Label("Indtast den nye brugers oplysninger");
		presentSuccess.setText(user.getOprName() + " er oprettet i databasen");
		table = new Grid(4,2);
		table.setWidget(0, 0, new Label("Navn"));
		table.setWidget(0, 1, nameBox =new TextBox());
		table.setWidget(1, 0, new Label("Initialer"));
		table.setWidget(1, 1, iniBox = new TextBox());
		table.setWidget(2, 0, new Label("CPR"));
		table.setWidget(2, 1, cprBox = new TextBox());
		table.setWidget(3, 0, new Label("Rolle"));
		table.setWidget(3, 1, roleList = new ListBox());

		roleList.addItem("Operatør");
		roleList.addItem("Værkfører");
		roleList.addItem("Farmaceut");
		roleList.addItem("Administrator");
		vPanel.add(table);

		nameBox.addKeyUpHandler(new NameBoxHandler());
		iniBox.addKeyUpHandler(new IniBoxHandler());
		cprBox.addKeyUpHandler(new CprBoxHandler());

		vPanel.add(okBtn);


		okBtn.addClickHandler(new ClickHandler() {
			UserDTO newUser = new UserDTO(0,nameBox.getText(),iniBox.getText(),"", cprBox.getText(),roleList.getTabIndex(),true);
			@Override
			public void onClick(ClickEvent event) {

				mc.getUserController().createUser(newUser);

			}
		});


	}


	public CreateUserView() {

	}


	private class NameBoxHandler implements KeyUpHandler{

		@Override
		public void onKeyUp(KeyUpEvent event) {
			if(!FieldVerifier.isValidName(nameBox.getText())){
				nameBox.setStyleName("gwt-TextBox-invalidEntry");
				nameCheck = false;
			}
			else{
				nameBox.removeStyleName("gwt-TextBox-invalidEntry");
				nameCheck = true;
			}
			okButtonEnabler();

		}
	}
	private class IniBoxHandler implements KeyUpHandler{

		@Override
		public void onKeyUp(KeyUpEvent event) {
			if(!FieldVerifier.isInitialsValid(iniBox.getText())){
				iniBox.setStyleName("gwt-TextBox-invalidEntry");
				iniCheck = false;
			}
			else{
				iniBox.removeStyleName("gwt-TextBox-invalidEntry");
				iniCheck = true;
			}
			okButtonEnabler();
		}
	}
	private class CprBoxHandler implements KeyUpHandler{

		@Override
		public void onKeyUp(KeyUpEvent event) {
			if(!FieldVerifier.isCPRValid(cprBox.getText())){
				cprBox.setStyleName("gwt-TextBox-invalidEntry");
				cprCheck = false;
			}
			else{
				cprBox.removeStyleName("gwt-TextBox-invalidEntry");
				cprCheck = true;
			}
			okButtonEnabler();
		}
	}
	public void okButtonEnabler(){
		if(nameCheck && iniCheck && cprCheck){
			okBtn.setEnabled(true);
		}
		else okBtn.setEnabled(false);

	}
}








