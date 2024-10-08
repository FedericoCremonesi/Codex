package giocatori;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import campoECaselle.Campo;
import campoECaselle.CasellaGiocabile;
import carte.Carta;
import carte.CartaGiocabile;
import carte.CartaObiettivo;
import carte.CartaOro;
import carte.CartaRisorsa;
import tavoloEMazzi.Mazzo;

public class Giocatore implements Comparable<Giocatore> {
	
	
	public static final int MIN_GIOCATORI = 2;
	public static final int MAX_GIOCATORI = 4;
	
	public static int numeroGiocatori;
	
	private String nickname;
	private Pedina colorePedina;
	private Integer punti;
	private Campo campo;
	private Mano mano;
	private CartaObiettivo obiettivoSegreto;
	private int conteggioObiettiviCompletati;
	
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Pedina getColorePedina() {
		return colorePedina;
	}
	public void setColorePedina(Pedina colorePedina) {
		this.colorePedina = colorePedina;
	}
	
	public int getPunti() {
		return punti;
	}
	public void setPunti(int punti) {
		this.punti = punti;
	}

	public Campo getCampo() {
		return campo;
	}
	public void setCampo(Campo campo) {
		this.campo = campo;
	}

	public Mano getMano() {
		return mano;
	}
	public void setMano(Mano mano) {
		this.mano = mano;
	}

	public CartaObiettivo getObiettivoSegreto() {
		return obiettivoSegreto;
	}
	public void setObiettivoSegreto(CartaObiettivo obiettivoSegreto) {
		this.obiettivoSegreto = obiettivoSegreto;
	}

