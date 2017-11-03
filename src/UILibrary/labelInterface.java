package UILibrary;

import javax.swing.JLabel;
import javax.swing.border.Border;

public interface labelInterface {

	void setborder(String ID, Border borderkind);
	void setFontFamliy(String ID , String afontFamliy);
	void setFontType(String ID , int afontType);
	void setFontSize(String ID ,int size);
	void updateFont(String ID);
	
}
