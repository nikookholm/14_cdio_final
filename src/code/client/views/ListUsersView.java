package code.client.views;

import java.util.ArrayList;

import javax.management.relation.RoleNotFoundException;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
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

public class ListUsersView extends Composite{

	MainController mc;

	ArrayList<UserDTO> users;

	VerticalPanel vPanel;
	Label Header, fillerLabel, presentError;
	FlexTable table;
	Anchor edit, ok, cancel, prevCancel;
	int selectedRow;
	TextBox nameBox, iniBox, pwBox;
	ListBox roleBox;
	CheckBox deactivate;

	public ListUsersView(ArrayList<UserDTO> users, MainController mc){
		this.users = users;
		this.mc = mc;

		vPanel = new VerticalPanel();
		initWidget(vPanel);

		Header       = new Label("Vis Brugere ");
		presentError = new Label("");

		vPanel.add(Header);
		vPanel.add(presentError);
		table = new FlexTable();


		table.setText(0, 0, "ID");
		table.setText(0, 1, "Navn");
		table.setText(0, 2, "Initialer");
		table.setText(0, 3, "CPR");
		table.setText(0, 4, "Password");
		table.setText(0, 5, "Rolle");

		for (int i = 0; i < this.users.size(); i++) {
			table.setText(i+1, 0,	"" + this.users.get(i).getOprId());
			table.setText(i+1, 1, 	"" + this.users.get(i).getOprName());
			table.setText(i+1, 2, 	"" + this.users.get(i).getIni());
			table.setText(i+1, 3,  	"" + this.users.get(i).getCpr());
			table.setText(i+1, 4, 	"" + this.users.get(i).getPassword());
			table.setText(i+1, 5, 	"" + parseRole(this.users.get(i).getRole()));
			table.setText(i+1, 6, 	"" + this.users.get(i).getActive());

			table.setWidget(i+1, 8, edit = new Anchor("rediger"));
			edit.addClickHandler(new EditButtonHandler());

		}

		vPanel.add(table);



		nameBox 	= new TextBox();
		iniBox 		= new TextBox();
		pwBox 		= new TextBox();
		roleBox 	= new ListBox();
		deactivate  = new CheckBox();



	}
	private class EditButtonHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {

			// annulerer forrige event
			if(prevCancel != null){
				prevCancel.fireEvent(new ClickEvent(){});
			}
			//Finds the row the user clicked
			selectedRow = table.getCellForEvent(event).getRowIndex();

			nameBox.setText(table.getText(selectedRow, 1));
			iniBox.setText(table.getText(selectedRow, 2));
			pwBox.setText(table.getText(selectedRow, 4));

			roleBox.addItem("Operatør");
			roleBox.addItem("Værkfører");
			roleBox.addItem("Farmaceut");
			roleBox.addItem("Administrator");

			roleBox.setItemSelected(reverseParseRole(table.getText(selectedRow, 5)), true);

			deactivate.setValue(table.getText(selectedRow, 6).equals("true"));

			table.setWidget(selectedRow, 1, nameBox);
			table.setWidget(selectedRow, 2, iniBox);
			table.setWidget(selectedRow, 4, pwBox);
			table.setWidget(selectedRow, 5, roleBox);
			table.setWidget(selectedRow, 6, deactivate);
			table.setWidget(selectedRow, 7, cancel = new Anchor("cancel"));	
			table.clearCell(selectedRow, 8);
			table.setWidget(selectedRow, 8, ok = new Anchor("ok"));

			nameBox.setFocus(true);

			final Anchor edit =  (Anchor) event.getSource();

			final String name = nameBox.getText();
			final String ini = iniBox.getText();
			final String pw = pwBox.getText();
			final String role = roleBox.getItemText(roleBox.getTabIndex());
			final String isActive = "" + parseCheckBox(deactivate.getFormValue());

			final Anchor ok = new Anchor("ok");

			cancel.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {

					table.setText(selectedRow, 1,	name);
					table.setText(selectedRow, 2, 	ini);
					table.setText(selectedRow, 4, 	pw);
					table.setText(selectedRow, 5, 	role);
					table.setText(selectedRow, 6, 	isActive);

				}
			}); 


		}
	}

	private String parseRole(int roleNo){

		if(roleNo == 1) return "Operatør";
		else if(roleNo == 2) return "Værkfører";
		else if(roleNo == 3) return "Farmaceut";
		else if(roleNo == 4) return "Administrator";
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

}