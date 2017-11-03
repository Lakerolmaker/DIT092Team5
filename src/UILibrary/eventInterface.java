package UILibrary;

import java.awt.event.WindowEvent;

public interface eventInterface {

	public void addWindowOpened(Runnable function);

	public void addWindowClosing(Runnable function);

	public void addWindowClosed(Runnable function);

	public void addWindowIconified(Runnable function);

	public void addWindowDeiconified(Runnable function);

	public void addWindowActivated(Runnable function);

	public void addWindowDeactivated(Runnable function);
	
	
}
