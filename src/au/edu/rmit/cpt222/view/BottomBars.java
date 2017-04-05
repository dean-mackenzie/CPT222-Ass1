package au.edu.rmit.cpt222.view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class BottomBars extends JPanel {
		
	private MainWindow mw;
	
	private JPanel status;
	private JPanel errorLog;
	
	public BottomBars(MainWindow mw) {
		this.mw = mw;
		createBottomBars();
	}
	
	public void createBottomBars() {
		//Add buttons
		status = new JPanel();
		errorLog = new JPanel();
		
		status.setBackground(Color.GREEN);
		status.setPreferredSize(new Dimension((int) (mw.getWidth() / 2.5), 30));
		errorLog.setBackground(Color.RED);
		errorLog.setPreferredSize(new Dimension((int) (mw.getWidth() / 2.5), 30));

		this.add(errorLog);
		this.add(status);
		
		//all goes to main controller: remember way of differentiating
		
		
	}

}
