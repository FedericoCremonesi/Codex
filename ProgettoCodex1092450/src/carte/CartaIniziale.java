package carte;

import campoECaselle.Casella;
import facceEAngoli.Faccia;

public class CartaIniziale extends CartaGiocabile{


	public CartaIniziale(int id, Faccia fronte, Faccia retro) {
		super(id, fronte, retro);
	}
	
	
	@Override
	public String toString() {
		return "C.Iniziale: "+super.toString();
	}
}
