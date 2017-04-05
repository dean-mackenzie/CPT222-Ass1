package au.edu.rmit.cpt222.driver;

import javax.swing.SwingUtilities;

import au.edu.rmit.cpt222.model.GameEngineImpl;
import au.edu.rmit.cpt222.model.interfaces.GameEngine;
import au.edu.rmit.cpt222.view.MainWindow;

public class Ass1Driver {
	
	final static GameEngine engine = new GameEngineImpl();
	
	public static void main(String args[]) {
	     SwingUtilities.invokeLater(new Runnable() {
	          @Override
	           public void run() { 
	        	  // Initialise view
	        	  MainWindow mw = new MainWindow(engine);
	        	  mw.setVisible(true);
	           }
	     });
	}
}
