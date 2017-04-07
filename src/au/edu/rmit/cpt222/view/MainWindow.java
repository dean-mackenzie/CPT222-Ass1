package au.edu.rmit.cpt222.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import au.edu.rmit.cpt222.controller.MainController;
import au.edu.rmit.cpt222.model.interfaces.GameEngine;
import au.edu.rmit.cpt222.model.interfaces.Player;

public class MainWindow extends JFrame  {
	Container window = new JFrame();
	private GameEngine gameEngine;
	
	private TopMenu topMenu;
	private BottomBars bottomBars;
	private RollArea rollArea;

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
	public void updateRollPanel(int dice1, int dice2) {
		rollArea.updatePlayerDice1(dice1);
		rollArea.updatePlayerDice2(dice2);
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
	
}
