package carte;

import campoECaselle.Casella;
import facceEAngoli.Faccia;

public class CartaRisorsa extends CartaGiocabile {


	private ColoreCarta colore;
	
	private int punti;

	
	public CartaRisorsa(int id, String facciaDiGioco, Casella casellaDiGioco, Faccia fronte, Faccia retro,
			ColoreCarta colore, int punti) {
		super(id, facciaDiGioco, casellaDiGioco, fronte, retro);
		this.colore = colore;
		this.punti = punti;
	}
	
}
