package code.client.views;

import java.util.ArrayList;

import javax.management.relation.RoleNotFoundException;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import code.client.controllers.MainController;
import code.database.UserDTO;
import code.shared.FieldVerifier;

public class ListUsersView extends Composite{

	MainController mc;

	ArrayList<UserDTO> users;

	VerticalPanel vPanel;
	Label idLabel		= new Label("ID");
	Label nameLabel		= new Label("Navn");
	Label iniLabel		= new Label("Initialer");
	Label cprLabel		= new Label("CPR");
	Label passwordLabel	= new Label("Password");
	Label roleLabel		= new Label("Rolle");
	Label Header		= new Label("Vis Brugere");
	Label fillerLabel, presentError;
	FlexTable table;
	Anchor edit,ok, prevCancel = null;
	int selectedRow;
	TextBox nameBox, iniBox, pwBox;
	ListBox roleBox;
	CheckBox deactivate;

	
	boolean nameCheck = false; 
	boolean iniCheck  = false;
	boolean cprCheck  = false;

	public ListUsersView(ArrayList<UserDTO> users, MainController mc){
		this.users = users;
		this.mc = mc;
		vPanel = new VerticalPanel();
		
		presentError = new Label("");

		table = new FlexTable();
		
		vPanel.add(Header);
		Header.setStyleName("caption");
		vPanel.add(presentError);
		table.setWidget(0, 0, idLabel);
		idLabel.setStyleName("input-text");
		table.setWidget(0, 1, nameLabel);
		nameLabel.setStyleName("input-text");
		table.setWidget(0, 2, iniLabel);
		iniLabel.setStyleName("input-text");
		table.setWidget(0, 3, cprLabel);
		cprLabel.setStyleName("input-text");
		table.setWidget(0, 4, passwordLabel);
		passwordLabel.setStyleName("input-text");
		table.setWidget(0, 5, roleLabel);
		roleLabel.setStyleName("input-text");
		table.getCellFormatter().setWidth(0, 0, "150px;");
		table.getCellFormatter().setWidth(0, 1, "150px;");
		table.getCellFormatter().setWidth(0, 2, "150px;");
		table.getCellFormatter().setWidth(0, 3, "150px;");
		table.getCellFormatter().setWidth(0, 4, "150px;");
		table.getCellFormatter().setWidth(0, 5, "150px;");
		
		for (int i = 0; i < this.users.size(); i++) {
			table.setText(i+1, 0,	"" + this.users.get(i).getOprId());
			table.setText(i+1, 1, 	"" + this.users.get(i).getOprName());
			table.setText(i+1, 2, 	"" + this.users.get(i).getIni());
			table.setText(i+1, 3,  	"" + this.users.get(i).getCpr());
			table.setText(i+1, 4, 	"" + this.users.get(i).getPassword());
			table.setText(i+1, 5, 	"" + parseRole(this.users.get(i).getRole()));
			table.setText(i+1, 6, 	"" + this.users.get(i).getActive());

			table.setWidget(i+1, 7, edit = new Anchor("rediger"));
			edit.addClickHandler(new EditButtonHandler());

		}

		vPanel.add(table);
		initWidget(this.vPanel);

		nameBox 	= new TextBox();
		iniBox 		= new TextBox();
		pwBox 		= new TextBox();
		roleBox 	= new ListBox();
		deactivate  = new CheckBox();


	}
	private class EditButtonHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			
			String activityParser;

			// annulerer forrige event
			if(prevCancel != null){
				prevCancel.fireEvent(new ClickEvent(){});
			}
			//Finds the row the user clicked
			selectedRow = table.getCellForEvent(event).getRowIndex();


			nameBox.setText(table.getText(selectedRow, 1));
			//nameBox.setWidth("150px");
			iniBox.setText(table.getText(selectedRow, 2));
			//iniBox.setWidth("40px");
			pwBox.setText(table.getText(selectedRow, 4));
			//pwBox.setWidth("100px");

			roleBox.clear();
			roleBox.addItem("Operatør");
			roleBox.addItem("Værkfører");
			roleBox.addItem("Farmaceut");
			roleBox.addItem("Administrator");

			roleBox.setItemSelected(reverseParseRole(table.getText(selectedRow, 5)), true);

			deactivate.setValue(table.getText(selectedRow, 6).equals("true"));

			table.setWidget(selectedRow, 1, nameBox);
			nameBox.addKeyUpHandler(new NameBoxHandler());

			table.setWidget(selectedRow, 2, iniBox);
			iniBox.addKeyUpHandler(new IniBoxHandler());
			table.setWidget(selectedRow, 4, pwBox);
			table.setWidget(selectedRow, 5, roleBox);
			table.setWidget(selectedRow, 6, deactivate);



			nameBox.setFocus(true);

			final Anchor edit =  (Anchor) event.getSource();
			final int id = Integer.parseInt(table.getText(selectedRow, 0));
			final String name = nameBox.getText();
			final String ini = iniBox.getText();
			final String cpr = (table.getText(selectedRow, 3));
			final String pw = pwBox.getText();
			final String role = roleBox.getItemText(roleBox.getTabIndex());
			final int roleToSave = roleBox.getTabIndex();
			final String isActive = "" + parseCheckBox(deactivate.getFormValue());
			activityParser = isActive;

			final Anchor ok = new Anchor("ok");

			ok.addClickHandler(new ClickHandler() {



				

				@Override
				public void onClick(ClickEvent event) {
					//gemmer de opdateret værdier
					UserDTO updatedUser = new UserDTO(id,nameBox.getText(),iniBox.getName(), cpr ,pwBox.getText(), roleBox.getSelectedIndex(), deactivate.getValue());
						mc.getUserController().updateUser(updatedUser);
					
				}
			});


			Anchor cancel = new Anchor("cancel");
			prevCancel = cancel;

			cancel.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {

					nameBox.setText(name);
					nameBox.fireEvent(new KeyUpEvent() {});

					iniBox.setText(ini);
					iniBox.fireEvent(new KeyUpEvent() {});

					table.setText(selectedRow, 1,	name);
					table.setText(selectedRow, 2, 	ini);
					table.setText(selectedRow, 4, 	pw);
					table.setText(selectedRow, 5, 	role);
					table.setText(selectedRow, 6, 	isActive);
					table.setWidget(selectedRow, 7, edit);


					table.clearCell(selectedRow, 8);

					prevCancel = null;
				}
			}); 
			table.setWidget(selectedRow, 7, cancel);
			table.setWidget(selectedRow, 8, ok);
		}

	}

	private String parseRole(int roleNo){

		if(roleNo == 0) return "Operatør";
		else if(roleNo == 1) return "Værkfører";
		else if(roleNo == 2) return "Farmaceut";
		else if(roleNo == 3) return "Administrator";
		else return null;
	}

	private int reverseParseRole(String roleName){
		if("Operatør".equals(roleName)) return 0;
		else if("Værkfører".equals(roleName)) return 1;
		else if("Farmaceut".equals(roleName)) return 2;
		else if("Administrator".equals(roleName)) return 3;
		else return -1;
	}

	private boolean parseCheckBox(String on){
		if(on.equalsIgnoreCase("on")) return true;
		else return false;

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

		}
	}






}