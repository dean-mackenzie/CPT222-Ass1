package au.edu.rmit.cpt222.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import au.edu.rmit.cpt222.model.GUICallbackImpl;
import au.edu.rmit.cpt222.model.SimplePlayer;
import au.edu.rmit.cpt222.model.interfaces.GameEngine;
import au.edu.rmit.cpt222.model.interfaces.Player;
import au.edu.rmit.cpt222.view.MainWindow;

// perform all model communication
public class MainController {
	
	private MainWindow mw;
	private GameEngine engine;
	
	public MainController(MainWindow mw) {
		this.mw = mw;
		this.engine = mw.getModel();
		this.engine.addGameEngineCallback(new GUICallbackImpl(this));
	}
	
	public void roll() {
		// Check at least 1 player exists
		if (this.engine.getAllPlayers().size() < 1) {
			// TODO: Popup to say must add player
			System.out.println("Player must be added before playing.");
			return;
		}
		
		// TODO: check for bet
		
		//TODO: remove debug print
		System.out.println("Roll was clicked");
		new Thread() {
			public void run() {

				//TODO: use proper parameters
				//TODO: bob for testing only
				SimplePlayer bob = new SimplePlayer("1", "bob", 100);				
				//MainController.this.engine.addPlayer(bob);
				MainController.this.engine.rollPlayer(bob, 0, 0, 0);
			}
		}.start();
	}
	
	//TODO: do above for placebet and other methods too
	
	public boolean addPlayer(String playerId, String playerName, int points) {
		SimplePlayer player = new SimplePlayer(playerId, playerName, points);
		this.engine.addPlayer(player);
		
		return true;
	}
	
	
	
	// Methods to update view
	public void updateRollArea(int dice1, int dice2) {
		System.out.println("In controller - updating View");
		//then call subcomponent to update view
		this.mw.updateRollPanel(dice1, dice2);
	}
	
	public List<String> getPlayerNames() {
		// Handle model logic
		List<String> playerNames = new ArrayList<String>();
		Collection<Player> players = this.engine.getAllPlayers();
		
		for (Player player : players) {
			playerNames.add(player.getPlayerName());
		}
		
		return playerNames;
	}
}
