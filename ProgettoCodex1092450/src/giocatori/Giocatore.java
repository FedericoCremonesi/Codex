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
import sviluppoGioco.ColoreTesto;
import sviluppoGioco.Partita;
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
		//System.out.println("Creazione di un giocatore con questo nickname in corso...");
		//(Usato per testing)
		System.out.println("Ok!");
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
		setColorePedina(pedinaScelta);
		
		String codiceColorePedina = Pedina.ottieniStringCodiceColoreDaStringa(pedinaScelta.toString());
		nickname = codiceColorePedina+nickname+Pedina.CODICE_RESET_COLORE;
	}
	
	
	public void stampaLineaColorata() {
		System.out.println(Pedina.ottieniStringCodiceColoreSfondoDaStringa(colorePedina.toString()));
		for(int t=0; t<Partita.NUMERO_CARATTERI_MASSIMI_ORIZZONTALI; t++) {
			System.out.print(" ");
		}
		System.out.println(Pedina.CODICE_RESET_COLORE);
	}
	
	
	public void giocaCartaDaMano() {
		//Non stampo di nuovo le carte in mano perchè le ho già fatte visualizzare a inizio turno
		
		CartaGiocabile cartaDaGiocare;
		
		boolean indietroASceltaCarta;
		boolean indietroASceltaFaccia;
		String facciaSceltaOIndietro = "non ancora giocata"; //inizializzo alla stringa di default, poi verrà sovrascritta con la faccia scelta dall'utente
		
		//Con questi do while annidati permetto all'utente di tornare indietro se la scelta appena fatta non lo soddisfa, seguendo lo schema:
		//						SceltaCarta <-> SceltaFaccia <-> VuoiProseguire -> SceltaCaselle
		
		do {
			indietroASceltaCarta = false; //inizializzo valore (verrà sovrascritto)
			cartaDaGiocare = scegliCartaDaMano();
			
			do {
				indietroASceltaFaccia = false; //inizializzo valore (verrà sovrascritto)
				facciaSceltaOIndietro = scegliFacciaDiGioco(cartaDaGiocare, true);
				if(facciaSceltaOIndietro.equals("FRONTE") || facciaSceltaOIndietro.equals("RETRO")) {
					indietroASceltaCarta = false;
				} else if(facciaSceltaOIndietro.equals("INDIETRO")) {
					indietroASceltaCarta = true;
				}
				
				if(!indietroASceltaCarta) {
					boolean chiediConfermaVolontà = true;
					
					if( (cartaDaGiocare instanceof CartaOro) && (facciaSceltaOIndietro.equals("FRONTE")) ) { //Non posso usare il getFacciaDiGioco perchè non è ancora stata settata, verrà settata dopo che l'utente ha confermato la sua decisione
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
		
		//Qui (fuori dai cicli precedenti), facciaSceltaOIndietro sarà sicuramente o "FRONTE" o "RETRO", non "INDIETRO"
		/*
		 * Inoltre l'utente non potrà più tornare indietro, posso quindi settare la faccia di gioco scelta
		 * Non lo faccio nel metodo "scegliFacciaDiGioco" perchè se l'utente decidesse di tornare indietro e scegliere un'altra carta dopo la scelta della faccia,
		 * la faccia di gioco della carte scelta inizialmente rimarrebbe settata a "FRONTE" o "RETRO", quando invece dovrebbe essere "non ancora giocata"
		 * Questo causa successivi problemi nella stampa della carta nella mano, in quanto (senza questa correzione) viene visualizzata solo la faccia frontale o sul retro, non entrambe (come dovrebbe accadere)
		 */
		cartaDaGiocare.setFacciaDiGioco(facciaSceltaOIndietro);
		
		while(true) {
			System.out.println(ColoreTesto.CODICE_COLORE_AZZURRO+"In quale casella vuoi giocare la carta?"+ColoreTesto.CODICE_RESET_COLORE);
			int[] coordinateCasella = scegliCoordinateCasellaGiocabile();
			
			CasellaGiocabile casellaInCuiPosizionareCarta = (CasellaGiocabile) campo.getCasellaDaCoordinate(coordinateCasella[0],coordinateCasella[1]);
			
			//Controllo se sul campo ci sono le condizioni per giocare una carta nella posizione desiderata
			if(campo.controllaCondizioniGiocataCartaSuCampo(casellaInCuiPosizionareCarta)) {
				//Posiziono sul campo
				cartaDaGiocare.posizionaSuCampo(casellaInCuiPosizionareCarta);
				int numeroAngoliCopertiConGiocata = campo.copriAngoliAdiacentiACartaGiocata(casellaInCuiPosizionareCarta);
				mano.removeCarta(cartaDaGiocare);
				
				//Assegno eventuali punti
				if(cartaDaGiocare instanceof CartaOro) { //Devo prima controllare se è una carta oro,
														 //altrimenti eseguirà sicuramente il metodo delle carte risorsa
														 //(essendo carta oro classe figlia di carta risorsa, tutte le carte oro sono anche risorsa)
					((CartaOro) cartaDaGiocare).assegnaPunti(this, numeroAngoliCopertiConGiocata);
				} else {
					((CartaRisorsa) cartaDaGiocare).assegnaPunti(this);
				} 
				System.out.println("\t"+nickname+" i tuoi punti attuali sono: "+punti);
				return;
			}
			
		}
		
	}
	
	
	public CartaGiocabile scegliCartaDaMano() {
		int indiceCartaScelta = 0; //dato un valore iniziale come esempio, non sarà ovviamente accettato
		do {
			try {
				System.out.println(nickname+ColoreTesto.CODICE_COLORE_AZZURRO+" quale carta vuoi giocare dalla tua mano?"+ColoreTesto.CODICE_COLORE_NERO+" [1/2/3]"+ColoreTesto.CODICE_RESET_COLORE);
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
	
	
	public String scegliFacciaDiGioco(CartaGiocabile cartaDaGiocare, boolean consentiTornaIndietro) {
		String scelta;
		do {
			System.out.print(nickname+ColoreTesto.CODICE_COLORE_AZZURRO+" su quale faccia vuoi giocare la carta?"+ColoreTesto.CODICE_COLORE_NERO+" [Fronte/Retro]"+ColoreTesto.CODICE_RESET_COLORE);
			
			if(consentiTornaIndietro) {
				System.out.println("\n"+ColoreTesto.CODICE_COLORE_AZZURRO+" (puoi anche tornare indietro alla scelta della carta inserendo "+ColoreTesto.CODICE_COLORE_NERO+"[Indietro]"+ColoreTesto.CODICE_COLORE_AZZURRO+")"+ColoreTesto.CODICE_RESET_COLORE);
			} else {
				System.out.println();
			}
			
			Scanner sc = new Scanner(System.in);
			scelta = sc.nextLine().toUpperCase().trim();
			if(scelta.equals("FRONTE") || scelta.equals("RETRO")) {
				//prima qui settavo definitivamente la faccia di gioco, ma questo causava problemi (nel metodo "giocaCartaDaMano" di Giocatore sono spiegate le motivazioni)
				return scelta; //nel caso sia stata inserita una delle due facce di gioco
			} else if(consentiTornaIndietro && scelta.equals("INDIETRO")) {
				return scelta; //nel caso sia stato scelto di tornare indietro
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
			System.out.println(nickname+ColoreTesto.CODICE_COLORE_AZZURRO+" vuoi proseguire con la giocata della carta?"+ColoreTesto.CODICE_COLORE_NERO+" [Si/No]"+"\n"+ColoreTesto.CODICE_COLORE_AZZURRO+" Se sì, non potrai più tornare indietro (in caso contrario, si torna alla scelta della faccia di gioco)"+ColoreTesto.CODICE_RESET_COLORE);
			Scanner sc = new Scanner(System.in);
			String scelta = sc.nextLine().toUpperCase().trim();
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
				
				System.out.print("\tCoordinata righe: ");
				coordinate[0] = sc.nextInt()+40; //L'utente inserirà una coordinata tra -40 e +40 perchè sono quelle stampate ai lati della matrice, in realtà gli indici degli array vanno da 0 a 80, dunque sommo 40
				System.out.print("\tCoordinata colonne: ");
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
		mazzoCarteRisorsa.visualizzaTreCartePerPesca(); //potrebbe stampare "Carte finite!" se le carte nel mazzo sono terminate
		System.out.println("Mazzo oro:");
		mazzoCarteOro.visualizzaTreCartePerPesca();
		
		boolean sceltaCartaNonPresente = false; //questo booleano mi indica se l'utente ha scelto una carta non presente nel mazzo perchè questo sta terminando
		
		if( !(mazzoCarteRisorsa.controllaSeMazzoFinito() && mazzoCarteOro.controllaSeMazzoFinito()) ) { //Controllo che non siano finiti entrambi i mazzi
			
			String mazzoDaCuiPescare;
			do {
				System.out.println(ColoreTesto.CODICE_COLORE_AZZURRO+"Da quale mazzo vuoi pescare la carta?"+ColoreTesto.CODICE_COLORE_NERO+" [Risorsa/Oro]"+ColoreTesto.CODICE_RESET_COLORE);
				Scanner sc=new Scanner(System.in);
				mazzoDaCuiPescare=sc.nextLine().toUpperCase().trim();
				if(mazzoDaCuiPescare.equals("RISORSA") || mazzoDaCuiPescare.equals("ORO")) {
					
					//Gestisco il caso in cui l'utente abbia scelto un mazzo in cui sono terminate le carte
					if(mazzoDaCuiPescare.equals("RISORSA") && mazzoCarteRisorsa.controllaSeMazzoFinito()) {
						System.out.println("Le carte nel mazzo selezionato sono finite, devi pescare dal mazzo delle carte oro");
						mazzoDaCuiPescare = "ORO";
					} else if(mazzoDaCuiPescare.equals("ORO") && mazzoCarteOro.controllaSeMazzoFinito()) {
						System.out.println("Le carte nel mazzo selezionato sono finite, devi pescare dal mazzo delle carte risorsa");
						mazzoDaCuiPescare = "RISORSA";
					} else {
						System.out.print("Ok! ");
					}
					
					int numeroCartaScelta = 0; //dato un valore iniziale come esempio, non sarà ovviamente accettato
					do {
						try {
							System.out.println(ColoreTesto.CODICE_COLORE_AZZURRO+"Quale carta vuoi prendere tra le tre proposte?"+ColoreTesto.CODICE_COLORE_NERO+" [1/2/3]"+ColoreTesto.CODICE_RESET_COLORE);
							sceltaCartaNonPresente = false;
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
						} catch(IndexOutOfBoundsException e) {
							//Qui catcho e gestisco il caso in cui l'utente inserisca ad esempio l'indice 3 nella decisione della carta da estrarre, ma nel mazzo rimangono solo altre 2 carte
							//E' l'eccezione lanciata dal metodo estraiCartaDaMazzo (con throws)
							System.out.println("Le carte nel mazzo stanno terminando, la carta indicata non esiste");
							sceltaCartaNonPresente=true;
						}
					} while ( (!(numeroCartaScelta >= 1 && numeroCartaScelta <= 3)) || (sceltaCartaNonPresente) );
					
				} else {
					System.out.println("Inserimento non valido, scrivere Risorsa oppure Oro");
				}
			} while(! (mazzoDaCuiPescare.equals("RISORSA") || mazzoDaCuiPescare.equals("ORO")) );
			
		} else {
			System.out.println("Risultano terminate le carte in entrambi i mazzi, impossibile pescare");
			return;
		}
		
	}
	
	
	@Override
	//Metodo derivante dall'interfaccia: posso confrontare due giocatori tra di loro in base al loro punteggio
	//(questo confronto mi servirà per ordinare la lista dei giocatori a fine partita per dichiarare il vincitore)
	public int compareTo(Giocatore altroGiocatore) {
		return punti.compareTo(altroGiocatore.getPunti()); //per poter usare compareTo, devo avere punti come Integer (non int, tipo primitivo)
	}
}
