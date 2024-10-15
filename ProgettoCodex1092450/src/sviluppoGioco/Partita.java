package sviluppoGioco;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MulticastSocket;
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
import carte.CartaObiettivoPerDisposizione;
import carte.CartaOro;
import carte.CartaRisorsa;
import carte.ColoreCarta;
import facceEAngoli.Risorsa;
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
	
	public final static int PUNTEGGIO_MINIMO_FINE_PARTITA = 20;
	public final static int NUMERO_CARATTERI_MASSIMI_ORIZZONTALI = 200;
	
	
	
	public static void stampaSchermataDiGioco() {
		System.out.println("\n\n\t\t   Benvenuti in questa partita di:\n");
		System.out.println();
		System.out.println( Risorsa.CODICE_COLORE_RISORSA_VEGETALE+"\t\t\t"+"       ___     "  +Risorsa.CODICE_COLORE_RISORSA_ANIMALE+"     _                    "    +"\n"+
							Risorsa.CODICE_COLORE_RISORSA_VEGETALE+"\t\t\t"+"      / __\\___ " +Risorsa.CODICE_COLORE_RISORSA_ANIMALE+"  __| | _____  __         "    +"\n"+
							Risorsa.CODICE_COLORE_RISORSA_VEGETALE+"\t\t\t"+"     / /  / _ \\" +Risorsa.CODICE_COLORE_RISORSA_ANIMALE+" / _` |/ _ \\ \\/ /         "  +"\n"+
							Risorsa.CODICE_COLORE_RISORSA_VEGETALE+"\t\t\t"+"    / /__| (_) "  +Risorsa.CODICE_COLORE_RISORSA_ANIMALE+"| (_| |  __/>  <          "    +"\n"+
							Risorsa.CODICE_COLORE_RISORSA_VEGETALE+"\t\t\t"+"    \\____/\\___/"+Risorsa.CODICE_COLORE_RISORSA_ANIMALE+" \\__,_|\\___/_/\\_\\         ");
		System.out.println();
		System.out.println( Risorsa.CODICE_COLORE_RISORSA_INSETTI+"\t\t\t"+"     __      _        "      +Risorsa.CODICE_COLORE_RISORSA_FUNGHI+"           _ _     "  +ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+"\n"+
							Risorsa.CODICE_COLORE_RISORSA_INSETTI+"\t\t\t"+"  /\\ \\ \\__ _| |_ _   _"   +Risorsa.CODICE_COLORE_RISORSA_FUNGHI+" _ __ __ _| (_)___ "  +ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+"\n"+
							Risorsa.CODICE_COLORE_RISORSA_INSETTI+"\t\t\t"+" /  \\/ / _` | __| | | "     +Risorsa.CODICE_COLORE_RISORSA_FUNGHI+"| '__/ _` | | / __|"  +ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+"\n"+
							Risorsa.CODICE_COLORE_RISORSA_INSETTI+"\t\t\t"+"/ /\\  / (_| | |_| |_| "     +Risorsa.CODICE_COLORE_RISORSA_FUNGHI+"| | | (_| | | \\__ \\"+ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+"\n"+
							Risorsa.CODICE_COLORE_RISORSA_INSETTI+"\t\t\t"+"\\_\\ \\/ \\__,_|\\__|\\__,_"+Risorsa.CODICE_COLORE_RISORSA_FUNGHI+"|_|  \\__,_|_|_|___/" +ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO);
		System.out.println(Risorsa.CODICE_RESET_COLORE);
	}
	
	
	public static void premiInvioPerContinuare() {
		System.out.println(ColoreTesto.CODICE_COLORE_NERO+"\n\t\t    Premere invio per continuare..."+ColoreTesto.CODICE_RESET_COLORE);
		Scanner sc = new Scanner(System.in);
		String inserimentoUtente = sc.nextLine();
		return;
	}
	
	
	public static void stampaRingraziamentiERealizzatoDa() {
		System.out.println("\n\n\n\t\t"+"Grazie per aver giocato!");
		System.out.println("\t\t"+"Questo programma è stato realizzato da:");
		System.out.println("\t\t\t"+"Cremonesi Federico");
		System.out.println("\t\t\t"+"Codice Matricola: 1092450");
		System.out.println("\t\t\t"+"Università di Bergamo - UNIBG");
		System.out.println("\t\t\t"+"Percorso di studi: Ingegneria Informatica");
		System.out.println("\t\t\t"+"Corso di: Programmazione ad Oggetti");
		System.exit(0);
	}
	
	
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
		stampaSchermataDiGioco();
		premiInvioPerContinuare();
		System.out.println("Una nuova partita ha inizio!");
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
		while(!ultimoRound) {
			giocaRound();
		}
		controllaObiettivi();
		dichiaraVincitore();
	}
	
	
	public static int richiediNumeroGiocatori() {
		int numeroGiocatori = 0; //dato un valore iniziale come esempio, non sarà ovviamente accettato
		do {
			try {
				System.out.println(ColoreTesto.CODICE_COLORE_AZZURRO+"\nQuanti giocatori vogliono giocare questa partita?"+ColoreTesto.CODICE_RESET_COLORE);
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
		System.out.println("\n(Nota:\tl'ordine di inserimento dei nickname non sarà\n\tnecessariamente l'ordine di gioco,\n\tquest'ultimo è determinato casualmente)");
		for(int i=0; i<Giocatore.numeroGiocatori; i++)
		{
			boolean ok;
			do {
				ok = true;
				String nickname;
				System.out.println(ColoreTesto.CODICE_COLORE_AZZURRO+"\nInserire il nickname del giocatore "+(i+1)+ColoreTesto.CODICE_RESET_COLORE);
				Scanner sc = new Scanner(System.in);
				nickname = sc.nextLine().trim(); //con questo metodo tolgo gli spazi all'inizio e alla fine del nickname inserito
				if(nickname.isBlank()) { //verifico che l'utente non inserisca spazio, tab o nulla
					System.out.println("Il nickname inserito risulta essere vuoto, inserire un nickname con almeno un carattere");
					ok = false;
				} else if(verificaNicknameGiaInserito(nickname)) { //verifico che il nickname inserito non sia già in uso
					System.out.println("Il nickname inserito appartiene già ad un giocatore, inserirne uno diverso");
					ok = false;
				} else {
					Giocatore giocatore = new Giocatore(nickname);
					gruppoGiocatori.add(giocatore);
					//System.out.println("Giocatore creato e aggiunto alla lista");
					//(Usato per testing)
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
		//System.out.println("\nGenerando ordine di gioco casuale...");
		//(Usato per testing)
		Collections.shuffle(gruppoGiocatori);
		System.out.println("\nL'ordine di gioco dei giocatori è:");
		
		int indicePerOrdineGioco=1;
		for(Giocatore g : gruppoGiocatori)
		{
			System.out.print(indicePerOrdineGioco+") ");
			g.stampaNickname();
			indicePerOrdineGioco++;
		}
	}
	
	
	public static void creaListaColori() {
		//System.out.println("\n\nCreando la lista con i colori delle pedine disponibili...");
		//(Usato per testing)
		coloriPedinaDisponibili = new HashSet<Pedina>();
		coloriPedinaDisponibili.add(Pedina.ROSSA);
		coloriPedinaDisponibili.add(Pedina.GIALLA);
		coloriPedinaDisponibili.add(Pedina.VERDE);
		coloriPedinaDisponibili.add(Pedina.BLU);
		//System.out.println("Lista delle pedine creata !");
		//(Usato per testing)
	}
	
	
	public static void stampaListaColori() {
		System.out.println("\nLe pedine disponibili al momento sono:");
		for(Pedina colorePedina : coloriPedinaDisponibili)
		{
			System.out.print("- ");
			System.out.println(Pedina.ottieniStringCodiceColoreDaStringa(colorePedina.toString())+ colorePedina.toString().toLowerCase() +Pedina.CODICE_RESET_COLORE);
		}
	}
	
	
	public static void faiScegliereColore() {
		for(Giocatore g : gruppoGiocatori)
		{
			boolean coloreSceltoEUnColore = false;
			boolean coloreSceltoEDisponibile = false;
			do {
				stampaListaColori();
				System.out.println(ColoreTesto.CODICE_COLORE_AZZURRO+"\n"+g.getNickname()+", quale pedina preferisci scegliere?"+ColoreTesto.CODICE_RESET_COLORE);
				Scanner sc = new Scanner(System.in);
				String coloreScelto = sc.nextLine().trim();
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
					System.out.println("Ok! Colore assegnato");
					coloriPedinaDisponibili.remove(Pedina.valueOf(coloreScelto));
				}
			} while ((!coloreSceltoEUnColore) || (!coloreSceltoEDisponibile));
		}
	}
	
	
	public void consegnaEGiocoCarteIniziali() {
		System.out.println();
		for(Giocatore g : gruppoGiocatori)
		{
			System.out.println("\nEstraendo una carta iniziale per: "+g.getNickname()+"...");
			CartaIniziale cartaInizialeEstratta = (CartaIniziale) tavoloDiGioco.getMazzoCarteIniziali().estraiCartaDaMazzo(0, true);
			
			String facciaDiGiocoScelta = g.scegliFacciaDiGioco(cartaInizialeEstratta, false); //passo false al metodo perchè in questo caso non si può tornare indietro
																 //(a differenza di quando si sceglie la faccia di gioco di una delle carte dalla mano, lì si può tornare alla scelta della carta da giocare)
			
			//Poichè il settaggio della faccia di gioco non avviene nel metodo "scegliFacciaDiGioco" di Giocatore (nel metodo stesso sono spiegate le motivazioni), devo farlo qui
			cartaInizialeEstratta.setFacciaDiGioco(facciaDiGiocoScelta);
			
			cartaInizialeEstratta.posizionaSuCampo((CasellaGiocabile) g.getCampo().getCasellaDaCoordinate(40,40));
		}
	}
	
	
	public void consegnaCarteRisorsaEOro() {
		System.out.println();
		for(Giocatore g : gruppoGiocatori)
		{
			//Carte risorsa:
			System.out.println("\nEstraendo due carte risorsa per: "+g.getNickname()+"...");
			for(int i=0; i<2; i++) {
				CartaRisorsa cartaRisorsaEstratta = (CartaRisorsa) tavoloDiGioco.getMazzoCarteRisorsa().estraiCartaDaMazzo(0, true); //passo 0 per estrarre la prima carta
				
				cartaRisorsaEstratta.aggiungiAMano(g.getMano());
			}
			
			//Carta oro:
			System.out.println("\nEstraendo una carta oro per: "+g.getNickname()+"...");
			CartaOro cartaOroEstratta = (CartaOro) tavoloDiGioco.getMazzoCarteOro().estraiCartaDaMazzo(0, true); //passo 0 per estrarre la prima carta
			
			cartaOroEstratta.aggiungiAMano(g.getMano());
			
			premiInvioPerContinuare();
		}
	}
	
	
	public void scopriObiettiviComuni() {
		System.out.println("\nEstraendo i due obiettivi comuni...");
		for(int i=0; i<2; i++) {
			tavoloDiGioco.aggiungiObiettivoComune( i, (CartaObiettivo)(tavoloDiGioco.getMazzoCarteObiettivo().estraiCartaDaMazzo(0, true)) );
		}
	}

	
	public void assegnaObiettivoSegreto() {
		for(Giocatore g : gruppoGiocatori)
		{
			System.out.println("\nEstraendo due carte obiettivo per: "+g.getNickname()+"...");
			System.out.println("A:");
			CartaObiettivo obiettivoEstratto1 = (CartaObiettivo)(tavoloDiGioco.getMazzoCarteObiettivo().estraiCartaDaMazzo(0, true));
			System.out.println("B:");
			CartaObiettivo obiettivoEstratto2 = (CartaObiettivo)(tavoloDiGioco.getMazzoCarteObiettivo().estraiCartaDaMazzo(0, true));
			
			String sceltaObiettivo;
			do {
				System.out.println(g.getNickname()+ColoreTesto.CODICE_COLORE_AZZURRO+" quale dei due obiettivi vuoi che sia il tuo obiettivo segreto?"+ColoreTesto.CODICE_RESET_COLORE);
				Scanner sc = new Scanner(System.in);
				sceltaObiettivo = sc.nextLine().toUpperCase().trim();
				
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
		System.out.println(ColoreTesto.CODICE_COLORE_NERO+"\n Inizia un nuovo round!");
		numeroRoundGiocati++;
		System.out.println(" Round numero: "+numeroRoundGiocati+ColoreTesto.CODICE_RESET_COLORE);
		for(Giocatore g : gruppoGiocatori)
		{
			giocaTurno(g);
		}
		System.out.println(ColoreTesto.CODICE_COLORE_NERO+"\n Fine del round"+ColoreTesto.CODICE_RESET_COLORE);
	}
	
	
	public void giocaTurno(Giocatore g) {
		g.stampaLineaColorata(); //Per dividere le schermate dei vari giocatori
		
		System.out.println("\n"+g.getNickname()+" è il tuo turno!");
		
		System.out.println("\nPunteggio attuale: "+g.getPunti());
		
		g.getCampo().stampaMatriceCampoAQuadratini();
		
		g.getCampo().contaRisorseEOggettiVisibili(40,40,true); //inizio a contare risorse e oggetti dalla carta iniziale, in posizione 40,40
		//il true passato al metodo serve per indicare che è la prima volta che questa funzione viene chiamata ricorsivamente
		g.getCampo().stampaConteggioRisorseEOggettiVisibili();
			
		g.getMano().stampaCarteInMano();
		
		String sceltaOpzione;
		do {
			System.out.println("\n"+g.getNickname()+ColoreTesto.CODICE_COLORE_AZZURRO+" cosa vuoi fare?"+ColoreTesto.CODICE_RESET_COLORE);
			System.out.println("\tA: Visualizzare le carte attorno ad una precisa carta sul campo");
			System.out.println("\tB: Visualizzare gli obiettivi comuni");
			System.out.println("\tC: Visualizzare il tuo obiettivo segreto");
			System.out.println("\tD: Proseguire con il turno");

			Scanner sc = new Scanner(System.in);
			sceltaOpzione = sc.nextLine().toUpperCase().trim();
			
			switch (sceltaOpzione) {
			case "A":
				System.out.println(ColoreTesto.CODICE_COLORE_AZZURRO+"Di quale carta vuoi visualizzare le adiacenti?"+ColoreTesto.CODICE_RESET_COLORE);
				int[] coordinateCasella = g.scegliCoordinateCasellaGiocabile();
				g.getCampo().stampa5x5AttornoACartaInCampo(coordinateCasella);
				break;
			case "B":
				tavoloDiGioco.stampaObiettiviComuni();
				break;
			case "C":
				System.out.println(g.getNickname()+", il tuo obiettivo segreto è:");
				g.getObiettivoSegreto().print("all");
				break;
			case "D":
				System.out.println(); //va semplicemente a capo (ed esce dal ciclo)
				break;
			default:
				System.out.println("Inserimento non valido, scrivere A, B, C oppure D");
			}
				
		} while (!( sceltaOpzione.equals("D") ));
		
		g.giocaCartaDaMano();
		premiInvioPerContinuare();
		g.pescaCartaDaMazzi(tavoloDiGioco.getMazzoCarteRisorsa(), tavoloDiGioco.getMazzoCarteOro());
		
		if(!ultimoRound) {
			ultimoRound = eseguiCheckFinePartita();
		}
		
		g.stampaLineaColorata();
	}

	
	public boolean eseguiCheckFinePartita() {
		//Controllo se almeno un giocatore ha raggiunto (o superato) i 20 punti
		boolean almeno20Punti = false;
		
		for(Giocatore g : gruppoGiocatori)
		{
			if(g.getPunti()>=PUNTEGGIO_MINIMO_FINE_PARTITA) {
				System.out.println("\n\tATTENZIONE:");
				System.out.println(g.getNickname()+" ha raggiunto "+g.getPunti()+" punti!"); //Questo viene stampato solo una volta, quando un giocatore del gruppo raggiunge per primo i 20 punti
				almeno20Punti = true;
			}
		}
		
		
		//Controllo se entrambi i mazzi di carte sono finiti
		boolean fineMazzoRisorsa = false;
		boolean fineMazzoOro = false;
		boolean fineEntrambiMazzi = false;;
		
		if(tavoloDiGioco.getMazzoCarteRisorsa().controllaSeMazzoFinito()) {
			System.out.println("\n\tLe carte nel mazzo risorsa sono finite");
			fineMazzoRisorsa = true;
		}
		if(tavoloDiGioco.getMazzoCarteOro().controllaSeMazzoFinito()) {
			System.out.println("\n\tLe carte nel mazzo oro sono finite");
			fineMazzoOro = true;
		}
		
		if(fineMazzoRisorsa && fineMazzoOro) {
			System.out.println("\n\tATTENZIONE:");
			System.out.println("Sono finite le carte in entrambi i mazzi (risorsa e oro)!");
			fineEntrambiMazzi = true;
		}
		
		
		//Annuncio che siamo verso la fine della partita nel caso sia verificata almeno una delle condizioni verificate in precedenza
		if(almeno20Punti || fineEntrambiMazzi) {
			System.out.println("Alla conclusione di questo round non se ne inizierà uno nuovo");
			return true;
		} else {
			return false;
		}
	}
	
	
	public void controllaObiettivi() {
		System.out.println("\n\n"+"La partita è finita, controllo degli obiettivi completati in corso...");
		
		//stampo tutti gli obiettivi (comuni e segreti) per ricordarli ai giocatori
		System.out.println("Ricordiamo gli obiettivi di tutti i giocatori:");
		System.out.println("\nObiettivi comuni:");
		tavoloDiGioco.getObiettivoComuneDatoIndice(0).print("all");
		tavoloDiGioco.getObiettivoComuneDatoIndice(1).print("all");
		for(Giocatore g : gruppoGiocatori)
		{
			System.out.println("\nObiettivo segreto di "+g.getNickname()+":");
			g.getObiettivoSegreto().print("all");
		}
		
		for(Giocatore g : gruppoGiocatori)
		{
			System.out.println("\n\nControllando gli obiettivi completati da: "+g.getNickname());
			
			g.getCampo().stampaMatriceCampoAQuadratini();
			g.getCampo().contaRisorseEOggettiVisibili(40,40,true);
			g.getCampo().stampaConteggioRisorseEOggettiVisibili();
			
			int puntiGiocatore = g.getPunti();
			int numeroObiettiviCompletati = g.getConteggioObiettiviCompletati();
			
			int numeroVolteCompletatoObiettivo;
			
			//il numero di volte che è stato completato l'obiettivo è il numero di "set" presenti sul campo che lo soddisfano
			numeroVolteCompletatoObiettivo = tavoloDiGioco.getObiettivoComuneDatoIndice(0).controllaObiettivo(g.getCampo()); //Controllo il primo obiettivo comune
			puntiGiocatore = puntiGiocatore + ( numeroVolteCompletatoObiettivo*tavoloDiGioco.getObiettivoComuneDatoIndice(0).getPuntiPerSet() );
			numeroObiettiviCompletati = numeroObiettiviCompletati + numeroVolteCompletatoObiettivo;
			
			numeroVolteCompletatoObiettivo = tavoloDiGioco.getObiettivoComuneDatoIndice(1).controllaObiettivo(g.getCampo()); //Controllo il secondo obiettivo comune
			puntiGiocatore = puntiGiocatore + ( numeroVolteCompletatoObiettivo*tavoloDiGioco.getObiettivoComuneDatoIndice(1).getPuntiPerSet() );
			numeroObiettiviCompletati = numeroObiettiviCompletati + numeroVolteCompletatoObiettivo;
			
			numeroVolteCompletatoObiettivo = g.getObiettivoSegreto().controllaObiettivo(g.getCampo()); //Controllo l'obiettivo segreto
			puntiGiocatore = puntiGiocatore + ( numeroVolteCompletatoObiettivo*g.getObiettivoSegreto().getPuntiPerSet() );
			numeroObiettiviCompletati = numeroObiettiviCompletati + numeroVolteCompletatoObiettivo;
					
			g.setPunti(puntiGiocatore);
			g.setConteggioObiettiviCompletati(numeroObiettiviCompletati);
			System.out.print(g.getNickname()+" hai completato in totale "+numeroObiettiviCompletati);
			if(numeroObiettiviCompletati==1) {
				System.out.print(" obiettivo");
			} else {
				System.out.print(" obiettivi");
			}
			System.out.println(" (anche contando uno stesso obiettivo completato più volte)");
			System.out.println("Punteggio finale: "+puntiGiocatore+" punti!");
		}
	}
	
	
	public void dichiaraVincitore() {
		System.out.println("\n\n\tClassifica finale dei giocatori:");
		Collections.sort(gruppoGiocatori, Collections.reverseOrder()); //Ordino l'arraylist di giocatori in ordine decrescente in base al punteggio
																	   //(Posso perchè ho reso i giocatori comparabili tra di loro con un'interfaccia implementata dalla classe di cui sono istanze)
																	   //Così facendo avrò il giocatore con il punteggio più alto nella prima posizione
		for(int k=0; k<gruppoGiocatori.size(); k++)
		{
			System.out.println("\t"+(k+1)+") "+gruppoGiocatori.get(k).getNickname()+" con "+gruppoGiocatori.get(k).getPunti()+" punti");
		}
		
		
		if(gruppoGiocatori.get(0).getPunti() == gruppoGiocatori.get(1).getPunti()) {
			System.out.println("\nI primi giocatori risultano aver raggiunto lo stesso punteggio");
			System.out.println("Il vincitore sarà quindi colui che, tra i giocatori con punteggio più alto, ha completato più obiettivi");
			int punteggioVincente = gruppoGiocatori.get(0).getPunti();
			for(int k=0; k<gruppoGiocatori.size(); k++)
			{
				if(gruppoGiocatori.get(k).getPunti() != punteggioVincente)
				{
					gruppoGiocatori.remove(k); //rimuovo i giocatori non "primi a pari merito" per punteggio
					k = k-1; //devo decrementare k perchè ho appena rimosso un elemento dalla lista, se non lo facessi si "salterebbe" un giocatore
				}
			}
			
			List<Giocatore> vincitori = new ArrayList<>(); //in questa lista tengo solo il giocatore (o giocatori) con il maggior numero di obiettivi completati (tra quelli con punteggio maggiore a pari merito)
			vincitori.add(gruppoGiocatori.get(0)); //considero inizialmente il primo giocatore come unico vincitore
			
			for(int k=1; k<gruppoGiocatori.size(); k++) { //Nota: parto a scorrere gli altri giocatori con k=1, non 0, perchè il primo giocatore è già nella lista "vincitori"
				//nei successivi 3 casi considerati confronto sempre con il primo giocatore nella lista "vincitori"
				if(gruppoGiocatori.get(k).getConteggioObiettiviCompletati() == vincitori.get(0).getConteggioObiettiviCompletati()) { //Caso =
					vincitori.add(gruppoGiocatori.get(k));
				} else if(gruppoGiocatori.get(k).getConteggioObiettiviCompletati() < vincitori.get(0).getConteggioObiettiviCompletati()) { //Caso <
					//Il giocatore in analisi sicuramente non sarà tra i vincitori perchè, nonostante abbia il punteggio più alto raggiunto (a pari merito con altri), non ha sicuramente completato il numero maggiore di pbiettivi
				} else if(gruppoGiocatori.get(k).getConteggioObiettiviCompletati() > vincitori.get(0).getConteggioObiettiviCompletati()) { //Caso >
					vincitori.clear(); //Svuoto la lista, il numero massimo di obiettivi completati è stato battuto
					vincitori.add(gruppoGiocatori.get(k));
				}
			}
			
			if(vincitori.size() == 1) {
				System.out.println("\nIl vincitore è: "+vincitori.get(0).getNickname()+" con "+vincitori.get(0).getConteggioObiettiviCompletati()+" obiettivi completati, complimenti!");
				stampaRingraziamentiERealizzatoDa(); //Termina il programma (caso 2 di 3 totali)
			} else {
				System.out.print("\nNon c'è un solo vincitore in questa partita, sono arrivati primi a pari merito: ");
				for(int k=0; k<vincitori.size(); k++) {
					System.out.print(vincitori.get(k).getNickname());
					if(k!=(vincitori.size()-1)) {
						System.out.print(" e ");
					}
				}
				System.out.println(", ognuno con "+vincitori.get(0).getConteggioObiettiviCompletati()+" obiettivi completati, complimenti!");
				stampaRingraziamentiERealizzatoDa(); //Termina il programma (caso 3 di 3 totali)
			}
		} else {
			System.out.println("\nIl vincitore è: "+gruppoGiocatori.get(0).getNickname()+", complimenti!");
			stampaRingraziamentiERealizzatoDa(); //Termina il programma (caso 1 di 3 totali)
		}
	}
}
