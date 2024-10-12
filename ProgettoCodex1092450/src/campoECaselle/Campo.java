package campoECaselle;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import carte.CartaOro;
import facceEAngoli.AngoloVisibile;
import facceEAngoli.Risorsa;
import facceEAngoli.Angolo;
import facceEAngoli.AngoloNascosto;
import sviluppoGioco.Partita;

public class Campo implements campoECaselle.Reset {
	
	public final static int DIM = 81;
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
	public HashMap<String, Integer> contaRisorseEOggettiVisibili(int i, int j, boolean inizioScorrimento) {
		
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
			//Inoltre "resetto" tutte le carte per fare in modo di poter contare i simboli come previsto*
			resetConteggioSimboliOControlloDisposizione(this);
		}
				
		if( ((CasellaGiocabile) caselleDelCampo[i][j]).isEmpty() ) {
			return null;
		} else if (((CasellaGiocabile) caselleDelCampo[i][j]).getCartaContenuta().isContatiSimboli()) {
			return null; //se della carta ho già contato tutti i simboli, non li conto ancora
		} else {
			
			//Inizio a contare gli eventuali simboli al centro...
			if( ((CasellaGiocabile) caselleDelCampo[i][j]).getCartaContenuta().getFacciaDiGioco().equals("RETRO") ) {
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
			
			//...poi conto quelli negli angoli in senso orario
			//Analizzo l'angolo in alto a sinistra della faccia fronte
			if( ((CasellaGiocabile) caselleDelCampo[i][j]).getCartaContenuta().ottieniFacciaSuCuiGiocata().getAngoloAltoSx() instanceof AngoloVisibile ) { //gestisco caso: angolo nascosto
				if( ((AngoloVisibile) ((CasellaGiocabile) caselleDelCampo[i][j]).getCartaContenuta().ottieniFacciaSuCuiGiocata().getAngoloAltoSx()).isCoperto() ) { //gestistisco caso: angolo coperto
					contaRisorseEOggettiVisibili(i-1,j-1,false); //chiamo ricorsivamente la funzione, stavolta dicendogli di Controllare la casella (giocabile) in alto a sx rispetto a quella in analisi adesso
				} else if( ((AngoloVisibile) ((CasellaGiocabile) caselleDelCampo[i][j]).getCartaContenuta().ottieniFacciaSuCuiGiocata().getAngoloAltoSx()).getContenuto()!=null ) { //gestistisco caso: angolo vuoto
					incrementaConteggioDatoSimbolo( ((AngoloVisibile) ((CasellaGiocabile) caselleDelCampo[i][j]).getCartaContenuta().ottieniFacciaSuCuiGiocata().getAngoloAltoSx()).getContenuto() );
				}
			}
			
			//Analizzo l'angolo in alto a destra della faccia fronte
			if( ((CasellaGiocabile) caselleDelCampo[i][j]).getCartaContenuta().ottieniFacciaSuCuiGiocata().getAngoloAltoDx() instanceof AngoloVisibile ) {
				if( ((AngoloVisibile) ((CasellaGiocabile) caselleDelCampo[i][j]).getCartaContenuta().ottieniFacciaSuCuiGiocata().getAngoloAltoDx()).isCoperto() ) {
					contaRisorseEOggettiVisibili(i-1,j+1,false);
				} else if( ((AngoloVisibile) ((CasellaGiocabile) caselleDelCampo[i][j]).getCartaContenuta().ottieniFacciaSuCuiGiocata().getAngoloAltoDx()).getContenuto()!=null ) {
					incrementaConteggioDatoSimbolo( ((AngoloVisibile) ((CasellaGiocabile) caselleDelCampo[i][j]).getCartaContenuta().ottieniFacciaSuCuiGiocata().getAngoloAltoDx()).getContenuto() );
				}
			}
			
			//Analizzo l'angolo in basso a destra della faccia fronte
			if( ((CasellaGiocabile) caselleDelCampo[i][j]).getCartaContenuta().ottieniFacciaSuCuiGiocata().getAngoloBassoDx() instanceof AngoloVisibile ) {
				if( ((AngoloVisibile) ((CasellaGiocabile) caselleDelCampo[i][j]).getCartaContenuta().ottieniFacciaSuCuiGiocata().getAngoloBassoDx()).isCoperto() ) {
					contaRisorseEOggettiVisibili(i+1,j+1,false);
				} else if( ((AngoloVisibile) ((CasellaGiocabile) caselleDelCampo[i][j]).getCartaContenuta().ottieniFacciaSuCuiGiocata().getAngoloBassoDx()).getContenuto()!=null ) {
					incrementaConteggioDatoSimbolo( ((AngoloVisibile) ((CasellaGiocabile) caselleDelCampo[i][j]).getCartaContenuta().ottieniFacciaSuCuiGiocata().getAngoloBassoDx()).getContenuto() );
				}
			}
			
			//Appena prima di controllare l'ultimo angolo della carta, setto che sono già state contate tutte le risorse della carta
			((CasellaGiocabile) caselleDelCampo[i][j]).getCartaContenuta().setContatiSimboli(true);
			
			//Analizzo l'angolo in basso a sinistra della faccia fronte
			if( ((CasellaGiocabile) caselleDelCampo[i][j]).getCartaContenuta().ottieniFacciaSuCuiGiocata().getAngoloBassoSx() instanceof AngoloVisibile ) {
				if( ((AngoloVisibile) ((CasellaGiocabile) caselleDelCampo[i][j]).getCartaContenuta().ottieniFacciaSuCuiGiocata().getAngoloBassoSx()).isCoperto() ) {
					contaRisorseEOggettiVisibili(i+1,j-1,false);
				} else if( ((AngoloVisibile) ((CasellaGiocabile) caselleDelCampo[i][j]).getCartaContenuta().ottieniFacciaSuCuiGiocata().getAngoloBassoSx()).getContenuto()!=null ) {
					incrementaConteggioDatoSimbolo( ((AngoloVisibile) ((CasellaGiocabile) caselleDelCampo[i][j]).getCartaContenuta().ottieniFacciaSuCuiGiocata().getAngoloBassoSx()).getContenuto() );
				}
			}
			
		}
		
		//*se facessi il reset qui, come avevo ipotizzato, il campo verrebbe resettato alla fine di ogni carta (dunque sarebbe inutile avere un booleano che controlla di quali carte sono già stati contati i simboli)
		
		return conteggioRisorseEOggetti; //viene ritornata la hashmap in caso serva utilizzarla per altri motivi (es assegnare punti date determinate condizioni delle carte oro o obiettivo)
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
	
	
	//Metodo da interfaccia implementata
	@Override
	public void resetConteggioSimboliOControlloDisposizione(Campo campo) {
		for(int i=0; i<Campo.DIM; i++) {
			for(int j=0; j<Campo.DIM; j++) {
				if(campo.getCasellaDaCoordinate(i,j) instanceof CasellaGiocabile) {
					if(!(((CasellaGiocabile) campo.getCasellaDaCoordinate(i,j)).isEmpty())) {
						((CasellaGiocabile) campo.getCasellaDaCoordinate(i,j)).getCartaContenuta().setContatiSimboli(false);
					}
				}
			}
		}
		System.out.println("Campo resettato (risorse)");
	}
	
	
	public void stampaConteggioRisorseEOggettiVisibili() {
		System.out.println("\nSul tuo campo sono visibili:");
		for (String risorsaOppureOggetto : conteggioRisorseEOggetti.keySet()) { //Utilizzo keyset per scorrere gli elementi della hashmap
			String coloreTesto = Risorsa.ottieniStringCodiceColoreDaRisorsa(risorsaOppureOggetto);
			
			System.out.println("➤ "+" Simboli di tipo "+coloreTesto+risorsaOppureOggetto.toLowerCase()+Risorsa.CODICE_RESET_COLORE+": "+conteggioRisorseEOggetti.get(risorsaOppureOggetto)); //con get accedo al valore intero associato ad ogni stringa
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
	
	
	public void stampa5x5AttornoACartaInCampo(int[] coordinateCasella) {
		int partenzaI = coordinateCasella[0]-2; //voglio stampare anche la carta nella seconda riga sopra rispetto a quella alle coordinate comunicate, quindi calcolo -2
		int partenzaJ = coordinateCasella[1]-2; //voglio stampare anche la carta nella seconda colonna a sinistra rispetto a quella alle coordinate comunicate, quindi calcolo -2
		System.out.println();
		
		//Con questi 3 cicli for stampo una "matrice" 5x5 di caselle (giocabili e non) affiancate
		
		for(int i=partenzaI; i<=(partenzaI+4); i++) //Scorre le righe della matrice Campo
		{
			for(Integer k=0; k<13; k++) //Scorre le linee delle singole carte
			{
				for(int j=partenzaJ; j<=(partenzaJ+4); j++) //Scorre le colonne della matrice Campo
				{
					caselleDelCampo[i][j].stampaLineaCasellaIngrandita(k);
				}
				System.out.println(); //va a capo una volta finito di stampare la linea k di tutte le 5 caselle affiancate
			}
		}
		
	}
	
	
	public boolean controllaRisorseNecessariePerCartaOro(CartaOro cartaDaGiocare) {
		System.out.println("Verifica dei requisiti necessari per giocare questa carta oro sulla faccia frontale...");
		contaRisorseEOggettiVisibili(40,40,true);
		
		boolean ok1=false;
		boolean ok2=false;
		
		if( (conteggioRisorseEOggetti.get(cartaDaGiocare.getRisorsaNecessaria1().toString()))>=cartaDaGiocare.getQuantitàRisorsaNecessaria1() ) {
			ok1 = true;
		}
		
		if( (conteggioRisorseEOggetti.get(cartaDaGiocare.getRisorsaNecessaria2())) == null) { //questo è il caso in cui la carta oro ha un solo tipo di risorsa necessaria
			ok2 = true;
		} else if( (conteggioRisorseEOggetti.get(cartaDaGiocare.getRisorsaNecessaria2().toString()))>=cartaDaGiocare.getQuantitàRisorsaNecessaria2() ) {
			ok2 = true;
		}
		
		if(ok1 && ok2) {
			System.out.println("Ok! Hai abbastanza requisiti");
			return true;
		} else {
			System.out.println("Non hai abbastanza risorse visibili sul campo per giocare la carta sulla faccia frontale");
			return false;
		}
	}
	
	
	public boolean controllaCondizioniGiocataCartaSuCampo(CasellaGiocabile casellaInCuiPosizionareCarta) {
		System.out.println("Verificando le condizioni per giocare la carta sul campo...");
		
		//Controllo che la casella selezionata sia libera per posizionare la carta
		if( !(casellaInCuiPosizionareCarta.isEmpty()) ) {
			System.out.println(" ➤"+"\u001B[31m"+"La casella selezionata risulta essere già occupata"+"\u001B[0m");
			return false;
		} else {
			System.out.println(" ➤"+"\u001B[32m"+"La casella selezionata è libera, ulteriori controlli in corso..."+"\u001B[0m");
		}
		
		
		//Controllo altre due condizioni (scritte nelle variabili qui sotto) per posizionare la carta
			/*
			 * tra le condizioni non verifico che gli angoli visibili siano anche non coperti,
			 * ciò non è necessario perchè il caso in cui gli angoli visibili risultano coperti è solo quello in cui la casella presa in considerazione sia già occupata
			 * (e questo ho già verificato che non fosse vero in precedenza)
			 */
		boolean almenoUnAngoloVisibile = false; //basta un angolo visibile per rendere true questa variabile *
		boolean tutteCaselleAdiacentiLibereOConAngoliVisibili = true; //basta una casella occupata con angolo nascosto per rendere false questa variabile **
		
		int x = casellaInCuiPosizionareCarta.getX();
		int y = casellaInCuiPosizionareCarta.getY();
		
		CasellaGiocabile casellaDaControllare;
		
		casellaDaControllare = ((CasellaGiocabile) caselleDelCampo[x-1][y-1]);
		if( !( casellaDaControllare.isEmpty() ) ) {
			String facciaDiGiocoCarta = casellaDaControllare.getCartaContenuta().getFacciaDiGioco();
			Angolo angoloDaControllare = null;
			angoloDaControllare = casellaDaControllare.getCartaContenuta().ottieniFacciaSuCuiGiocata().getAngoloBassoDx();
			
			if( (angoloDaControllare instanceof AngoloVisibile) ) {
				System.out.println("In alto a sinistra: c'è angolo visibile non coperto a cui agganciarsi");
				almenoUnAngoloVisibile = true;
			} else if(angoloDaControllare instanceof AngoloNascosto) {
				tutteCaselleAdiacentiLibereOConAngoliVisibili = false;
			}
		}
		
		casellaDaControllare = ((CasellaGiocabile) caselleDelCampo[x-1][y+1]);
		if( !( casellaDaControllare.isEmpty() ) ) {
			String facciaDiGiocoCarta = casellaDaControllare.getCartaContenuta().getFacciaDiGioco();
			Angolo angoloDaControllare = null;
			angoloDaControllare = casellaDaControllare.getCartaContenuta().ottieniFacciaSuCuiGiocata().getAngoloBassoSx();
			
			if( (angoloDaControllare instanceof AngoloVisibile) ) {
				System.out.println("In alto a destra: c'è angolo visibile non coperto a cui agganciarsi");
				almenoUnAngoloVisibile = true;
			} else if(angoloDaControllare instanceof AngoloNascosto) {
				tutteCaselleAdiacentiLibereOConAngoliVisibili = false;
			}
		}
		
		casellaDaControllare = ((CasellaGiocabile) caselleDelCampo[x+1][y-1]);
		if( !( casellaDaControllare.isEmpty() ) ) {
			String facciaDiGiocoCarta = casellaDaControllare.getCartaContenuta().getFacciaDiGioco();
			Angolo angoloDaControllare = null;
			angoloDaControllare = casellaDaControllare.getCartaContenuta().ottieniFacciaSuCuiGiocata().getAngoloAltoDx();
			
			if( (angoloDaControllare instanceof AngoloVisibile) ) {
				System.out.println("In basso a sinistra: c'è angolo visibile non coperto a cui agganciarsi");
				almenoUnAngoloVisibile = true;
			} else if(angoloDaControllare instanceof AngoloNascosto) {
				tutteCaselleAdiacentiLibereOConAngoliVisibili = false;
			}
		}
		
		casellaDaControllare = ((CasellaGiocabile) caselleDelCampo[x+1][y+1]);
		if( !( casellaDaControllare.isEmpty() ) ) {
			String facciaDiGiocoCarta = casellaDaControllare.getCartaContenuta().getFacciaDiGioco();
			Angolo angoloDaControllare = null;
			angoloDaControllare = casellaDaControllare.getCartaContenuta().ottieniFacciaSuCuiGiocata().getAngoloAltoSx();
			
			if( (angoloDaControllare instanceof AngoloVisibile) ) {
				System.out.println("In basso a destra: c'è angolo visibile non coperto a cui agganciarsi");
				almenoUnAngoloVisibile = true;
			} else if(angoloDaControllare instanceof AngoloNascosto) {
				tutteCaselleAdiacentiLibereOConAngoliVisibili = false;
			}
		}
		
		
		if(!almenoUnAngoloVisibile) {
			System.out.println("  ➤"+"\u001B[31m"+"Non risulta esserci nessun angolo a cui la carta possa agganciarsi"+"\u001B[0m");
			return false;
		} else {
			System.out.println("  ➤"+"\u001B[32m"+"E' stato trovato almeno un angolo (visibile) a cui la carta che si sta giocando può agganciarsi"+"\u001B[0m"); //*
		}
		
		if(!tutteCaselleAdiacentiLibereOConAngoliVisibili) {
			System.out.println("   ➤"+"\u001B[31m"+"Giocando la carta in questa posizione, questa andrà a sovrapporsi ad un angolo nascosto, ciò non è ammesso"+"\u001B[0m"); //**
			return false;
		} else {
			System.out.println("   ➤"+"\u001B[32m"+"Tutte le caselle adiacenti sono libere oppure gli angoli su cui andrà a posizionarsi la carta sono visibili"+"\u001B[0m");
		}
		
		
		/*
		 * se sono stati superati tutti gli if precedenti senza eseguire il loro interno (cioè eseguendo solo gli else),
		 * la funzione ritornerà true a simboleggiare che sia tutto ok e che si può giocare la carta nella casella selezionata
		 */
		return true;
	}
	
	
	public int copriAngoliAdiacentiACartaGiocata(CasellaGiocabile casellaInCuiPosizionataCarta) {
		int numeroAngoliCopertiConGiocata=0;
		
		int x = casellaInCuiPosizionataCarta.getX();
		int y = casellaInCuiPosizionataCarta.getY();
		
		CasellaGiocabile casellaACuiCoprireAngolo;
		
		
		casellaACuiCoprireAngolo = ((CasellaGiocabile) caselleDelCampo[x-1][y-1]);
		if( !( casellaACuiCoprireAngolo.isEmpty() ) ) {
			String facciaDiGiocoCarta = casellaACuiCoprireAngolo.getCartaContenuta().getFacciaDiGioco();
			Angolo angoloDaControllare = null;
			if(facciaDiGiocoCarta.equals("FRONTE")) {
				angoloDaControllare = casellaACuiCoprireAngolo.getCartaContenuta().getFronte().getAngoloBassoDx();
			}
			if(facciaDiGiocoCarta.equals("RETRO")) {
				angoloDaControllare = casellaACuiCoprireAngolo.getCartaContenuta().getRetro().getAngoloBassoDx();
			}
			
			/*
			 * Non serve controllare che l'angolo sia visibile perchè ciò è già stato controllato prima, nelle condizioni della giocata della carta su campo
			 * quindi non uso if( (angoloDaControllare instanceof AngoloVisibile) )
			 */
			System.out.println("E' stato coperto un angolo");
			((AngoloVisibile) angoloDaControllare).setCoperto(true);
			numeroAngoliCopertiConGiocata++;
		}
		
		//Come prima, ma cambiate 3 cose: (*) e (**) e (***)
		casellaACuiCoprireAngolo = ((CasellaGiocabile) caselleDelCampo[x-1][y+1]); //(*)
		if( !( casellaACuiCoprireAngolo.isEmpty() ) ) {
			String facciaDiGiocoCarta = casellaACuiCoprireAngolo.getCartaContenuta().getFacciaDiGioco();
			Angolo angoloDaControllare = null;
			if(facciaDiGiocoCarta.equals("FRONTE")) {
				angoloDaControllare = casellaACuiCoprireAngolo.getCartaContenuta().getFronte().getAngoloBassoSx(); //(**)
			}
			if(facciaDiGiocoCarta.equals("RETRO")) {
				angoloDaControllare = casellaACuiCoprireAngolo.getCartaContenuta().getRetro().getAngoloBassoSx(); //(***)
			}
			
			System.out.println("E' stato coperto un angolo");
			((AngoloVisibile) angoloDaControllare).setCoperto(true);
			numeroAngoliCopertiConGiocata++;
		}
		
		//Come prima, ma cambiate 3 cose: (*) e (**) e (***)
		casellaACuiCoprireAngolo = ((CasellaGiocabile) caselleDelCampo[x+1][y-1]); //(*)
		if( !( casellaACuiCoprireAngolo.isEmpty() ) ) {
			String facciaDiGiocoCarta = casellaACuiCoprireAngolo.getCartaContenuta().getFacciaDiGioco();
			Angolo angoloDaControllare = null;
			if(facciaDiGiocoCarta.equals("FRONTE")) {
				angoloDaControllare = casellaACuiCoprireAngolo.getCartaContenuta().getFronte().getAngoloAltoDx(); //(**)
			}
			if(facciaDiGiocoCarta.equals("RETRO")) {
				angoloDaControllare = casellaACuiCoprireAngolo.getCartaContenuta().getRetro().getAngoloAltoDx(); //(***)
			}
			
			System.out.println("E' stato coperto un angolo");
			((AngoloVisibile) angoloDaControllare).setCoperto(true);
			numeroAngoliCopertiConGiocata++;
		}

		//Come prima, ma cambiate 3 cose: (*) e (**) e (***)
		casellaACuiCoprireAngolo = ((CasellaGiocabile) caselleDelCampo[x+1][y+1]); //(*)
		if( !( casellaACuiCoprireAngolo.isEmpty() ) ) {
			String facciaDiGiocoCarta = casellaACuiCoprireAngolo.getCartaContenuta().getFacciaDiGioco();
			Angolo angoloDaControllare = null;
			if(facciaDiGiocoCarta.equals("FRONTE")) {
				angoloDaControllare = casellaACuiCoprireAngolo.getCartaContenuta().getFronte().getAngoloAltoSx(); //(**)
			}
			if(facciaDiGiocoCarta.equals("RETRO")) {
				angoloDaControllare = casellaACuiCoprireAngolo.getCartaContenuta().getRetro().getAngoloAltoSx(); //(***)
			}
			
			System.out.println("E' stato coperto un angolo");
			((AngoloVisibile) angoloDaControllare).setCoperto(true);
			numeroAngoliCopertiConGiocata++;
		}
		
		
		System.out.println("Totale angoli coperti con la giocata di questa carta: "+numeroAngoliCopertiConGiocata);
		
		return numeroAngoliCopertiConGiocata;
	}
	
}
