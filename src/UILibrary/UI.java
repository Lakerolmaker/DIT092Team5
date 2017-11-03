package UILibrary;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerModel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeListener;

import com.sun.java.swing.plaf.gtk.GTKConstants.Orientation;
import com.sun.prism.paint.Color;
import com.sun.xml.internal.ws.api.config.management.policy.ManagementAssertion.Setting;
import com.sun.xml.internal.ws.api.server.Container;


import UILibrary.*;
import javafx.geometry.Insets;
import javafx.scene.image.Image;


public class UI {
	
	public  JFrame frame; 
	private String framename;
	private  ArrayList<JTextField> activeTextFields = new ArrayList<JTextField>(); 
	private  ArrayList<JButton> activebuttons = new ArrayList<JButton>(); 
	private  ArrayList<JLabel> activelabels = new ArrayList<JLabel>(); 
	private  ArrayList<JProgressBar> activeProgressBar = new ArrayList<JProgressBar>(); 
	private  ArrayList<JSlider> activeSliders = new ArrayList<JSlider>(); 
	private  ArrayList<JComboBox> activeDropdown = new ArrayList<JComboBox>(); 
	private  ArrayList<JPasswordField> activePasswordFields = new ArrayList<JPasswordField>(); 
	private  ArrayList<JSpinner> activeSpinners = new ArrayList<JSpinner>(); 
	private  ArrayList<JTable> activeGridTable = new ArrayList<JTable>(); 
	
	
	public UI(String frameID, String title , int width , int height ,  Boolean fullScreen ,  Boolean togglefullscreen, Boolean mainWindow) {
		
		framename = frameID;
		
		//: Creates the frame object
		JFrame createdFrame = new JFrame(frameID); 
		//: Sets the dimensions the the frame
		createdFrame.setSize(width, height);
		//: Defines that the window will close when the user presses the close button if it is the main window
		if(mainWindow) {
			createdFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}else {
			createdFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		}
		//: Sets the name of frame so it canbe acced later
		createdFrame.setName(frameID);
		
		//: Sets the title of the window
		createdFrame.setTitle(title);
		
		//: Defines fullscreen mode if it is specified
		if(fullScreen)
			createdFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		//: Defines if you can resize the window
		createdFrame.setResizable(togglefullscreen);
		
	
		//: Get´s the dimension of the window
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int ScreenWidth = gd.getDisplayMode().getWidth();
		int ScreenHeight = gd.getDisplayMode().getHeight();
			
		//: Calculates the center of the screen and removes the the size of the frame.
		int x = ScreenWidth/2 - (int)createdFrame.getSize().getWidth()/2;
		int y = ScreenHeight/2 - (int)createdFrame.getSize().getHeight()/2;
		//: Sets the coordinates of the frame in the center of the screen
		createdFrame.setLocation(x,y);
				
		//: Shows the frame
		createdFrame.setLocationRelativeTo(null);
		
		createdFrame.setLayout(null);
		
		//:Adds the frame to a list so it can be accessed
		frame = createdFrame;
		
		//: Adds the events to the frame
		this.addEvents();
	}

	public UI(String frameNameInput, String title , int x , int y , int width , int height ,  Boolean fullScreen ,  Boolean togglefullscreen , Boolean mainWindow) {
		
		framename = frameNameInput;
		
		//: Creates the frame object
		JFrame createdFrame = new JFrame(frameNameInput); 
		//: Sets the dimensions the the frame
		createdFrame.setSize(width, height);
		
		//: Defines that the window will close when the user presses the close button if it is the main window
		if(mainWindow) {
			createdFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}else {
			createdFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		}

		//: Sets the name of frame so it canbe acced later
		createdFrame.setName(frameNameInput);
		
		//: Sets the title of the window
		createdFrame.setTitle(title);
		
		//: Defines fullscreen mode if it is specified
		if(fullScreen)
			createdFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		//: Defines if you can resize the window
		createdFrame.setResizable(togglefullscreen);

		//: Sets coordinates
		createdFrame.setLocation(x,y);
				
		//: Sets the layout
		createdFrame.setLayout(null);
		
		//:Adds the frame to a list so it can be accessed
		frame = createdFrame;
		
		//: Adds the events to the frame
		this.addEvents();
	}
	
