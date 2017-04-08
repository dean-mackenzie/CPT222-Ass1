package au.edu.rmit.cpt222.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import au.edu.rmit.cpt222.model.exceptions.InsufficientFundsException;
import au.edu.rmit.cpt222.model.interfaces.DicePair;
import au.edu.rmit.cpt222.model.interfaces.GameEngine;
import au.edu.rmit.cpt222.model.interfaces.GameEngineCallback;
import au.edu.rmit.cpt222.model.interfaces.Player;


public class GameEngineImpl implements GameEngine {
	//Variables
	public final static int INITIAL_DELAY = 1;
	public final static int FINAL_DELAY = 1000;
	public final static int DELAY_INCREMENT = 200;
	
	//ConcurrentHashMap is a better choice for multi-player/threaded
	private Map<String, Player> players = new HashMap<String, Player>(); 
	private DicePair playerDice;
	private DicePair houseDice;
	private Set<GameEngineCallback> callbacks = Collections.
			newSetFromMap(new HashMap<GameEngineCallback, Boolean>());
	
	//Constructor
	public GameEngineImpl() {
	}
	
	//Methods
	public void addGameEngineCallback(GameEngineCallback gameEngineCallback) {
		callbacks.add(gameEngineCallback);
	}
	
	public void addPlayer(Player player) {
		players.put(player.getPlayerId(), player);
	}
	
	public void calculateResult() {
		//Looping for multiple players (not needed for Ass 1)
		for (GameEngineCallback callbacks : callbacks) {

			// Roll for house
			rollHouse(INITIAL_DELAY, FINAL_DELAY, DELAY_INCREMENT);
			
			for (Player player : players.values()) {
				// TODO: if player has no dice value, haven't rolled, so bypass?
				// Compare rolls, set result and add/subtract points
				if (houseDice.getTotalScore() > player.getRollResult().getTotalScore()) {
					GameStatus status = GameEngine.GameStatus.LOST;					
					player.setGameResult(status);
					
					// Subtract bet from player points
					int points = player.getPoints() - player.getBet();
					this.setPlayerPoints(player, points);
				}
				else if (houseDice.getTotalScore() == player.getRollResult().getTotalScore()) {
					GameStatus status = GameEngine.GameStatus.DREW;					
					player.setGameResult(status);
					
					// No change to points on a draw
				}
				else {
					GameStatus status = GameEngine.GameStatus.WON;					
					player.setGameResult(status);
					
					// Add bet to player points
					int points = player.getPoints() + player.getBet();
					this.setPlayerPoints(player, points);
				}
				
				// Display result
				callbacks.gameResult(player, player.getGameResult(), this);
			}
		}
	}

	public Collection<Player> getAllPlayers() {
		return Collections.unmodifiableCollection(new ArrayList<Player>(
				players.values()));
	}
	
	public Player getPlayer(String id) {
		return players.get(id);
	}
	
	public void placeBet(Player player, int betPoints) throws InsufficientFundsException {
		Player playerToBet = getPlayer(player.getPlayerId());
		
		//Check if enough points to bet, then place bet
		
		if (betPoints > playerToBet.getPoints()) {
			throw new InsufficientFundsException();
		}
		else if (betPoints < 1) {
			throw new IllegalArgumentException();
		}
		else {
			playerToBet.placeBet(betPoints);
		}
	}

	public void removeGameEngineCallback(GameEngineCallback gameEngineCallback) {
		callbacks.remove(gameEngineCallback);
	}

	public boolean removePlayer(Player player) {
		try {
			players.remove(player.getPlayerId());
			return true;
		}
		catch (NullPointerException e) {
			return false;
		}
	}
	
	public void rollHouse(int initialDelay, int finalDelay, int delayIncrement) {
		// As per rollPlayer (minus player)
		for (GameEngineCallback callback : this.callbacks) {
			for(int i = 0; i < FINAL_DELAY; i = i + DELAY_INCREMENT) {
				// Handles GUI animation
				houseDice = new DicePairImpl();
				callback.houseRoll(houseDice, this);
				
				try {
					Thread.sleep(DELAY_INCREMENT);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			callback.houseRollOutcome(houseDice, this);
		}
	}
	
	public void rollPlayer(	Player player, int initialDelay, 
			int finalDelay, int delayIncrement) {
		
		// TODO: FORUM: the model (i.e. this) should be rolling the dice, and for each 'bounce' call callback.playerRoll(), 
		// then callback.playerRollOutcome() for the final result of the roll.
		
		//The callback's job is to take output from the model and convert it into language that the view (via its controller) understands, 
		//thus separating model and view logic. E.g. the view shouldn't need to know about DicePair.
		
		// This is the intermediate rolling
		for (GameEngineCallback callback : this.callbacks) {
			for(int i = 0; i < FINAL_DELAY; i = i + DELAY_INCREMENT) {
				// Handles GUI animation
				playerDice = new DicePairImpl();
				callback.playerRoll(player, playerDice, this);
				player.setRollResult(playerDice);
				try {
					Thread.sleep(DELAY_INCREMENT);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			// Get final roll outcome
			callback.playerRollOutcome(player, player.getRollResult(), this);
		}
	}
	
	public void setPlayerPoints(Player player, int totalPoints) {
		player.setPoints(totalPoints);
	}

}
