package UILibrary;

import java.awt.Color;

import javax.swing.border.Border;

public interface gridInterfacee {

	
	interface GridStyle {
		
        void none(String gridID);
  	
	}

	
	 public void setGridColor(String ID , Color SelectedColor);
	 public void setBackground(String ID , java.awt.Color SelectedColor);
	 public void setSelectModel(String ID , int selectedGridFormats);
	 public GridReturn getValues(String ID);
}
