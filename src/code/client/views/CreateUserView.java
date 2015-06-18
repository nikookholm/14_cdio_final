package code.client.views;

import code.client.controllers.MainController;
import code.database.UserDTO;
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
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class CreateUserView extends Composite {

	MainController mc;
	VerticalPanel vPanel;
	HorizontalPanel hPanel;
	TextBox idBox, nameBox, iniBox, cprBox, pswdBox;
	ListBox roleList;
	Button okBtn, cancelBtn;
	FlexTable flex;
	Label headLabel, idLabel, nameLabel, iniLabel, cprLabel, pswdLabel, roleLabel, presentSuccess;

	// Flag der skal afgøre om ok knappen skal gøres tilgænglig
	boolean idCheck   = false; 
	boolean nameCheck = false; 
	boolean iniCheck  = false;
	boolean cprCheck  = false;
	boolean pswdCheck = false;
	
	public CreateUserView(final UserDTO user, final MainController mc)
	{
		vPanel = new VerticalPanel();
		hPanel 		= new HorizontalPanel();
		initWidget(vPanel);
		
		headLabel	= new Label("Opret ny bruger");
		idLabel		= new Label("ID");
		nameLabel	= new Label("Navn");
		iniLabel	= new Label("Initialer");
		cprLabel	= new Label("CPR");
		pswdLabel	= new Label("Password");
		roleLabel	= new Label("Rolle");
		
		idBox		= new TextBox();
		nameBox 	= new TextBox();
		iniBox 		= new TextBox();
		cprBox 		= new TextBox();
		pswdBox		= new TextBox();
		roleList 	= new ListBox();
		
		if(user != null){
			vPanel.add(new Label("Brugeren " + user.getOprName() + " er oprettet"));
		}

		okBtn = new Button("OK");
		okBtn.setEnabled(false);
		cancelBtn = new Button("Annullér");
	
		flex = new FlexTable();
		flex.setTitle("Lav ny bruger");
		flex.setWidget(0, 0, idLabel);
		flex.setWidget(0, 1, idBox);
		flex.setWidget(1, 0, nameLabel);
		flex.setWidget(1, 1, nameBox);
		flex.setWidget(2, 0, iniLabel);
		flex.setWidget(2, 1, iniBox);
		flex.setWidget(3, 0, cprLabel);
		flex.setWidget(3, 1, cprBox);
		flex.setWidget(4, 0, pswdLabel);
		flex.setWidget(4, 1, pswdBox);
		flex.setWidget(5, 0, roleLabel);
		flex.setWidget(5, 1, roleList);
		flex.setWidget(6, 0, hPanel);
		flex.getFlexCellFormatter().setColSpan(6, 0, 2);
		flex.getCellFormatter().setAlignment(6, 0, HasHorizontalAlignment.ALIGN_RIGHT, HasVerticalAlignment.ALIGN_MIDDLE);
		
		headLabel.setStyleName("caption");
		idLabel.setStyleName("input-text");
		nameLabel.setStyleName("input-text");
		iniLabel.setStyleName("input-text");
		cprLabel.setStyleName("input-text");
		pswdLabel.setStyleName("input-text");
		roleLabel.setStyleName("input-text");

		hPanel.add(okBtn);
		hPanel.add(cancelBtn);
		
		roleList.addItem("Operatør");
		roleList.addItem("Værkfører");
		roleList.addItem("Farmaceut");
		roleList.addItem("Administrator");
		
		vPanel.add(headLabel);
		vPanel.add(flex);
		
		idBox.addKeyUpHandler(new IdBoxHandler());
		nameBox.addKeyUpHandler(new NameBoxHandler());
		iniBox.addKeyUpHandler(new IniBoxHandler());
		cprBox.addKeyUpHandler(new CprBoxHandler());
		pswdBox.addKeyUpHandler(new PswdBoxHandler());

		okBtn.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				UserDTO newUser = new UserDTO(Integer.parseInt(idBox.getText()), nameBox.getText(), iniBox.getText(), cprBox.getText(), pswdBox.getText(), roleList.getSelectedIndex());
				mc.show(mc.getUserController().createUser(newUser));
			}
		});
		
		cancelBtn.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
			mc.show(new MainView(mc));
			}
		});
	}

	private class IdBoxHandler implements KeyUpHandler{

		@Override
		public void onKeyUp(KeyUpEvent event) {
			if(!FieldVerifier.ispbNoValid(idBox.getText())){
				idBox.setStyleName("gwt-TextBox-invalidEntry");
				idCheck = false;
			}
			else{
				idBox.removeStyleName("gwt-TextBox-invalidEntry");
				idCheck = true;
			}
			okButtonEnabler();
		}
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
	
	private class PswdBoxHandler implements KeyUpHandler{

		@Override
		public void onKeyUp(KeyUpEvent event) {
			if(!FieldVerifier.isPswdValid(pswdBox.getText())){
				pswdBox.setStyleName("gwt-TextBox-invalidEntry");
				pswdCheck = false;
			}
			else{
				pswdBox.removeStyleName("gwt-TextBox-invalidEntry");
				pswdCheck = true;
			}
			okButtonEnabler();
		}
	}

	public void okButtonEnabler(){
		if(idCheck && nameCheck && iniCheck && cprCheck && pswdCheck){
			okBtn.setEnabled(true);
		}
		else okBtn.setEnabled(false);
	}

}

