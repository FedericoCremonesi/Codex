package carte;

import campoECaselle.Casella;
import facceEAngoli.FacciaFronte;
import facceEAngoli.FacciaRetro;

public class CartaRisorsa extends CartaGiocabile implements Giocabile {


	private ColoreCarta colore;
	
	private int punti;

	
	public CartaRisorsa(int id, FacciaFronte fronte, FacciaRetro retro, int punti) {
		super(id, fronte, retro);
		this.punti = punti;
	}
	
	
	@Override
	public String toString() {
		return "C.Risorsa: "+super.toString()+" "+colore+" "+punti;
	}
	
	
	@Override
	public void print() {
		
	}
}
