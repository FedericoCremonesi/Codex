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
	
	public int getPunti() {
		return punti;
	}
	public void setPunti(int punti) {
		this.punti = punti;
	}

	
	/**
	 * Metodo Costruttore
	 * @param id
	 * @param fronte
	 * @param retro
	 * @param punti
	 */
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
	public void print(String numeroLineaDaStampare) {
		String tipologiaCarta = "risorsa";
		if(super.getFacciaDiGioco().equals("FRONTE"))
		{
			super.getFronte().printFaccia(colore.toString(), tipologiaCarta, numeroLineaDaStampare);
		}
		if(super.getFacciaDiGioco().equals("RETRO"))
		{
			super.getRetro().printFaccia(colore.toString(), tipologiaCarta, numeroLineaDaStampare);
		}
		//Così stampo le due faccie affiancate (nel caso non sia ancora stata giocata la carta)
		if (super.getFacciaDiGioco().equals("non ancora giocata")) {
			for(Integer k=0; k<FacciaFronte.NUMERO_LINEE_FACCIA; k++) {
				//per poter usare il metodo toString, devo usare il tipo Integer (non int, che è un tipo primitivo e non ha metodi)
				super.getFronte().printFaccia(colore.toString(), tipologiaCarta, k.toString());
				System.out.print(" \t");
				super.getRetro().printFaccia(colore.toString(), tipologiaCarta, k.toString());
				System.out.print("\n");
			}
		}
		
		if (super.getFacciaDiGioco().equals("non ancora giocata")) {
			System.out.println(ColoreCarta.CODICE_COLORE_CARTA_RISORSA+" ➤ Punti dati dalla carta: "+ColoreCarta.CODICE_RESET_COLORE+punti);
		}
		//se la carta non è ancora stata giocata sul campo, stampo anche le informazioni aggiuntive relative ad essa
	}
	
	
	/**
	 * Metodo per assegnare punti ad un giocatore in caso la carta risorsa ne dia (se giocata sulla faccia frontale)
	 * @param giocatore
	 */
	public void assegnaPunti(Giocatore giocatore) {
		if(super.getFacciaDiGioco().equals("FRONTE")) {
			int puntiIniziali = giocatore.getPunti();
			
			if(this.punti == 0) {
				System.out.println("\tLa giocata di questa carta non fa guadagnare punti");
			} else {
				giocatore.setPunti(puntiIniziali+this.punti);
				System.out.print("\tHai guadagnato "+this.punti);
				if(this.punti == 1) {
					System.out.println(" punto");
				} else {
					System.out.println(" punti");
				}
			}
		}
	}
	
	
	
}
