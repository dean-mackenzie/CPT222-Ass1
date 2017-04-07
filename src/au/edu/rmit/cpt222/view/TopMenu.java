package au.edu.rmit.cpt222.view;

import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import au.edu.rmit.cpt222.controller.AddPlayerButtonListener;
import au.edu.rmit.cpt222.controller.PlayButtonListener;
import au.edu.rmit.cpt222.controller.QuitButtonListener;
import au.edu.rmit.cpt222.controller.ResetButtonListener;
import au.edu.rmit.cpt222.controller.TopMenuController;
import au.edu.rmit.cpt222.model.interfaces.Player;

public class TopMenu extends JPanel {
	
	public static final String PLAY_TEXT = "Play";
	public static final String ADDPLAYER_TEXT = "AddPlayer";
	public static final String QUIT_TEXT = "Quit";
		
	private MainWindow mw;
	private TopMenuController tmc;
	
	private JButton addPlayer;
	private JButton play;
	private JButton quit;
	//private JButton reset;
	private JComboBox playerCombo;
	
	
	public TopMenu(MainWindow mw) {
		this.mw = mw;
		createTopMenu();
	}
	
	public void createTopMenu() {
		//Add buttons
		addPlayer = new JButton(ADDPLAYER_TEXT);
		play = new JButton(PLAY_TEXT);
		quit = new JButton(QUIT_TEXT);
		// TODO: Add reset only if time
		//	reset = new JButton("Reset");
		
		//Add UI components to panel
		this.playerCombo = new JComboBox();
		
		this.add(playerCombo);
		this.add(addPlayer);
		this.add(play);
		this.add(quit);
	//	this.add(reset);
		

		this.addPlayer.setActionCommand(ADDPLAYER_TEXT);
		this.play.setActionCommand(PLAY_TEXT);
		this.quit.setActionCommand(QUIT_TEXT);
		
		// Set action listeners
		this.tmc = new TopMenuController(this);
		this.addPlayer.addActionListener(this.tmc);
		this.play.addActionListener(this.tmc);
		this.quit.addActionListener(this.tmc);
		

		// delete
//		addPlayer.addActionListener(new AddPlayerButtonListener(mw, playerCombo));
//		play.addActionListener(new PlayButtonListener(mw));
//		quit.addActionListener(new QuitButtonListener(mw));
//	//	reset.addActionListener(new ResetButtonListener(mw));
		
	}
	
	public MainWindow getMainWindow() {
		return mw;
	}
	
	public void updatePlayerCombo(List<String> playerNames) {
		@SuppressWarnings("unchecked")
		DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) this.playerCombo.getModel();
		model.removeAllElements();
		
		for (String name : playerNames) {
			model.addElement(name);
		}
		
		this.playerCombo.setModel(model);
	}

}
