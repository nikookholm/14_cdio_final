package code.client.views;

import java.util.ArrayList;

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
	
	public ListUsersView(){
		this.mc = mc;
		
		vPanel = new VerticalPanel();
		initWidget(vPanel);
		
		
		Header       = new Label("Vis Brugere");
		fillerLabel  = new Label("Antal brugere " + users.size());
		presentError = new Label("");
		
		vPanel.add(Header);
		vPanel.add(fillerLabel);
		vPanel.add(presentError);
		
		table = new FlexTable();
		
		
		table.setText(0, 0, "ID");
		table.setText(0, 1, "Navn");
		table.setText(0, 2, "Initialer");
		table.setText(0, 3, "CPR");
		table.setText(0, 4, "Password");
		table.setText(0, 5, "Rolle");
		
		for (int i = 0; i < users.size(); i++) {
			table.setText(i+1, 0, Integer.toString(users.get(i).getOprId()));
			table.setText(i+1, 0, ""+ users.get(i).getOprName() );
			table.setText(i+1, 0, "" + users.get(i).getIni() );
			table.setText(i+1, 0,""+ users.get(i).getCpr() );
			table.setText(i+1, 0, ""+ users.get(i).getPassword() );
			table.setText(i+1, 0, Integer.toString(users.get(i).getRole()));
		}
		vPanel.add(table);
	}
}