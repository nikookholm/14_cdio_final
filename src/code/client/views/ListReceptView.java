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

public class ListReceptView extends Composite{

	ReceptController receptC;
	MainController mc;
	VerticalPanel VPanel;
	Button backButton;
	FlexTable ft;
	
	ArrayList<ReceptDTO> list;
	
	public ListReceptView(ArrayList<ReceptDTO> receptDTO, MainController mc)
	{
		this.mc = mc;
		VPanel = new VerticalPanel();
		VPanel.setHeight("100px");
		
		initWidget(this.VPanel);
		
		backButton = new Button("Tilbage");
		backButton.setEnabled(true);
		
		this.ft = new FlexTable();
		ft.setTitle("ReceptView");
		ft.setText(0, 0, "recept id");
		ft.setText(0, 1, "recept name");
		
		for (int i=0; i < list.size(); i++) {
			ft.setText(i+1, 0, "" + list.get(i).getReceptId());
			ft.setText(i+1, 1, "" + list.get(i).getReceptName());
		}
		VPanel.add(ft);
		backButton.addClickHandler(new backClickHandler());
	}
	
	private class backClickHandler implements ClickHandler
	{
		public void onClick(ClickEvent event)
		{
			mc.show(new MainView(mc));
		}
	}
}
