package au.edu.rmit.cpt222.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import au.edu.rmit.cpt222.model.interfaces.GameEngine;
import au.edu.rmit.cpt222.view.MainWindow;

public class ResetButtonListener implements ActionListener {
	   MainWindow window;
	   GameEngine gameEngine;
			   
	   public ResetButtonListener(MainWindow window, GameEngine gameEngine)
	   {
		      super();
		      this.window = window;
		      this.gameEngine = gameEngine;
		   }

	   @Override
	   public void actionPerformed(ActionEvent arg0)
	   {
		   //remove players
		   
		   //anything else?
	   }
  
	   
}
