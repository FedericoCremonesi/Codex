package campoECaselle;

import carte.Carta;
import carte.CartaGiocabile;
import carte.CartaIniziale;
import carte.CartaOro;
import carte.CartaRisorsa;
import carte.ColoreCarta;

public class CasellaGiocabile extends Casella {

	
	private CartaGiocabile cartaContenuta;
	
	//Questa costante (array di stringhe) serve per stampare una casella giocabile nel caso non contenga ancora una carta giocabile
	private static final String[] INSIEME_LINEE_CASELLA_GIOCABILE_VUOTA =  {"▛---                              ---▜",
																			"|                                    |",
																			"|                                    |",
																			"                                      ",
																			"                                      ",
																			"               Casella                ",
																			"                                      ",
																			"                Vuota                 ",
																			"                                      ",
																			"                                      ",
																			"|                                    |",
																			"|                                    |",
																			"▙---                              ---▟"};
	
	
	public CartaGiocabile getCartaContenuta() {
		return cartaContenuta;
	}
	public void setCartaContenuta(CartaGiocabile cartaContenuta) {
		this.cartaContenuta = cartaContenuta;
	}
	
	
	public CasellaGiocabile(int x, int y) {
		super(x, y);
	}
	
	
	public boolean isEmpty()
	{
		if(cartaContenuta == null)
		{
			return true;
		} else {
			return false;
		}
	}
	
	
	//Questo metodo serve per stampare il campo come tabella di quadratini
	@Override
	public void stampaCasellaComeQuadratino() {
		if(cartaContenuta instanceof CartaIniziale) {
			System.out.print(ColoreCarta.CODICE_COLORE_CARTA_RISORSA+"■■■"+ColoreCarta.CODICE_RESET_COLORE);
					//uso impropriamente la costante con il codice colore nero per le carte risorsa per colorare la carta iniziale quando stampata nella matrice
			return;
		} else {
			String coloreCarta = null;
			if(cartaContenuta instanceof CartaRisorsa) {
				coloreCarta = ((CartaRisorsa) cartaContenuta).getColore().toString();
			} else if(cartaContenuta instanceof CartaOro) {
				coloreCarta = ((CartaOro) cartaContenuta).getColore().toString();
			} else if(isEmpty()){
				coloreCarta = "";
			}
			
			String codiceColoreDaColoreCarta = ColoreCarta.ottieniStringCodiceColoreDaStringa(coloreCarta);
			
			System.out.print(codiceColoreDaColoreCarta+"■■■"+ColoreCarta.CODICE_RESET_COLORE);
			return;
		}
	}
	
	
	//Questo metodo serve per stampare un'area 3x3 di caselle "ingrandite"
	@Override
	public void stampaLineaCasellaIngrandita(Integer numeroLineaDaStampare) {
		if(isEmpty()) {
			System.out.print(INSIEME_LINEE_CASELLA_GIOCABILE_VUOTA[numeroLineaDaStampare]); //NON println, altrimenti risulta sbagliata la stampa delle 3x3 caselle ingrandite
		} else {
			boolean stoStampandoALinee = true;
			cartaContenuta.print(numeroLineaDaStampare.toString()); //chiamo funzione print che chiama funzione printFaccia (a cui passa numeroLineaDaStampare)
			/*
			 * Ho dovuto dichiarare il numero della linea da stampare come Integer ("classe wrapper" di int) per poter utilizzare il metodo toString(),
			 * ciò è necessario perchè il metodo print riceve in ingresso una stringa, non un intero
			 */
		}
	}
	
}
