package giocatori;

import carte.ColoreCarta;

public enum Pedina {
	
	ROSSA,	//Codice colore: "\u001B[31m"
	GIALLA,	//Codice colore: "\u001B[33m"
	VERDE,	//Codice colore: "\u001B[32m"
	BLU;	//Codice colore: "\u001B[34m"
			//Codice per reset colore: "\u001B[0m"
	
	
	public static final String CODICE_RESET_COLORE = "\u001B[0m";
	
	
	public static String ottieniStringCodiceColoreDaStringa(String stringaConColore)
	//utilizzo i codici dei colori per "colorare" i nickname dei giocatori in base al colore scelto
	{
		if( stringaConColore.equals(Pedina.ROSSA.toString()) ) {
			return "\u001B[31m";
		} else if( stringaConColore.equals(Pedina.GIALLA.toString()) ) {
			return "\u001B[33m";
		} else if( stringaConColore.equals(Pedina.VERDE.toString()) ) {
			return "\u001B[32m";
		} else if( stringaConColore.equals(Pedina.BLU.toString()) ) {
			return "\u001B[34m";
		} else {
			return "";
		}
	}

}
