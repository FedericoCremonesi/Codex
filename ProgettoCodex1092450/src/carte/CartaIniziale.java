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
		String tipologiaCarta = "iniziale";
		if (!(super.getFacciaDiGioco().toLowerCase().equals("retro")))
		{
			super.getFronte().printFaccia("Nessun colore", tipologiaCarta);
		}
		if (!(super.getFacciaDiGioco().toLowerCase().equals("fronte")))
		{
			super.getRetro().printFaccia("Nessun colore", tipologiaCarta);
		}
		
		//usando le negazioni delle condizioni negli if è possibile stampare sia il fronte che il retro della carta nel caso in cui non sia ancora stata giocata
	}
}
