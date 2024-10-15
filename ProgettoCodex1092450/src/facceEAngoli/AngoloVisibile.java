package facceEAngoli;

import facceEAngoli.Risorsa;
import facceEAngoli.Oggetto;

public class AngoloVisibile extends Angolo {

	private String contenuto; //può essere una Risorsa, un Oggetto o nulla, uso String
	private boolean coperto;
	
	
	public String getContenuto() {
		return contenuto;
	}
	public void setContenuto(String contenuto) {
		this.contenuto = contenuto;
	}

	public boolean isCoperto() {
		return coperto;
	}
	public void setCoperto(boolean coperto) {
		this.coperto = coperto;
	}

	
	/**
	 * Metodo Costruttore di un angolo visibile
	 * @param contenuto dell'angolo (una risorsa scritta come Stringa, un oggetto scritto come Stringa, o "vuoto")
	 */
	public AngoloVisibile(String contenuto) {
		this.contenuto = contenuto;
		this.coperto = false;
	}
	
	
	@Override
	public String toString()
	{
		if(coperto) {
			return " (coperto)";
		} else {
			return ottieniStringaConSpaziDaStringaSimbolo(contenuto);
		}
	}
	
	
	/**
	 * Presa in ingresso una Stringa con un simbolo (risorsa o oggetto), restituisce una Stringa con un numero preciso di spazi prima e/o dopo il nome del simbolo stesso, in modo da stampare in modo corretto le facce delle carte (giocabili e obiettivo)
	 *  Se la stringa con il simbolo passata in ingresso è vuota, restituisce un numero preciso di spazi (10), ovvero il numero totale di caratteri che si ottengono da questa funzione qualsiasi sia l'input ricevuto
	 * @param stringaSimbolo (risorsa come Stringa, oggetto come Stringa o "vuoto")
	 * @return La stessa String in ingresso, ma con degli spazi vuoti per arrivare a 10 caratteri (o 10 spazi vuoti se l'input è "vuoto")
	 */
	public static String ottieniStringaConSpaziDaStringaSimbolo(String stringaSimbolo) {
		if(stringaSimbolo.equals(Risorsa.FUNGHI.toString())) {
			return Risorsa.CODICE_COLORE_RISORSA_FUNGHI+"  FUNGHI  "+Risorsa.CODICE_RESET_COLORE; //uso i codici colore per colorare le scritte delle risorse con i corrispettivi colori
		} else if(stringaSimbolo.equals(Risorsa.VEGETALE.toString())) {
			return Risorsa.CODICE_COLORE_RISORSA_VEGETALE+" VEGETALE "+Risorsa.CODICE_RESET_COLORE;
		} else if(stringaSimbolo.equals(Risorsa.ANIMALE.toString())) {
			return Risorsa.CODICE_COLORE_RISORSA_ANIMALE+"  ANIMALE "+Risorsa.CODICE_RESET_COLORE;
		} else if(stringaSimbolo.equals(Risorsa.INSETTI.toString())) {
			return Risorsa.CODICE_COLORE_RISORSA_INSETTI+"  INSETTI "+Risorsa.CODICE_RESET_COLORE;
		} else if(stringaSimbolo.equals(Oggetto.PIUMA.toString())) {
			return "   PIUMA  ";
		} else if(stringaSimbolo.equals(Oggetto.INCHIOSTRO.toString())) {
			return "INCHIOSTRO";
		} else if(stringaSimbolo.equals(Oggetto.PERGAMENA.toString())) {
			return " PERGAMENA";
		} else {
			return "          ";
		}
	}
}
