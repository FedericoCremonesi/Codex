package carte;

import campoECaselle.Campo;
import campoECaselle.Casella;
import facceEAngoli.FacciaFronte;
import facceEAngoli.FacciaRetro;
import facceEAngoli.Risorsa;
import giocatori.Giocatore;

public class CartaRisorsa extends CartaGiocabile {


	private ColoreCarta colore;
	
	private int punti;
	
	
	public ColoreCarta getColore() {
		return colore;
	}
	public void setColore(ColoreCarta colore) {
		this.colore = colore;
	}

	
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
	public void print(String numeroLineaDaStampare) {
		String tipologiaCarta = "risorsa";
		if (!(super.getFacciaDiGioco().equals("RETRO")))
		{
			super.getFronte().printFaccia(colore.toString(), tipologiaCarta, numeroLineaDaStampare);
		}
		if (!(super.getFacciaDiGioco().equals("FRONTE")))
		{
			super.getRetro().printFaccia(colore.toString(), tipologiaCarta, numeroLineaDaStampare);
		}
		//usando le negazioni delle condizioni negli if è possibile stampare sia il fronte che il retro della carta nel caso in cui non sia ancora stata giocata
		
		
		if (super.getFacciaDiGioco().equals("non ancora giocata")) {
			System.out.println(ColoreCarta.CODICE_COLORE_CARTA_RISORSA+" ➤ Punti dati dalla carta: "+ColoreCarta.CODICE_RESET_COLORE+punti);
		}
		//se la carta non è ancora stata giocata sul campo, stampo anche le informazioni aggiuntive relative ad essa
	}
	
	
	public void assegnaPunti(Giocatore giocatore) {
		if(super.getFacciaDiGioco().equals("FRONTE")) {
			int puntiIniziali = giocatore.getPunti();
			
			if(this.punti == 0) {
				System.out.println("La giocata di questa carta non fa guadagnare punti");
			} else {
				giocatore.setPunti(puntiIniziali+this.punti);
				System.out.print("Hai guadagnato "+this.punti);
				if(this.punti == 1) {
					System.out.println(" punto");
				} else {
					System.out.println(" punti");
				}
			}
		}
	}
	
	
	
}
