package au.edu.rmit.cpt222.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import au.edu.rmit.cpt222.view.MainWindow;

public class QuitButtonListener implements ActionListener {
	   private MainWindow mw;
			   
	   public QuitButtonListener(MainWindow mw)
	   {
	      this.mw = mw;
	   }

	   @Override
	   public void actionPerformed(ActionEvent arg0)
	   {
		   mw.setVisible(false);
		   mw.dispose();
	   }
  
	   
}
