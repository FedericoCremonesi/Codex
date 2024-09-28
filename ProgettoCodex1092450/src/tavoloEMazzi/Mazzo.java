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
			cartaDaStampare.print("all"); //passo al metodo print il valore "all" per comunicare al metodo printFaccia() in FacciaFronte di stampare tutte le righe della carta
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
	
	
	public Carta estraiPrimaCartaDaMazzo() {
		System.out.println("\nCarta estratta:");
		T cartaEstratta = carteNelMazzo.get(0); //indice 0 perchè voglio la prima carta (e le arraylist partono da 0)
		cartaEstratta.print("all"); //passo al metodo print il valore "all" per comunicare al metodo printFaccia() in FacciaFronte di stampare tutte le righe della carta
		carteNelMazzo.remove(0);
		return cartaEstratta;
	}
}
