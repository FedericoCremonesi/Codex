package giocatori;

import carte.ColoreCarta;

public enum Pedina {
	
	ROSSA,	//Codice colore: "\u001B[31m"	-> Sfondo: "\u001B[41m"
	GIALLA,	//Codice colore: "\u001B[33m"	-> Sfondo: "\u001B[43m"
	VERDE,	//Codice colore: "\u001B[32m"	-> Sfondo: "\u001B[42m"
	BLU;	//Codice colore: "\u001B[34m"	-> Sfondo: "\u001B[44m"
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
	
	public static String ottieniStringCodiceColoreSfondoDaStringa(String stringaConColore)
	//utilizzo i codici dei colori per "colorare" i nickname dei giocatori in base al colore scelto
	{
		if( stringaConColore.equals(Pedina.ROSSA.toString()) ) {
			return "\u001B[41m";
		} else if( stringaConColore.equals(Pedina.GIALLA.toString()) ) {
			return "\u001B[43m";
		} else if( stringaConColore.equals(Pedina.VERDE.toString()) ) {
			return "\u001B[42m";
		} else if( stringaConColore.equals(Pedina.BLU.toString()) ) {
			return "\u001B[44m";
		} else {
			return "";
		}
	}

}
