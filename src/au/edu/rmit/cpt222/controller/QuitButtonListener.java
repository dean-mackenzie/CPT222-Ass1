package au.edu.rmit.cpt222.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import au.edu.rmit.cpt222.view.MainWindow;

public class QuitButtonListener implements ActionListener {
	   MainWindow window;
			   
	   public QuitButtonListener(MainWindow window)
	   {
	      super();
	      this.window = window;
	   }

	   @Override
	   public void actionPerformed(ActionEvent arg0)
	   {
		   window.setVisible(false);
		   window.dispose();
	   }
  
	   
}
