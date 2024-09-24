package tavoloEMazzi;

import java.util.ArrayList;
import java.util.Collections;
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

	
	public void stampaMazzo()
	{
		for(Carta cartaDaStampare : carteNelMazzo)
		{
			cartaDaStampare.print();
		}
	}
	
	
	public void mescolaMazzo()
	{
		Collections.shuffle(carteNelMazzo);
	}
	
	
	//Overloading
	//Qui viene rimossa dal mazzo una carta specifica passata in ingresso
	public void removeCarta(Carta cartaDaRimuovere)
	{
		carteNelMazzo.remove(cartaDaRimuovere);
	}
	
	//Overloading
	//Qui viene rimossa dal mazzo la prima carta del mazzo stesso
	public void removeCarta(int i)
	{
		carteNelMazzo.remove(i);
	}
	
	public T getCarta(int i)
	{
		return carteNelMazzo.get(i);
	}
}
