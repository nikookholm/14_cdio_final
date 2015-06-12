package code.client.controllers;

import java.util.ArrayList;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;

import code.client.views.CreateProductBatchView;
import code.client.views.ListProductBatchView;
import code.database.*;

public class ProductBatchController 
{
	MainController mc;
	ProductBatchDTO pbDTO;
	
	ArrayList<ProductBatchDTO> pbs;
	
	public ProductBatchController(MainController mc)
	{
		this.mc = mc;
	}
	
	public Widget createProductBatch(ProductBatchDTO pbDTO)
	{
		this.pbDTO = pbDTO;
		
		if(pbDTO != null){
			mc.databaseService.productBatch_table_create(pbDTO, new AsyncCallback<Void>() {
				@Override
				public void onSuccess(Void result) {
					
				}
				@Override
				public void onFailure(Throwable caught) {
					Window.alert("Server fejl! : " + caught.getMessage());
				}
			});
			return new CreateProductBatchView(pbDTO, mc);	
		}
		else{
			return new CreateProductBatchView(pbDTO, mc);
		}
	}
	
	public Widget listProductBatch()
	{
		mc.databaseService.productBatch_table_list(new AsyncCallback<ArrayList<ProductBatchDTO>>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Server fejl!" + caught.getMessage());
			}
			@Override
			public void onSuccess(ArrayList<ProductBatchDTO> result) {
				pbs = result;
			}
		});
		return new ListProductBatchView(pbs, mc);
	}
	
	private void printProductBatch()
	{
		
	}
	
}
