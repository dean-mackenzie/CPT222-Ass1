package au.edu.rmit.cpt222.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import au.edu.rmit.cpt222.model.GUICallbackImpl;
import au.edu.rmit.cpt222.model.SimplePlayer;
import au.edu.rmit.cpt222.model.exceptions.InsufficientFundsException;
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
	
	public void betAndRoll(int bet) {
		// Check at least 1 player exists
		if (this.engine.getAllPlayers().size() < 1) {
			this.mw.displayWarning("Player must be added before playing.");
			return;
		}
		
		//Place bet
		for (Player player : this.engine.getAllPlayers()) {
			try {
				player.placeBet(bet);
				
				new Thread() {
					public void run() {
						
					// TODO:	SimplePlayer bob = new SimplePlayer("1", "bob", 100); (for testing only)				
						//TODO: use proper parameters
						MainController.this.engine.rollPlayer(player, 0, 0, 0);
						MainController.this.engine.calculateResult();
					}
				}.start();
			} catch (InsufficientFundsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public boolean addPlayer(String playerId, String playerName, int points) {
		SimplePlayer player = new SimplePlayer(playerId, playerName, points);
		this.engine.addPlayer(player);
		
		return true;
	}
	
	public void placeBet() {
		
	}
	
	
	
	// Methods to update view
	public void updateRollArea(int dice1, int dice2) {
		System.out.println("In controller - updating player dice");
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
