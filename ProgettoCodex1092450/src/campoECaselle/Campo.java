package campoECaselle;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

import facceEAngoli.AngoloVisibile;
import facceEAngoli.Risorsa;
import facceEAngoli.AngoloNascosto;
import sviluppoGioco.Partita;

public class Campo {
	
	public final int DIM = 81;
	/*
	 * DIM è la dimensione di ognuno dei due array componenti la matrice campo
	 * la dimensione degli array è 81, ovvero la quantità delle caselle totali presenti in ognuno di questi
	 * le coordinate delle caselle che verranno visualizzate vanno da -40 a 40 e sono quindi 81 (considerando lo 0)
	 */
	
	private Casella[][] caselleDelCampo;
	//Creo una hashmap con tutte le risorse e gli oggetti
	HashMap<String, Integer> conteggioRisorseEOggetti = new HashMap<String, Integer>();
	
	
	public Casella getCasellaDaCoordinate(int i, int j) {
		return caselleDelCampo[i][j];
	}
	
	
	public Campo() {
		caselleDelCampo = new Casella[DIM][DIM]; //creo la matrice (doppio vettore) vuota
		
		for(int i=0; i<DIM; i++) //i indica la riga
								 //gli indici vanno da 0 a 80 (verranno visualizzate coordinate da -40 a 40)
		{
			for(int j=0; j<DIM; j++) //j indica la colonna
									 //gli indici vanno da 0 a 80 (verranno visualizzate coordinate da -40 a 40)
			{
				if( (i%2==0 && j%2==0) || (i%2!=0 && j%2!=0) ) { //se entrambe le coordinate sono pari o entrambe sono dispari, la casella che creo è giocabile
					caselleDelCampo[i][j] = new CasellaGiocabile(i,j);
				} else {
					caselleDelCampo[i][j] = new CasellaNonGiocabile(i,j);
				}
			}
		}
		
	}
	
	
	//Funzione ricorsiva per contare tutte le risorse e gli oggetti sul campo
	public void contaRisorseEOggettiVisibili(int i, int j, boolean inizioScorrimento, boolean stampaConteggio) {
		
		if(inizioScorrimento) {
			//All'inizio di ogni scorrimento del campo, pongo a 0 tutti i valori interi associati alle risorse e agli oggetti
			//(altrimenti continuerebbero a incrementare ogni volta che li conta, senza ripartire a contare da 0)
			conteggioRisorseEOggetti.put("FUNGHI", 0);
			conteggioRisorseEOggetti.put("VEGETALE", 0);
			conteggioRisorseEOggetti.put("ANIMALE", 0);
			conteggioRisorseEOggetti.put("INSETTI", 0);
			conteggioRisorseEOggetti.put("PIUMA", 0);
			conteggioRisorseEOggetti.put("INCHIOSTRO", 0);
			conteggioRisorseEOggetti.put("PERGAMENA", 0);
		}
				
		if( ((CasellaGiocabile) caselleDelCampo[i][j]).isEmpty() ) {
			return;
		} else {
			if( ((CasellaGiocabile) caselleDelCampo[i][j]).getCartaContenuta().getFacciaDiGioco().equals("FRONTE") ) {
				
				//Analizzo l'angolo in alto a sinistra della faccia fronte
				if( ((CasellaGiocabile) caselleDelCampo[i][j]).getCartaContenuta().getFronte().getAngoloAltoSx() instanceof AngoloVisibile ) { //gestisco caso: angolo nascosto
					if( ((AngoloVisibile) ((CasellaGiocabile) caselleDelCampo[i][j]).getCartaContenuta().getFronte().getAngoloAltoSx()).isCoperto() ) { //gestistisco caso: angolo coperto
						contaRisorseEOggettiVisibili(i-1,j-1,false,false); //chiamo ricorsivamente la funzione, stavolta dicendogli di analizzare la casella (giocabile) in alto a sx rispetto a quella in analisi adesso
					} else if( ((AngoloVisibile) ((CasellaGiocabile) caselleDelCampo[i][j]).getCartaContenuta().getFronte().getAngoloAltoSx()).getContenuto()!=null ) { //gestistisco caso: angolo vuoto
						incrementaConteggioDatoSimbolo( ((AngoloVisibile) ((CasellaGiocabile) caselleDelCampo[i][j]).getCartaContenuta().getFronte().getAngoloAltoSx()).getContenuto() );
					}
					
				}
				
				//Analizzo l'angolo in alto a destra della faccia fronte
				if( ((CasellaGiocabile) caselleDelCampo[i][j]).getCartaContenuta().getFronte().getAngoloAltoDx() instanceof AngoloVisibile ) {
					if( ((AngoloVisibile) ((CasellaGiocabile) caselleDelCampo[i][j]).getCartaContenuta().getFronte().getAngoloAltoDx()).isCoperto() ) {
						contaRisorseEOggettiVisibili(i-1,j+1,false,false);
					} else if( ((AngoloVisibile) ((CasellaGiocabile) caselleDelCampo[i][j]).getCartaContenuta().getFronte().getAngoloAltoDx()).getContenuto()!=null ) {
						incrementaConteggioDatoSimbolo( ((AngoloVisibile) ((CasellaGiocabile) caselleDelCampo[i][j]).getCartaContenuta().getFronte().getAngoloAltoDx()).getContenuto() );
					}
					
				}
				
				//Analizzo l'angolo in basso a sinistra della faccia fronte
				if( ((CasellaGiocabile) caselleDelCampo[i][j]).getCartaContenuta().getFronte().getAngoloBassoSx() instanceof AngoloVisibile ) {
					if( ((AngoloVisibile) ((CasellaGiocabile) caselleDelCampo[i][j]).getCartaContenuta().getFronte().getAngoloBassoSx()).isCoperto() ) {
						contaRisorseEOggettiVisibili(i+1,j-1,false,false);
					} else if( ((AngoloVisibile) ((CasellaGiocabile) caselleDelCampo[i][j]).getCartaContenuta().getFronte().getAngoloBassoSx()).getContenuto()!=null ) {
						incrementaConteggioDatoSimbolo( ((AngoloVisibile) ((CasellaGiocabile) caselleDelCampo[i][j]).getCartaContenuta().getFronte().getAngoloBassoSx()).getContenuto() );
					}
					
				}
				
				//Analizzo l'angolo in basso a destra della faccia fronte
				if( ((CasellaGiocabile) caselleDelCampo[i][j]).getCartaContenuta().getFronte().getAngoloBassoDx() instanceof AngoloVisibile ) {
					if( ((AngoloVisibile) ((CasellaGiocabile) caselleDelCampo[i][j]).getCartaContenuta().getFronte().getAngoloBassoDx()).isCoperto() ) {
						contaRisorseEOggettiVisibili(i+1,j+1,false,false);
					} else if( ((AngoloVisibile) ((CasellaGiocabile) caselleDelCampo[i][j]).getCartaContenuta().getFronte().getAngoloBassoDx()).getContenuto()!=null ) {
						incrementaConteggioDatoSimbolo( ((AngoloVisibile) ((CasellaGiocabile) caselleDelCampo[i][j]).getCartaContenuta().getFronte().getAngoloBassoDx()).getContenuto() );
					}
					
				}
				
			} else if( ((CasellaGiocabile) caselleDelCampo[i][j]).getCartaContenuta().getFacciaDiGioco().equals("RETRO") ) {
				
				//Analizzo l'angolo in alto a sinistra della faccia retro
				if( ((CasellaGiocabile) caselleDelCampo[i][j]).getCartaContenuta().getRetro().getAngoloAltoSx() instanceof AngoloVisibile ) { //gestisco caso: angolo nascosto
					if( ((AngoloVisibile) ((CasellaGiocabile) caselleDelCampo[i][j]).getCartaContenuta().getRetro().getAngoloAltoSx()).isCoperto() ) { //gestistisco caso: angolo coperto
						contaRisorseEOggettiVisibili(i-1,j-1,false,false); //chiamo ricorsivamente la funzione, stavolta dicendogli di analizzare la casella (giocabile) in alto a sx rispetto a quella in analisi adesso
					} else if( ((AngoloVisibile) ((CasellaGiocabile) caselleDelCampo[i][j]).getCartaContenuta().getRetro().getAngoloAltoSx()).getContenuto()!=null ) { //gestistisco caso: angolo vuoto
						incrementaConteggioDatoSimbolo( ((AngoloVisibile) ((CasellaGiocabile) caselleDelCampo[i][j]).getCartaContenuta().getRetro().getAngoloAltoSx()).getContenuto() );
					}
					
				}
				
				//Analizzo l'angolo in alto a destra della faccia retro
				if( ((CasellaGiocabile) caselleDelCampo[i][j]).getCartaContenuta().getRetro().getAngoloAltoDx() instanceof AngoloVisibile ) {
					if( ((AngoloVisibile) ((CasellaGiocabile) caselleDelCampo[i][j]).getCartaContenuta().getRetro().getAngoloAltoDx()).isCoperto() ) {
						contaRisorseEOggettiVisibili(i-1,j+1,false,false);
					} else if( ((AngoloVisibile) ((CasellaGiocabile) caselleDelCampo[i][j]).getCartaContenuta().getRetro().getAngoloAltoDx()).getContenuto()!=null ) {
						incrementaConteggioDatoSimbolo( ((AngoloVisibile) ((CasellaGiocabile) caselleDelCampo[i][j]).getCartaContenuta().getRetro().getAngoloAltoDx()).getContenuto() );
					}
					
				}
				
				//Analizzo l'angolo in basso a sinistra della faccia retro
				if( ((CasellaGiocabile) caselleDelCampo[i][j]).getCartaContenuta().getRetro().getAngoloBassoSx() instanceof AngoloVisibile ) {
					if( ((AngoloVisibile) ((CasellaGiocabile) caselleDelCampo[i][j]).getCartaContenuta().getRetro().getAngoloBassoSx()).isCoperto() ) {
						contaRisorseEOggettiVisibili(i+1,j-1,false,false);
					} else if( ((AngoloVisibile) ((CasellaGiocabile) caselleDelCampo[i][j]).getCartaContenuta().getRetro().getAngoloBassoSx()).getContenuto()!=null ) {
						incrementaConteggioDatoSimbolo( ((AngoloVisibile) ((CasellaGiocabile) caselleDelCampo[i][j]).getCartaContenuta().getRetro().getAngoloBassoSx()).getContenuto() );
					}
					
				}
				
				//Analizzo l'angolo in basso a destra della faccia retro
				if( ((CasellaGiocabile) caselleDelCampo[i][j]).getCartaContenuta().getRetro().getAngoloBassoDx() instanceof AngoloVisibile ) {
					if( ((AngoloVisibile) ((CasellaGiocabile) caselleDelCampo[i][j]).getCartaContenuta().getRetro().getAngoloBassoDx()).isCoperto() ) {
						contaRisorseEOggettiVisibili(i+1,j+1,false,false);
					} else if( ((AngoloVisibile) ((CasellaGiocabile) caselleDelCampo[i][j]).getCartaContenuta().getRetro().getAngoloBassoDx()).getContenuto()!=null ) {
						incrementaConteggioDatoSimbolo( ((AngoloVisibile) ((CasellaGiocabile) caselleDelCampo[i][j]).getCartaContenuta().getRetro().getAngoloBassoDx()).getContenuto() );
					}
					
				}
				
				//Analizzo la risorsa al centro sul retro n1 (che hanno tutte le carte giocabili)
				incrementaConteggioDatoSimbolo( ((CasellaGiocabile) caselleDelCampo[i][j]).getCartaContenuta().getRetro().getRisorsaRetroCentrale().toString() );
			
				//Analizzo la risorsa al centro sul retro n2 (che hanno alcune carte iniziali)
				if( ((CasellaGiocabile) caselleDelCampo[i][j]).getCartaContenuta().getRetro().getRisorsaRetroCentraleAggiuntiva1()!=null ) {
					incrementaConteggioDatoSimbolo( ((CasellaGiocabile) caselleDelCampo[i][j]).getCartaContenuta().getRetro().getRisorsaRetroCentraleAggiuntiva1().toString() );
				}
				
				//Analizzo la risorsa al centro sul retro n2 (che hanno alcune carte iniziali)
				if( ((CasellaGiocabile) caselleDelCampo[i][j]).getCartaContenuta().getRetro().getRisorsaRetroCentraleAggiuntiva2()!=null ) {
					incrementaConteggioDatoSimbolo( ((CasellaGiocabile) caselleDelCampo[i][j]).getCartaContenuta().getRetro().getRisorsaRetroCentraleAggiuntiva2().toString() );
				}
			}
		}
		
		if(stampaConteggio) {
			stampaConteggioRisorseEOggettiVisibili();
		}
	}
	
	
	public void incrementaConteggioDatoSimbolo(String simbolo) {
		String simboloInHashMap=""; //inizializzo a Stringa non contenuta in hashmap (tra le chiavi), sarà sovrascritto durante lo switch
		Integer conteggioSimboloInHashMap=0; //inizializzo a 0, sarà sovrascritto durante lo switch
		switch (simbolo) {
		case "FUNGHI":
			simboloInHashMap = "FUNGHI";
			conteggioSimboloInHashMap = conteggioRisorseEOggetti.get("FUNGHI");
			break;
		case "VEGETALE":
			simboloInHashMap = "VEGETALE";
			conteggioSimboloInHashMap = conteggioRisorseEOggetti.get("VEGETALE");
			break;
		case "ANIMALE":
			simboloInHashMap = "ANIMALE";
			conteggioSimboloInHashMap = conteggioRisorseEOggetti.get("ANIMALE");
			break;
		case "INSETTI":
			simboloInHashMap = "INSETTI";
			conteggioSimboloInHashMap = conteggioRisorseEOggetti.get("INSETTI");
			break;
		case "PIUMA":
			simboloInHashMap = "PIUMA";
			conteggioSimboloInHashMap = conteggioRisorseEOggetti.get("PIUMA");
			break;
		case "INCHIOSTRO":
			simboloInHashMap = "INCHIOSTRO";
			conteggioSimboloInHashMap = conteggioRisorseEOggetti.get("INCHIOSTRO");
			break;
		case "PERGAMENA":
			simboloInHashMap = "PERGAMENA";
			conteggioSimboloInHashMap = conteggioRisorseEOggetti.get("PERGAMENA");
			break;
		default:
			return; //se non è stato trovata una risorsa o un oggetto, si smette di eseguire questo metodo (altrimenti alla hashmap viene aggiunto un nuovo elemento, di chiave "")
		}
		conteggioSimboloInHashMap++;
		conteggioRisorseEOggetti.put(simboloInHashMap, conteggioSimboloInHashMap);
	}
	
	
	public void stampaConteggioRisorseEOggettiVisibili() {
		System.out.println("\nSul tuo campo sono visibili:");
		for (String risorsaOppureOggetto : conteggioRisorseEOggetti.keySet()) { //Utilizzo keyset per scorrere gli elementi della hashmap
			String coloreTesto = "";
			if( risorsaOppureOggetto.equals(Risorsa.FUNGHI.toString()) ) {
				coloreTesto = Risorsa.CODICE_COLORE_RISORSA_FUNGHI;
			} else if( risorsaOppureOggetto.equals(Risorsa.VEGETALE.toString()) ) {
				coloreTesto = Risorsa.CODICE_COLORE_RISORSA_VEGETALE;
			} else if( risorsaOppureOggetto.equals(Risorsa.ANIMALE.toString()) ) {
				coloreTesto = Risorsa.CODICE_COLORE_RISORSA_ANIMALE;
			} else if( risorsaOppureOggetto.equals(Risorsa.INSETTI.toString()) ) {
				coloreTesto = Risorsa.CODICE_COLORE_RISORSA_INSETTI;
			}
			System.out.println("➤ "+conteggioRisorseEOggetti.get(risorsaOppureOggetto)+" simboli di tipo "+coloreTesto+risorsaOppureOggetto.toLowerCase()+Risorsa.CODICE_RESET_COLORE); //con get accedo al valore intero associato ad ogni stringa
		}
	}


