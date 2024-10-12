package tavoloEMazzi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import carte.Carta;
import carte.CartaGiocabile;

public class Mazzo <T extends Carta> {
	
	private List<T> carteNelMazzo;
	
	
	public List<T> getCarteNelMazzo() {
		return carteNelMazzo;
	}
	public void setCarteNelMazzo(List<T> carteNelMazzo) {
		this.carteNelMazzo = carteNelMazzo;
	}


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
			cartaDaStampare.print("all"); //passo al metodo print il valore "all" per comunicare al metodo printFaccia() in FacciaFronte di stampare tutte le linee della carta
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
	
	
	public Carta estraiCartaDaMazzo(int indiceCartaDaEstrarre, boolean stampa) throws IndexOutOfBoundsException {
		if(stampa)
			System.out.println("\nCarta estratta:");
		T cartaEstratta = carteNelMazzo.get(indiceCartaDaEstrarre); //per estrarre la prima carta serve indice 0 perchè voglio la prima carta (e le arraylist partono da 0)
		if(stampa)
			cartaEstratta.print("all"); //passo al metodo print il valore "all" per comunicare al metodo printFaccia() in FacciaFronte di stampare tutte le linee della carta
		carteNelMazzo.remove(indiceCartaDaEstrarre);
		return cartaEstratta;
	}
	
	
	public void visualizzaTreCartePerPesca() {
		if(carteNelMazzo.size()==0) {
			System.out.println("Carte finite!");
			return;
		} else {
			try {
				for(int k=0; k<3; k++) { //stampo le 3 carte in cima (cioè di indici 0, 1 e 2)
					boolean modificataFacciaDiGioco = false;
					if(k==2) {
						((CartaGiocabile) carteNelMazzo.get(k)).setFacciaDiGioco("RETRO"); //setto la faccia di gioco sul retro anche se in realtà non è vero, così facendo posso stampare solo la faccia posteriore col metodo print
						modificataFacciaDiGioco = true;
					}
					
					if(carteNelMazzo.size()>=(k+1)) { //così non stampo l'indice di una carta non esistente (quando queste stanno finendo)
						System.out.println(k+1+":"); //associata ad ogni carta stampo un numero (tra 1 e 3, dunque prendo la sua posizione nella lista e aggiungo 1
					}
					carteNelMazzo.get(k).print("all");
					
					if(modificataFacciaDiGioco) { //in caso abbia modificato la faccia di gioco (quando la carta non è stata effettivamente giocata), ri-setto questo attributo alla stringa iniziale
						((CartaGiocabile) carteNelMazzo.get(k)).setFacciaDiGioco("non ancora giocata");
					}
				}
				System.out.println("(di quest'ultima carta puoi conoscere solo il retro)\n");
			} catch (IndexOutOfBoundsException e) {
				//Questo è il caso in cui si tenta di stampare una o più carte che non sono più nel mazzo (perchè questo si sta esaurendo o è esaurito)
				System.out.println("\nNel mazzo non sono presenti ulteriori carte\n");
				return; //Con questo stampo al massimo una volta il messaggio, non due o tre
			}
		}
	}
	
	
	public boolean controllaSeMazzoFinito() {
		if(carteNelMazzo.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
}
