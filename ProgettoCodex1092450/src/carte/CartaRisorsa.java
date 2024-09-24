package carte;

import campoECaselle.Casella;
import facceEAngoli.FacciaFronte;
import facceEAngoli.FacciaRetro;
import facceEAngoli.Risorsa;

public class CartaRisorsa extends CartaGiocabile {


	private ColoreCarta colore;
	
	private int punti;

	
	public CartaRisorsa(int id, FacciaFronte fronte, FacciaRetro retro, int punti) {
		super(id, fronte, retro);
		this.punti = punti;
		if(retro.getRisorsaRetroCentrale().toString().toUpperCase().equals(Risorsa.FUNGHI.toString())) {
			this.colore = ColoreCarta.ROSSO;
		} else if(retro.getRisorsaRetroCentrale().toString().toUpperCase().equals(Risorsa.VEGETALE.toString())) {
			this.colore = ColoreCarta.VERDE;
		} else if(retro.getRisorsaRetroCentrale().toString().toUpperCase().equals(Risorsa.ANIMALE.toString())) {
			this.colore = ColoreCarta.BLU;
		} else if(retro.getRisorsaRetroCentrale().toString().toUpperCase().equals(Risorsa.INSETTI.toString())) {
			this.colore = ColoreCarta.VIOLA;
		}
	}
	
	
	@Override
	public String toString() {
		return "C.Risorsa: "+super.toString()+" "+colore+" "+punti;
	}
	
	
	@Override
	public void print() {
		String tipologiaCarta = "risorsa";
		if (!(super.getFacciaDiGioco().toLowerCase().equals("retro")))
		{
			super.getFronte().printFaccia(colore.toString(), tipologiaCarta);
		}
		if (!(super.getFacciaDiGioco().toLowerCase().equals("fronte")))
		{
			super.getRetro().printFaccia(colore.toString(), tipologiaCarta);
		}
		//usando le negazioni delle condizioni negli if è possibile stampare sia il fronte che il retro della carta nel caso in cui non sia ancora stata giocata
		System.out.println("Punti dati dalla carta: "+punti);
	}
}
