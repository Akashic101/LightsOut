package game;

import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main {

	public static int x;
	public static int y;
	public static boolean notDone = true;

	public static void main(String[] args) throws IOException {

		setXandY();

		LightsOutJava game = new LightsOutJava(x, y); // Erstellt ein neues Exemplar von LightsOutJava mit der
														// Gridgröße input(x) und input(y)
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Wenn der X-Button gedrückt wird wird die Anwendung
																// geschlossen
		game.setTitle("LightsOut"); // Setzt den Namen der Anwendung
		ImageIcon JFrameIcon = new ImageIcon("Icon/LightsOut.png"); // Erstellt einen neuen ImageIcon
		game.setIconImage(JFrameIcon.getImage()); // Setzt den Icon der Anwendung auf den Pfad von JFrameIcon

	}

	public static void setXandY() {

		String xInput = null;
		String yInput = null;

		ImageIcon row = new ImageIcon("Images/row.png");
		ImageIcon column = new ImageIcon("Images/column.png");

		try {

			xInput = (String) JOptionPane.showInputDialog(null, "How many rows should the game have??", "Layout",
					JOptionPane.QUESTION_MESSAGE, row, null, null);
			x = Integer.parseInt(xInput);

			yInput = (String) JOptionPane.showInputDialog(null, "How many columns should the game have??", "Layout",
					JOptionPane.QUESTION_MESSAGE, column, null, null);
			y = Integer.parseInt(yInput);

			if (x < 1 || y < 1) {
				JOptionPane.showMessageDialog(null, "Please enter only valid numbers");
				setXandY();
			}

		} catch (NumberFormatException e) {
			if (xInput == null || yInput == null) {
				setXandY();
			} else {
				JOptionPane.showMessageDialog(null, "Please enter only valid numbers");
				setXandY();
			}

		}
	}

}