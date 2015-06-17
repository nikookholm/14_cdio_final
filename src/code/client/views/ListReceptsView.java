package code.client.views;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import code.client.controllers.MainController;
import code.client.controllers.ReceptController;
import code.database.ReceptDTO;

public class ListReceptsView extends Composite{

	ReceptController receptC;
	MainController mc;
	VerticalPanel VPanel;
	Label infoLabel				= new Label("Recepter");
	Label receptIdLabel			= new Label("Recept ID");
	Label receptNameLabel		= new Label("Recept navn");
	Button backButton;
	FlexTable ft;
	Grid subTable;

	ArrayList<ReceptDTO> receptDTO;

	public ListReceptsView(ArrayList<ReceptDTO> receptDTO, MainController mc)
	{
		this.mc = mc;
		this.receptDTO = receptDTO;
		
		VPanel = new VerticalPanel();
		
		ft = new FlexTable();
		
		VPanel.add(infoLabel);
		infoLabel.setStyleName("caption");
		ft.setWidget(0, 0, receptIdLabel);
		receptIdLabel.setStyleName("input-text");
		ft.setWidget(0, 1, receptNameLabel);
		receptNameLabel.setStyleName("input-text");
		ft.getCellFormatter().setWidth(0, 0, "150px;");
		ft.getCellFormatter().setWidth(0, 1, "150px;");

		int i = 0;
		for (ReceptDTO recept : receptDTO) {
			i++;
			ft.setText(i+1, 0, "" + recept.getReceptId());
			ft.setText(i+1, 1, "" + recept.getReceptName());
		}

		backButton = new Button("Tilbage");
		backButton.setEnabled(true);
	
		
		VPanel.add(ft);
		VPanel.add(backButton);
		initWidget(this.VPanel);
		
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
