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

	VerticalPanel vPanel;
	TextBox nameBox, iniBox, cprBox;
	ListBox roleList;
	Button okBtn, cancelBtn;
	FlexTable table;
	Label viewInfo, presentSuccess;
	MainController mc;
	HorizontalPanel hPanel;
	int width = 250;

	// Flag der skal afgøre om ok knappen skal gøres tilgænglig
	boolean nameCheck = false; 
	boolean iniCheck  = false;
	boolean cprCheck  = false;

	public CreateUserView(final UserDTO user, final MainController mc){

		this.vPanel = new VerticalPanel();
		initWidget(vPanel);

		if(user != null){
			vPanel.add(new Label(user.getOprName() + " er oprettet"));
		}

		okBtn = new Button("Ok");
		okBtn.setEnabled(false);
		cancelBtn = new Button("Annullér");
		viewInfo = new Label("Indtast den nye brugers oplysninger");

		vPanel.add(viewInfo);
	
		nameBox = new TextBox();
		iniBox = new TextBox();
		cprBox = new TextBox();
		roleList = new ListBox();
		hPanel = new HorizontalPanel();
		
		table = new FlexTable();
		table.setWidget(0, 0, new Label("Navn"));
		table.setWidget(0, 1, nameBox);
		table.setWidget(1, 0, new Label("Initialer"));
		table.setWidget(1, 1, iniBox);
		table.setWidget(2, 0, new Label("CPR"));
		table.setWidget(2, 1, cprBox);
		table.setWidget(3, 0, new Label("Rolle"));
		table.setWidget(3, 1, roleList);
		table.setWidget(4, 0, hPanel);
		table.getFlexCellFormatter().setColSpan(4, 0, 2);
		table.getCellFormatter().setAlignment(4, 0, HasHorizontalAlignment.ALIGN_RIGHT, HasVerticalAlignment.ALIGN_MIDDLE);

		hPanel.add(okBtn);
		hPanel.add(cancelBtn);
		
		roleList.addItem("Operatør");
		roleList.addItem("Værkfører");
		roleList.addItem("Farmaceut");
		roleList.addItem("Administrator");
		
//		nameBox.setWidth(width + "px;");
//		iniBox.setWidth(width + "px;");
//		cprBox.setWidth(width + "px;");
//		roleList.setWidth(width + "px;");
		
		vPanel.add(table);

		nameBox.addKeyUpHandler(new NameBoxHandler());
		iniBox.addKeyUpHandler(new IniBoxHandler());
		cprBox.addKeyUpHandler(new CprBoxHandler());

		okBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				UserDTO newUser = new UserDTO(nameBox.getText(), iniBox.getText(), cprBox.getText(),roleList.getSelectedIndex());
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

