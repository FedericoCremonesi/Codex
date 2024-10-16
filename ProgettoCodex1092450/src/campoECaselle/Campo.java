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
import sviluppoGioco.ColoreTesto;
import sviluppoGioco.Partita;

public class Campo implements campoECaselle.Resetter {
	
	public final static int DIM = 81;
	/*
	 * DIM è la dimensione di ognuno dei due array componenti la matrice campo
	 * la dimensione degli array è 81, ovvero la quantità delle caselle totali presenti in ognuno di questi
	 * le coordinate delle caselle che verranno visualizzate vanno da -40 a 40 e sono quindi 81 (considerando lo 0)
	 */
	
	private Casella[][] caselleDelCampo;
	//Creo una hashmap con tutte le risorse e gli oggetti
	HashMap<String, Integer> conteggioRisorseEOggetti = new HashMap<String, Integer>();
	
	
	/**
	 * Restituisce la casella del campo posizionata a determinate coordinate passate in ingresso
	 * @param i indica la riga
	 * @param j indica la colonna
	 */
	public Casella getCasellaDaCoordinate(int i, int j) {
		return caselleDelCampo[i][j];
	}
	
	
	/**
	 * Metodo costruttore del campo
	 */
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
	
	
	/**
	 * Funzione "ricorsiva" per contare tutte le risorse e gli oggetti sul campo
	 *  (non è propriamente ricorsiva perchè non è sè stessa a chiamarsi, ma il metodo analizzaAngoloPerConteggioSimboli, chiamato esclusivamente da questa)
	 * @param i indica la coordinata delle righe della casella in analisi
	 * @param j indica la coordinata delle colonne della casella in analisi
	 * @param inizioScorrimento è un booleano che indica se quella in analisi è la prima casella studiata,
	 * 							sarà sempre false quando il metodo verrà richiamato da sè stesso, true se chiamato dall'esterno
	 * @return Una HashMap con ogni simbolo del gioco (le keys, le chiavi), con associato il numero di volte che questo è visibile sul campo (i valori)
	 */
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
			analizzaAngoloPerConteggioSimboli(i,j, i-1,j-1, 0);
			
			//Analizzo l'angolo in alto a destra della faccia fronte
			analizzaAngoloPerConteggioSimboli(i,j, i-1,j+1, 1);
			
			//Analizzo l'angolo in basso a destra della faccia fronte
			analizzaAngoloPerConteggioSimboli(i,j, i+1,j+1, 3);
			
			//Appena prima di controllare l'ultimo angolo della carta, setto che sono già state contate tutte le risorse della carta
			((CasellaGiocabile) caselleDelCampo[i][j]).getCartaContenuta().setContatiSimboli(true);
			
