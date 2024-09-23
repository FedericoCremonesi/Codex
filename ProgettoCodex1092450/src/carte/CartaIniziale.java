package carte;

import campoECaselle.Casella;
import facceEAngoli.FacciaFronte;
import facceEAngoli.FacciaRetro;

public class CartaIniziale extends CartaGiocabile implements Giocabile {


	public CartaIniziale(int id, FacciaFronte fronte, FacciaRetro retro) {
		super(id, fronte, retro);
	}
	
	
	@Override
	public String toString() {
		return "C.Iniziale: "+super.toString();
	}


	@Override
	public void print() {
		
	}
}
