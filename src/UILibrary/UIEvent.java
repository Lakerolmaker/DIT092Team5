package UILibrary;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class UIEvent {

	public JFrame frame; 
	public static ArrayList<JTextField> activeTextFields = new ArrayList<JTextField>(); 
	
	public String framename;
	public char buttonPressed;
	public int keyCode;
	
	public UIEvent(String frameNameInput, String title ,Boolean fullScreen , Runnable function) {
		
		Runnable r = new Runnable() {
			
            @Override
            public void run() {
		
            	framename = frameNameInput;
        		
        	//: Creates the frame object
        	JFrame createdFrame = new JFrame(frameNameInput); 
		//: Sets the dimensions the the frame
        	createdFrame.setSize(400, 300);
		//: Defines that the window will close when the user presses the close button
        	createdFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//: Sets the name of frame so it canbe acced later
        	createdFrame.setName(framename);
		
		//: Sets the title of the window
        	createdFrame.setTitle(title);
		
		//: Defines fullscreen mode if it is specified
		if(fullScreen)
			createdFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		
		//: Get´s the dimension of the window
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();
			
		//: Calculates the center of the screen and removes the the size of the frame.
		int x = width/2 - (int)createdFrame.getSize().getWidth()/2;
		int y = height/2 - (int)createdFrame.getSize().getHeight()/2;
		//: Sets the coordinates of the frame in the center of the screen
		createdFrame.setLocation(x,y);
				
		//: Shows the frame
		createdFrame.setLocationRelativeTo(null);
		          
		
                JPanel panel = new JPanel();
               
                panel.setLayout(null);

                createdFrame.getContentPane().add(panel);
                
                //:Adds the frame to a list so it can be acced
              //  activeFrames.add(panel);
            
                panel.setName(framename);

                panel.addKeyListener(new KeyListener() {

                    @Override
                    public void keyTyped(KeyEvent e) {}

                    @Override
                    public void keyReleased(KeyEvent e) {}

                    @Override
                    public void keyPressed(KeyEvent e) {
                   
                    	buttonPressed = e.getKeyChar();
                    keyCode = e.getKeyCode();
               			  function.run();
          
                    }
                    
                });

                panel.setFocusable(true);
                panel.requestFocusInWindow();
                
                createdFrame.setVisible(true);
                
              //:Adds the frame to a list so it can be acced
        		frame = createdFrame;
            }

        };

        SwingUtilities.invokeLater(r);
		
	}
	
	public void addButton(String ButtonLabel, int x , int y , int width , int height , Runnable function) {

	
		JButton button = new JButton(ButtonLabel);
		button.setBounds(x, y, width, height);
		button.setSize(width, height);
		
		//: Adds the button to the frame
		frame.add(button);
		//: Updates the frame
		frame.repaint();
		
		button.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
			  function.run();
		  }
		});


	}
	
	public void addLabel(String FrameName , String LabelText, int x , int y , int width , int height) {
		
		JLabel label = new JLabel(LabelText);
		label.setBounds(x, y, width, height);
		label.setSize(width, height);
		label.setVisible(true);

		frame.add(label);
		
		frame.repaint();
	}
	
	public void addTextField(String FrameName , String fieldName,  String deafualtInput, int x , int y , int width , int height) {
		
		JTextField input = new JTextField(deafualtInput);
		
		input.setName(fieldName);
		
		input.setBounds(x, y, width, height);

		frame.add(input);
		
		activeTextFields.add(input);
		
		frame.repaint();
		
	}
	
	public String GetTextFieldText(String fieldName) {
		
		String fieldValue = "";
		

		for(int i = 0 ; i < activeTextFields.size(); i++) {

			if(activeTextFields.get(i).getName() == fieldName )
				fieldValue = activeTextFields.get(i).getText();
			}	
		
		return fieldValue;
					
	}
	
	public void removeUI(String FrameName) {
		
		frame.setVisible(true); 
		frame.dispose(); 		
	}
	 	
	public void updateobject(String fieldName) {
		
		JTextField selectedField = null;

			for(int i = 0 ; i < activeTextFields.size(); i++) {

				if(activeTextFields.get(i).getName() == fieldName )
					selectedField = activeTextFields.get(i);
				}	
			
			//selectedField.getX()
			//selectedField.getY()
			selectedField.setBounds(20, 20, selectedField.getWidth(), selectedField.getHeight());
			selectedField.updateUI();
			
		}
	
	
	
	
	
}
