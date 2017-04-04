package au.edu.rmit.cpt222.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import au.edu.rmit.cpt222.controller.AddPlayerButtonListener;
import au.edu.rmit.cpt222.controller.QuitButtonListener;
import au.edu.rmit.cpt222.controller.ResetButtonListener;
import au.edu.rmit.cpt222.model.GameEngineImpl;
import au.edu.rmit.cpt222.model.interfaces.GameEngine;
import au.edu.rmit.cpt222.model.interfaces.Player;

public class MainWindow extends JFrame  {
	Container window = new JFrame();
	
	final GameEngine gameEngine;
	
//	private JOptionPane resetModal = new JOptionPane();
//	private JOptionPane quitModal = new JOptionPane();
	
	private JButton addPlayer = new JButton("Add Player");
	private JButton play = new JButton("Play");
	private JButton quit = new JButton("Quit");
	private JButton reset = new JButton("Reset");
	
	private JPanel topMenu = new JPanel();
	private JPanel rollArea = new JPanel();
	private JPanel leftDice = new JPanel();
	private JPanel rightDice = new JPanel();
	private JPanel bottomBars = new JPanel();
	private JPanel status = new JPanel();
	private JPanel errorLog = new JPanel();
	
	private JComboBox playerList = new JComboBox();
	
	// DO I need to instantiate model here?
	// Not sure how else you'd do it
	
	//https://docs.oracle.com/javase/tutorial/uiswing/examples/layout/BorderLayoutDemoProject/src/layout/BorderLayoutDemo.java
	
	public MainWindow() {
		super("Roll-o-Rama");
		setBounds(100, 100, 800, 600);
		
		gameEngine = new GameEngineImpl();
		
		window.setLayout(new BorderLayout());
		window = getContentPane();
		
		// Top menu buttons
		window.add(topMenu, BorderLayout.PAGE_START);
		topMenu.add(playerList);
		topMenu.add(addPlayer);
		topMenu.add(play);
		topMenu.add(reset);
		topMenu.add(quit);
		
		addPlayer.addActionListener(new AddPlayerButtonListener(this));
		quit.addActionListener(new QuitButtonListener(this));
		reset.addActionListener(new ResetButtonListener(this, gameEngine));
				
		// Main panel
		window.add(rollArea, BorderLayout.CENTER);
		rollArea.add(leftDice);
		rollArea.add(rightDice);
		leftDice.add(new JLabel("Where player dice go"));
		rightDice.add(new JLabel("Where house dice go"));
			
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		// Bottom bars
		window.add(bottomBars, BorderLayout.PAGE_END);
		bottomBars.add(errorLog);
		bottomBars.add(status);
		
		status.setBackground(Color.GREEN);
		status.setPreferredSize(new Dimension((window.getWidth() / 3), 30));
		
		errorLog.setBackground(Color.RED);
		errorLog.setPreferredSize(new Dimension((window.getWidth() / 3), 30));

	}
	
	public void updateCombo(Collection<Player> players) {
		DefaultComboBoxModel model = (DefaultComboBoxModel) playerList.getModel();
		model.removeAllElements();
		
		for (Player player : players) {
			model.addElement(player.getPlayerName());
		}
		
		playerList.setModel(model);
	}
	
	public GameEngine getGameEngine() {
		return this.gameEngine;
	}
	
}
