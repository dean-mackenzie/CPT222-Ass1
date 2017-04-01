package au.edu.rmit.cpt222.model;

import au.edu.rmit.cpt222.model.interfaces.GameEngine;
import au.edu.rmit.cpt222.model.interfaces.GameEngine.GameStatus;
import au.edu.rmit.cpt222.model.interfaces.Player;

public class GUICallbackImpl extends GameEngineCallbackImpl {
	
	public GUICallbackImpl() {
		super();
	}
	//However, my recommendation would be to use only GUICallbackImpl when working with the GUI portion of your system (you don't really need to create instances of both Callback types). 
	//his way you can utilise this GUICallbackImpl to perform both logging and view updates e.g. in GUICallbackImpl 
	
	@Override
	public void gameResult(Player player, GameStatus result, GameEngine engine) {
	    super.gameResult (player, result, engine); // "ask" default GameEngineCallbackImpl to log to the console
	   // controller.updateXYZ(...);  // "ask" Controller to update the View
	}

}
