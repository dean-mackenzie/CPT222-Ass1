package au.edu.rmit.cpt222.view;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import au.edu.rmit.cpt222.controller.TopController;

public class TopMenu extends JPanel {
	public static final String PLAY_TEXT = "Play";
	public static final String ADDPLAYER_TEXT = "Add Player";
	public static final String QUIT_TEXT = "Quit";
	
	MainWindow mw;
	
	TopMenuBar topMenuBar;
	TopMenuButtons topMenuButtons;
	
	TopController tc;
	
	public TopMenu(MainWindow mw) {
		this.mw = mw;
		createTopMenu();
	}
	
	public void createTopMenu() {
		this.setLayout(new BorderLayout());
		
		topMenuBar = new TopMenuBar(this.mw, this.tc);
		topMenuButtons = new TopMenuButtons(this.mw, this.tc);
		
		this.add(topMenuBar, BorderLayout.PAGE_START);
		this.add(topMenuButtons, BorderLayout.PAGE_END);
		
		// Set controller to components
		this.tc = new TopController(topMenuButtons, topMenuBar);
		topMenuBar.setController(tc);
		topMenuButtons.setController(tc);
	}
	
	public MainWindow getMainWindow() {
		return this.mw;
	}
	
	public TopController getTopController() {
		return tc;
	}
	
	

}
