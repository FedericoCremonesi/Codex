package tavoloEMazzi;

import java.util.List;

import carte.Carta;

public class Mazzo <T extends Carta> {
	
	private List<T> carteNelMazzo;
	
	
	public Mazzo(List<T> carteNelMazzo) {
		this.carteNelMazzo = carteNelMazzo;
	}
	
	
	public void aggiungiCartaAMazzo(Carta cartaDaAggiungere)
	{
		carteNelMazzo.add((T) cartaDaAggiungere);
	}

}
