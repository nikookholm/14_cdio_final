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
	
	ArrayList<ProductBatchDTO> pbLs;
	boolean bool = false;
	Widget returnView;
	
	public ProductBatchController(MainController mc)
	{
		this.mc = mc;
	}
	
	public Widget createProductBatch(final ProductBatchDTO pbDTO)
	{
		this.pbDTO = pbDTO;
		
		if(pbDTO != null){
			int recId = pbDTO.getReceptId();
			mc.databaseService.recept_table_get(recId, new AsyncCallback<ReceptDTO>() {
				@Override
				public void onFailure(Throwable caught) {
					bool = false;
				}
				@Override
				public void onSuccess(ReceptDTO result) {
					bool = true;
				}
			});
			
			if(bool == true){
				mc.databaseService.productBatch_table_create(pbDTO, new AsyncCallback<Void>() {
					@Override
					public void onSuccess(Void result) {
					}
					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Server fejl! : " + caught.getMessage());
					}
				});
			}
			return new CreateProductBatchView(pbDTO, mc);
		}else{
			return new CreateProductBatchView(null, mc);
		}
	}
	
	
	public Widget listProductBatch()
	{
		mc.databaseService.productBatch_table_list(new AsyncCallback<ArrayList<ProductBatchDTO>>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Server fejl!" + caught.getMessage());
				pbLs = null;
			}
			@Override
			public void onSuccess(ArrayList<ProductBatchDTO> result) {
				pbLs = result;
			}
		});
		return new ListProductBatchView(pbLs, mc);
	}
	
	
/**	
*	private void printProductBatch(){}
**/
	
}
