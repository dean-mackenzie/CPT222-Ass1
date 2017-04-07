package au.edu.rmit.cpt222.model;

import java.util.logging.Level;
import java.util.logging.Logger;

import au.edu.rmit.cpt222.model.interfaces.DicePair;
import au.edu.rmit.cpt222.model.interfaces.GameEngine;
import au.edu.rmit.cpt222.model.interfaces.Player;
import au.edu.rmit.cpt222.model.interfaces.GameEngine.GameStatus;
import au.edu.rmit.cpt222.model.interfaces.GameEngineCallback;

public class GameEngineCallbackImpl implements GameEngineCallback {
	private DicePair result;
	
	//TODO: public class GUICallbackImpl extends GameEngineCallbackImpl
	//This class will be a controller for the GUI - basically a specialised version of this for the GUI
	
	protected Logger logger = Logger.getLogger("Test");

	public void gameResult(Player player, GameStatus result, GameEngine engine) {
		this.logger.log(Level.INFO, "Game result: " + player.getPlayerName()
				+ " has " + result);
	}
	
	// Returns house roll as a score
	public int getHouseRoll() {
		return result.getTotalScore();
	}

	public void houseRoll(DicePair dicePair, GameEngine engine) {
		// Set the result dice to parameter
		result = dicePair;
				
		//TODO: Add GUIcallback method for this to update UI rollArea for house
		
		//TODO: this is temp logging to show intermediate rolls
		System.out.println("House roll is: " + result.getTotalScore());
	}
	
	public void houseRollOutcome(DicePair result, GameEngine engine) {
		// TODO: Update view to show mapped images in GUI(?)
		
		// Log result
		this.logger.log(Level.INFO, "House has rolled: " + result.getTotalScore());
	}
	
	public void playerRoll(Player player, DicePair dicePair, GameEngine engine) {
		// Log intermediate roll
		System.out.println("Roll for " + player.getPlayerName() + " is: " + dicePair.getTotalScore());
		
		// Set current roll result (only final one will be used)
		player.setRollResult(dicePair);
	}
	
	//This is for the "final" roll (i.e. the player's roll)
	public void playerRollOutcome(Player player, DicePair result, GameEngine engine) {
		// Log final roll outcome
		this.logger.log(Level.INFO, "Player: " + player.getPlayerName()
		+ " has rolled " + result.getTotalScore());

		//TODO: Return result to view
	}
}
