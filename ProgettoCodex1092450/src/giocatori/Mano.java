package giocatori;

import java.util.ArrayList;
import java.util.List;

import carte.Carta;
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
			c.print("all"); //passo al metodo print il valore "all" per comunicare al metodo printFaccia() in FacciaFronte di stampare tutte le linee della carta
			n++;
			System.out.println();
		}
	}
	
	
	public CartaGiocabile getCartaDaMano(int indice) {
		return carteInMano.get(indice);
	}
	
	
	//Viene rimossa dalla mano una carta specifica passata in ingresso
	public void removeCarta(Carta cartaDaRimuovere)
	{
		carteInMano.remove(cartaDaRimuovere);
	}
}
