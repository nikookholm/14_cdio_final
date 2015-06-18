package code.client.controllers;

import java.util.ArrayList;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;

import code.client.views.ListProductBatchCompView;
import code.database.*;


public class ProductBatchCompController 
{
	MainController mc;
	ArrayList<ProductBatchCompDTO> pbCompDTO;

	public ProductBatchCompController(MainController mc)
	{
		this.mc = mc;
	}
	
	//Widget liste-visning over produktbatchkomponenter.
	public Widget listProductBatchComp()
	{
		mc.databaseService.productBatchComp_table_list(new AsyncCallback<ArrayList<ProductBatchCompDTO>>(){
			
			@Override
			public void onSuccess(ArrayList<ProductBatchCompDTO> list)
			{
				//hvis brugeren ikke har rollen operator vises en fuldstændig liste
				if(mc.getUser().getRole() != 4){
					pbCompDTO = list;
				}
				//hvis brugeren har rollen operator, vises kun de produktbatchkomponenter, som tilhører dennes id
				else{
					for(ProductBatchCompDTO pbC : list){
						if(mc.getUser().getOprId() != pbC.getOprId()){
							list.remove(pbC);
						}
					}
				pbCompDTO = list;
				}
			}
			
			@Override
			public void onFailure(Throwable caught)
			{
				Window.alert("Kunne ikke hente liste fra server" + caught.getMessage());
				pbCompDTO = null;
			}
		});
		return new ListProductBatchCompView(pbCompDTO, mc);
	}
}
