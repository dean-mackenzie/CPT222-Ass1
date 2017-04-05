package au.edu.rmit.cpt222.view;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class RollArea extends JPanel {
	
	private MainWindow mw;
	
	private JPanel leftDice;
	private JPanel rightDice;
	
	public RollArea(MainWindow mw) {
		this.mw = mw;
		createRollArea();
	}
	
	public void createRollArea() {
		leftDice = new JPanel();
		rightDice = new JPanel();
		
		this.add(leftDice);
		this.add(rightDice);
		
		leftDice.add(new JLabel("Where player dice go"));
		rightDice.add(new JLabel("Where house dice go"));

		//all goes to main controller: remember way of differentiating
	
	}

}
