package au.edu.rmit.cpt222.view;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class RollArea extends JPanel {
	
	private MainWindow mw;
	
	private JPanel leftDice;
	private JPanel rightDice;
	
	private JLabel playerDice1;
	private JLabel playerDice2;
	private JLabel houseDice1;
	private JLabel houseDice2;
	
	public RollArea(MainWindow mw) {
		this.mw = mw;
		createRollArea();
	}
	
	public void createRollArea() {
		// Create components
		leftDice = new JPanel();
		rightDice = new JPanel();
		
		playerDice1 = new JLabel("Player Dice 1");
		playerDice2 = new JLabel("Player Dice 2");
		houseDice1 = new JLabel("House Dice 1");
		houseDice2 = new JLabel("House Dice 2");
		
		// Add components
		this.add(leftDice);
		this.add(rightDice);
		
		leftDice.add(playerDice1);
		leftDice.add(playerDice2);
		rightDice.add(houseDice1);
		rightDice.add(houseDice2);
	}
	
	public void updatePlayerDice1(int dice) {
		playerDice1.setText(Integer.toString(dice));
	}
	
	public void updatePlayerDice2(int dice) {
		playerDice2.setText(Integer.toString(dice));
	}


}
