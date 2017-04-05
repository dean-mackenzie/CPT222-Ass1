package au.edu.rmit.cpt222.view;

import java.util.Collection;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import au.edu.rmit.cpt222.model.interfaces.Player;

public class PlayerListCombo extends JPanel {
	private MainWindow mw;
	private JComboBox<String> playerList;
	
	public PlayerListCombo(MainWindow mw) {
		this.mw = mw;
		this.playerList = new JComboBox<String>();
		this.add(playerList);
	}

	// Updates the player combo box with all player names
	public void updateCombo(Collection<Player> players) {
		DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) this.playerList.getModel();
		model.removeAllElements();
		
		for (Player player : players) {
			model.addElement(player.getPlayerName());
		}
		
		this.playerList.setModel(model);
	}

}
