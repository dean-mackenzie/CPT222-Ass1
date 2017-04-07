package au.edu.rmit.cpt222.view;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RollArea extends JPanel {
	
	private MainWindow mw;
	
	private JPanel leftDice;
	private JPanel rightDice;
	
	private JLabel playerDice1;
	private JLabel playerDice2;
	private JLabel houseDice1;
	private JLabel houseDice2;
	
	private JLabel diceImgPnl1;
	private JLabel diceImgPnl2;
	private JLabel diceImgPnl3;
	private JLabel diceImgPnl4;
	private JLabel diceImgPnl5;
	private JLabel diceImgPnl6;
	private JLabel diceImgPnl7;
	private JLabel diceImgPnl8;
	private JLabel diceImgPnl9;
	private JLabel diceImgPnl10;
	private JLabel diceImgPnl11;
	private JLabel diceImgPnl12;
	
	private Image diceImg1;
	private Image diceImg2;
	private Image diceImg3;
	private Image diceImg4;
	private Image diceImg5;
	private Image diceImg6;
	
	private JLabel toDisplay1;
	private JLabel toDisplay2;
	
	// 2 maps to avoid problems with duplicate results (e.g. 2 and 2)
	private Map<Integer, JLabel> diceRoll1 = new HashMap<Integer, JLabel>();
	private Map<Integer, JLabel> diceRoll2 = new HashMap<Integer, JLabel>();
	
	public RollArea(MainWindow mw) {
		this.mw = mw;
		createRollArea();
	}
	
	public void createRollArea() {
		// Create components
		leftDice = new JPanel();
		rightDice = new JPanel();
		
		// Load images
		this.loadImages();

		playerDice1 = new JLabel("P1");
		playerDice2 = new JLabel("P2");
		houseDice1 = new JLabel("H1");
		houseDice2 = new JLabel("H2");
		
		// Add components
		this.add(leftDice);
		this.add(rightDice);
		
		leftDice.add(this.playerDice1);
		leftDice.add(this.playerDice2);
		leftDice.add(this.diceImgPnl1);
		leftDice.add(this.diceImgPnl2);

		rightDice.add(this.houseDice1);
		rightDice.add(this.houseDice2);
		rightDice.add(this.diceImgPnl3);
		rightDice.add(this.diceImgPnl4);
	}
	
	public void loadImages() {
		try {
		    diceImg1 = ImageIO.read(new File("assets/1-dice.png"));
		    diceImg2 = ImageIO.read(new File("assets/2-dice.png"));
		    diceImg3 = ImageIO.read(new File("assets/3-dice.png"));
		    diceImg4 = ImageIO.read(new File("assets/4-dice.png"));
		    diceImg5 = ImageIO.read(new File("assets/5-dice.png"));
		    diceImg6 = ImageIO.read(new File("assets/6-dice.png"));

		} catch (IOException e) {
			//TODO: default to numbers?
		}
		
		// Load dice into JLabels (1 set per dice)
		diceImgPnl1 = new JLabel(new ImageIcon(diceImg1));
		diceImgPnl2 = new JLabel(new ImageIcon(diceImg2));
		diceImgPnl3 = new JLabel(new ImageIcon(diceImg3));
		diceImgPnl4 = new JLabel(new ImageIcon(diceImg4));
		diceImgPnl5 = new JLabel(new ImageIcon(diceImg5));
		diceImgPnl6 = new JLabel(new ImageIcon(diceImg6));
		
		diceImgPnl7 = new JLabel(new ImageIcon(diceImg1));
		diceImgPnl8 = new JLabel(new ImageIcon(diceImg2));
		diceImgPnl9 = new JLabel(new ImageIcon(diceImg3));
		diceImgPnl10 = new JLabel(new ImageIcon(diceImg4));
		diceImgPnl11 = new JLabel(new ImageIcon(diceImg5));
		diceImgPnl12 = new JLabel(new ImageIcon(diceImg6));
		
		// Add images to HashMap for mapping
		diceRoll1.put(1, diceImgPnl1);
		diceRoll1.put(2, diceImgPnl2);
		diceRoll1.put(3, diceImgPnl3);
		diceRoll1.put(4, diceImgPnl4);
		diceRoll1.put(5, diceImgPnl5);
		diceRoll1.put(6, diceImgPnl6);
		
		diceRoll2.put(1, diceImgPnl7);
		diceRoll2.put(2, diceImgPnl8);
		diceRoll2.put(3, diceImgPnl9);
		diceRoll2.put(4, diceImgPnl10);
		diceRoll2.put(5, diceImgPnl11);
		diceRoll2.put(6, diceImgPnl12);
	}
	
	public void updatePlayerDice(int dice1, int dice2) {
		this.updateDice(leftDice, dice1, dice2);
		
		playerDice1.setText(Integer.toString(dice1));
		playerDice2.setText(Integer.toString(dice2));

	}
	
	public void updateHouseDice(int dice1, int dice2) {
		this.updateDice(rightDice, dice1, dice2);
		
		houseDice1.setText(Integer.toString(dice1));
		houseDice2.setText(Integer.toString(dice2));
	}
	
	public void updateDice(JPanel toUpdate, int dice1, int dice2) {
		// Remove existing dice images
		try {
			toUpdate.remove(2);
			toUpdate.revalidate();
			toUpdate.remove(2);
			toUpdate.revalidate();
		} catch (ArrayIndexOutOfBoundsException e) {
			;
		}

		//Find images from map
		toDisplay1 = diceRoll1.get(dice1);
		toUpdate.add(toDisplay1);
		toUpdate.revalidate();
		toDisplay2 = diceRoll2.get(dice2);
		toUpdate.add(toDisplay2);
		toUpdate.revalidate();
	}
}
