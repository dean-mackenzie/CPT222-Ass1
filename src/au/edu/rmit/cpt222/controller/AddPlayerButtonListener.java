package au.edu.rmit.cpt222.controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JOptionPane;

import au.edu.rmit.cpt222.model.SimplePlayer;
import au.edu.rmit.cpt222.model.interfaces.GameEngine;
import au.edu.rmit.cpt222.model.interfaces.Player;
import au.edu.rmit.cpt222.view.MainWindow;
import au.edu.rmit.cpt222.view.PlayerListCombo;

public class AddPlayerButtonListener implements ActionListener {
   private MainWindow mw;
   PlayerListCombo comp = new PlayerListCombo(mw);
   ArrayList<Integer> idsUsed = new ArrayList<Integer>();
		   
   public AddPlayerButtonListener(MainWindow mw, PlayerListCombo comp)
   {
      super();
      this.mw = mw;
      this.comp = comp;
   }

   @Override
   public void actionPerformed(ActionEvent arg0)
   {
	   String name = enterName();
	   // TODO: if Cancel or Close clicked, abort
	   int points = enterPoints();

	   // Create a unique player ID and store (so it's not re-used)
	   int id = ThreadLocalRandom.current().nextInt(1, 100);
	   for (int i = 0; i < idsUsed.size(); i++) {
		   if(id == idsUsed.get(i)) {
			   // Assign a new ID and restart loop
			   id = ThreadLocalRandom.current().nextInt(1, 100);
			   i = 0;
		   }
	   }
	   // If unique, add to list
	   idsUsed.add(id);
	   String playerId = Integer.toString(id);
	   
	   mw.getModel().addPlayer(new SimplePlayer(playerId, name, points));
	   
	   //Update player combo
	   //TODO: move to main controller
	   Collection<Player> players = mw.getModel().getAllPlayers();
	   
	   comp.updateCombo(players);
   }
   
   public String enterName() {
	   String playerName = "";
	   boolean valid = false;
	   while (!valid) {
		   try {
			   playerName = (String)JOptionPane.showInputDialog(
	                   mw,
	                   "Enter Player Name:",
	                   "Add Player",
	                   JOptionPane.PLAIN_MESSAGE);
			   
			   if (playerName.length() > 20) {
				   throw new IllegalArgumentException();
			   }
			   
			   valid = true;
		   } catch (IllegalArgumentException e) {
			   JOptionPane.showMessageDialog(mw,
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
	                   mw,
	                   "Enter points:",
	                   "Add Player",
	                   JOptionPane.PLAIN_MESSAGE);
			   points = Integer.parseInt(initialPoints);
			   
			   if (points < 1) {
				   throw new IllegalArgumentException();
			   }
			   
			   valid = true;
		   } catch (NumberFormatException e) {
			   JOptionPane.showMessageDialog(mw,
					    "Please enter an integer.");
			   continue;
		   } catch (IllegalArgumentException e) {
			   JOptionPane.showMessageDialog(mw,
					    "Please enter a value greater than zero.");
			   continue;
		   }   
	   }
	   return points;
  }
   
}
