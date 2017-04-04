package au.edu.rmit.cpt222.driver;

import javax.swing.SwingUtilities;

import au.edu.rmit.cpt222.view.MainWindow;

public class DiceGameMain {
	
	public static void main(String args[]) {
	     SwingUtilities.invokeLater(new Runnable() {
	          @Override
	           public void run() { new MainWindow(); }
	     });
	}
}
