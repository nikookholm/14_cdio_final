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
	
	public Widget listProductBatchComp()
	{
		mc.databaseService.productBatchComp_table_list(new AsyncCallback<ArrayList<ProductBatchCompDTO>>(){
			
			@Override
			public void onSuccess(ArrayList<ProductBatchCompDTO> list)
			{
				if(mc.getUser().getRole() != 4){
					pbCompDTO = list;
				}
				else{
					for(ProductBatchCompDTO pbC : list){
						if(pbC.getOprId() != mc.getUser().getOprId()){
							list.remove(pbC);
						}
					}
				}
				mc.show(new ListProductBatchCompView(pbCompDTO, mc));
			}
			
			@Override
			public void onFailure(Throwable caught)
			{
				Window.alert("Kunne ikke hente liste fra server" + caught.getMessage());
				pbCompDTO = null;
			}
		});
		return null;
	}
}
