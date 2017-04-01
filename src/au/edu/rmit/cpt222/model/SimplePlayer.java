
package au.edu.rmit.cpt222.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Set;
import java.util.logging.Level;

import au.edu.rmit.cpt222.model.exceptions.InsufficientFundsException;
import au.edu.rmit.cpt222.model.interfaces.DicePair;
import au.edu.rmit.cpt222.model.interfaces.GameEngineCallback;
import au.edu.rmit.cpt222.model.interfaces.GameEngine.GameStatus;
import au.edu.rmit.cpt222.model.interfaces.Player;

public class SimplePlayer implements Player {
	//Variables
	private String playerID;
	private String playerName;
	private int points;
	private int bet;
	private DicePair playerDice;
	private GameStatus status;
			
	//Constructor
	public SimplePlayer(String playerID, String playerName, int points) {
		this.playerID = playerID;
		this.playerName = playerName;
		this.points = points;
	}
	
	//Methods
	public String getPlayerId() {
		return playerID;
	}
	
	public String getPlayerName() {
		return playerName;
	}
	public int getPoints() {
		return points;
	}
	
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	
	public void setPoints(int points) {
		this.points = points;
	}
	
	public int getBet() {
		return bet;
	}
	
	public void placeBet(int bet) throws InsufficientFundsException {
		//Throw exception if bet larger than points
		if (bet > points) {
			throw new InsufficientFundsException();
		}
		
		//Place bet
		this.bet = bet;
		
		//TODO: anything else?
	}
	
	//TODO: Ignore for Assignment 1 (as per interface details)
	public void resetBet() throws InsufficientFundsException {
		;
	}

	public void setGameResult(GameStatus status) {
		this.status = status;
	}
	
	public GameStatus getGameResult() {
		return status;
	}
	
	public DicePair getRollResult() {
		return playerDice;
	}
	
	public void setRollResult(DicePair rollResult) {
		playerDice = rollResult;
	}
	
	public String toString() {
		String playerDetails = (getPlayerId() + " : " + getPlayerName() 
			+ " : " + getPoints());
		return playerDetails;
	}
	

}
