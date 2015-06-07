package code.client.views;

import code.client.DatabaseService;
import code.client.controllers.MainController;
import code.database.DALException;
import code.database.UserDTO;
import code.shared.FieldVerifier;

import com.google.gwt.dev.asm.tree.IntInsnNode;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class CreateUserView extends Composite {
	VerticalPanel vPanel;
	
	TextBox nameBox, iniBox, cprBox;
	ListBox roleList;
	Button okBtn;
	Grid table;

	MainController mc;

	// Flag der skal afgøre om ok knappen skal gøres tilgænglig
	boolean nameCheck = false; 
	boolean iniCheck  = false;
	boolean cprCheck  = false;



	public CreateUserView(final MainController mc){

		this.mc = mc;
		this.vPanel = new VerticalPanel();
		initWidget(vPanel);


		
		

		okBtn = new Button("Ok");
		okBtn.setEnabled(false);

		table = new Grid(4,2);
		table.setWidget(0, 0, new Label("Navn"));
		table.setWidget(0, 1, nameBox = new TextBox());
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
	    
	    vPanel.add(okBtn);
	    

			
		}
}








