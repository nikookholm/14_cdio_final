package code.client.controllers;

import java.util.ArrayList;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;

import code.client.views.CreateReceptView;
import code.client.views.ListReceptsView;
import code.database.ReceptDTO;

public class ReceptController {

	MainController mc;
	ArrayList<ReceptDTO> receptDTO;
	ReceptDTO rcptDTO;
	boolean booln = false; 
	Widget returnView;
	
	public ReceptController(MainController mc)
	{
		this.mc = mc;
	}

	//Widget der laver en recept. Hvis den forsøgte oprettede recept har samme id, som én i systemet, oprettes ikke
	public Widget createRecept(final ReceptDTO receptDTO)
	{	
		this.rcptDTO = receptDTO;

		if(receptDTO != null)
		{
			int checkId = receptDTO.getReceptId();
			mc.databaseService.recept_table_get(checkId, new AsyncCallback<ReceptDTO>(){
				@Override
				public void onSuccess(ReceptDTO result)
				{
					booln = true;
				}

				@Override
				public void onFailure(Throwable caught)
				{
					booln = false;
					Window.alert("Kunne ikke oprette en recept" +caught.getMessage());
				}
			});

			if(booln == true){

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
			}
			return new CreateReceptView(receptDTO, mc);
		}else{
			return new CreateReceptView(null, mc);
		}
	}
	
	//Widget, der lister recepterne for brugeren
	public Widget listRecepts()
	{		
		mc.databaseService.recept_table_list(new AsyncCallback<ArrayList<ReceptDTO>>() {

			@Override
			public void onSuccess(ArrayList<ReceptDTO> list) {
				receptDTO = list;
				mc.show(new ListReceptsView(receptDTO, mc));
			}

			@Override
			public void onFailure(Throwable caught) {
				receptDTO = null;
				Window.alert("Listen over recepter kunne ikke hentes "+caught.getMessage());
			}
		});

		return returnView;
	}
}
