package game;

import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) throws IOException {

		LightsOutJava game = new LightsOutJava(5, 5); // Erstellt ein neues Exemplar von LightsOutJava mit der Gridgröße 5 und 5
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Wenn der X-Button gedrückt wird wird die Anwendung geschlossen
		game.setTitle("LightsOut"); // Setzt den Namen der Anwendung
		ImageIcon JFrameIcon = new ImageIcon("Icon/LightsOut.png");	//Erstellt einen neuen ImageIcon
		game.setIconImage(JFrameIcon.getImage());	//Setzt den Icon der Anwendung auf den Pfad von JFrameIcon
	}

}