package au.edu.rmit.cpt222.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JOptionPane;

import au.edu.rmit.cpt222.model.SimplePlayer;
import au.edu.rmit.cpt222.model.interfaces.GameEngine;
import au.edu.rmit.cpt222.model.interfaces.Player;
import au.edu.rmit.cpt222.view.MainWindow;

public class AddPlayerButtonListener implements ActionListener {
   MainWindow window;
		   
   public AddPlayerButtonListener(MainWindow window)
   {
      super();
      this.window = window;
   }

   @Override
   public void actionPerformed(ActionEvent arg0)
   {
	   String name = enterName();
	   // TODO: if Cancel or Close clicked, abort
	   int points = enterPoints();

	   // TODO: enter this or generate somehow
	   String playerId = "Test";
	   
	   window.getGameEngine().addPlayer(new SimplePlayer(playerId, name, points));
	   
	   //Update player combo
	   Collection<Player> players = new ArrayList<Player>();
	   players = window.getGameEngine().getAllPlayers();
	   window.updateCombo(players);
   }
   
   public String enterName() {
	   String playerName = "";
	   boolean valid = false;
	   while (!valid) {
		   try {
			   playerName = (String)JOptionPane.showInputDialog(
	                   window,
	                   "Enter Player Name:",
	                   "Add Player",
	                   JOptionPane.PLAIN_MESSAGE);
			   
			   if (playerName.length() > 20) {
				   throw new IllegalArgumentException();
			   }
			   
			   valid = true;
		   } catch (IllegalArgumentException e) {
			   JOptionPane.showMessageDialog(window,
					    "Please enter a name no longer than 20 characters.");
			   continue;
		   }
	   }
	   
	   return playerName;
   }
   
   public int enterPoints() {
	   int points = 0;
	   boolean valid = false;
	   while (!valid) {
		   try {
			   String initialPoints = (String)JOptionPane.showInputDialog(
	                   window,
	                   "Enter points:",
	                   "Add Player",
	                   JOptionPane.PLAIN_MESSAGE);
			   points = Integer.parseInt(initialPoints);
			   
			   if (points < 1) {
				   throw new IllegalArgumentException();
			   }
			   
			   valid = true;
		   } catch (NumberFormatException e) {
			   JOptionPane.showMessageDialog(window,
					    "Please enter an integer.");
			   continue;
		   } catch (IllegalArgumentException e) {
			   JOptionPane.showMessageDialog(window,
					    "Please enter a value greater than zero.");
			   continue;
		   }   
	   }
	   return points;
  }
   
}
