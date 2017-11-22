/* @Author: Nadja Kosir
 * Purpose: Interface for all other GUI's 
 */
import javax.swing.JOptionPane;
public interface UiInterface {

	JOptionPane.showMessageDialog (null, "default content", "default title", JOptionPane.PLAIN_MESSAGE);
	public void start();
}