	public int getConteggioObiettiviCompletati() {
		return conteggioObiettiviCompletati;
	}
	public void setConteggioObiettiviCompletati(int conteggioObiettiviCompletati) {
		this.conteggioObiettiviCompletati = conteggioObiettiviCompletati;
	}
	
	
	public Giocatore(String nickname) {
		System.out.println("Creazione di un giocatore con questo nickname in corso...");
		this.nickname = nickname;
		this.campo = new Campo();
		this.mano = new Mano();
		this.punti = 0;
		this.conteggioObiettiviCompletati = 0;
	}
	
	
	public void stampaNickname() {
		System.out.println(nickname);
	}
	
	
	public void assegnaColorePedina(Pedina pedinaScelta) {
		String codiceColorePedina = Pedina.ottieniStringCodiceColoreDaStringa(pedinaScelta.toString());
		nickname = codiceColorePedina+nickname+Pedina.CODICE_RESET_COLORE;
	}
	
	
	
	
	public void giocaCartaDaMano() {
		//Non stampo di nuovo le carte in mano perchè le ho già fatte visualizzare a inizio turno
		
		CartaGiocabile cartaDaGiocare;
		
		boolean indietroASceltaCarta;
		boolean indietroASceltaFaccia;
		
		//Con questi do while annidati permetto all'utente di tornare indietro se la scelta appena fatta non lo soddisfa
		
		do {
			cartaDaGiocare = scegliCartaDaMano();
			indietroASceltaCarta = false;
			
			do {
				indietroASceltaCarta = scegliFacciaDiGioco(cartaDaGiocare, true);
				indietroASceltaFaccia = false;
				
				if(!indietroASceltaCarta) {
					boolean chiediConfermaVolontà = true;
					
					if( (cartaDaGiocare instanceof CartaOro) && (cartaDaGiocare.getFacciaDiGioco().equals("FRONTE")) ) {
						//Controllo le risorse necessarie per giocare una carta oro sulla faccia frontale
						indietroASceltaFaccia = !( getCampo().controllaRisorseNecessariePerCartaOro((CartaOro) cartaDaGiocare) ); //posso eseguire il casting perchè ho appena controllato che la carta da giocare fosse di tipo oro
						chiediConfermaVolontà = !indietroASceltaFaccia; //se bisogna tornare automaticamente alla scelta della faccia perchè il giocatore non ha abbastanza risorse necessarie per giocare la carta (oro sul fronte), non si chiederà ovviamente conferma se il giocatore vuole proseguire (perchè non può)
																		//viceversa, se le risorse sul campo del giocatore soddisfano i requisiti necessari, si dovrà chiedere al giocatore se vuole proseguire con la giocata della carta
					}
					if(chiediConfermaVolontà) {
						indietroASceltaFaccia = !( confermaVolontàDiGioco() );
					}
				}
				
			} while (indietroASceltaFaccia); //questo è il caso in cui il giocatore abbia deciso di tornare indietro alla scelta della faccia di gioco OPPURE le risorse sul campo non soddisfino i requisiti per giocare la carta oro sul fronte
			
		} while (indietroASceltaCarta); //questo è il caso in cui il giocatore abbia deciso di tornare indietro alla scelta della carta
		
		
		
		while(true) {
			System.out.println("In quale casella vuoi giocare la carta?");
			int[] coordinateCasella = scegliCoordinateCasellaGiocabile();
			
			CasellaGiocabile casellaInCuiPosizionareCarta = (CasellaGiocabile) campo.getCasellaDaCoordinate(coordinateCasella[0],coordinateCasella[1]);
			
			//Controllo se sul campo ci sono le condizioni per giocare una carta nella posizione desiderata
			if(campo.controllaCondizioniGiocataCartaSuCampo(casellaInCuiPosizionareCarta)) {
				//Posiziono sul campo
				cartaDaGiocare.posizionaSuCampo(casellaInCuiPosizionareCarta);
				int numeroAngoliCopertiConGiocata = campo.copriAngoliAdiacentiACartaGiocata(casellaInCuiPosizionareCarta);
				mano.removeCarta(cartaDaGiocare);
				
				//Assegno eventuali punti
				if(cartaDaGiocare instanceof CartaRisorsa) {
					((CartaRisorsa) cartaDaGiocare).assegnaPunti(this);
				} else if(cartaDaGiocare instanceof CartaOro) {
					((CartaOro) cartaDaGiocare).assegnaPunti(this, numeroAngoliCopertiConGiocata);
				} 
				System.out.println(nickname+" i tuoi punti attuali sono: "+punti);
				return;
			}
			
		}
		
	}
	
	
	public CartaGiocabile scegliCartaDaMano() {
		int indiceCartaScelta = 0; //dato un valore iniziale come esempio, non sarà ovviamente accettato
		do {
			try {
				System.out.println(nickname+" quale carta vuoi giocare dalla tua mano?");
				Scanner sc = new Scanner(System.in);
				indiceCartaScelta = sc.nextInt();
				if(!(indiceCartaScelta >= 1 && indiceCartaScelta <= 3))
				{
					System.out.println("Indice inserito non valido, inserire un numero tra 1 e 3");
				}
			} catch(InputMismatchException e) { //gestisco il caso con una eccezione NON controllata
				System.out.println("Inserimento non valido, inserire un numero");
			}
		} while (!(indiceCartaScelta >= 1 && indiceCartaScelta <= 3));
		indiceCartaScelta = indiceCartaScelta-1; //decremento di 1 il valore dell'indice inserito perchè gli indici delle carte nella lista mano vanno da 0 a 2 (non da 1 a 3)
		return mano.getCartaDaMano(indiceCartaScelta);
	}
	
	
	public boolean scegliFacciaDiGioco(CartaGiocabile cartaDaGiocare, boolean consentiTornaIndietro) {
		String scelta;
		do {
			System.out.print(nickname+" su quale faccia vuoi giocare la carta?");
			
			if(consentiTornaIndietro) {
				System.out.println(" (puoi anche tornare indietro alla scelta della carta)");
			} else {
				System.out.println();
			}
			
			Scanner sc = new Scanner(System.in);
			scelta = sc.nextLine().toUpperCase();
			if(scelta.equals("FRONTE") || scelta.equals("RETRO")) {
				cartaDaGiocare.setFacciaDiGioco(scelta); //setto la faccia di gioco della carta (giocabile) a "FRONTE" o "RETRO"
				return false; //ritorna false nel caso sia stata inserita una delle due facce di gioco
			} else if(consentiTornaIndietro && scelta.equals("INDIETRO")) {
				return true; //ritorna true nel caso sia stato scelto di tornare indietro
			} else {
				System.out.print("Inserimento non valido, scrivere Fronte oppure Retro");
				
				if(consentiTornaIndietro) {
					System.out.println(" oppure Indietro");
				} else {
					System.out.println();
				}
				
			}
		} while (true); //eseguirà questo ciclo all'infinito, uscirà dal metodo quando si verificherà un "return",
						//ovvero nei casi in cui l'utente inserisca "Fronte" o "Retro" (o "Indietro" nel caso questo sia accettato)
	}
	
	
	public boolean confermaVolontàDiGioco() {
		while(true) { //il ciclo verrà eseguito all'infinito finchè non si incontrerà un return (all'interno degli if)
			System.out.println(nickname+" vuoi proseguire con la giocata della carta? Se sì, non potrai più tornare indietro (in caso contrario, si torna alla scelta della faccia di gioco)");
			Scanner sc = new Scanner(System.in);
			String scelta = sc.nextLine().toUpperCase();
			if(scelta.equals("SI")) {
				return true;
			} else if(scelta.equals("NO")) {
				return false;
			} else {
				System.out.println("Inserimento non valido, scrivere Si oppure No");
			}
		}
	}
	
	
	public int[] scegliCoordinateCasellaGiocabile() {
		int[] coordinate = {0,0}; //inseriti dei qualsiasi valori di partenza, verranno sovrascritti da ciò che inserirà l'utente
		boolean ok;
		
		do {
			
			ok = false;
			try {
				Scanner sc = new Scanner(System.in); //la dichiarazione dello scanner va qui, altrimenti in caso di errore il ciclo viene eseguito all'infinito
				
				System.out.print("Coordinata righe: ");
				coordinate[0] = sc.nextInt()+40; //L'utente inserirà una coordinata tra -40 e +40 perchè sono quelle stampate ai lati della matrice, in realtà gli indici degli array vanno da 0 a 80, dunque sommo 40
				System.out.print("Coordinata colonne: ");
				coordinate[1] = sc.nextInt()+40;
				
				if(campo.getCasellaDaCoordinate(coordinate[0],coordinate[1]) instanceof CasellaGiocabile) {
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
	
	
	
	public void pescaCartaDaMazzi(Mazzo mazzoCarteRisorsa, Mazzo mazzoCarteOro) {
		System.out.println("\n"+nickname+", ora devi pescare una carta da uno dei due mazzi");
		System.out.println("Mazzo risorsa:");
		mazzoCarteRisorsa.visualizzaTreCartePerPesca();
		System.out.println("Mazzo oro:");
		mazzoCarteOro.visualizzaTreCartePerPesca();
		
		if( !(mazzoCarteRisorsa.controllaSeMazzoFinito() && mazzoCarteOro.controllaSeMazzoFinito()) ) { //Controllo che non siano finiti entrambi i mazzi
			
			String mazzoDaCuiPescare;
			do {
				System.out.println("Da quale mazzo vuoi pescare la carta?");
				Scanner sc=new Scanner(System.in);
				mazzoDaCuiPescare=sc.nextLine().toUpperCase();
				if(mazzoDaCuiPescare.equals("RISORSA") || mazzoDaCuiPescare.equals("ORO")) {
					System.out.print("Ok! ");
					
					int numeroCartaScelta = 0; //dato un valore iniziale come esempio, non sarà ovviamente accettato
					do {
						try {
							System.out.println("Quale carta vuoi prendere tra le tre proposte?");
							sc = new Scanner(System.in);
							numeroCartaScelta = sc.nextInt();
							if(numeroCartaScelta >= 1 && numeroCartaScelta <= 3)
							{
								if(mazzoDaCuiPescare.equals("RISORSA")) {
									mano.aggiungiCartaAMano((CartaGiocabile) mazzoCarteRisorsa.estraiCartaDaMazzo(numeroCartaScelta-1, false));
									//poichè ho stampato gli indici delle carte nel mazzo sommando 1 (per rendere più facile la scelta dell'utente), quando utilizzo l'input da tastiera dovrò diminuire il valore ottenuto di 1
									System.out.println("Carta risorsa aggiunta alla mano!");
								} else if(mazzoDaCuiPescare.equals("ORO")) {
									mano.aggiungiCartaAMano((CartaGiocabile) mazzoCarteOro.estraiCartaDaMazzo(numeroCartaScelta-1, false));
									System.out.println("Carta oro aggiunta alla mano!");
								}
							} else {
								System.out.println("Inserimento non valido, inserire un numero tra 1 e 3");
							}
						} catch(InputMismatchException e) { //gestisco il caso con una eccezione NON controllata
							System.out.println("Inserimento non valido, inserire un numero");
						}
					} while (!(numeroCartaScelta >= 1 && numeroCartaScelta <= 3));
					
				} else {
					System.out.println("Inserimento non valido, scrivere risorsa oppure oro");
				}
			} while(! (mazzoDaCuiPescare.equals("RISORSA") || mazzoDaCuiPescare.equals("ORO")) );
			
		} else {
			System.out.println("Risultano terminate le carte in entrambi i mazzi, impossibile pescare");
		}
		
	}
	
	
	@Override
	//Metodo derivante dall'interfaccia: posso confrontare due giocatori tra di loro in base al loro punteggio
	//(questo confronto mi servirà per ordinare la lista dei giocatori a fine partita per dichiarare il vincitore)
	public int compareTo(Giocatore altroGiocatore) {
		return punti.compareTo(altroGiocatore.getPunti()); //per poter usare compareTo, devo avere punti come Integer (non int, tipo primitivo)
	}
}
