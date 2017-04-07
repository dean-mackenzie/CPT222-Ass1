package au.edu.rmit.cpt222.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import au.edu.rmit.cpt222.model.GUICallbackImpl;
import au.edu.rmit.cpt222.model.SimplePlayer;
import au.edu.rmit.cpt222.model.exceptions.InsufficientFundsException;
import au.edu.rmit.cpt222.model.interfaces.GameEngine;
import au.edu.rmit.cpt222.model.interfaces.GameEngine.GameStatus;
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
	
	public boolean addPlayer(String playerId, String playerName, int points) {
		// TODO: if error, return false
		SimplePlayer player = new SimplePlayer(playerId, playerName, points);
		this.engine.addPlayer(player);
		this.mw.updatePoints(player.getBet(), player.getPoints());
		
		return true;
	}
	
	public void betAndRoll(int bet) {
		//Place bet
		for (Player player : this.engine.getAllPlayers()) {
			try {
				player.placeBet(bet);
				
				new Thread() {
					public void run() {
						//TODO: use proper parameters
						MainController.this.engine.rollPlayer(player, 1, 500, 20);
						MainController.this.engine.calculateResult();
					}
				}.start();
			} catch (InsufficientFundsException e) {
				this.mw.displayWarning("Insufficient funds to place a bet of that size.");
			}
		}
	}
	
	public boolean checkPlayerExists() {
		if (this.engine.getAllPlayers().size() < 1) {
			this.mw.displayWarning("Player must be added before playing.");
			return false;
		}
		return true;
	}
	
	
	// Methods to update view
	public void updateRollArea(String rollType, int dice1, int dice2) {
		this.mw.updateRollPanel(rollType, dice1, dice2);
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
	
	public void updateStatusArea(GameStatus result, int bet, int points) {
		String resultText = String.valueOf(result);
		this.mw.getBottomBars().updateGameStatus(resultText, bet, points);
	}
}
