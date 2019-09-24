package game;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

public class LightsOutJava extends JFrame {

	private static final long serialVersionUID = 201909242322L; // Erstellt eine Seriennummer
	private Container fenster; // Erstellt einen Container
	private JButton[][] buttons; // Erstellt ein 2-dimensoniales Array des typen JButton
	private int amountMoves = 0;
	private int minimumMoves = 0;
	// ArrayList<ArrayList<Integer>> steps = new ArrayList<>();
	ArrayList<String> steps = new ArrayList<String>();
	JLabel moves = new JLabel();
	JLabel minimum = new JLabel();
	JButton start = new JButton();
	JButton revert = new JButton();
	JProgressBar progress = new JProgressBar();

	public LightsOutJava(int x, int y) {
		buttons = new JButton[x][y]; // Erstellt eine neue Liste der Größe die im Konstruktor angegeben wird, wobei x
										// die Anzahl der Spalten und y die Anzahl der Reihen ist
		fenster = getContentPane(); // Gibt das contentPane-Objekt für diesen Rahmen zurück
		fenster.setLayout(new GridLayout(0, y)); // Erstellt ein neues Grid-Layout der Größe die im Konstruktor
													// angegeben wird

		progress.setMinimum(1);
		progress.setMaximum(x * y);

		int counter = 0;
		for (int i = 0; i < buttons.length; i++) {
			for (int j = 0; j < buttons[i].length; j++) {
				counter++;
				buttons[i][j] = new JButton("" + counter); // Erstellt einen neuen Button mit dem Text (i+1) +
															// (j+1)
				buttons[i][j].setBackground(Color.white); // Setzt den Hintergrund auf Weiß
				buttons[i][j].addActionListener(new ButtonPress()); // Fügt die Klasse ButtonPress als Actionlistener
																	// dem Button hinzu
				fenster.add(buttons[i][j]); // Fügt den Button dem Container hinzu
				progress.setValue(steps.size());

			}
		}

		fenster.add(moves);
		fenster.add(minimum);
		fenster.add(progress);

		revert.setText("revert");
		revert.setVisible(false);
		fenster.add(revert);
		revert.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				for (int i = 0; i < buttons.length; i++) {
					for (int j = 0; j < buttons[i].length; j++) {

						if (buttons[i][j].getText().equals(steps.get(steps.size() - 1))) {
							steps.remove(steps.size() - 1);
							buttons[i][j].doClick();

						}
					}
				}

			}
		});

		setSize(x * 100, y * 100); // Setzt die Größe des Containers auf x*100 und y*100

		Random random = new Random(); // Erstellt einen neuen Random
		for (JButton[] buttonrow : buttons) { // Geht einmal durch das JButton-Array
			for (JButton button : buttonrow) {
				boolean bool = random.nextBoolean(); // Setzt einen zufälligen Boolean bei jedem neuem Button
				if (bool == true) { // Wenn bool true ist
					button.doClick(); // wird der Button angeklickt und ButtonPress wird ausgeführt
					amountMoves = 0;
					moves.setVisible(false);
				}
			}
		}

		for (int i = 0; i < buttons.length; i++) {
			for (int j = 0; j < buttons[i].length; j++) {
				buttons[i][j].setEnabled(false);
			}
		}

		fenster.repaint(); // Updatet das Fenster
		setVisible(true); // Setzt das Fenster auf sichtbar

		start.setText("Start");
		fenster.add(start);
		start.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < buttons.length; i++) {
					for (int j = 0; j < buttons[i].length; j++) {
						buttons[i][j].setEnabled(true);
						start.setVisible(false);
						moves.setVisible(true);
						revert.setVisible(true);
					}
				}
			}
		});

	}

	private class ButtonPress implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			if (start.isVisible()) {
				minimumMoves++;
			}

			amountMoves++;
			moves.setText("Moves: " + amountMoves);
			minimum.setText("Minimum: " + minimumMoves);

			int x = 0;
			int y = 0;
			for (int i = 0; i < buttons.length; i++) { // Geht einmal durch das gesamte Array
				for (int j = 0; j < buttons[i].length; j++) {
					if (((JButton) e.getSource()).equals(buttons[i][j])) { // Wenn die Source des angeklickten Buttons
						x = i; // Setzt X und Y auf den Wert die Position des Buttons damit diese im
								// switchColor benutzt werden können
						y = j;

						if (start.isVisible()) {
							steps.add(buttons[i][j].getText());
							System.out.println(steps.get(steps.size() - 1));
							progress.setVisible(true);
						}

						break; // Beendet die Schleife
					}
				}
			}

			switchColor(buttons[x][y]); // Ändert die Farbe des Buttons links vom angeklickten Button
			if (x > 0) {
				switchColor(buttons[x - 1][y]);
			}
			if (x < buttons.length - 1) {// Ändert die Farbe des Buttons rechts vom angeklickten Button
				switchColor(buttons[x + 1][y]);
			}
			if (y > 0) {
				switchColor(buttons[x][y - 1]); // Ändert die Farbe des Buttons oben vom angeklickten Button
			}
			if (y < buttons[x].length - 1) { // Ändert die Farbe des Buttons unten vom angeklickten Button
				switchColor(buttons[x][y + 1]);
			}

			if (checkWin() == true) {
				
				for (JButton[] buttonrow : buttons) {
					for (JButton button : buttonrow) {
						button.setBackground(Color.green);
						button.setForeground(Color.black);
						button.setEnabled(false);
						

						for (int i = 0; i < buttons.length; i++) { // Geht einmal durch das gesamte Array
							for (int j = 0; j < buttons[i].length; j++) {
								buttons[i][j].setForeground(Color.black);
								buttons[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
							}
						}
					}
				}
				
				if(amountMoves == minimumMoves) {
					JOptionPane.showMessageDialog(null, "Congratulations, You won with a perfect score!");
				}
				else {
					JOptionPane.showMessageDialog(null, "Congratulations, You won!");
				}
				
				
			}
		}

		private boolean checkWin() {
			for (JButton[] buttonrow : buttons) {
				for (JButton button : buttonrow) {
					if (button.getBackground() == Color.black) {
						return false;
					}
				}
			}
			return true;
		}

		public void switchColor(JButton button) { // Ändert die Farbe des angeklickten Buttons
			if (button.getBackground().equals(Color.black)) { // Wenn die Hintergrundfarbe des Buttons Schwarz ist
				button.setBackground(Color.white); // Wird die Hintergrundfarbe auf weiß gesetzt
				button.setForeground(Color.black); // Und die Farbe des Textes wird auf Weiß gesetzt
			} else {
				button.setBackground(Color.black); // Ansonsten wird sie auf Schwarz gesetzt
				button.setForeground(Color.white);// Und die Farbe des Textes wird auf Weiß gesetzt
			}
		}

	}

}