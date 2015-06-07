package code.client.views;

import code.*;
import code.client.controllers.MainController;
import code.client.controllers.ProductBatchController;
import code.database.ProductBatchDTO;
import code.database.UserDTO;

import com.google.gwt.*;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class CreateProductBatchView extends Composite
{
	ProductBatchController pbC;
	VerticalPanel VPanel;
	Button OKButton;
	
	Label prodBatchNoLabel; 
	Label receptNoLabel;
//	Label dateLabel;
	Label statLabel;
	
	TextBox prodBatchNoBox; 
	TextBox receptNoBox;
//	TextBox dateBox;
	TextBox statBox;
	
	boolean prodtBathNoValidity;
	boolean receptNoValidity;
//	boolean dateValidity;
	boolean statValidity;
	
	public CreateProductBatchView(MainController mc)
	{
		VPanel = new VerticalPanel();
		VPanel.setHeight("100px");
		initWidget(this.VPanel);
		
		HorizontalPanel prodBatchNoPan 	= new HorizontalPanel(); 
		HorizontalPanel receptNoPan 	= new HorizontalPanel();
//		HorizontalPanel datePan 		= new HorizontalPanel();
		HorizontalPanel statPan 		= new HorizontalPanel();
		
		prodBatchNoLabel 	= new Label("prodBatchNo");
		receptNoLabel 		= new Label("receptNo");
//		dateLabel 			= new Label("date");
		statLabel 			= new Label("status");
		
		prodBatchNoLabel.setWidth("100px");
		receptNoLabel.setWidth("100px");
//		dateLabel.setWidth("100px");
		statLabel.setWidth("100px");
		
		prodBatchNoBox 	= new TextBox();
		receptNoBox 	= new TextBox();
//		dateBox 		= new TextBox();
		statBox 		= new TextBox();
		
		prodBatchNoBox.setHeight("1em");
		receptNoBox.setHeight("1em");
//		dateBox.setHeight("1em");
		statBox.setHeight("1em");
		
		prodBatchNoPan.add(prodBatchNoLabel);
		prodBatchNoPan.add(prodBatchNoBox);
		receptNoPan.add(receptNoLabel);
		receptNoPan.add(receptNoBox);
//		datePan.add(dateLabel);
//		datePan.add(dateBox);
		statPan.add(statLabel);
		statPan.add(statBox);
		
		OKButton = new Button();
		OKButton.setEnabled(true);
		OKButton.addClickHandler(new ClickHandler() 
		{	
			@Override
			public void onClick(ClickEvent event) 
			{
				int pbI = Integer.parseInt(prodBatchNoBox.getText());
				int statI = Integer.parseInt(statBox.getText());
				int recI = Integer.parseInt(receptNoBox.getText());
//				int datI = Integer.parseInt(dateBox.getText());
//				String dato = pbC.getDate();
				ProductBatchDTO uDTO = new ProductBatchDTO(pbI, statI, recI);
				
				try 
				{
					mc.service.create(create, new AsyncCallback<Void>() 
						{
							@Override
							public void onSuccess(Void result) 
							{
								Window.alert("ProduktBatchen er gemt succefuldt");
							}
	
							@Override
							public void onFailure(Throwable caught) 
							{
								Window.alert("Server fejl!" + caught.getMessage());
							}
	
						}
					);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
				OKButton.setEnabled(true);
			}
		});
		
		
	}
	
	
}
