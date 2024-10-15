package tavoloEMazzi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import carte.Carta;
import carte.CartaGiocabile;

public class Mazzo <T extends Carta> { //utilizzo il tipo generico T, che estende Carta (si potranno creare quindi mazzi di carte risorsa/oro/iniziali/obiettivo)
	
	private List<T> carteNelMazzo;
	
	
	public List<T> getCarteNelMazzo() {
		return carteNelMazzo;
	}
	public void setCarteNelMazzo(List<T> carteNelMazzo) {
		this.carteNelMazzo = carteNelMazzo;
	}


	/**
	 * Metodo Costruttore
	 * @param carteNelMazzo
	 */
	public Mazzo(List<T> carteNelMazzo) {
		this.carteNelMazzo = carteNelMazzo;
	}
	
	
	/**
	 * Aggiunge alle carte nel mazzo una carta passata in ingresso
	 * @param cartaDaAggiungere
	 */
	public void aggiungiCartaAMazzo(Carta cartaDaAggiungere)
	{
		carteNelMazzo.add((T) cartaDaAggiungere);
	}

	
	/**
	 * Per ogni carta nel mazzo, stampa tutta la carta (fronte, retro e informazioni aggiuntive, dato che non è ancora stata giocata)
	 */
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
	/**
	 * Rimuove dal mazzo una carta specifica passata in ingresso
	 * @param cartaDaRimuovere
	 */
	public void removeCarta(Carta cartaDaRimuovere)
	{
		carteNelMazzo.remove(cartaDaRimuovere);
	}
	
	//Overloading
	/**
	 * Rimuove dal mazzo la carta associata all'indice i (cioè che occupa quella posizione nell'arraylist carteNelMazzo)
	 * @param i
	 */
	public void removeCarta(int i)
	{
		carteNelMazzo.remove(i);
	}
	
	
	/**
	 * Restituisce la carta associata all'indice i (cioè che occupa quella posizione nell'arraylist carteNelMazzo)
	 * @param i
	 * @return
	 */
	public T getCarta(int i)
	{
		return carteNelMazzo.get(i);
	}
	
	
	/**
	 * Restituisce la carta associata all'indice i tra le carte nel mazzo, rimuovendola da quest'ultimo
	 * @param indiceCartaDaEstrarre
	 * @param stampa (per stampare a schermo "Carta estratta" prima della carta stessa
	 * @return La carta estratta dal mazzo (che precedentemente si trovava alla posizione i)
	 * @throws IndexOutOfBoundsException
	 */
	public Carta estraiCartaDaMazzo(int indiceCartaDaEstrarre, boolean stampa) throws IndexOutOfBoundsException { //Lancia l'eccezione al metodo chiamante
		if(stampa) {
			//System.out.println("\nCarta estratta:");
			//(Usato per testing)
		}
		T cartaEstratta = carteNelMazzo.get(indiceCartaDaEstrarre); //per estrarre la prima carta serve indice 0 perchè voglio la prima carta (e le arraylist partono da 0)
		if(stampa)
			cartaEstratta.print("all"); //passo al metodo print il valore "all" per comunicare al metodo printFaccia() in FacciaFronte di stampare tutte le linee della carta
		carteNelMazzo.remove(indiceCartaDaEstrarre);
		return cartaEstratta;
	}
	
	
	/**
	 * Stampa a schermo le prime due carte del mazzo (fronte e retro), e solo il retro della carta appena successiva
	 */
	public void visualizzaTreCartePerPesca() {
		if(carteNelMazzo.size()==0) {
			System.out.println("Carte finite!\n");
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
