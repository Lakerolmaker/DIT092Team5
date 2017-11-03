package UILibrary;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;

public interface GetterInterface {

	public  JTextField getTextField(String ID);
	public  JButton getButton(String ID);
	public  JLabel getLabel(String ID);
	public  JProgressBar getProgressbar(String ID);
	public  JSlider getSlider(String ID);
	public  JComboBox getCombobox(String ID);
	public  JPasswordField getPasswordfield(String ID);
	public  JSpinner getSpinner(String ID);
	public  JTable getGridTable(String ID);
	
	 
	
}
