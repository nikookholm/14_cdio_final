package code.client.controllers;

import java.util.ArrayList;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;

import code.client.views.CreateReceptView;
import code.database.ReceptDTO;

public class ReceptController {

	MainController mc;
	ReceptDTO receptDTO;
	
	public ReceptController(MainController mc)
	{
		this.mc = mc;
	}
	
	public Widget createRecept(ReceptDTO receptDTO)
	{	
		if(receptDTO !=null)
		{
			mc.databaseService.recept_table_create(receptDTO, new AsyncCallback<Void>(){
				
				@Override
				public void onSuccess(Void result)
				{
					
				}
				
				@Override
				public void onFailure(Throwable caught)
				{
					Window.alert("Kunne ikke oprette en recept" +caught.getMessage());
				}
			});
			return new CreateReceptView(mc, receptDTO);
		}else{
			return new CreateReceptView(mc, null);
		}
		
	}
	
	public void getReceptList()
	{		
		mc.databaseService.recept_table_list(new AsyncCallback<ArrayList<ReceptDTO>>() {
			
			@Override
			public void onSuccess(ArrayList<ReceptDTO> result) {
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Listen over recepter kunne ikke hentes"+caught.getMessage());
			}
		});
		return new ListReceptView();
	}
}
