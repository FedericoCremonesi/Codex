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
}
