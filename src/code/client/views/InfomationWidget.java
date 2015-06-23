package code.client.views;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class InfomationWidget extends Composite {
	
	public Widget showInfomation(Object info)
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
			img = new Image("error.png");
			img.setSize("60px", "60px");
			panel.add(img);
			infoLabel = new Label(((Throwable) info).getMessage());
			//infoLabel = new Label(((Throwable) info).getMessage().substring(((Throwable) info).getMessage().indexOf(':')+2));
			infoLabel.addStyleName("errorInfo");
		}
		else if (info instanceof String)
		{
			img = new Image("info.png");
			img.setSize("60px", "60px");
			panel.add(img);
			infoLabel = new Label((String)info);
			infoLabel.addStyleName("errorInfo");
			
		}

		panel.add(infoLabel);
		
		//return panel;
		return panel;
	}
	
}
