package carte;

import campoECaselle.Casella;
import facceEAngoli.Faccia;

public class CartaRisorsa extends CartaGiocabile {


	private ColoreCarta colore;
	
	private int punti;

	
	public CartaRisorsa(int id, Faccia fronte, Faccia retro, int punti) {
		super(id, fronte, retro);
		this.punti = punti;
	}
	
	
	@Override
	public String toString() {
		return "C.Risorsa: "+super.toString()+" "+colore+" "+punti;
	}
}