			//Analizzo l'angolo in basso a sinistra della faccia fronte
			analizzaAngoloPerConteggioSimboli(i,j, i+1,j-1, 2);
			
		}
		
		//*se facessi il reset qui, come avevo ipotizzato, il campo verrebbe resettato alla fine di ogni carta (dunque sarebbe inutile avere un booleano che controlla di quali carte sono già stati contati i simboli)
		
		return conteggioRisorseEOggetti; //viene ritornata la hashmap in caso serva utilizzarla per altri motivi (es assegnare punti date determinate condizioni delle carte oro o obiettivo)
	}
	
	
	/**
	 * Metodo chiamato esclusivamente da contaRisorseEOggettiVisibili, serve per evitare di ripetere 4 volte le stesse linee di codice per
	 *  analizzare tutti i 4 angoli di una carta e aggiungere eventuali simboli visibili al conteggio.
	 *  Nel caso l'angolo in analisi sia coperto, è questa funzione a ri-chiamare contaRisorseEOggettiVisibili
	 * @param i è la coordinata delle righe della casella contenente la carta di cui si sta analizzando l'angolo
	 * @param j è la coordinata delle colonne della casella contenente la carta di cui si sta analizzando l'angolo
	 * @param iJump è la coord. delle righe della casella contenente la carta per cui si vuole ri-chiamare la funzionzione contaRisorseEOggettiVisibili (in caso l'angolo in analisi sia coperto)
	 * @param jJump è la coord. delle colonne della casella contenente la carta per cui si vuole ri-chiamare la funzionzione contaRisorseEOggettiVisibili (in caso l'angolo in analisi sia coperto)
	 * @param indiceAngolo indica quale angolo della carta si sta studiando (0, 1, 2, 3 a seconda che sia quello AltoSx, AltoDx, BassoSx, BassoDx)
	 */
	public void analizzaAngoloPerConteggioSimboli(int i,int j, int iJump,int jJump, int indiceAngolo) {
		if( ((CasellaGiocabile) caselleDelCampo[i][j]).getCartaContenuta().ottieniFacciaSuCuiGiocata().getAngoloDaIndice(indiceAngolo) instanceof AngoloVisibile ) { //Caso: angolo nascosto
			if( ((AngoloVisibile) ((CasellaGiocabile) caselleDelCampo[i][j]).getCartaContenuta().ottieniFacciaSuCuiGiocata().getAngoloDaIndice(indiceAngolo)).isCoperto() ) { //Caso: angolo coperto
				contaRisorseEOggettiVisibili(iJump,jJump,false); //chiamo ricorsivamente la funzione, stavolta dicendogli di controllare la casella (giocabile) in cui si trova la carta che copre questo angolo
			} else if( ((AngoloVisibile) ((CasellaGiocabile) caselleDelCampo[i][j]).getCartaContenuta().ottieniFacciaSuCuiGiocata().getAngoloDaIndice(indiceAngolo)).getContenuto()!=null ) { //Caso: angolo vuoto
				incrementaConteggioDatoSimbolo( ((AngoloVisibile) ((CasellaGiocabile) caselleDelCampo[i][j]).getCartaContenuta().ottieniFacciaSuCuiGiocata().getAngoloDaIndice(indiceAngolo)).getContenuto() );
			}
		}
	}
	
	
	/**
	 * Metodo per incrementare il conteggio di un simbolo visibile sul campo, è chiamato da contaRisorseEOggettiVisibili se è un simbolo presente al centro di una carta giocata sul retro, altrimenti da
	 *  analizzaAngoloPerConteggioSimboli se si trova in uno dei 4 angoli della faccia di gioco
	 * @param simbolo
	 */
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
	
	
	/**
	 * Metodo derivante dall'interfaccia campoECaselle.Resetter implementata da questa classe.
	 *  Serve per "resettare" il campo di gioco prima di un nuovo conteggio dei simboli, dichiarando che nessuna delle carte presenti sul campo va "saltata" perchè già presa in analisi
	 */
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
		//System.out.println("Campo resettato (risorse)");
		//(Usato per testing)
	}
	
	
	/**
	 * Stampa un elenco puntato con tutti i simboli del gioco (risorse e oggetti), con associata la rispettiva quantità di simboli visibili sul campo
	 */
	public void stampaConteggioRisorseEOggettiVisibili() {
		System.out.println("\nSimboli di ogni tipo visibili sul tuo campo:");
		for (String risorsaOppureOggetto : conteggioRisorseEOggetti.keySet()) { //Utilizzo keyset per scorrere gli elementi della hashmap
			String coloreTesto = Risorsa.ottieniStringCodiceColoreDaRisorsa(risorsaOppureOggetto);
			
			System.out.println(" ➤ "+coloreTesto+risorsaOppureOggetto.toLowerCase()+Risorsa.CODICE_RESET_COLORE+": "+conteggioRisorseEOggetti.get(risorsaOppureOggetto)); //con get accedo al valore intero associato ad ogni stringa
		}
	}


	/**
	 * Stampa a schermo l'intero campo di gioco illustrando le caselle giocabili vuote come quadratini bianchi, e quelle con una carta all'interno colorate del rispettivo colore
	 *  La dimensione del campo visualizzato aumenta con lo scorrere dei turni, per evitare di stampare sin da subito un campo 81x81
	 *  -> questo è un caso limite verificatosi in cui si giochi in due, si esauriscano tutte le carte di entrambi i mazzi (e in mano, cosa non possibile secondo le regole del gioco)
	 *  e si giochino tutte le carte in una singola diagonale, che parte dal centro e va verso uno dei 4 angoli del campo
	 */
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
	
	
	/**
	 * Funzione chiamata da stampaMatriceCampoAQuadratini, serve per stampare le coordinate del campo lasciando il corretto numero di spazi prima del numero in modo che siano incolonnate bene
	 * @param coordinataDaStampare
	 */
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
	
	
	/**
	 * Funzione utile per far visualizzare al giocatore le singole carte sul suo campo di gioco, con eventuali simboli, angoli nascosti, coperti ecc.
	 *  Viene eseguito uno "zoom" del campo di gioco attorno ad una casella indicata dall'utente
	 * @param coordinateCasella è la coppia di coordinate (i e j) che indicano la casella di cui vengono stampate a schermo le adiacenti
	 */
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
	
	
	/**
	 * Metodo che controlla che sul campo ci siano abbastanza risorse per poter giocare una carta oro sulla faccia frontale (questo ovviamente dipende dalle risorse necessarie
	 *  e dalla loro rispettiva quantità indicate sulla carta oro stessa)
	 * @param cartaDaGiocare è la carta che si intende giocare
	 * @return Vero o Falso a seconda che si abbiano abbastanza risorse per giocare la carte in questione
	 */
	public boolean controllaRisorseNecessariePerCartaOro(CartaOro cartaDaGiocare) {
		System.out.println(ColoreTesto.CODICE_COLORE_NERO+"Verifica dei requisiti necessari per giocare questa carta oro sulla faccia frontale..."+ColoreTesto.CODICE_RESET_COLORE);
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
			System.out.println(ColoreTesto.CODICE_COLORE_NERO+" ➤ "+ColoreTesto.CODICE_COLORE_VERDE+"Ok! Hai abbastanza requisiti"+ColoreTesto.CODICE_RESET_COLORE);
			return true;
		} else {
			System.out.println(ColoreTesto.CODICE_COLORE_NERO+" ➤ "+ColoreTesto.CODICE_COLORE_ROSSO+"Non hai abbastanza risorse visibili sul campo per giocare la carta sulla faccia frontale"+ColoreTesto.CODICE_RESET_COLORE);
			cartaDaGiocare.setFacciaDiGioco("non ancora giocata"); //devo settare di nuovo la faccia di gioco a quella default,
																   //altrimenti rimarrà selezionata quella frontale per tutto il corso della partita,
																   //quindi se il giocatore sceglie di cambiare carta da giocare (cioè non gioca questa sul retro),
																   //al suo successivo turno troverà stampata nella sua mano solo la faccia frontale di questa carta
			return false;
		}
	}
	
	
	/**
	 * Metodo per controllare che la casella in cui si vuole giocare una carta sia idonea, ovvero rispetti 3 parametri:
	 *  1) Sia libera
	 *  2) Ci sia almeno un angolo (visibile) a cui agganciarsi
	 *  3) La carta che si vuole giocare non vada a sovrapporsi a nessun angolo nascosto
	 * @param casellaInCuiPosizionareCarta
	 * @return Vero o Falso a seconda che la casella di gioco sia idonea
	 */
	public boolean controllaCondizioniGiocataCartaSuCampo(CasellaGiocabile casellaInCuiPosizionareCarta) {
		System.out.println(ColoreTesto.CODICE_COLORE_NERO+"Verificando le condizioni per giocare la carta sul campo..."+ColoreTesto.CODICE_RESET_COLORE);
		
		//Controllo che la casella selezionata sia libera per posizionare la carta
		if( !(casellaInCuiPosizionareCarta.isEmpty()) ) {
			System.out.println(ColoreTesto.CODICE_COLORE_NERO+" ➤ "+ColoreTesto.CODICE_COLORE_ROSSO+"La casella selezionata risulta essere già occupata"+ColoreTesto.CODICE_RESET_COLORE);
			return false;
		} else {
			//System.out.println(ColoreTesto.CODICE_COLORE_NERO+" ➤ "+ColoreTesto.CODICE_COLORE_VERDE+"La casella selezionata è libera"+ColoreTesto.CODICE_RESET_COLORE);
			//(Usato per testing)
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
			angoloDaControllare = casellaDaControllare.getCartaContenuta().ottieniFacciaSuCuiGiocata().getAngoloDaIndice(3);
			
			if( (angoloDaControllare instanceof AngoloVisibile) ) {
				//System.out.println("In alto a sinistra: c'è angolo visibile non coperto a cui agganciarsi");
				//(Usato per testing)
				almenoUnAngoloVisibile = true;
			} else if(angoloDaControllare instanceof AngoloNascosto) {
				tutteCaselleAdiacentiLibereOConAngoliVisibili = false;
			}
		}
		
		casellaDaControllare = ((CasellaGiocabile) caselleDelCampo[x-1][y+1]);
		if( !( casellaDaControllare.isEmpty() ) ) {
			String facciaDiGiocoCarta = casellaDaControllare.getCartaContenuta().getFacciaDiGioco();
			Angolo angoloDaControllare = null;
			angoloDaControllare = casellaDaControllare.getCartaContenuta().ottieniFacciaSuCuiGiocata().getAngoloDaIndice(2);
			
			if( (angoloDaControllare instanceof AngoloVisibile) ) {
				//System.out.println("In alto a destra: c'è angolo visibile non coperto a cui agganciarsi");
				//(Usato per testing)
				almenoUnAngoloVisibile = true;
			} else if(angoloDaControllare instanceof AngoloNascosto) {
				tutteCaselleAdiacentiLibereOConAngoliVisibili = false;
			}
		}
		
		casellaDaControllare = ((CasellaGiocabile) caselleDelCampo[x+1][y-1]);
		if( !( casellaDaControllare.isEmpty() ) ) {
			String facciaDiGiocoCarta = casellaDaControllare.getCartaContenuta().getFacciaDiGioco();
			Angolo angoloDaControllare = null;
			angoloDaControllare = casellaDaControllare.getCartaContenuta().ottieniFacciaSuCuiGiocata().getAngoloDaIndice(1);
			
			if( (angoloDaControllare instanceof AngoloVisibile) ) {
				//System.out.println("In basso a sinistra: c'è angolo visibile non coperto a cui agganciarsi");
				//(Usato per testing)
				almenoUnAngoloVisibile = true;
			} else if(angoloDaControllare instanceof AngoloNascosto) {
				tutteCaselleAdiacentiLibereOConAngoliVisibili = false;
			}
		}
		
		casellaDaControllare = ((CasellaGiocabile) caselleDelCampo[x+1][y+1]);
		if( !( casellaDaControllare.isEmpty() ) ) {
			String facciaDiGiocoCarta = casellaDaControllare.getCartaContenuta().getFacciaDiGioco();
			Angolo angoloDaControllare = null;
			angoloDaControllare = casellaDaControllare.getCartaContenuta().ottieniFacciaSuCuiGiocata().getAngoloDaIndice(0);
			
			if( (angoloDaControllare instanceof AngoloVisibile) ) {
				//System.out.println("In basso a destra: c'è angolo visibile non coperto a cui agganciarsi");
				//(Usato per testing)
				almenoUnAngoloVisibile = true;
			} else if(angoloDaControllare instanceof AngoloNascosto) {
				tutteCaselleAdiacentiLibereOConAngoliVisibili = false;
			}
		}
		
		
		if(!almenoUnAngoloVisibile) {
			System.out.println(ColoreTesto.CODICE_COLORE_NERO+"  ➤ "+ColoreTesto.CODICE_COLORE_ROSSO+"Non risulta esserci nessun angolo a cui la carta possa agganciarsi"+ColoreTesto.CODICE_RESET_COLORE);
			return false;
		} else {
			//System.out.println(ColoreTesto.CODICE_COLORE_NERO+"  ➤ "+ColoreTesto.CODICE_COLORE_VERDE+"E' stato trovato almeno un angolo (visibile) a cui la carta che si sta giocando può agganciarsi"+ColoreTesto.CODICE_RESET_COLORE); //*
			//(Usato per testing)
		}
		
		if(!tutteCaselleAdiacentiLibereOConAngoliVisibili) {
			System.out.println(ColoreTesto.CODICE_COLORE_NERO+"   ➤ "+ColoreTesto.CODICE_COLORE_ROSSO+"Giocando la carta in questa posizione, questa andrà a sovrapporsi ad un angolo nascosto, ciò non è ammesso"+ColoreTesto.CODICE_RESET_COLORE); //**
			return false;
		} else {
			//System.out.println(ColoreTesto.CODICE_COLORE_NERO+"   ➤ "+ColoreTesto.CODICE_COLORE_VERDE+"Tutte le caselle adiacenti sono libere oppure gli angoli su cui andrà a posizionarsi la carta sono visibili"+ColoreTesto.CODICE_RESET_COLORE);
			//(Usato per testing)
		}
		
		
		/*
		 * se sono stati superati tutti gli if precedenti senza eseguire il loro interno (cioè eseguendo solo gli else),
		 * la funzione ritornerà true a simboleggiare che sia tutto ok e che si può giocare la carta nella casella selezionata
		 */
		return true;
	}
	
	
	/**
	 * Metodo per coprire gli angoli di eventuali carte posizionate nelle caselle adiacenti a quella in cui è appena stata giocata una carta
	 * @param casellaInCuiPosizionataCarta
	 * @return Il numero di angoli coperti con la giocata di questa carta (utile per assegnare i punti di alcune carte oro)
	 */
	public int copriAngoliAdiacentiACartaGiocata(CasellaGiocabile casellaInCuiPosizionataCarta) {
		int numeroAngoliCopertiConGiocata=0;
		
		int x = casellaInCuiPosizionataCarta.getX();
		int y = casellaInCuiPosizionataCarta.getY();
		
		if(copriAngolo(x-1, y-1, 3)) {
			numeroAngoliCopertiConGiocata++; //Se è stato coperto un angolo, si incrementa il conteggio degli angoli coperti
		}
		
		if(copriAngolo(x-1, y+1, 2)) {
			numeroAngoliCopertiConGiocata++;
		}
		
		if(copriAngolo(x+1, y-1, 1)) {
			numeroAngoliCopertiConGiocata++;
		}
		
		if(copriAngolo(x+1, y+1, 0)) {
			numeroAngoliCopertiConGiocata++;
		}	
				
		//System.out.println("Totale angoli coperti con la giocata di questa carta: "+numeroAngoliCopertiConGiocata);
		//(Usato per testing)
		
		return numeroAngoliCopertiConGiocata;
	}
	
	
	/**
	 * Metodo chiamato esclusivamente da copriAngoliAdiacentiACartaGiocata, serve per evitare ripetizioni di codice
	 *  (non viene scritto 4 volte lo stesso blocco di codice cambiando solo le coordinate della casella e l'indice dell'angolo)
	 * @param coordX è coordinata delle righe della casella contenente la carta di cui si deve coprire un angolo
	 * @param coordY è coordinata delle colonne della casella contenente la carta di cui si deve coprire un angolo
	 * @param indiceAngolo indica quale dei 4 angoli va coperto (0, 1, 2, 3 a seconda che sia quello AltoSx, AltoDx, BassoSx, BassoDx)
	 * @return Vero se è stato coperto l'angolo in analisi, Falso se ciò non è accaduto (ovvero nella casella adiacenti in analisi non è presente una carta di cui coprire un angolo)
	 */
	public boolean copriAngolo(int coordX, int coordY, int indiceAngolo) {
		CasellaGiocabile casellaACuiCoprireAngolo;
		
		casellaACuiCoprireAngolo = ((CasellaGiocabile) caselleDelCampo[coordX][coordY]);
		if( !( casellaACuiCoprireAngolo.isEmpty() ) ) {
			String facciaDiGiocoCarta = casellaACuiCoprireAngolo.getCartaContenuta().getFacciaDiGioco();
			Angolo angoloDaControllare = null;
			if(facciaDiGiocoCarta.equals("FRONTE")) {
				angoloDaControllare = casellaACuiCoprireAngolo.getCartaContenuta().getFronte().getAngoloDaIndice(indiceAngolo);
			}
			if(facciaDiGiocoCarta.equals("RETRO")) {
				angoloDaControllare = casellaACuiCoprireAngolo.getCartaContenuta().getRetro().getAngoloDaIndice(indiceAngolo);
			}
			
			/*
			 * Non serve controllare che l'angolo sia visibile perchè ciò è già stato controllato prima, nelle condizioni della giocata della carta su campo
			 * quindi non uso if( (angoloDaControllare instanceof AngoloVisibile) )
			 */
			//System.out.println("E' stato coperto un angolo");
			//(Usato per testing)
			((AngoloVisibile) angoloDaControllare).setCoperto(true);
			return true;
		} else {
			return false;
		}
	}
	
}
