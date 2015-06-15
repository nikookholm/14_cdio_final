package code.client.views;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.VerticalPanel;

import code.client.controllers.MainController;
import code.client.controllers.ReceptController;
import code.database.ReceptDTO;
import code.database.UserDTO;

public class ListReceptsView extends Composite{

	ReceptController receptC;
	MainController mc;
	VerticalPanel VPanel;
	Button backButton;
	FlexTable ft;
	
	ArrayList<ReceptDTO> receptDTO;
	
	public ListReceptsView(ArrayList<ReceptDTO> receptDTO, MainController mc)
	{
		this.mc = mc;
		this.receptDTO = receptDTO;
		VPanel = new VerticalPanel();
		
		initWidget(this.VPanel);
		
		backButton = new Button("Tilbage");
		backButton.setEnabled(true);
		
		ft = new FlexTable();
		ft.setTitle("ReceptView");
		ft.setText(0, 0, "recept id");
		ft.setText(0, 1, "recept name");
		
		for (int i=0; i < this.receptDTO.size(); i++) {
			ft.setText(i+1, 0, "" + this.receptDTO.get(i).getReceptId());
			ft.setText(i+1, 1, "" + this.receptDTO.get(i).getReceptName());
		}
		VPanel.add(ft);
		backButton.addClickHandler(new backClickHandler());
	}
	
	private class backClickHandler implements ClickHandler
	{
		@Override
		public void onClick(ClickEvent event)
		{
			mc.show(new MainView(mc));
		}
	}
}
