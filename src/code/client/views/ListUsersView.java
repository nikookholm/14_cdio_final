package code.client.views;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
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
	Label headerLabel, idLabel, nameLabel, iniLabel, cprLabel, passwordLabel, roleLabel, actLabel;
	FlexTable flex;
	Anchor edit, prevCancel, ok;

	TextBox nameBox, iniBox, pwBox;
	ListBox roleBox;
	CheckBox deactivate;
	Button backButton;
	int id, selecedRow ;
	String cpr;

	//bools til at verificere om felterne er udfyldt rigtigt
	boolean nameCheck 		= false; 
	boolean iniCheck  		= false;
	boolean passwordCheck	= false; 

	public ListUsersView(ArrayList<UserDTO> users, MainController mc)
	{
		this.users = users;
		this.mc = mc;
		vPanel = new VerticalPanel();


		headerLabel		= new Label("Brugere");		
		idLabel			= new Label("ID");
		nameLabel		= new Label("Navn");
		iniLabel		= new Label("Initialer");
		cprLabel		= new Label("CPR");
		passwordLabel	= new Label("Kodeord");
		roleLabel		= new Label("Rolle");
		actLabel		= new Label("Aktiv");

		flex = new FlexTable();

		headerLabel.setStyleName("caption");
		idLabel.setStyleName("input-text");
		nameLabel.setStyleName("input-text");
		iniLabel.setStyleName("input-text");
		cprLabel.setStyleName("input-text");
		passwordLabel.setStyleName("input-text");		
		roleLabel.setStyleName("input-text");
		actLabel.setStyleName("input-text");
		flex.getCellFormatter().setWidth(0, 0, "142px;");
		flex.getCellFormatter().setWidth(0, 1, "142px;");
		flex.getCellFormatter().setWidth(0, 2, "142px;");
		flex.getCellFormatter().setWidth(0, 3, "142px;");
		flex.getCellFormatter().setWidth(0, 4, "142px;");
		flex.getCellFormatter().setWidth(0, 5, "142px;");
		flex.getCellFormatter().setWidth(0, 6, "142px;");

		flex.setWidget(0, 0, idLabel);
		flex.setWidget(0, 1, nameLabel);
		flex.setWidget(0, 2, iniLabel);
		flex.setWidget(0, 3, cprLabel);
		flex.setWidget(0, 4, passwordLabel);
		flex.setWidget(0, 5, roleLabel);
		flex.setWidget(0, 6, actLabel);


		for (int i = 0; i < this.users.size(); i++) {
			flex.setText(i+1, 0, "" + this.users.get(i).getOprId());
			flex.setText(i+1, 1, "" + this.users.get(i).getOprName());
			flex.setText(i+1, 2, "" + this.users.get(i).getIni());
			flex.setText(i+1, 3, "" + this.users.get(i).getCpr());
			flex.setText(i+1, 4, "" + this.users.get(i).getPassword());
			flex.setText(i+1, 5, "" + parseRole(this.users.get(i).getRole()));
			flex.setText(i+1, 6, "" + this.users.get(i).getActive());

			Anchor edit = new Anchor("Redigér");
			edit.addClickHandler(new EditButtonHandler());

			flex.setWidget(i+1, 7, edit );
		}

		backButton = new Button("Tilbage");	
		backButton.setEnabled(true);
		backButton.addClickHandler(new backClickHandler());

		vPanel.add(headerLabel);
		vPanel.add(flex);
		vPanel.add(backButton);
		vPanel.setCellHorizontalAlignment(backButton, HasHorizontalAlignment.ALIGN_CENTER);
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


			if(prevCancel != null){
				prevCancel.fireEvent(new ClickEvent(){});
			}

			final int selectedRow = flex.getCellForEvent(event).getRowIndex();


			nameBox.setText(flex.getText(selectedRow, 1));
			iniBox.setText(flex.getText(selectedRow, 2));
			pwBox.setText(flex.getText(selectedRow, 4));

			roleBox.clear();
			roleBox.addItem("Operatør");
			roleBox.addItem("Værkfører");
			roleBox.addItem("Farmaceut");
			roleBox.addItem("Administrator");
			roleBox.setItemSelected(reverseParseRole(flex.getText(selectedRow, 5)), true);
			deactivate.setValue(flex.getText(selectedRow, 6).equals("true"));

			flex.setWidget(selectedRow, 1, nameBox);
			flex.setWidget(selectedRow, 2, iniBox);
			flex.setWidget(selectedRow, 4, pwBox);
			flex.setWidget(selectedRow, 5, roleBox);
			flex.setWidget(selectedRow, 6, deactivate);



			final Anchor edit =  (Anchor) event.getSource();
			id = Integer.parseInt(flex.getText(selectedRow, 0));
			final String name = nameBox.getText();
			final String ini = iniBox.getText();
			cpr = flex.getText(selectedRow, 3);
			final String pw = pwBox.getText();
			final String role = roleBox.getItemText(roleBox.getTabIndex());

			final String isActive = "" + deactivate.getValue();

			final Anchor ok = new Anchor("ok");
			ok.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					flex.setText(selectedRow, 1, nameBox.getName());
					flex.setText(selectedRow, 2, iniBox.getText());
					flex.setText(selectedRow, 4, pwBox.getText());
					flex.setText(selectedRow, 5, parseRole(roleBox.getTabIndex()));
					flex.setText(selectedRow, 6, "" + deactivate.getValue());

					UserDTO updatedUser = new UserDTO(id, nameBox.getText(), iniBox.getText(), cpr, pwBox.getText(), roleBox.getSelectedIndex(), deactivate.getValue());
					mc.getUserController().updateUser(updatedUser);

					flex.setWidget(selectedRow, 7, edit);
					flex.clearCell(selectedRow, 8);
					
					prevCancel = null;
				}
				
				
			});




			Anchor cancel = new Anchor("Annullér");

			prevCancel = cancel;
			cancel.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {

					nameBox.setText(name);
					nameBox.fireEvent(new KeyUpEvent() {});

					iniBox.setText(ini);
					iniBox.fireEvent(new KeyUpEvent() {});

					pwBox.setText(pw);
					pwBox.fireEvent(new KeyUpEvent() {});

					flex.setText(selectedRow, 1,	name);
					flex.setText(selectedRow, 2, 	ini);
					flex.setText(selectedRow, 4, 	pw);
					flex.setText(selectedRow, 5, 	role);
					flex.setText(selectedRow, 6, 	isActive);
					flex.setWidget(selectedRow, 7, 	edit);

					flex.clearCell(selectedRow, 8);

					prevCancel = null;
				}
			}); 

			nameBox.addKeyUpHandler(new KeyUpHandler() {

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
			});

			iniBox.addKeyUpHandler(new KeyUpHandler() {

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
			});
			pwBox.addKeyUpHandler(new KeyUpHandler() {

				@Override
				public void onKeyUp(KeyUpEvent event) {
					if(!FieldVerifier.isPswdValid(pwBox.getText())){
						pwBox.setStyleName("gwt-TextBox-invalidEntry");
						passwordCheck = false;

					}

					else{
						pwBox.removeStyleName("gwt-TextBox-invalidEntry");
						passwordCheck = true;


					}

				}
			});

			flex.setWidget(selectedRow, 8, ok);
			flex.setWidget(selectedRow, 7, cancel);




		}
	}

	private String parseRole(int roleNo)
	{
		if(roleNo == 1) return "Administrator" ;
		else if(roleNo == 2) return "Farmaceut";
		else if(roleNo == 3) return "Værkfører";
		else if(roleNo == 4) return "Operatør";
		else return null;
	}

	private int reverseParseRole(String roleName)
	{
		if("Administrator".equals(roleName)) return 1;
		else if("Farmaceut".equals(roleName)) return 2;
		else if("Værkfører".equals(roleName)) return 3;
		else if("Operatør".equals(roleName)) return 4;
		else return -1;
	}

	private boolean parseCheckBox(String on)
	{
		if(on.equalsIgnoreCase("on")) return true;
		else return false;
	}







	private class backClickHandler implements ClickHandler{
		@Override
		public void onClick(ClickEvent event) {
			mc.show(new MainView(mc));
		}
	}

}