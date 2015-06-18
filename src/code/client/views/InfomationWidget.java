package code.client.views;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class InfomationWidget extends Composite {
	
	public static Widget showInfomation(Object info)
	{
		HorizontalPanel panel = new HorizontalPanel();
		Image 			img;
		Label 			infoLabel = null;
		if (info == null)
		{
			infoLabel = new Label("");
		}
		else if (info instanceof Throwable)
		{
			infoLabel = new Label(((Throwable) info).getMessage());
		}
		else if (info instanceof String)
		{
			infoLabel = new Label((String)info);
		}

		panel.add(infoLabel);
		
		//return panel;
		return new Label("hej hej");
	}
	
}
