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
import au.edu.rmit.cpt222.model.interfaces.GameEngine.GameStatus;
import au.edu.rmit.cpt222.model.interfaces.Dice;


public class GameEngineImpl implements GameEngine {
	//Variables
	public final static int INITIAL_DELAY = 1;
	public final static int FINAL_DELAY = 100;
	public final static int DELAY_INCREMENT = 20;
	
	//ConcurrentHashMap is a better choice for multi-player/threaded
	private Map<String, Player> players = new HashMap<String, Player>(); 
	private DicePair pair;
	
	private Set<GameEngineCallback> callbacks = Collections.
			newSetFromMap(new HashMap<GameEngineCallback, Boolean>());
	
	//Constructor
	public GameEngineImpl() {
	}
	
	//Methods
	// Methods to be implemented (here or in sub-class): see interface for purpose of method
	//Interesting: returns only the player values (which I guess is what we want?)
	public Collection<Player> getAllPlayers() {
		return Collections.unmodifiableCollection(new ArrayList<Player>(
				players.values()));
	}
	
	public Player getPlayer(String id) {
		return players.get(id);
	}
	
	public void addPlayer(Player player) {
		players.put(player.getPlayerId(), player);
	}
	
	public boolean removePlayer(Player player) {
		try {
			players.remove(player);
			return true;
		}
		catch (NullPointerException e) {
			return false;
		}
	}
	
	public void placeBet(Player player, int betPoints) throws InsufficientFundsException {
		try {
			Player playerToBet = getPlayer(player.getPlayerId());
			
			//Check if enough points to bet, then place bet
			if (betPoints > playerToBet.getPoints()) {
				throw new InsufficientFundsException();
			}
			else {
				playerToBet.placeBet(betPoints);
			}
		} catch (InsufficientFundsException e) {
			//TODO: Not sure just throw this up stack for Client to catch
			;
		}
	}
	
	/**
	 * This method rolls for the house and then goes through all players and
	 * applies win/loss/draw outcome to update betting points.
	 * {@link GameEngineCallback#gameResult(Player, GameStatus, GameEngine)}
	 * should also be called with final result for each player.
	 */
	public void calculateResult() {
		//Looping for multiple players (not needed for Ass 1)
		for (GameEngineCallback callbacks : callbacks) {

			// Roll for house
			rollHouse(INITIAL_DELAY, FINAL_DELAY, DELAY_INCREMENT);
			
			for (Player player : players.values()) {
				// Compare house vs player
				if (((GameEngineCallbackImpl) callbacks).getHouseRoll() > player.getRollResult().getTotalScore()) {
					GameStatus status = GameEngine.GameStatus.LOST;					
					player.setGameResult(status);
				}
				else if (((GameEngineCallbackImpl) callbacks).getHouseRoll() == player.getRollResult().getTotalScore()) {
					GameStatus status = GameEngine.GameStatus.DREW;					
					player.setGameResult(status);
				}
				else {
					GameStatus status = GameEngine.GameStatus.WON;					
					player.setGameResult(status);
				}
				
				// Display result
				callbacks.gameResult(player, player.getGameResult(), this);
			}
		}
	}

	//This method is actually intended to be called from the "client" in order to update the player's balance 
	//(e.g. upon depositing more funds etc.). This functionality will be covered in Ass 2.
	public void setPlayerPoints(Player player, int totalPoints) {
		;
	}
	
	public void rollPlayer(	Player player, int initialDelay, 
			int finalDelay, int delayIncrement) {
		
		// FROM FORUM FOR DICE ROLLING QUESTION - 
		// The model (i.e. this) should be rolling the dice, and for each 'bounce' call callback.playerRoll(), 
		// then callback.playerRollOutcome() for the final result of the roll.
		
		//The callback's job is to take output from the model and convert it into language that the view (via its controller) understands, 
		//thus separating model and view logic. E.g. the view shouldn't need to know about DicePair.
		
		 /** 1. start at initialDelay then increment by delayIncrement each time a new
		 * number is shown on the dice faces; 
		 * 2. call GameEngineCallback.playerRoll(...) or houseRoll(...) each time a pair of
		 * new dice faces are shown until delay is equal or greater than finalDelay;
		 * 3. call GameEngineCallback.playerRollOutcome(...) or
		 * houseRollOutcome(...) with final result for player or house; 
		 * 4. update the player with final result so it can be retrieved later
		 **/
		
		// This is the intermediate rolling
		for (GameEngineCallback callbacks : callbacks) {
			System.out.println("Rolling dice for player...");
			for(int i = 0; i < FINAL_DELAY; i = i + DELAY_INCREMENT) {
				// Handles GUI animation
				pair = new DicePairImpl();
				callbacks.playerRoll(player, pair, this);
				try {
					Thread.sleep(DELAY_INCREMENT);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			// Get final roll outcome
			callbacks.playerRollOutcome(player, player.getRollResult(), this);
		}
	}

	public void addGameEngineCallback(GameEngineCallback gameEngineCallback) {
		callbacks.add(gameEngineCallback);
	}

	public void removeGameEngineCallback(GameEngineCallback gameEngineCallback) {
		callbacks.remove(gameEngineCallback);
	}

	public void rollHouse(int initialDelay, int finalDelay, int delayIncrement) {
		//as per rollPlayer minus player
		for (GameEngineCallback callbacks : callbacks) {
			for(int i = initialDelay; i < finalDelay; i = i+delayIncrement) {
				// Handles GUI animation
				pair = new DicePairImpl();
				callbacks.houseRoll(pair, this);
			}
			callbacks.houseRollOutcome(pair, this);
		}
	}
	//Sets the dicepair stored in this class for further use
	public void setPair(DicePair pair) {
		this.pair = pair;
	}
}
