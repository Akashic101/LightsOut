package game;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;

public class LightsOutJava extends JFrame {

	private static final long serialVersionUID = 1L; // Erstellt eine Seriennummer
	private Container fenster; // Erstellt einen Container
	private JButton[][] buttons; // Erstellt ein 2-dimensoniales Array des typen JButton

	public LightsOutJava(int x, int y) {
		buttons = new JButton[x][y]; // Erstellt eine neue Liste der Größe die im Konstruktor angegeben wird, wobei x die Anzahl der Spalten und y die Anzahl der Reihen ist
		fenster = getContentPane();	//Gibt das contentPane-Objekt für diesen Rahmen zurück
		fenster.setLayout(new GridLayout(0, y)); // Erstellt ein neues Grid-Layout der Größe die im Konstruktor
														// angegeben wird
		for (int i = 0; i < buttons.length; i++) {
			for (int j = 0; j < buttons[i].length; j++) {
				buttons[i][j] = new JButton("" + (i + 1) * (j + 1)); // Erstellt einen neuen Button mit dem Text (i+1) +
																		// (j+1)
				buttons[i][j].setBackground(Color.white); // Setzt den Hintergrund auf Weiß
				buttons[i][j].addActionListener(new ButtonPress()); // Fügt die Klasse ButtonPress als Actionlistener
																	// dem Button hinzu
				fenster.add(buttons[i][j]); // Fügt den Button dem Container hinzu
			}
		}
		setSize(5 * 100, 5 * 100); // Setzt die Größe des Containers auf 500 x 500

		Random random = new Random(); // Erstellt einen neuen Random
		for (JButton[] buttonrow : buttons) { // Geht einmal durch das JButton-Array
			for (JButton button : buttonrow) {
				boolean temp = random.nextBoolean(); // Setzt einen zufälligen Boolean bei jedem neuem Button
				if (temp == true) { // Wenn temp true ist
					button.doClick(); // wird der Button angeklickt und ButtonPress wird ausgeführt

				}
			}
		}
		fenster.repaint(); // Updatet das Fenster
		setVisible(true); // Setzt das Fenster auf sichtbar
	}

	private class ButtonPress implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			int x = 0;
			int y = 0;
			for (int i = 0; i < buttons.length; i++) { // Geht einmal durch das gesamte Array
				for (int j = 0; j < buttons[i].length; j++) {
					if (((JButton) e.getSource()).equals(buttons[i][j])) { // Wenn die Source des angeklickten Buttons
						x = i; // Setzt X (und Y in Z.58) auf den Wert die Position des Buttons damit diese im
						// switchColor benutzt werden können
						y = j;
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