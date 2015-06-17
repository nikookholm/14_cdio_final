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
	boolean bool = false;
	
	public ProductBatchController(MainController mc)
	{
		this.mc = mc;
	}
	
	public Widget createProductBatch(ProductBatchDTO pbDTO)
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
	
	public void updateProductBatch(ProductBatchDTO pbDTO)
	{
		this.pbDTO = pbDTO;
		mc.databaseService.productBatch_table_create(pbDTO, new AsyncCallback<Void>() {
			@Override
			public void onSuccess(Void result) {
				mc.show(mc.getProductBatchController().listProductBatch());
			}
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Server fejl! : " + caught.getMessage());
			}
		});
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
	
	
/**	
*	private void printProductBatch(){}
**/
	
}
