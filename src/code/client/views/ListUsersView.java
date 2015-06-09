package code.client.views;

import java.util.ArrayList;

import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import code.client.controllers.MainController;
import code.database.UserDTO;

public class ListUsersView extends Composite{
	
	MainController mc;
	
	ArrayList<UserDTO> users;
	
	VerticalPanel vPanel;
	Label Header, fillerLabel, presentError;
	FlexTable table;
	Anchor edit;
	
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
			table.setText(i+1, 0, "" + this.users.get(i).getOprId());
			table.setText(i+1, 1, "" + this.users.get(i).getOprName());
			table.setText(i+1, 2, "" + this.users.get(i).getIni());
			table.setText(i+1, 3, "" + this.users.get(i).getCpr());
			table.setText(i+1, 4, "" + this.users.get(i).getPassword());
			table.setText(i+1, 5, "" + this.users.get(i).getRole());
			table.setText(i+1, 6, "" + this.users.get(i).getActive());
		    table.setWidget(i+1, 8, edit = new Anchor("rediger"));
			
		}
		vPanel.add(table);
	}
}