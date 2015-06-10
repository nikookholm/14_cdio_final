package code.client.controllers;

import java.util.ArrayList;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;

import code.client.views.CreateProductBatchView;
import code.client.views.ListProductBatchView;
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
		mc.databaseService.productBatchComp_table_list(new AsyncCallBack <ArrayList<ProductBatchCompDTO>>(){
			
		}
	}
}
