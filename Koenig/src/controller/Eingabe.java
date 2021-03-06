package controller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import model.Spiel;
import model.Spieler;


import view.Anzeige;
import view.MarktplatzView;
import view.View;

public class Eingabe {
	private Spieler				spieler;
	private Anzeige				ausgabe;
	private String				eingabe;
	private Map<String, String>	validKeys	= new HashMap<>();
	private Spiel spiel;

	public Eingabe() {
		super();
		spieler = new Spieler( "Herzogin", "Andrea" );
		ausgabe = new Anzeige(/*sdf*/);
		spiel = new Spiel();
		//testen
		

	}

	public void test() {
		View view = new MarktplatzView(spiel);
		ausgabe.getViews().put( "MARKTPLATZ", view );
		System.out.println(ausgabe.show( "MARKTPLATZ", 0, spieler ));
	}

	private void loopRunde() {
		boolean stop = false;
		String eingabeAusUntererEbene = null;
		do {
			if ( eingabeAusUntererEbene != null ) {
				eingabe = eingabeAusUntererEbene;
				eingabeAusUntererEbene = null;
			} else {
				eingabe = getEingabe();
			}
			// Ignore Case fehlt noch
			if ( validKeys.keySet().contains( eingabe ) ) {
				switch ( validKeys.get( eingabe ) ) {
					case "MARKTPLATZ_KAUFEN":
						
						ausgabe.show( "Marktplatz", 2, spieler );
						
//						boolean innerStop = false;
//						do {
//							eingabe = getEingabe();
//
//							if ( !stringToInt( eingabe ) || !kaufekorn() ) {
//
//								// falls F-Taste anstatt erwarteter Zahl
//								if ( validKeys.keySet().contains( eingabe ) ) {
//									eingabeAusUntererEbene = validKeys.get( eingabe );
//									innerStop = true;
//								}
//								ausgabe.show( "Marktplatz", 4, spieler );
//							}else{
//								innerStop = true;
//							}
//
//						} while ( !innerStop );
//						ausgabe.show( "Marktplatz", 1, spieler );
						break;
					case "MARKTPLATZ_KAUFEN_ERROR":

					case "MARKTPLATZ_KAUFEN_KORN":
						// ausgabe.setView( ausgabe.getViews().get( "Marktplatz" ) );
						// ausgabe.getView().setViewIndex( 3 );
						// ausgabe.show( spieler );
						eingabe = getEingabe();

						break;

					
					
					case "ESC":
						try {
							ausgabe.showPreviousView();
						} catch ( IndexOutOfBoundsException e ) {
						}
						break;

					default:

				}
			} else {
				System.out.println( "Ung�ltige Eingabe" );
				// ausgabe.show( spieler );
			}
		} while ( !stop );

	}

	private String getEingabe() {
		String line = null;
		BufferedReader bufferedReader;
		InputStreamReader inputStreamReader;

		inputStreamReader = new InputStreamReader( System.in );
		bufferedReader = new BufferedReader( inputStreamReader );

		try {
			line = bufferedReader.readLine();
		} catch ( IOException e ) {

		}

		return line;
	}
}
