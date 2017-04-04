package au.edu.rmit.cpt222.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import au.edu.rmit.cpt222.model.interfaces.GameEngine;
import au.edu.rmit.cpt222.view.MainWindow;

public class PlayerListListener implements ActionListener {
	   MainWindow window;
			   
	   public PlayerListListener(MainWindow window)
	   {
	      super();
	      this.window = window;
	   }
	   
	   //Update combo box

	   //This is for when player is selected
	   @Override
	   public void actionPerformed(ActionEvent arg0)
	   {
		   //Set player in model
	   }
  
	   
}
