package au.edu.rmit.cpt222.view;

import javax.swing.JButton;
import javax.swing.JPanel;

import au.edu.rmit.cpt222.controller.AddPlayerButtonListener;
import au.edu.rmit.cpt222.controller.PlayButtonListener;
import au.edu.rmit.cpt222.controller.QuitButtonListener;
import au.edu.rmit.cpt222.controller.ResetButtonListener;

public class TopMenu extends JPanel {
	
	private MainWindow mw;
	
	private JButton addPlayer;
	private JButton play;
	private JButton quit;
	private JButton reset;
	private PlayerListCombo playerCombo;
	
	public TopMenu(MainWindow mw) {
		this.mw = mw;
		createTopMenu();
	}
	
	public void createTopMenu() {
		//Add buttons
		addPlayer = new JButton("Add Player");
		play = new JButton("Play");
		quit = new JButton("Quit");
		reset = new JButton("Reset");
		
		playerCombo = new PlayerListCombo(mw);
		
		this.add(playerCombo);
		this.add(addPlayer);
		this.add(play);
		this.add(reset);
		this.add(quit);
		
		//all goes to main controller: remember way of differentiating
		addPlayer.addActionListener(new AddPlayerButtonListener(mw, playerCombo));
		play.addActionListener(new PlayButtonListener(mw));
		quit.addActionListener(new QuitButtonListener(mw));
		reset.addActionListener(new ResetButtonListener(mw));
		
	}

}
