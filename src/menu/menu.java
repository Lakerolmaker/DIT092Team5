package menu;

import java.awt.Color;

import com.sun.glass.ui.Cursor;

import UILibrary.*;
import program.*;


public class menu implements UiInterface  {
	
	
	public void start() {
	
	 
		main.frame.addButton("hello", 400, 400, 100, 40,()->{
			
			recation();
		});
		main.frame.addLabel("id1", "hello", 10, 10, 100, 20);
		main.frame.show();
		
		stop();
		
	}
	
	
	public void recation() {
		
	}
	

	public void stop() {
		
		 
		main.frame.frame.removeAll();
		main.frame.update();		
		
		
	}
	
	
	
}
