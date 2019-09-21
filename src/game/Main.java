package game;

import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) throws IOException {

		String input= JOptionPane.showInputDialog("Wie groß soll das Spielfeld sein?"); //Fragefeld was die Größe des Spielfeldes abfragt

		LightsOutJava game = new LightsOutJava(Integer.parseInt(input), Integer.parseInt(input)); // Erstellt ein neues Exemplar von LightsOutJava mit der Gridgröße input(x) und input(y)
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Wenn der X-Button gedrückt wird wird die Anwendung geschlossen
		game.setTitle("LightsOut"); // Setzt den Namen der Anwendung
		ImageIcon JFrameIcon = new ImageIcon("Icon/LightsOut.png");	//Erstellt einen neuen ImageIcon
		game.setIconImage(JFrameIcon.getImage()); //Setzt den Icon der Anwendung auf den Pfad von JFrameIcon
	}

}