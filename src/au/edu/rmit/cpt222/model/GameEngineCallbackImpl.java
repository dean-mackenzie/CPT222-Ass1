package au.edu.rmit.cpt222.model;

import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

import au.edu.rmit.cpt222.model.interfaces.DicePair;
import au.edu.rmit.cpt222.model.interfaces.GameEngine;
import au.edu.rmit.cpt222.model.interfaces.Player;
import au.edu.rmit.cpt222.model.interfaces.GameEngine.GameStatus;
import au.edu.rmit.cpt222.model.interfaces.GameEngineCallback;

public class GameEngineCallbackImpl implements GameEngineCallback {
	
	private DicePair result = new DicePairImpl();
	private int houseRoll;
	
	//TODO: public class GUICallbackImpl extends GameEngineCallbackImpl
	//This class will be a controller for the GUI - basically a specialised version of this for the GUI
	
	protected Logger logger = Logger.getLogger("Test");

	/**
	 * Called for each Player to indicate the outcome of the current game. Use
	 * this to update your GUI display or log to console.
	 */
	public void gameResult(Player player, GameStatus result, GameEngine engine) {
		this.logger.log(Level.INFO, "Game result: " + player.getPlayerName()
				+ " has " + result);
	}

	//TODO: refer to dice and dicepair
	public void houseRoll(DicePair dicePair, GameEngine engine) {
		// Get the house total score (dice already initalised)
		houseRoll = dicePair.getTotalScore();
				
		//TODO: Update view to show mapped images in view
			//code here
	}
	
	public void houseRollOutcome(DicePair result, GameEngine engine) {
		//Log result
		this.logger.log(Level.INFO, "House rolled: " + result.getTotalScore());
		
		//TODO: anything else?
	}
	
	// Looping is done in GameEngineImpl - so this is a single "roll"
	public void playerRoll(Player player, DicePair dicePair, GameEngine engine) {
		//TODO: Update view to show mapped images in GUI
			//code here
		
		// Set current roll result (only final one will be used)
		player.setRollResult(dicePair);

	}
	
	//This is for the "final" roll (i.e. the player's roll)
	public void playerRollOutcome(Player player, DicePair result, GameEngine engine) {
		//TODO: this doesn't use result?
		
		//Result of final roll should have been set in player.setRollResult() (above)
		int res = player.getRollResult().getTotalScore();
		
		this.logger.log(Level.INFO, "Player: " + player.getPlayerName()
		+ " has rolled " + res);

		//TODO: Return result to view

	}
	
	public int getHouseRoll() {
		return houseRoll;
	}

}
