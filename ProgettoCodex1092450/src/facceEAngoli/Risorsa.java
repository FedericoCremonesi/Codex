package facceEAngoli;

import giocatori.Pedina;

public enum Risorsa {
	
	FUNGHI,		//Codice colore: "\u001B[31m"
	VEGETALE,	//Codice colore: "\u001B[32m"
	ANIMALE,	//Codice colore: "\u001B[34m"
	INSETTI;	//Codice colore: "\u001B[35m"
				//Codice per reset colore: "\u001B[0m"
	
	
	public static final String CODICE_COLORE_RISORSA_FUNGHI = "\u001B[31m";
	public static final String CODICE_COLORE_RISORSA_VEGETALE = "\u001B[32m";
	public static final String CODICE_COLORE_RISORSA_ANIMALE = "\u001B[34m";
	public static final String CODICE_COLORE_RISORSA_INSETTI = "\u001B[35m";
	public static final String CODICE_RESET_COLORE = "\u001B[0m";
	
	
	/**
	 * Data in ingresso una risorsa come Stringa, restituisce una Stringa con il codice colore corrispondente al colore stesso ricevuto in ingresso
	 * @param risorsa come String
	 * @return Stringa con codice colore
	 */
	public static String ottieniStringCodiceColoreDaRisorsa(String risorsa)
	{
		if( risorsa.equals(Risorsa.FUNGHI.toString()) ) {
			return Risorsa.CODICE_COLORE_RISORSA_FUNGHI;
		} else if( risorsa.equals(Risorsa.VEGETALE.toString()) ) {
			return Risorsa.CODICE_COLORE_RISORSA_VEGETALE;
		} else if( risorsa.equals(Risorsa.ANIMALE.toString()) ) {
			return Risorsa.CODICE_COLORE_RISORSA_ANIMALE;
		} else if( risorsa.equals(Risorsa.INSETTI.toString()) ) {
			return Risorsa.CODICE_COLORE_RISORSA_INSETTI;
		} else {
			return "";
		}
	}
}
