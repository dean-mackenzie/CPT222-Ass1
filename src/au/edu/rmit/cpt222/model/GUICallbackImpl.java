package au.edu.rmit.cpt222.model;

import java.util.logging.Level;

import au.edu.rmit.cpt222.controller.MainController;
import au.edu.rmit.cpt222.model.interfaces.DicePair;
import au.edu.rmit.cpt222.model.interfaces.GameEngine;
import au.edu.rmit.cpt222.model.interfaces.GameEngine.GameStatus;
import au.edu.rmit.cpt222.model.interfaces.Player;

public class GUICallbackImpl extends GameEngineCallbackImpl {
	MainController controller;
	
	public GUICallbackImpl(MainController controller) {
		this.controller = controller;
	}

	//However, my recommendation would be to use only GUICallbackImpl when working with the GUI portion of your system (you don't really need to create instances of both Callback types). 
	//his way you can utilise this GUICallbackImpl to perform both logging and view updates e.g. in GUICallbackImpl 
	
	public void houseRoll(DicePair dicePair, GameEngine engine) {
		super.houseRoll(dicePair, engine);
		
		// Update the controller/view
		String rollType = "house";
		this.controller.updateRollArea(rollType, dicePair.getDice1().getFace(), dicePair.getDice2().getFace());
	}
	
	@Override
	public void playerRoll(Player player, DicePair dicePair, GameEngine engine) {
		// Make the actual roll in callbacks
		super.playerRoll(player, dicePair, engine);
		
		// Update the controller/view
		String rollType = "player";
		this.controller.updateRollArea(rollType, dicePair.getDice1().getFace(), dicePair.getDice2().getFace());
	}
	
	public void playerRollOutcome(Player player, DicePair result, GameEngine engine) {
		// TODO: Update controller with final result (where in the view to display?)
	}
	
	@Override
	public void gameResult(Player player, GameStatus result, GameEngine engine) {
	    super.gameResult(player, result, engine);
	    
	    // Update the controller/view
	    int bet = player.getBet();
	    int points = player.getPoints();
	    this.controller.updateStatusArea(result, bet, points);

	}

}
