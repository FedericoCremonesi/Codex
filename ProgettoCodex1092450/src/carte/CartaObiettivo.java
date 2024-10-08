package carte;

import carte.ColoreCarta;
import facceEAngoli.Oggetto;
import facceEAngoli.Risorsa;

public abstract class CartaObiettivo extends Carta {

	private int puntiPerSet;
	public final static int NUMERO_LINEE_FACCIA_CARTA=13;
	
	
	public CartaObiettivo(int id, int puntiPerSet) {
		super(id);
		this.puntiPerSet = puntiPerSet;
	}
	

	
	@Override
	public String toString() {
		return "C.Obiettivo: "+super.toString();
	}
	
	
	@Override
	public abstract void print(String numeroLineaDaStampare);
}
