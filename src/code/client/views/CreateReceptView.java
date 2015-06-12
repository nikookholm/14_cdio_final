package code.client.views;

import code.client.controllers.MainController;
import code.client.controllers.ReceptController;
import code.client.views.CreateIngredientView.ingredientId;
import code.client.views.CreateIngredientView.ingredientName;
import code.client.views.CreateIngredientView.leverandoer;
import code.database.IngredientDTO;
import code.database.ReceptDTO;
import code.shared.FieldVerifier;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class CreateReceptView extends Composite
{
	ReceptController receptC;
	ReceptDTO receptDTO;
	MainController mc;
	
	VerticalPanel vPanel;
	Grid table, subTable;
	boolean receptNameCheck	= false;
	boolean receptIdCheck	= false;
	
	private TextBox receptNameBox = new TextBox();    	//receptnavn
	private TextBox receptIdBox   = new TextBox(); 	 	//receptid
	
	private Label receptNameLabel = new Label("recept name");
	private Label receptIdLabel   = new Label("recept id");
	private Button OkButton     = new Button("Ok");
	private Button cancelButton = new Button("cancel");
	
	public CreateReceptView(final MainController mc, ReceptDTO receptDTO)
	{
		this.receptDTO 	= receptDTO;
		this.mc 		= mc;
		this.vPanel		= new VerticalPanel();
		
		initWidget(vPanel);
		
		if(receptDTO != null)
		{
			vPanel.add(new Label(receptDTO.getReceptName() + "blev oprettet"));
		}
		
		this.table = new Grid(2,2);
		
		table.setWidget(0, 1, receptNameLabel);
		table.setWidget(0, 1, receptNameBox);
		table.setWidget(1, 0, receptIdLabel);
		table.setWidget(1, 1, receptIdBox);
		
		this.subTable = new Grid(1,2);
		
		subTable.setWidget(0, 0, cancelButton);
		subTable.setWidget(0, 1, OkButton);
		
		OkButton.setEnabled(false);
		OkButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				ReceptDTO receptDTO = new ReceptDTO(Integer.parseInt(receptIdBox.getText()), receptNameBox.getText());
				mc.show(mc.getReceptController().createRecept(receptDTO));

			}
		});
	
		cancelButton.setEnabled(true);
		cancelButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				mc.show(new MainView(mc));
			}
		});
		
		vPanel.add(table);

		receptIdBox.addKeyUpHandler(new receptId());
		receptNameBox.addKeyUpHandler(new receptName());

		vPanel.add(subTable);
	}
	
	private class receptId implements KeyUpHandler
	{
		@Override
		public void onKeyUp(KeyUpEvent event)
		{
			if(!FieldVerifier.isReceptNoValid(receptIdBox.getText()))
		}
	}
}
