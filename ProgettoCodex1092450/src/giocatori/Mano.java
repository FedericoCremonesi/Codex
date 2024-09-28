package giocatori;

import java.util.ArrayList;
import java.util.List;

import carte.CartaGiocabile;

public class Mano {

	private List<CartaGiocabile> carteInMano;

	
	public Mano() {
		this.carteInMano = new ArrayList<>();
	}
	
	
	public void aggiungiCartaAMano(CartaGiocabile cartaDaAggiungere) {
		carteInMano.add(cartaDaAggiungere);
	}
	
	
	public void stampaCarteInMano() {
		System.out.println("\nCarte in mano:");
		int n = 1;
		for(CartaGiocabile c : carteInMano) {
			System.out.print(n+":\n");
			c.print("all"); //passo al metodo print il valore "all" per comunicare al metodo printFaccia() in FacciaFronte di stampare tutte le righe della carta
			n++;
			System.out.println();
		}
	}
}