	public void stampaMatriceCampoAQuadratini() {
		System.out.println("\nCampo di gioco:");
		
		int coordinataDaStampare;
		
		System.out.print("    "); //stampo degli spazi per allineare al meglio gli indici delle colonne
		
		for(int j=0; j<DIM; j++) //uso j perchè per convenzione ho indicato con questa lettera le colonne, potevo usare una qualsiasi altra variabile
		{
			/*
			 * Le coordinate della matrice vanno da 0 a 80,
			 * ma per migliorare l'estetica del campo visualizzo le coordinate da -40 a (+)40
			 */
			coordinataDaStampare = (j-40);
			
			/*
			 * Con questa condizione evito di stampare sempre tutta la matrice (81x81, quindi molto grande),
			 * ne stampo invece solo alcune caselle, partendo da quella centrale e aggiungendone una ad ogni round della partita
			 */
			if( j>=(39-Partita.getNumeroRoundGiocati()) && j<=(41+Partita.getNumeroRoundGiocati()) )
			/*
			* Nota: i, come j, si riferisce all'indice effettivo della matrice (va quindi da 0 a 80),
			* non alle coordinate visualizzate (che vanno da -40 a (+)40)
			*/
			{
				//Stampo in alto la coordinata di ogni colonna
				stampaCoordinataConSpaziPrima(coordinataDaStampare);
			}
		}
		
		System.out.println(); //vado a capo per cominciare a stampare la tabella stessa (con a lato le coordinate delle righe)
		
		for(int i=0; i<DIM; i++)
		{
			if( i>=(39-Partita.getNumeroRoundGiocati()) && i<=(41+Partita.getNumeroRoundGiocati()) )
			{
				coordinataDaStampare = (i-40);
				
				//Stampo a lato la coordinata della riga analizzata
				stampaCoordinataConSpaziPrima(coordinataDaStampare);
				//Stampo uno spazio tra ogni coordinata a lato e le caselle della matrice
				System.out.print(" ");
				
				for(int j=0; j<DIM; j++)
				{
					if( j>=(39-Partita.getNumeroRoundGiocati()) && j<=(41+Partita.getNumeroRoundGiocati()) )
					{
						//Stampo sia le caselle giocabili che non giocabili (i rispettivi metodi specificheranno cosa stampare per ognuno dei due casi)
						caselleDelCampo[i][j].stampaCasellaComeQuadratino();
					}
				}
				
				System.out.println(); //vado a capo una volta finita la riga
			}
		}
		
	}
	
	
	public void stampaCoordinataConSpaziPrima(int coordinataDaStampare) {
		if(coordinataDaStampare>=0 && coordinataDaStampare<=9)
		{
			//aggiungo davanti 2 spazi se la coordinata è tra 0 e 9
			System.out.print("  "+coordinataDaStampare);
		} else if((coordinataDaStampare>=-9 && coordinataDaStampare<=-1) || (coordinataDaStampare>=10 && coordinataDaStampare<=40))
		{
			//aggiungo davanti 1 spazio se la coordinata è tra -9 e -1 oppure tra 10 e 40
			System.out.print(" "+coordinataDaStampare);
		} else {
			//non aggiungo alcuno spazio se stampando il numero occupo già 3 spazi (cioè se il numero è tra -40 e -10)
			System.out.print(coordinataDaStampare);
		}
	}
	
	
	public void stampa3x3AttornoACartaInCampo(int[] coordinateCasella) {
		int partenzaI = coordinateCasella[0]-1; //voglio stampare anche la carta nella riga sopra rispetto a quella alle coordinate comunicate, quindi calcolo -1
		int partenzaJ = coordinateCasella[1]-1; //voglio stampare anche la carta nella colonna a sinistra rispetto a quella alle coordinate comunicate, quindi calcolo -1
		System.out.println();
		
		//Con questi 3 cicli for stampo una "matrice" 3x3 di caselle (giocabili e non) affiancate
		
		for(int i=partenzaI; i<=(partenzaI+2); i++) //Scorre le righe della matrice Campo
		{
			for(Integer k=0; k<13; k++) //Scorre le linee delle singole carte
			{
				for(int j=partenzaJ; j<=(partenzaJ+2); j++) //Scorre le colonne della matrice Campo
				{
					caselleDelCampo[i][j].stampaLineaCasellaIngrandita(k);
				}
				System.out.println(); //va a capo una volta finito di stampare la linea k di tutte le 3 caselle affiancate
			}
		}
		
	}
	
	
	
}
