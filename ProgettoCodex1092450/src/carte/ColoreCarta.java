package carte;

public enum ColoreCarta {
	
	ROSSO,	//Codice colore: "\u001B[31m"
	VERDE,	//Codice colore: "\u001B[32m"
	BLU,	//Codice colore: "\u001B[34m"
	VIOLA;	//Codice colore: "\u001B[35m"
			//Codice per reset colore: "\u001B[0m"
	
	
	public static final String CODICE_COLORE_CARTA_RISORSA = "\u001B[30m";		//colore nero
	public static final String CODICE_COLORE_CARTA_ORO = "\u001B[33m";			//colore giallo
	public static final String CODICE_COLORE_CARTA_OBIETTIVO = "\u001B[33m";	//colore giallo
	public static final String CODICE_RESET_COLORE = "\u001B[0m";				//reset colore
	
	
	/**
	 * Data in ingresso una Stringa con il nome del colore, restituisce una Stringa con il codice colore che permette di colorare il testo stampato a schermo del colore stesso inserito
	 * @param stringaConColore Stringa con nome colore
	 * @return Stringa con codice colore
	 */
	public static String ottieniStringCodiceColoreDaStringa(String stringaConColore)
	//utilizzo questi codici per colorare le delimitazioni (contorno e lati angoli) delle carte stampare
	{
		if( stringaConColore.equals(ColoreCarta.ROSSO.toString()) ) {
			return "\u001B[31m";
		} else if( stringaConColore.equals(ColoreCarta.VERDE.toString()) ) {
			return "\u001B[32m";
		} else if( stringaConColore.equals(ColoreCarta.BLU.toString()) ) {
			return "\u001B[34m";
		} else if( stringaConColore.equals(ColoreCarta.VIOLA.toString()) ) {
			return "\u001B[35m";
		} else {
			return "";
			/*
			 * Questo viene eseguito quando si tenta di stampare una qualsiasi faccia di una carta iniziale,
			 * si dovrebbe usare il codice del colore di default, tuttavia questo dipende dall'estetica dell'IDE su cui viene eseguito il programma,
			 * dunque non stampando nulla si risolve il problema
			 */
		}
	}

}
