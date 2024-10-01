package sviluppoGioco;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import campoECaselle.Campo;
import campoECaselle.Casella;
import campoECaselle.CasellaGiocabile;
import carte.CartaIniziale;
import carte.CartaObiettivo;
import carte.CartaOro;
import carte.CartaRisorsa;
import giocatori.Giocatore;
import giocatori.Pedina;
import tavoloEMazzi.Mazzo;
import tavoloEMazzi.Tavolo;

public class Partita {

	private static List<Giocatore> gruppoGiocatori;
	private static Set<Pedina> coloriPedinaDisponibili;
	private Tavolo tavoloDiGioco;
	private static int numeroRoundGiocati;
	private Boolean ultimoRound;
	
	
	public static int getNumeroRoundGiocati() {
		return numeroRoundGiocati;
	}
	
	
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
			//tavoloDiGioco.stampaMazzi(); //testing
		tavoloDiGioco.mescolaMazzi();
			//tavoloDiGioco.stampaMazzi(); //testing
		Giocatore.numeroGiocatori = richiediNumeroGiocatori();
		richiediNicknameGiocatori();
		mischiaOrdineGiocatori();
		creaListaColori();
		faiScegliereColore();
		consegnaEGiocoCarteIniziali();
		consegnaCarteRisorsaEOro();
		scopriObiettiviComuni();
		assegnaObiettivoSegreto();
		if(!ultimoRound) {
			giocaRound();
		} else {
			//controlla punti carte obiettivo ecc (TODO)
		}
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
			} catch(InputMismatchException e) { //gestisco il caso con una eccezione NON controllata
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
		System.out.println("L'ordine di gioco dei giocatori è:");
		
		int indicePerOrdineGioco=1;
		for(Giocatore g : gruppoGiocatori)
		{
			System.out.print(indicePerOrdineGioco+"- ");
			g.stampaNickname();
			indicePerOrdineGioco++;
		}
	}
	
	
	public static void creaListaColori() {
		System.out.println("\n\nCreando la lista con i colori delle pedine disponibili...");
		coloriPedinaDisponibili = new HashSet<Pedina>();
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
	
	
	public static void faiScegliereColore() {
		for(Giocatore g : gruppoGiocatori)
		{
			boolean coloreSceltoEUnColore = false;
			boolean coloreSceltoEDisponibile = false;
			do {
				stampaListaColori();
				System.out.println("\n"+g.getNickname()+", quale pedina preferisci scegliere?");
				Scanner sc = new Scanner(System.in);
				String coloreScelto = sc.nextLine();
				coloreScelto = coloreScelto.toUpperCase(); //rende maiuscolo il colore inserito, in modo da considerare "Rosso","rosso","ROSSO" tutti come "ROSSO"
				
				//Controllo che la stringa inserita dall'utente sia il colore di una delle pedine del gioco
				coloreSceltoEUnColore = false;
				if ( coloreScelto.equals(Pedina.ROSSA.toString()) || coloreScelto.equals(Pedina.GIALLA.toString()) || coloreScelto.equals(Pedina.VERDE.toString()) || coloreScelto.equals(Pedina.BLU.toString()) ) {
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
					g.assegnaColorePedina(Pedina.valueOf(coloreScelto));
					coloriPedinaDisponibili.remove(Pedina.valueOf(coloreScelto));
				}
			} while ((!coloreSceltoEUnColore) || (!coloreSceltoEDisponibile));
		}
	}
	
	
	public void consegnaEGiocoCarteIniziali() {
		for(Giocatore g : gruppoGiocatori)
		{
			System.out.println("\nEstraendo una carta iniziale per: "+g.getNickname()+"...");
			CartaIniziale cartaInizialeEstratta = (CartaIniziale) tavoloDiGioco.getMazzoCarteIniziali().estraiPrimaCartaDaMazzo();
			
			g.scegliFacciaDiGioco(cartaInizialeEstratta);
		}
	}
	
	
	public void consegnaCarteRisorsaEOro() {
		for(Giocatore g : gruppoGiocatori)
		{
			//Carte risorsa:
			System.out.println("\nEstraendo due carte risorsa per: "+g.getNickname()+"...");
			for(int i=0; i<2; i++) {
				CartaRisorsa cartaRisorsaEstratta = (CartaRisorsa) tavoloDiGioco.getMazzoCarteRisorsa().estraiPrimaCartaDaMazzo();
				
				cartaRisorsaEstratta.aggiungiAMano(g.getMano());
			}
			
			//Carta oro:
			System.out.println("\nEstraendo una carta oro per: "+g.getNickname()+"...");
			CartaOro cartaOroEstratta = (CartaOro) tavoloDiGioco.getMazzoCarteOro().estraiPrimaCartaDaMazzo();
			
			cartaOroEstratta.aggiungiAMano(g.getMano());
			
		}
	}
	
	
	public void scopriObiettiviComuni() {
		System.out.println("\nEstraendo i due obiettivi comuni...");
		for(int i=0; i<2; i++) {
			tavoloDiGioco.aggiungiObiettivoComune( i, (CartaObiettivo)(tavoloDiGioco.getMazzoCarteObiettivo().estraiPrimaCartaDaMazzo()) );
		}
	}

	
	public void assegnaObiettivoSegreto() {
		for(Giocatore g : gruppoGiocatori)
		{
			System.out.println("\nEstraendo due carte obiettivo per: "+g.getNickname()+"...");
			System.out.print("A:");
			CartaObiettivo obiettivoEstratto1 = (CartaObiettivo)(tavoloDiGioco.getMazzoCarteObiettivo().estraiPrimaCartaDaMazzo());
			System.out.print("B:");
			CartaObiettivo obiettivoEstratto2 = (CartaObiettivo)(tavoloDiGioco.getMazzoCarteObiettivo().estraiPrimaCartaDaMazzo());
			
			String sceltaObiettivo;
			do {
				System.out.println(g.getNickname()+" quale dei due obiettivi vuoi che sia il tuo obiettivo segreto?");
				Scanner sc = new Scanner(System.in);
				sceltaObiettivo = sc.nextLine().toUpperCase();
				
				switch (sceltaObiettivo) {
				case "A":
					g.setObiettivoSegreto(obiettivoEstratto1);
					tavoloDiGioco.getMazzoCarteObiettivo().aggiungiCartaAMazzo(obiettivoEstratto2);
					break;
				case "B":
					g.setObiettivoSegreto(obiettivoEstratto2);
					tavoloDiGioco.getMazzoCarteObiettivo().aggiungiCartaAMazzo(obiettivoEstratto1);
					break;
				default:
					System.out.println("Inserimento non valido, scrivere A oppure B");
				}
			} while (!( (sceltaObiettivo.equals("A") || sceltaObiettivo.equals("B")) ));
		}
	}
	
	
	public void giocaRound() {
		System.out.println("\nInizia un nuovo round!");
		numeroRoundGiocati++;
		for(Giocatore g : gruppoGiocatori)
		{
			giocaTurno(g);
		}
	}
	
	
	public void giocaTurno(Giocatore g) {
		System.out.println("\n"+g.getNickname()+" è il tuo turno!");
		
		g.getCampo().stampaMatriceCampoAQuadratini();
		
		g.getCampo().contaRisorseEOggettiVisibili(40,40,true,true); //inizio a contare risorse e oggetti dalla carta iniziale, in posizione 40,40
		/*
		 * il primo true passato al metodo serve per indicare che è la prima volta che questa funzione viene chiamata ricorsivamente
		 * il secondo "true" passato al metodo serve per comunicargli di stampare il conteggio delle risorse e degli oggetti
		 */
			
		g.getMano().stampaCarteInMano();
		
		String sceltaOpzione;
		do {
			System.out.println("\n"+g.getNickname()+" cosa vuoi fare?");
			System.out.println("\tA: Visualizzare le carte attorno ad una precisa carta sul campo");
			System.out.println("\tB: Visualizzare gli obiettivi comuni");
			System.out.println("\tC: Visualizzare il tuo obiettivo segreto");
			System.out.println("\tD: Proseguire con il turno");

			Scanner sc = new Scanner(System.in);
			sceltaOpzione = sc.nextLine().toUpperCase();
			
			switch (sceltaOpzione) {
			case "A":
				int[] coordinateCasella = chiediCoordinateCasellaGiocabile(g);
				g.getCampo().stampa3x3AttornoACartaInCampo(coordinateCasella);
				break;
			case "B":
				tavoloDiGioco.stampaObiettiviComuni();
				break;
			case "C":
				System.out.println(g.getNickname()+", il tuo obiettivo segreto è:\n"+g.getObiettivoSegreto());
				break;
			case "D":
				System.out.println(); //va semplicemente a capo
				break;
			default:
				System.out.println("Inserimento non valido, scrivere A, B, C oppure D");
			}
				
		} while (!( sceltaOpzione.equals("D") ));
		
		g.giocaCartaDaMano();
	}
	
	
	public int[] chiediCoordinateCasellaGiocabile(Giocatore g) {
		int[] coordinate = {0,0}; //inseriti dei qualsiasi valori di partenza, verranno sovrascritti da ciò che inserirà l'utente
		boolean ok;
		
		do {
			
			ok = false;
			try {
				System.out.println("Di quale carta vuoi visualizzare le adiacenti?");
				
				Scanner sc = new Scanner(System.in); //va qui, altrimenti in caso di errore il ciclo viene eseguito all'infinito
				
				System.out.print("Coordinata righe: ");
				coordinate[0] = sc.nextInt()+40; //L'utente inserirà una coordinata tra -40 e +40 perchè sono quelle stampate ai lati della matrice, in realtà gli indici degli array vanno da 0 a 80, dunque sommo 40
				System.out.print("Coordinata colonne: ");
				coordinate[1] = sc.nextInt()+40;
				
				if(g.getCampo().getCasellaDaCoordinate(coordinate[0],coordinate[1]) instanceof CasellaGiocabile) {
					ok = true;
				} else {
					System.out.println("\nLe coordinate inserite risultano indicare una casella non giocabile");
				}
			} catch (InputMismatchException e) { //gestisco il caso con una eccezione NON controllata
				System.out.println("\nInserimento non valido, inserire due numeri");
			} catch (ArrayIndexOutOfBoundsException e) { //gestisco il caso con una eccezione NON controllata
				System.out.println("\nInserimento non valido, almeno uno degli indici inseriti risulta eccedere la dimensione dell'array");
			}
			
		} while (!ok);
		
		return coordinate;
	}
}
