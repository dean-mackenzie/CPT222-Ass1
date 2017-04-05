package au.edu.rmit.cpt222.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.DefaultComboBoxModel;

import au.edu.rmit.cpt222.model.interfaces.GameEngine;
import au.edu.rmit.cpt222.model.interfaces.Player;
import au.edu.rmit.cpt222.view.MainWindow;

public class PlayerListListener implements ActionListener {
   MainWindow window;
			   
   public PlayerListListener(MainWindow window)
   {
      super();
      this.window = window;
   }

   //This is for when player is selected
   @Override
   public void actionPerformed(ActionEvent arg0)
   {
	   //Set player in model
   }
}