	private void addEvents() {
		
		frame.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
	    	
	    	
	    });
		
	}
	
	public void addButton(String ID, int x , int y , int width , int height , Runnable function) {

		JPanel panel = new JPanel();
		   
		panel.setLayout(null);
	   	panel.setSize(new Dimension(width, height));
	   	panel.setBounds(x, y, width, height);
	  

	   	JButton button = new JButton(ID);
	   	button.setName(ID);
	   	button.setBounds(0, 0, width, height);
		panel.add(button);
		
		frame.add(panel);
		
		activebuttons.add(button);
		
		button.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
			  function.run();
		  }
		});


	}
	
	public void addLabel(String ID , String LabelText,  int x , int y , int width , int height) {
				
		JPanel panel = new JPanel();
		   
		panel.setLayout(null);
	   	panel.setSize(new Dimension(width, height));
	   	panel.setBounds(x, y, width, height);
	  
	   	JLabel label = new JLabel(LabelText);
	   	label.setName(ID);
	   	label.setBounds(0, 0, width, height);
		panel.add(label);
		
		frame.add(panel);
		
		activelabels.add(label);
	
	}
	
	public void updateLabel(String labelname ,String text) {
		JLabel selectedLabel = null;

		for(int i = 0 ; i < activelabels.size(); i++) {
			if(activelabels.get(i).getName() == labelname )
				selectedLabel = activelabels.get(i);
		}
		
		selectedLabel.setText(text);
		
		
	}
	
	public void addTextField(String ID,  String deafualtInput, int x , int y , int width , int height) {

		JPanel panel = new JPanel();
		   
		panel.setLayout(null);
	   	panel.setSize(new Dimension(width, height));
	   	panel.setBounds(x, y, width, height);
	  

	   	JTextField input = new JTextField(deafualtInput);
	   	input.setName(ID);
	   	input.setBounds(0, 0, width, height);
		panel.add(input);
		
		frame.add(panel);
		
		activeTextFields.add(input);
		
	}
	
	public String GetTextFieldText(String fieldName) {
		
		String fieldValue = "";
		

		for(int i = 0 ; i < activeTextFields.size(); i++) {

			if(activeTextFields.get(i).getName() == fieldName )
				fieldValue = activeTextFields.get(i).getText();
			}	
		
		return fieldValue;
					
	}
	
	public void addPogressbar( String progressBarName  , int x , int y , int width , int height,  boolean showNumber , int min , int max) {
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
	   	panel.setSize(new Dimension(width, height));
	   	panel.setBounds(x, y, width, height);
	  

	   	JProgressBar progressBar = new JProgressBar(min, max);
	   	progressBar.setName(progressBarName);
	   	progressBar.setBounds(0, 0, width, height);
		progressBar.setStringPainted(showNumber);
		progressBar.setOrientation(0);
		panel.add(progressBar);
		
		frame.add(panel);
		
		activeProgressBar.add(progressBar);
		
	}
	
	public void updateProgressbar( String progressBarName , int procentage){
		
		JProgressBar progressBar = null;

		for(int i = 0 ; i < activeProgressBar.size(); i++) {

			if(activeProgressBar.get(i).getName() == progressBarName )
				progressBar = activeProgressBar.get(i);
		}
		
		
		progressBar.setValue(procentage);
	}

	public void addSlider(String sliderName, int x , int y , int width , int height, int min, int max,int startingPoint , boolean horizontal){
		
		JPanel panel = new JPanel();
		 
		panel.setLayout(null);
	   	panel.setSize(new Dimension(width, height));
	   	panel.setBounds(x, y, width, height);
	  
	    JSlider slider = null;
		
			if(horizontal == true) 
				 slider = new JSlider(JSlider.HORIZONTAL, min, max, startingPoint);
			else
				 slider = new JSlider(JSlider.VERTICAL,  min, max , startingPoint);

		slider.setName(sliderName);
		slider.setBounds(0, 0, width, height);
		slider.setMinorTickSpacing(2);
		slider.setMajorTickSpacing(10);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setLabelTable(slider.createStandardLabels(10));
		panel.add(slider);
		
		frame.add(panel);
		activeSliders.add(slider);
		    
	    
	    
	    		
	}
	
	public int getSliderValue(String sliderName){
		
		int Slidervalue = 0;
		

		for(int i = 0 ; i < activeSliders.size(); i++) {

			if(activeSliders.get(i).getName() == sliderName )
				Slidervalue = activeSliders.get(i).getValue();
			}	
		
		
		return Slidervalue;
		
	}
	
	public void addDropdown(String dropdownName, int x , int y , int width , int height , String[]Choices ) {
        
        JPanel panel = new JPanel();
 	   
		panel.setLayout(null);
	   	panel.setSize(new Dimension(width, height));
	   	panel.setBounds(x, y, width, height);
	  

        JComboBox<String> dropdown = new JComboBox<String>(Choices);
        dropdown.setName(dropdownName);
        dropdown.setBounds(0, 0, width, height);
		panel.add(dropdown);
		
		frame.add(panel);
		
		activeDropdown.add(dropdown);

		} 
	
	public String getDropdownItem(String dropdownName) {
		
		String dropdownItem = "";
	
		for(int i = 0 ; i < activeDropdown.size(); i++) {
			if(activeDropdown.get(i).getName() == dropdownName )
				dropdownItem = activeDropdown.get(i).getSelectedItem().toString();
			}	
		
		return dropdownItem;
	
	}
	
	public void addPasswordField(String fieldName, int x , int y , int width , int height) {
		
		JPanel panel = new JPanel();
	   
		panel.setLayout(null);
	   	panel.setSize(new Dimension(width, height));
	   	panel.setBounds(x, y, width, height);
	  

		JPasswordField passwordField = new JPasswordField(10);
		passwordField.setName(fieldName);
		passwordField.setBounds(0, 0, width, height);
		panel.add(passwordField);
		
		frame.add(panel);
		
	   
	}
	
	public void addSpinner(String dropdownName, int x , int y , int width , int height , boolean editable , String[]Choices ) {

		 	JPanel panel = new JPanel();
	 	   
			panel.setLayout(null);
		   	panel.setSize(new Dimension(width, height));
		   	panel.setBounds(x, y, width, height);
		  
		   	JSpinner spinner = new JSpinner();
		  
		   	
		   	SpinnerModel model = new SpinnerListModel(Arrays.asList(Choices));
		   	
		    spinner.setModel(model);
		    
		    if(editable)
		    		spinner.setEditor(new JSpinner.DefaultEditor(spinner));
		    
	        spinner.setBounds(0, 0, width, height);
			panel.add(spinner);
			
			frame.add(panel);
			
			activeSpinners.add(spinner);
	}
	
	public void addGridTable(String ID, int x , int y , int width , int height , Object[][] rowData, Object[] columnNames) {
		
		JPanel panel = new JPanel();
		   
		panel.setLayout(null);
	   	panel.setSize(new Dimension(width, height));
	   	panel.setBounds(x, y, width, height);
	  
	   	
	   	JTable Table = new JTable(rowData, columnNames);
	   

	   	Table.setName(ID);
	   	
//	   	Table.setGridColor(java.awt.Color.BLACK);
//	   	Table.setBorder(border.blackline);
	   	
	   	Table.setBackground(new java.awt.Color(230, 228, 228));
	   	Table.setShowGrid(false);
	   	Table.setOpaque(false);
	   	Table.setBounds(0, 0, width, height);
		panel.add(Table);
		
		frame.add(panel);
		activeGridTable.add(Table);
		
		
	}
	
	public void updateobject(String elementName, String fieldName , int xIncrament ,  int yIncrament) {
		
		if(elementName == "textfield") {
			JTextField selectedField = null;

			for(int i = 0 ; i < activeTextFields.size(); i++) {

				if(activeTextFields.get(i).getName() == fieldName )
					selectedField = activeTextFields.get(i);
				}	
			selectedField.setBounds(selectedField.getX() + xIncrament, selectedField.getY() + yIncrament, selectedField.getWidth(), selectedField.getHeight());
			selectedField.updateUI();
			
			System.out.print("X : " + selectedField.getX() + "\n");
			System.out.print("Y : " + selectedField.getY() + "\n\n");
			
		}else if(elementName == "button") {
			JButton selectedButton = null;

			for(int i = 0 ; i < activebuttons.size(); i++) {
				if(activebuttons.get(i).getText() == fieldName )
					selectedButton = activebuttons.get(i);
				}	
			selectedButton.setBounds(selectedButton.getX()  + xIncrament, selectedButton.getY() + yIncrament , selectedButton.getWidth() , selectedButton.getHeight() );
			selectedButton.updateUI();
			
			System.out.print("X : " + selectedButton.getX() + "\n");
			System.out.print("Y : " + selectedButton.getY() + "\n\n");

		}else if(elementName == "label") {
			JLabel selectedLabel = null;

			for(int i = 0 ; i < activelabels.size(); i++) {
				if(activelabels.get(i).getName() == fieldName )
		
					selectedLabel = activelabels.get(i);
				}	
			selectedLabel.setBounds(selectedLabel.getX()  + xIncrament, selectedLabel.getY() + yIncrament , selectedLabel.getWidth() , selectedLabel.getHeight() );
			selectedLabel.updateUI();
			
			System.out.print("X : " + selectedLabel.getX() + "\n");
			System.out.print("Y : " + selectedLabel.getY() + "\n\n");

		}else if(elementName == "progressBar") {
			JProgressBar progressBar = null;

			for(int i = 0 ; i < activeProgressBar.size(); i++) {

				if(activeProgressBar.get(i).getName() == fieldName )
					progressBar = activeProgressBar.get(i);
			}
			
			progressBar.setBounds(progressBar.getX()  , progressBar.getY()  , progressBar.getWidth() + xIncrament, progressBar.getHeight() + yIncrament);
			progressBar.updateUI();
			
			System.out.print("X : " + progressBar.getX() + "\n");
			System.out.print("Y : " + progressBar.getY() + "\n\n");

		}else if(elementName == "frame") {
			
			frame.setSize(frame.getWidth() + xIncrament, frame.getHeight() + yIncrament);
			
			System.out.print("X : " + frame.getWidth() + "\n");
			System.out.print("Y : " + frame.getHeight() + "\n\n");

		}
		
		
		
	
		
	}
	
	public void show() {
		
		frame.setVisible(true);
	}
	
	public void hide() {
		
		frame.setVisible(false);
	}
	
	public void close() {
		
		frame.dispose();
	}
	
	public GetterInterface getter = new GetterInterface() {

		@Override
		public JTextField getTextField(String ID) {
			
			for(int i = 0 ; i < activeTextFields.size(); i++) {
				if(activeTextFields.get(i).getName().equals(ID))
					return  activeTextFields.get(i);
			}
			
			return null;
		}

		@Override
		public JButton getButton(String ID) {
			
			for(int i = 0 ; i < activebuttons.size(); i++) {
				if(activebuttons.get(i).getName().equals(ID))
					return  activebuttons.get(i);
			}
			
			return null;
		}

		@Override
		public JLabel getLabel(String ID) {
			
			for(int i = 0 ; i < activelabels.size(); i++) {
				if(activelabels.get(i).getName().equals(ID))
					return  activelabels.get(i);
			}
			
			return null;
		}

		@Override
		public JProgressBar getProgressbar(String ID) {
			
			for(int i = 0 ; i < activeProgressBar.size(); i++) {
				if(activeProgressBar.get(i).getName().equals(ID))
					return  activeProgressBar.get(i);
			}
			
			return null;
		}

		@Override
		public JSlider getSlider(String ID) {
			
			for(int i = 0 ; i < activeSliders.size(); i++) {
				if(activeSliders.get(i).getName().equals(ID))
					return  activeSliders.get(i);
			}
			
			return null;
		}

		@Override
		public JComboBox getCombobox(String ID) {
			
			for(int i = 0 ; i < activeDropdown.size(); i++) {
				if(activeDropdown.get(i).getName().equals(ID))
					return  activeDropdown.get(i);
			}
			
			return null;
		}

		@Override
		public JPasswordField getPasswordfield(String ID) {
			
			for(int i = 0 ; i < activePasswordFields.size(); i++) {
				if(activePasswordFields.get(i).getName().equals(ID))
					return  activePasswordFields.get(i);
			}
			
			return null;
		}

		@Override
		public JSpinner getSpinner(String ID) {
			
			for(int i = 0 ; i < activeSpinners.size(); i++) {
				if(activeSpinners.get(i).getName().equals(ID))
					return  activeSpinners.get(i);
			}
			
			return null;
		}

		@Override
		public JTable getGridTable(String ID) {
	
			for(int i = 0 ; i < activeGridTable.size(); i++) {
				if(activeGridTable.get(i).getName().equals(ID))
					return  activeGridTable.get(i);
			}
			
			return null;
		}

	
	};
	
	public labelInterface label = new labelInterface() {
		
		String fontFamliy = "Serif";
		int fontType =  Font.PLAIN;
		int fontSize = 12;
		
		public void setborder(String ID , Border borderkind) {		
			JLabel selectedLabel = getter.getLabel(ID);
			
			if(getter.getLabel(ID) != null)
				selectedLabel.setBorder(borderkind);
			
		}
		
		public void setFontFamliy(String ID , String afontFamliy) {
				JLabel selectedLabel = getter.getLabel(ID);
					
				if(selectedLabel != null)
					fontFamliy = afontFamliy;
					updateFont(ID);
		}
		
		public void setFontType(String ID , int afontType) {
				JLabel selectedLabel = getter.getLabel(ID);
					
				if(selectedLabel != null)
					fontType = afontType;
					updateFont(ID);
			
		}
		
		public void setFontSize(String ID ,int size) {
				JLabel selectedLabel = getter.getLabel(ID);
			
				if(selectedLabel != null)
					fontSize = size;
					updateFont(ID);
				
		}
		
		public void updateFont(String ID) {
			
			getter.getLabel(ID).setFont(new Font( fontFamliy, fontType, fontSize));
			
		}
	
	};	
	
	public progressbarInterface progressbar = new progressbarInterface() {
			
		public void setOrientation(String elementName, int chosenOrientation) {
			
			JProgressBar progressBar = null;

			for(int i = 0 ; i < activeProgressBar.size(); i++) {

				if(activeProgressBar.get(i).getName() == elementName )
					progressBar = activeProgressBar.get(i);
			}
			
			progressBar.setOrientation(chosenOrientation);
		}
		
	};
	
	public eventInterface event = new eventInterface() {

	
		@Override
		public void addWindowOpened(Runnable function) {
			
			frame.addWindowListener(new WindowListener() {

				@Override
				public void windowOpened(WindowEvent e) {
					function.run();
					
				}

				@Override
				public void windowClosing(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowClosed(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowIconified(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowDeiconified(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowActivated(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowDeactivated(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}
				
			});	
		}

		@Override
		public void addWindowClosing(Runnable function) {
		
			frame.addWindowListener(new WindowListener() {

				@Override
				public void windowOpened(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowClosing(WindowEvent e) {
					function.run();
					
				}

				@Override
				public void windowClosed(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowIconified(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowDeiconified(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowActivated(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowDeactivated(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}
				
			});
			
		}

		@Override
		public void addWindowClosed(Runnable function) {

			frame.addWindowListener(new WindowListener() {

				@Override
				public void windowOpened(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowClosing(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowClosed(WindowEvent e) {
					function.run();
					
				}

				@Override
				public void windowIconified(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowDeiconified(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowActivated(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowDeactivated(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}
				
			});
			
		}

		@Override
		public void addWindowIconified(Runnable function) {
			
			frame.addWindowListener(new WindowListener() {

				@Override
				public void windowOpened(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowClosing(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowClosed(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowIconified(WindowEvent e) {
					function.run();
					
				}

				@Override
				public void windowDeiconified(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowActivated(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowDeactivated(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}
				
			});
			
		}

		@Override
		public void addWindowDeiconified(Runnable function) {
			
			frame.addWindowListener(new WindowListener() {

				@Override
				public void windowOpened(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowClosing(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowClosed(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowIconified(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowDeiconified(WindowEvent e) {
					function.run();
					
				}

				@Override
				public void windowActivated(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowDeactivated(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}
				
			});
			
		}

		@Override
		public void addWindowActivated(Runnable function) {
			
			frame.addWindowListener(new WindowListener() {

				@Override
				public void windowOpened(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowClosing(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowClosed(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowIconified(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowDeiconified(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowActivated(WindowEvent e) {
					function.run();
					
				}

				@Override
				public void windowDeactivated(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}
				
			});
			
		}

		@Override
		public void addWindowDeactivated(Runnable function) {
			
			frame.addWindowListener(new WindowListener() {

				@Override
				public void windowOpened(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowClosing(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowClosed(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowIconified(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowDeiconified(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowActivated(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowDeactivated(WindowEvent e) {
					function.run();
					
				}
				
			});
			
		}

	
	};
	
	public gridInterfacee gridTable = new gridInterfacee() {
		

	};
	
}



