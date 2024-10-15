package giocatori;

import java.util.ArrayList;
import java.util.List;

import carte.Carta;
import carte.CartaGiocabile;

public class Mano {

	private List<CartaGiocabile> carteInMano;

	
	/**
	 * Metodo Costruttore della Mano
	 */
	public Mano() {
		this.carteInMano = new ArrayList<>();
	}
	
	
	/**
	 * Aggiunge una carta alle carte in mano
	 * @param cartaDaAggiungere
	 */
	public void aggiungiCartaAMano(CartaGiocabile cartaDaAggiungere) {
		carteInMano.add(cartaDaAggiungere);
	}
	
	
	/**
	 * Visualizza le tre carte presenti nella mano del giocatore
	 */
	public void stampaCarteInMano() {
		System.out.println("\nCarte in mano:");
		int n = 1;
		for(CartaGiocabile c : carteInMano) {
			System.out.print(n+":\n");
			c.print("all"); //passo al metodo print il valore "all" per comunicare al metodo printFaccia() in FacciaFronte di stampare tutte le linee della carta
			n++;
			System.out.println();
		}
	}
	
	
	/**
	 * Dato un indice in ingresso (tra 0 e 2), restituisce la carta in quella posizione nell'arraylist carteInMano
	 * @param indice
	 * @return Carta giocabile presente in quella posizione nell'arraylist carteInMano
	 */
	public CartaGiocabile getCartaDaMano(int indice) {
		return carteInMano.get(indice);
	}
	
	
	/**
	 * Rimuove dalla mano una carta specifica passata in ingresso
	 * @param cartaDaRimuovere
	 */
	public void removeCarta(Carta cartaDaRimuovere)
	{
		carteInMano.remove(cartaDaRimuovere);
	}
}
