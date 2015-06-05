package code.client.views;

import code.client.DatabaseService;
import code.client.controllers.MainController;
import code.database.UserDTO;

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
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class CreateUserView extends Composite {

	VerticalPanel vPanel;
	Label name, ini, cpr, role;
	TextBox nameBox, iniBox, cprBox;
	ListBox roleBox;
	Grid table;
	Button okBtn;

	public CreateUserView(final DatabaseService dbs){
		vPanel = new VerticalPanel();
		initWidget(vPanel);
		name = new Label("navn");
		ini = new Label("ini");
		cpr = new Label("CPR");
		role = new Label("Rolle");
		nameBox = new TextBox();
		iniBox = new TextBox();
		cprBox = new TextBox();

		roleBox = new ListBox();
		roleBox.insertItem("Operatør", 0);
		roleBox.insertItem("Værkfører", 1);
		roleBox.insertItem("Farmaceut", 2);
		roleBox.insertItem("Administrator", 3);
		vPanel.add(roleBox);
		
		table= new Grid(2,4);
		table.setWidget(0,0, name);
		table.setWidget(0, 1, nameBox);
		table.setWidget(1,0, ini);
		table.setWidget(1, 1, iniBox);
		table.setWidget(2,0, cpr);
		table.setWidget(2, 1, cprBox);
		table.setWidget(3,0, role);
		table.setWidget(3, 1, roleBox);
		vPanel.add(table);


		okBtn = new Button("Opret");
		



	}
	

	private class okClickHandler implements ClickHandler{
		UserDTO newPerson = new UserDTO(0, nameBox.getText(), iniBox.getText(), cprBox.getText(), "", roleBox.getTabIndex(), true);
		
		@Override
		public void onClick(ClickEvent event) {
			
		}

	}

private class verifyFields implements KeyUpHandler{

	
	@Override
	public void onKeyUp(KeyUpEvent event) {
		// TODO Auto-generated method stub
		
	}
	
}
	
}
