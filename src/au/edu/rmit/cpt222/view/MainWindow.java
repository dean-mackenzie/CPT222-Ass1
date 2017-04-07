package au.edu.rmit.cpt222.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import au.edu.rmit.cpt222.controller.MainController;
import au.edu.rmit.cpt222.model.interfaces.GameEngine;
import au.edu.rmit.cpt222.model.interfaces.Player;

public class MainWindow extends JFrame  {
	Container window = new JFrame();
	private GameEngine gameEngine;
	
	// Main window sections
	private TopMenu topMenu;
	private BottomBars bottomBars;
	private RollArea rollArea;
	
	private JOptionPane warning;

	private MainController controller;
	
	public MainWindow(GameEngine engine) {
		super("Roll-o-Rama");
		this.gameEngine = engine;
		this.controller = new MainController(this);
	
		createMainWindow();
	}
	
	public void createMainWindow() {
		setBounds(100, 100, 800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Set Layout Manager
		window.setLayout(new BorderLayout());
		window = getContentPane();
		
		// Add component views
		topMenu = new TopMenu(this);
		window.add(topMenu, BorderLayout.PAGE_START);

		rollArea = new RollArea(this);
		window.add(rollArea, BorderLayout.CENTER);

		bottomBars = new BottomBars(this);
		window.add(bottomBars, BorderLayout.PAGE_END);
	}
	
	// Methods to update view
	public void updateRollPanel(String rollType, int dice1, int dice2) {
		if (rollType.equals("player")) {
			rollArea.updatePlayerDice(dice1, dice2);
		}
		else {
			rollArea.updateHouseDice(dice1, dice2);
		}
	}
	
	public void updatePoints(int bet, int points) {
		bottomBars.updatePoints(bet, points);
	}
	
	public void displayWarning(String message) {
		JOptionPane.showMessageDialog(this, message);
	}
	

	
	// Accessor methods for other system parts
	public GameEngine getModel() {
		return this.gameEngine;
	}
	
	public MainWindow getMainWindow() {
		return this;
	}
	
	public MainController getMainController() {
		return this.controller;
	}
	
	public BottomBars getBottomBars() {
		return this.bottomBars;
	}
	
}
