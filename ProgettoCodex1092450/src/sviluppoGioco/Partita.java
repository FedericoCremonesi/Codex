package sviluppoGioco;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import carte.CartaObiettivo;
import carte.CartaOro;
import carte.CartaRisorsa;
import giocatori.Giocatore;
import tavoloEMazzi.Mazzo;
import tavoloEMazzi.Tavolo;

public class Partita {

	private List<Giocatore> gruppoGiocatori;
	private Tavolo tavoloDiGioco;
	private int numeroRoundGiocati;
	private Boolean ultimoRound;
	
	
	public Partita() {
		this.gruppoGiocatori = new ArrayList<Giocatore>();
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
		tavoloDiGioco.stampaMazzi(); //testing
		//...
		Giocatore.numeroGiocatori = richiediNumeroGiocatori();
	}
	
	
	public static int richiediNumeroGiocatori() {
		System.out.println("Quanti giocatori vogliono giocare questa partita?");
		Scanner sc = new Scanner(System.in);
		return sc.nextInt();
	}

}
