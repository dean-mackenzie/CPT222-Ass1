package au.edu.rmit.cpt222.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import au.edu.rmit.cpt222.view.MainWindow;
import au.edu.rmit.cpt222.view.PlayerListCombo;

public class AddPlayerButtonListener implements ActionListener {
   private MainWindow mw;
   PlayerListCombo comp = new PlayerListCombo(mw);
   ArrayList<Integer> idsUsed = new ArrayList<Integer>();
   
   // Created a separate class as this logic is more complex than
   // the other buttons (but still exists outside the model)
   
   // TODO: rename, it's not a listener any more
   public AddPlayerButtonListener(MainWindow mw, PlayerListCombo comp)
   {
      this.mw = mw;
      this.comp = comp;
   }

   @Override
   public void actionPerformed(ActionEvent arg0)
   {
//	   String name = enterName();
//	   // TODO: if Cancel or Close clicked, abort
//	   int points = enterPoints();
//
//	   // Create a unique player ID and store (so it's not re-used)
//	   int id = ThreadLocalRandom.current().nextInt(1, 100);
//	   for (int i = 0; i < idsUsed.size(); i++) {
//		   if(id == idsUsed.get(i)) {
//			   // Assign a new ID and restart loop
//			   id = ThreadLocalRandom.current().nextInt(1, 100);
//			   i = 0;
//		   }
//	   }
//	   // If unique, add to list
//	   idsUsed.add(id);
//	   String playerId = Integer.toString(id);
//	   
//	   mw.getModel().addPlayer(new SimplePlayer(playerId, name, points));
//	   
//	   //Update player combo
//	   //TODO: move to main controller
//	   Collection<Player> players = mw.getModel().getAllPlayers();
//	   
//	   comp.updateCombo(players);
   }
   

   

   
}
