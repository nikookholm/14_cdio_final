package code.client.controllers;

import java.util.ArrayList;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;

import code.client.views.CreateReceptView;
import code.client.views.InfomationWidget;
import code.client.views.ListReceptsView;
import code.database.ReceptDTO;

public class ReceptController {

	MainController mc;
	ArrayList<ReceptDTO> receptDTO;
	ReceptDTO rcptDTO;
	boolean booln = false; 
	Widget returnView;
	Widget returnInfo;
	
	public ReceptController(MainController mc)
	{
		this.mc = mc;
	}

	//Widget der laver en recept. Hvis den forsøgte oprettede recept har samme id, som én i systemet, oprettes ikke
	public Widget createRecept(final Object receptDTO)
	{	

		if(receptDTO instanceof ReceptDTO)
		{
			int checkId = ((ReceptDTO)receptDTO).getReceptId();
			mc.databaseService.recept_table_get(checkId, new AsyncCallback<ReceptDTO>(){
				@Override
				public void onSuccess(ReceptDTO result)
				{
					booln = true;
				}

				@Override
				public void onFailure(Throwable caught)
				{
					returnInfo = new InfomationWidget().showInfomation(caught);
					mc.show(new CreateReceptView(mc, returnInfo));
				}
			});

			if(booln == true){

				mc.databaseService.recept_table_create((ReceptDTO)receptDTO, new AsyncCallback<Void>(){

					@Override
					public void onSuccess(Void result)
					{
						returnInfo = new InfomationWidget().showInfomation("Recept " + ((ReceptDTO) receptDTO).getReceptName() + " oprettet!");
						mc.show(new CreateReceptView(mc, returnInfo));
					}

					@Override
					public void onFailure(Throwable caught)
					{
						returnInfo = new InfomationWidget().showInfomation(caught);
						mc.show(new CreateReceptView(mc, returnInfo));
					}
				});
			}
		}
		returnInfo = null;
		return new CreateReceptView(mc, returnInfo);
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
