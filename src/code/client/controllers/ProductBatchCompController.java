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
			public void onFailure(Throwable caught)
			{
				Window.alert("Kunne ikke hente liste fra server" + caught.getMessage());
			}
			@Override
			public void onSuccess(ArrayList<ProductBatchCompDTO> list)
			{
				SortedPBCompList(list);
			}
		});
		return new ListProductBatchCompView(pbCompDTO, mc);
	}
	
	public void SortedPBCompList(ArrayList<ProductBatchCompDTO> list)
	{
		pbCompDTO = new ArrayList<ProductBatchCompDTO>();
		
		for(int i = 0; i < pbCompDTO.size(); i++)
		{
			pbCompDTO.add(pbCompDTO.get(i));
		}
	}
}
