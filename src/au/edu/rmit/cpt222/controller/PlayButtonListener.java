package au.edu.rmit.cpt222.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import au.edu.rmit.cpt222.model.interfaces.GameEngine;
import au.edu.rmit.cpt222.view.MainWindow;

public class PlayButtonListener implements ActionListener {
	   private MainWindow mw;
			   
	public PlayButtonListener(MainWindow mw)
	{
		this.mw = mw;
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
	   //access controller : linkedcomponent.getMainView().getMainController().roll();
	}
  
	   
}
