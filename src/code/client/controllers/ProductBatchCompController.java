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
	ProductBatchCompDTO pbCompDTO;

	public ProductBatchCompController(MainController mc)
	{
		this.mc = mc;
	}
	
	public Widget listProductBatchComp()
	{
		mc.databaseService.productBatchComp_table_list(new AsyncCallback<ArrayList<ProductBatchCompDTO>>(){
			
			@Override
			public void onFailure(Throwable caught)
			{
				Window.alert("Kunne ikke hente liste fra server" + caught.getMessage());
			}
			@Override
			public void onSuccess(ArrayList<ProductBatchCompDTO> list)
			{
				//Success
			}
		});
		return new ListProductBatchCompView(pbCompDTO, mc);
	}
	
	public void SortedPBCompList(ArrayList<ProductBatchCompDTO> list)
	{
		list = new ArrayList<ProductBatchCompDTO>();
		
		for(int i = 0; i < list.size(); i++)
		{
			list.add(list.get(i));
		}
	}
}
