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
				//hvis brugeren har rollen operator, vises kun de produktbatchkomponenter, som tilhører dennes id
				if(mc.getUser().getRole() == 4){
					ArrayList<ProductBatchCompDTO> toRemove = new ArrayList<ProductBatchCompDTO>();
					for(ProductBatchCompDTO pbC : list){
						if(pbC.getOprId() != mc.getUser().getOprId()){
							toRemove.add(pbC);
						}
					}
					for(ProductBatchCompDTO	pbC : toRemove){
						list.remove(pbC);
					}
					pbCompDTO = list;
				}
				
				//hvis brugeren ikke har rollen operator vises en fuldstændig liste
				else{
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
