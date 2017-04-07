package au.edu.rmit.cpt222.model;

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
	
	@Override
	public void playerRoll(Player player, DicePair dicePair, GameEngine engine) {
		// Make the actual roll in callbacks
		super.playerRoll(player, dicePair, engine);
		
		// Update the controller/view
		this.controller.updateRollArea(dicePair.getDice1().getFace(), dicePair.getDice2().getFace());
	}
	
	@Override
	public void gameResult(Player player, GameStatus result, GameEngine engine) {
	    super.gameResult (player, result, engine); // "ask" default GameEngineCallbackImpl to log to the console
	   // controller.updateXYZ(...);  // "ask" Controller to update the View
	}

}
