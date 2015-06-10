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
	
	
	public ProductBatchController(MainController mc)
	{
		this.mc = mc;
	}
	
	public Widget createProductBatch(ProductBatchDTO pbDTO)
	{
		if(pbDTO != null){
			mc.databaseService.productBatch_table_create(pbDTO, new AsyncCallback<Void>() {
				
				@Override
				public void onSuccess(Void result) {
					
				}
				
				@Override
				public void onFailure(Throwable caught) {
					Window.alert("Server fejl!" + caught.getMessage());
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
			public void onSuccess(ArrayList<ProductBatchDTO> ls) {
				//Tillykke du er dygtig
			}
		});
		return new ListProductBatchView(pbDTO, mc);
	}
	
	public void SortedPBList(ArrayList<ProductBatchDTO> ls) {
		ls = new ArrayList<ProductBatchDTO>();

		for (int i=0; i<ls.size(); i++) {
			ls.add(ls.get(i));
		}
	}
	
	private void printProductBatch()
	{
		
	}
	
}
