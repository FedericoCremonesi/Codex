package sviluppoGioco;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import carte.CartaObiettivo;
import carte.CartaOro;
import carte.CartaRisorsa;
import giocatori.Giocatore;
import giocatori.Pedina;
import tavoloEMazzi.Mazzo;
import tavoloEMazzi.Tavolo;

public class Partita {

	private static List<Giocatore> gruppoGiocatori;
	private static List<Pedina> coloriPedinaDisponibili;
	private Tavolo tavoloDiGioco;
	private int numeroRoundGiocati;
	private Boolean ultimoRound;
	
	
	public Partita() {
		this.gruppoGiocatori = new ArrayList<Giocatore>();
		this.coloriPedinaDisponibili = new ArrayList<Pedina>(List.of(Pedina.ROSSA,Pedina.GIALLA,Pedina.VERDE,Pedina.BLU));
		this.tavoloDiGioco = new Tavolo();
		this.numeroRoundGiocati = 0;
		this.ultimoRound = false;
	}
	
	
	/**
	 * Funzione principale in cui sono scritti tutti i passaggi della partita
	 */
	public void gioca() {
		System.out.println("Una nuova partita ha inizio");
		tavoloDiGioco.creaMazzi();
		//tavoloDiGioco.stampaMazzi(); //testing
		tavoloDiGioco.mescolaMazzi();
		//tavoloDiGioco.stampaMazzi(); //testing
		Giocatore.numeroGiocatori = richiediNumeroGiocatori();
		richiediNicknameGiocatori();
		mischiaOrdineGiocatori();
		creaListaColori();
		stampaListaColori();
		faiScegliereColore();
	}
	
	
	public static int richiediNumeroGiocatori() {
		int numeroGiocatori = 0; //dato un valore iniziale come esempio, non sarà ovviamente accettato
		do {
			try {
				System.out.println("\nQuanti giocatori vogliono giocare questa partita?");
				Scanner sc = new Scanner(System.in);
				numeroGiocatori = sc.nextInt();
				if(!(numeroGiocatori >= 2 && numeroGiocatori <= 4))
				{
					System.out.println("Numero di giocatori inserito non valido, inserire un numero di giocatori tra 2 e 4");
				}
			} catch(InputMismatchException e) { //gestisco il caso con una eccezione non controllata
				System.out.println("Inserimento non valido, inserire un numero");
			}
		}while (!(numeroGiocatori >= 2 && numeroGiocatori <= 4));
		
		return numeroGiocatori;
	}

	
	public static void richiediNicknameGiocatori() {
		for(int i=0; i<Giocatore.numeroGiocatori; i++)
		{
			boolean ok;
			do {
				ok = true;
				String nickname;
				System.out.println("\nInserire il nickname del giocatore "+(i+1)+"\n(Nota: l'ordine di inserimento non necessariamente sarà l'ordine di gioco)");
				Scanner sc = new Scanner(System.in);
				nickname = sc.nextLine();
				if(nickname.isBlank()) { //verifico che l'utente non inserisca spazio, tab o nulla
					System.out.println("Il nickname inserito risulta essere vuoto, inserire un nickname con almeno un carattere");
					ok = false;
				} else if(verificaNicknameGiaInserito(nickname)) { //verifico che il nickname inserito non sia già in uso
					System.out.println("Il nickname inserito appartiene già ad un giocatore, inserirne uno diverso");
					ok = false;
				} else {
					Giocatore giocatore = new Giocatore(nickname);
					gruppoGiocatori.add(giocatore);
					System.out.println("Giocatore creato e aggiunto alla lista");
				}
			}while(!ok);
		}
	}
	
	
	public static boolean verificaNicknameGiaInserito(String nicknameDaCercare) {
		for(Giocatore g : gruppoGiocatori)
		{
			if(nicknameDaCercare.equals(g.getNickname())) {
				return true;
			}
		}
		return false;
	}
	
	
	public static void mischiaOrdineGiocatori() {
		System.out.println("\nGenerando ordine di gioco casuale...");
		Collections.shuffle(gruppoGiocatori);
		System.out.println("L'ordine di gioco del giocatori è :");
		for(Giocatore g : gruppoGiocatori)
		{
			System.out.print("- ");
			g.stampaNickname();
		}
	}
	
	//elimina
	public static void creaListaColori() {
		System.out.println("\n\nCreando la lista con i colori delle pedine disponibili...");
		coloriPedinaDisponibili.add(Pedina.ROSSA);
		coloriPedinaDisponibili.add(Pedina.GIALLA);
		coloriPedinaDisponibili.add(Pedina.VERDE);
		coloriPedinaDisponibili.add(Pedina.BLU);
		System.out.println("Lista delle pedine creata !");
	}
	
	
	public static void stampaListaColori() {
		System.out.println("Le pedine disponibili al momento sono: ");
		for(Pedina colorePedina : coloriPedinaDisponibili)
		{
			System.out.print("- ");
			System.out.println(colorePedina.toString().toLowerCase());
		}
	}
	
	//correggi
	public static void faiScegliereColore() {
		for(Giocatore g : gruppoGiocatori)
		{
			boolean coloreSceltoEUnColore;
			boolean coloreSceltoEDisponibile;
			do {
				stampaListaColori();
				System.out.println("\n"+g+", quale pedina preferisci scegliere?");
				Scanner sc = new Scanner(System.in);
				String coloreScelto = sc.nextLine();
				coloreScelto = coloreScelto.toUpperCase(); //rende maiuscolo il colore inserito, in modo da considerare "Rosso","rosso","ROSSO" tutti come "ROSSO"
				
				//Controllo che la stringa inserita dall'utente sia il colore di una delle pedine del gioco
				coloreSceltoEUnColore = false;
				if ( coloreScelto.equals(Pedina.ROSSA) || coloreScelto.equals(Pedina.GIALLA) || coloreScelto.equals(Pedina.VERDE) || coloreScelto.equals(Pedina.BLU) ) {
					coloreSceltoEUnColore = true;
				}
				
				//Controllo che il colore scelto sia ancora disponibile
				coloreSceltoEDisponibile = false;
				for (Pedina p : coloriPedinaDisponibili)
				{
					if (coloreScelto.equals(p.toString()))
					{
						coloreSceltoEDisponibile = true;
					}
				}
				
				if(!coloreSceltoEUnColore) {
					System.out.println("La stringa scritta non risulta essere uno dei colori del gioco, inserire Rossa, Gialla, Verde o Blu");
				} else if(!coloreSceltoEDisponibile) {
					System.out.println("Il colore inserito non è più disponibile, è già stato selezionato da un altro giocatore");
				} else {
					g.setColorePedina(Pedina.valueOf(coloreScelto));
					coloriPedinaDisponibili.remove(Pedina.valueOf(coloreScelto));
				}
			} while (!coloreSceltoEUnColore && !coloreSceltoEDisponibile);
		}
	}
}
