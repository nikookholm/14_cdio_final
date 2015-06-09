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
	
	public ListUsersView(MainController mc){
		this.mc = mc;
		
		vPanel = new VerticalPanel();
		initWidget(vPanel);
		
		
		Header       = new Label("Vis Brugere");
		
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
		
	
		vPanel.add(table);
	}
}