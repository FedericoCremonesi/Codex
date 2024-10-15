package carte;

import campoECaselle.Campo;
import carte.ColoreCarta;
import facceEAngoli.Oggetto;
import facceEAngoli.Risorsa;

public abstract class CartaObiettivo extends Carta {

	private int puntiPerSet;
	
	public final static int NUMERO_LINEE_FACCIA_CARTA=13;
	
	
	public int getPuntiPerSet() {
		return puntiPerSet;
	}
	public void setPuntiPerSet(int puntiPerSet) {
		this.puntiPerSet = puntiPerSet;
	}
	
	
	/**
	 * Metodo Costruttore
	 * @param id
	 * @param puntiPerSet
	 */
	public CartaObiettivo(int id, int puntiPerSet) {
		super(id);
		this.puntiPerSet = puntiPerSet;
	}
	
	
	@Override
	public abstract void print(String numeroLineaDaStampare);
	
	
	/**
	 * Controlla che l'obiettivo assegnato da questa carta sia verificato, analizzando il campo di gioco
	 * @param campo Campo di gioco da controllare
	 * @return Numero di volte che questo obiettivo è stato completato
	 */
	public abstract int controllaObiettivo(Campo campo);
}
