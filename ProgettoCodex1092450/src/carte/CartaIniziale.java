package carte;

import campoECaselle.Campo;
import campoECaselle.Casella;
import facceEAngoli.FacciaFronte;
import facceEAngoli.FacciaRetro;
import giocatori.Giocatore;

public class CartaIniziale extends CartaGiocabile {


	/**
	 * Metodo Costruttore
	 * @param id
	 * @param fronte
	 * @param retro
	 */
	public CartaIniziale(int id, FacciaFronte fronte, FacciaRetro retro) {
		super(id, fronte, retro);
	}


	@Override
	public void print(String numeroLineaDaStampare) {
		String tipologiaCarta = "iniziale";
		if (!(super.getFacciaDiGioco().equals("RETRO")))
		{
			super.getFronte().printFaccia("Nessun colore", tipologiaCarta, numeroLineaDaStampare);
		}
		if (!(super.getFacciaDiGioco().equals("FRONTE")))
		{
			super.getRetro().printFaccia("Nessun colore", tipologiaCarta, numeroLineaDaStampare);
		}
		//usando le negazioni delle condizioni negli if è possibile stampare sia il fronte che il retro della carta (una sopra l'altro) nel caso in cui non sia ancora stata giocata
		
	}
	
}