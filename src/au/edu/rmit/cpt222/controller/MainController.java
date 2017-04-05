package au.edu.rmit.cpt222.controller;

import au.edu.rmit.cpt222.model.GUICallbackImpl;
import au.edu.rmit.cpt222.model.interfaces.GameEngine;
import au.edu.rmit.cpt222.view.MainWindow;

// perform all model communication
public class MainController {
	
	private MainWindow mw;
	private GameEngine engine;
	
	public MainController(MainWindow mw) {
		this.mw = mw;
		this.engine = mw.getModel();
		this.engine.addGameEngineCallback(new GUICallbackImpl(this));
	}
	
	public void roll() {
		//Would actually hit model to rollPlayer() or whatever
		System.out.println("roll was clicked");
		new Thread() {
			public void run() {
				MainController.this.engine.rollPlayer(null, 0, 0, 0);
			}
		}.start();
	}
	
	public void updateRollArea() {
		System.out.println("In controller - asking View to update itself");
		//then call subcomponent to update view
		//this.mw.updateGamePanel("whatever");
	}

}
