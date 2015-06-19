package code.client.controllers;

import java.util.ArrayList;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;

import code.client.views.CreateProductBatchView;
import code.client.views.InfomationWidget;
import code.client.views.ListProductBatchView;
import code.database.*;

public class ProductBatchController 
{
	MainController mc;
	ProductBatchDTO pbDTO;
	
	ArrayList<ProductBatchDTO> pbLs;
	boolean bool = false;
	Widget returnView;
	Widget returnInfo;
	
	public ProductBatchController(MainController mc)
	{
		this.mc = mc;
	}
	
	public Widget createProductBatch(final Object pbDTO)
	{
		this.pbDTO = (ProductBatchDTO)pbDTO;
		
		
		if(pbDTO instanceof ProductBatchDTO){
			int recId = ((ProductBatchDTO)pbDTO).getReceptId();
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
				mc.databaseService.productBatch_table_create((ProductBatchDTO)pbDTO, new AsyncCallback<Void>() {
					@Override
					public void onSuccess(Void result) {
						returnInfo = new InfomationWidget().showInfomation("Product batch oprettet!");
						mc.show(new CreateProductBatchView(mc, returnInfo));
					}
					@Override
					public void onFailure(Throwable caught) {
						returnInfo = new InfomationWidget().showInfomation(caught);
						mc.show(new CreateProductBatchView(mc, returnInfo));
					}
				});
			}

			
		}
		returnInfo = null;
		return new CreateProductBatchView(mc, returnInfo);
		
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
				mc.show(new ListProductBatchView(pbLs, mc));
			}
		});
		return returnView;
	}
	
	
/**	
*	private void printProductBatch(){}
**/
	
}
