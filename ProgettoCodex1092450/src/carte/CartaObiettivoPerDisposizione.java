package carte;

import campoECaselle.Campo;
import campoECaselle.CasellaGiocabile;

public class CartaObiettivoPerDisposizione extends CartaObiettivo implements campoECaselle.Resetter {
	
	private String disposizione;

	private ColoreCarta coloreCarte1;
	private ColoreCarta coloreCarte2;
	
	private String[] insiemeLineeFacciaCarta =
		   {ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+"╭------------------------------------╮"+ColoreCarta.CODICE_RESET_COLORE,	//linea 1, indiceArray 0 (uguale in tutte carte obiettivo)
			ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+"|                                    |"+ColoreCarta.CODICE_RESET_COLORE,	//linea 2, indiceArray 1 (uguale in tutte carte obiettivo)
													  "",																		//linea 3, indiceArray 2 (costruisco in costruttori)
			ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+"|                                    |"+ColoreCarta.CODICE_RESET_COLORE,	//linea 4, indiceArray 3 (uguale in tutte carte obiettivo)
			ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+"|           per ogni set di          |"+ColoreCarta.CODICE_RESET_COLORE,	//linea 5, indiceArray 4 (uguale in tutte carte obiettivo)
			ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+"|                                    |"+ColoreCarta.CODICE_RESET_COLORE,	//linea 6, indiceArray 5 (uguale in tutte carte obiettivo)
			ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+"|        carte disposte così:        |"+ColoreCarta.CODICE_RESET_COLORE,	//linea 7, indiceArray 6 (uguale in tutte carte obiettivo per disposizione)
			ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+"|                                    |"+ColoreCarta.CODICE_RESET_COLORE,	//linea 8, indiceArray 7 (uguale in tutte carte obiettivo per disposizione)
													  "",																		//linea 9, indiceArray 8 (costruisco in costruttori)
													  "",																		//linea 10, indiceArray 9 (costruisco in costruttori)
													  "",																		//linea 11, indiceArray 10 (costruisco in costruttori)
			ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+"|                                    |"+ColoreCarta.CODICE_RESET_COLORE,	//linea 12, indiceArray 11 (uguale in tutte carte obiettivo per disposizione)
			ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+"╰------------------------------------╯"+ColoreCarta.CODICE_RESET_COLORE};//linea 13, indiceArray 12 (uguale in tutte carte obiettivo)
	


	public ColoreCarta getColoreCarte2() {
		return coloreCarte2;
	}
	

	
	/**
	 * Overloading del metodo costruttore.
	 * Questo costruisce le carte con id da 87 a 90
	 * cioè le 4 carte che danno punti per ogni set di carte in disposizione "diagonale"
	 * @param id
	 * @param puntiPerSet
	 * @param disposizione
	 * @param coloreCarte1 Il colore di tutte le 3 carte coinvolte nella disposizione
	 */
	public CartaObiettivoPerDisposizione(int id, int puntiPerSet, String disposizione, ColoreCarta coloreCarte1) {
		super(id, puntiPerSet);
		this.disposizione = disposizione;
		this.coloreCarte1 = coloreCarte1;
		
		insiemeLineeFacciaCarta[2] = ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+"|          Otterrai "+ColoreCarta.CODICE_RESET_COLORE +puntiPerSet+ ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+" punti          |"+ColoreCarta.CODICE_RESET_COLORE;
		
		if(disposizione.equals("DiagonaleCrescente")) {
			insiemeLineeFacciaCarta[8] =  ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+"|                     "+	ColoreCarta.CODICE_RESET_COLORE +ColoreCarta.ottieniStringCodiceColoreDaStringa(coloreCarte1.toString())+"■■■"+ColoreCarta.CODICE_RESET_COLORE+ ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+"            |"+			ColoreCarta.CODICE_RESET_COLORE;
			insiemeLineeFacciaCarta[9] =  ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+"|                 "+		ColoreCarta.CODICE_RESET_COLORE +ColoreCarta.ottieniStringCodiceColoreDaStringa(coloreCarte1.toString())+"■■■"+ColoreCarta.CODICE_RESET_COLORE+ ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+"                |"+		ColoreCarta.CODICE_RESET_COLORE;
			insiemeLineeFacciaCarta[10] = ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+"|             "+			ColoreCarta.CODICE_RESET_COLORE +ColoreCarta.ottieniStringCodiceColoreDaStringa(coloreCarte1.toString())+"■■■"+ColoreCarta.CODICE_RESET_COLORE+ ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+"                    |"+	ColoreCarta.CODICE_RESET_COLORE;
		} else if(disposizione.equals("DiagonaleDecrescente")) {
			insiemeLineeFacciaCarta[8] =  ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+"|             "+			ColoreCarta.CODICE_RESET_COLORE +ColoreCarta.ottieniStringCodiceColoreDaStringa(coloreCarte1.toString())+"■■■"+ColoreCarta.CODICE_RESET_COLORE+ ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+"                    |"+	ColoreCarta.CODICE_RESET_COLORE;
			insiemeLineeFacciaCarta[9] =  ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+"|                 "+		ColoreCarta.CODICE_RESET_COLORE +ColoreCarta.ottieniStringCodiceColoreDaStringa(coloreCarte1.toString())+"■■■"+ColoreCarta.CODICE_RESET_COLORE+ ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+"                |"+		ColoreCarta.CODICE_RESET_COLORE;
			insiemeLineeFacciaCarta[10] = ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+"|                     "+	ColoreCarta.CODICE_RESET_COLORE +ColoreCarta.ottieniStringCodiceColoreDaStringa(coloreCarte1.toString())+"■■■"+ColoreCarta.CODICE_RESET_COLORE+ ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+"            |"+			ColoreCarta.CODICE_RESET_COLORE;
		}
	}
	
	/**
	 * Overloading del metodo costruttore.
	 * Questo costruisce le carte con id da 91 a 94
	 * cioè le 4 carte che danno punti per ogni set di carte in disposizione "a spigolo"
	 * @param id
	 * @param puntiPerSet
	 * @param disposizione
	 * @param coloreCarte1 Il colore di 2 delle 3 carte coinvolte nella disposizione
	 * @param coloreCarte2 Il colore di 1 delle 3 carte coinvolte nella disposizione
	 */
	public CartaObiettivoPerDisposizione(int id, int puntiPerSet, String disposizione, ColoreCarta coloreCarte1, ColoreCarta coloreCarte2) {
		super(id, puntiPerSet);
		this.disposizione = disposizione;
		this.coloreCarte1 = coloreCarte1;
		this.coloreCarte2 = coloreCarte2;
		
		insiemeLineeFacciaCarta[2] = ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+"|          Otterrai "+ColoreCarta.CODICE_RESET_COLORE +puntiPerSet+ ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+" punti          |"+ColoreCarta.CODICE_RESET_COLORE;
		
		if(disposizione.equals("SpigoloBassoSx")) {
			insiemeLineeFacciaCarta[8] =  ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+"|               "+		ColoreCarta.CODICE_RESET_COLORE +ColoreCarta.ottieniStringCodiceColoreDaStringa(coloreCarte1.toString())+"■■■"+ColoreCarta.CODICE_RESET_COLORE+ ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+"                  |"+	ColoreCarta.CODICE_RESET_COLORE;
			insiemeLineeFacciaCarta[9] =  ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+"|               "+		ColoreCarta.CODICE_RESET_COLORE +ColoreCarta.ottieniStringCodiceColoreDaStringa(coloreCarte1.toString())+"■■■"+ColoreCarta.CODICE_RESET_COLORE+ ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+"                  |"+	ColoreCarta.CODICE_RESET_COLORE;
			insiemeLineeFacciaCarta[10] = ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+"|                   "+	ColoreCarta.CODICE_RESET_COLORE +ColoreCarta.ottieniStringCodiceColoreDaStringa(coloreCarte2.toString())+"■■■"+ColoreCarta.CODICE_RESET_COLORE+ ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+"              |"+		ColoreCarta.CODICE_RESET_COLORE;
		} else if(disposizione.equals("SpigoloBassoDx")) {
			insiemeLineeFacciaCarta[8] =  ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+"|                   "+	ColoreCarta.CODICE_RESET_COLORE +ColoreCarta.ottieniStringCodiceColoreDaStringa(coloreCarte1.toString())+"■■■"+ColoreCarta.CODICE_RESET_COLORE+ ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+"              |"+		ColoreCarta.CODICE_RESET_COLORE;
			insiemeLineeFacciaCarta[9] =  ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+"|                   "+	ColoreCarta.CODICE_RESET_COLORE +ColoreCarta.ottieniStringCodiceColoreDaStringa(coloreCarte1.toString())+"■■■"+ColoreCarta.CODICE_RESET_COLORE+ ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+"              |"+		ColoreCarta.CODICE_RESET_COLORE;
			insiemeLineeFacciaCarta[10] = ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+"|               "+		ColoreCarta.CODICE_RESET_COLORE +ColoreCarta.ottieniStringCodiceColoreDaStringa(coloreCarte2.toString())+"■■■"+ColoreCarta.CODICE_RESET_COLORE+ ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+"                  |"+	ColoreCarta.CODICE_RESET_COLORE;
		} else if(disposizione.equals("SpigoloAltoSx")) {
			insiemeLineeFacciaCarta[8] =  ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+"|                   "+	ColoreCarta.CODICE_RESET_COLORE +ColoreCarta.ottieniStringCodiceColoreDaStringa(coloreCarte2.toString())+"■■■"+ColoreCarta.CODICE_RESET_COLORE+ ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+"              |"+		ColoreCarta.CODICE_RESET_COLORE;
			insiemeLineeFacciaCarta[9] =  ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+"|               "+		ColoreCarta.CODICE_RESET_COLORE +ColoreCarta.ottieniStringCodiceColoreDaStringa(coloreCarte1.toString())+"■■■"+ColoreCarta.CODICE_RESET_COLORE+ ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+"                  |"+	ColoreCarta.CODICE_RESET_COLORE;
			insiemeLineeFacciaCarta[10] = ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+"|               "+		ColoreCarta.CODICE_RESET_COLORE +ColoreCarta.ottieniStringCodiceColoreDaStringa(coloreCarte1.toString())+"■■■"+ColoreCarta.CODICE_RESET_COLORE+ ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+"                  |"+	ColoreCarta.CODICE_RESET_COLORE;
		} else if(disposizione.equals("SpigoloAltoDx")) {
			insiemeLineeFacciaCarta[8] =  ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+"|               "+		ColoreCarta.CODICE_RESET_COLORE +ColoreCarta.ottieniStringCodiceColoreDaStringa(coloreCarte2.toString())+"■■■"+ColoreCarta.CODICE_RESET_COLORE+ ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+"                  |"+	ColoreCarta.CODICE_RESET_COLORE;
			insiemeLineeFacciaCarta[9] =  ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+"|                   "+	ColoreCarta.CODICE_RESET_COLORE +ColoreCarta.ottieniStringCodiceColoreDaStringa(coloreCarte1.toString())+"■■■"+ColoreCarta.CODICE_RESET_COLORE+ ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+"              |"+		ColoreCarta.CODICE_RESET_COLORE;
			insiemeLineeFacciaCarta[10] = ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+"|                   "+	ColoreCarta.CODICE_RESET_COLORE +ColoreCarta.ottieniStringCodiceColoreDaStringa(coloreCarte1.toString())+"■■■"+ColoreCarta.CODICE_RESET_COLORE+ ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+"              |"+		ColoreCarta.CODICE_RESET_COLORE;
		}
	}
	
	
	@Override
	public void print(String numeroLineaDaStampare)
	/*
	 * Ho messo questa String in ingresso per evitare che fosse eseguito il metodo (astratto) della superclasse Carta nel caso di una chiamata, da parte di un oggetto carta obiettivo, di un metodo print passante in ingresso una String
	 * 
	 * Infatti, se questo metodo print non avesse ricevuto in ingresso nulla,
	 * durante la fase di compilazione (per overloading, "scelta della segnatura") si sarebbe eseguito il metodo print della superclasse Carta, che prende in ingresso una String
	 * 
	 * Mettendo in ingresso una String anche in questo metodo print, per overriding il metodo eseguito (nel caso della chiamata descritta precedentemente) sarà questo
	 */
	{
		for(int k=0; k<NUMERO_LINEE_FACCIA_CARTA; k++) {
			System.out.println(insiemeLineeFacciaCarta[k]);
		}
	}
	
	
	@Override
	public int controllaObiettivo(Campo campo) {
		int numeroSetCarteDisposteInOrdine = 0;
		resetConteggioSimboliOControlloDisposizione(campo); //sposto il reset all'inizio, come ho fatto con l'altro metodo che lo richiama (nell'altra classe che implementa l'interfaccia Reset, ovvero Campo)
		
		//In ogni caso (ogni disposizione) inizio a controllare le carte da quella più in alto (che è la prima ad essere individuata in una eventuale disposizione corretta)
		
		for(int i=0; i<Campo.DIM; i++) {
			for(int j=0; j<Campo.DIM; j++) {
				
				if(campo.getCasellaDaCoordinate(i,j) instanceof CasellaGiocabile) {
					try {
						
						if (disposizione.equals("DiagonaleCrescente")) {
							if( !( ((CasellaGiocabile) campo.getCasellaDaCoordinate(i,j)).isEmpty() ) && 
								!( ((CasellaGiocabile) campo.getCasellaDaCoordinate(i+1,j-1)).isEmpty() ) &&
								!( ((CasellaGiocabile) campo.getCasellaDaCoordinate(i+2,j-2)).isEmpty() ) ) {
									if( !( ((CasellaGiocabile) campo.getCasellaDaCoordinate(i,j)).getCartaContenuta() instanceof CartaIniziale ) && 
										!( ((CasellaGiocabile) campo.getCasellaDaCoordinate(i+1,j-1)).getCartaContenuta() instanceof CartaIniziale ) &&
										!( ((CasellaGiocabile) campo.getCasellaDaCoordinate(i+2,j-2)).getCartaContenuta() instanceof CartaIniziale ) ) {
											if( !( (((CartaRisorsa) ((CasellaGiocabile) campo.getCasellaDaCoordinate(i,j)).getCartaContenuta()).isContataPerObiettivoDisposizione() ) ) && 
												!( (((CartaRisorsa) ((CasellaGiocabile) campo.getCasellaDaCoordinate(i+1,j-1)).getCartaContenuta()).isContataPerObiettivoDisposizione() ) ) &&
												!( (((CartaRisorsa) ((CasellaGiocabile) campo.getCasellaDaCoordinate(i+2,j-2)).getCartaContenuta()).isContataPerObiettivoDisposizione() ) ) ) {
													if( (((CartaRisorsa) ((CasellaGiocabile) campo.getCasellaDaCoordinate(i,j)).getCartaContenuta()).getColore() == coloreCarte1 ) && 
														(((CartaRisorsa) ((CasellaGiocabile) campo.getCasellaDaCoordinate(i+1,j-1)).getCartaContenuta()).getColore() == coloreCarte1 ) &&
														(((CartaRisorsa) ((CasellaGiocabile) campo.getCasellaDaCoordinate(i+2,j-2)).getCartaContenuta()).getColore() == coloreCarte1 ) ) {
															numeroSetCarteDisposteInOrdine++;
															((CartaRisorsa) ((CasellaGiocabile) campo.getCasellaDaCoordinate(i,j)).getCartaContenuta()).setContataPerObiettivoDisposizione(true);
															((CartaRisorsa) ((CasellaGiocabile) campo.getCasellaDaCoordinate(i+1,j-1)).getCartaContenuta()).setContataPerObiettivoDisposizione(true);
															((CartaRisorsa) ((CasellaGiocabile) campo.getCasellaDaCoordinate(i+2,j-2)).getCartaContenuta()).setContataPerObiettivoDisposizione(true);
															System.out.print("\tHai completato un obiettivo (per disposizione delle carte)! ");
															System.out.print("Hai ottenuto "+super.getPuntiPerSet());
															if(super.getPuntiPerSet()==1) {
																System.out.println(" punto");
															} else {
																System.out.println(" punti");
															}
													}
											}
									}
							}
						} else if (disposizione.equals("DiagonaleDecrescente")) {
							if( !( ((CasellaGiocabile) campo.getCasellaDaCoordinate(i,j)).isEmpty() ) && 
								!( ((CasellaGiocabile) campo.getCasellaDaCoordinate(i+1,j+1)).isEmpty() ) &&
								!( ((CasellaGiocabile) campo.getCasellaDaCoordinate(i+2,j+2)).isEmpty() ) ) {
									if( !( ((CasellaGiocabile) campo.getCasellaDaCoordinate(i,j)).getCartaContenuta() instanceof CartaIniziale ) && 
										!( ((CasellaGiocabile) campo.getCasellaDaCoordinate(i+1,j+1)).getCartaContenuta() instanceof CartaIniziale ) &&
										!( ((CasellaGiocabile) campo.getCasellaDaCoordinate(i+2,j+2)).getCartaContenuta() instanceof CartaIniziale ) ) {
											if( !( (((CartaRisorsa) ((CasellaGiocabile) campo.getCasellaDaCoordinate(i,j)).getCartaContenuta()).isContataPerObiettivoDisposizione() ) ) && 
												!( (((CartaRisorsa) ((CasellaGiocabile) campo.getCasellaDaCoordinate(i+1,j+1)).getCartaContenuta()).isContataPerObiettivoDisposizione() ) ) &&
												!( (((CartaRisorsa) ((CasellaGiocabile) campo.getCasellaDaCoordinate(i+2,j+2)).getCartaContenuta()).isContataPerObiettivoDisposizione() ) ) ) {
													if( (((CartaRisorsa) ((CasellaGiocabile) campo.getCasellaDaCoordinate(i,j)).getCartaContenuta()).getColore() == coloreCarte1 ) && 
														(((CartaRisorsa) ((CasellaGiocabile) campo.getCasellaDaCoordinate(i+1,j+1)).getCartaContenuta()).getColore() == coloreCarte1 ) &&
														(((CartaRisorsa) ((CasellaGiocabile) campo.getCasellaDaCoordinate(i+2,j+2)).getCartaContenuta()).getColore() == coloreCarte1 ) ) {
															numeroSetCarteDisposteInOrdine++;
															((CartaRisorsa) ((CasellaGiocabile) campo.getCasellaDaCoordinate(i,j)).getCartaContenuta()).setContataPerObiettivoDisposizione(true);
															((CartaRisorsa) ((CasellaGiocabile) campo.getCasellaDaCoordinate(i+1,j+1)).getCartaContenuta()).setContataPerObiettivoDisposizione(true);
															((CartaRisorsa) ((CasellaGiocabile) campo.getCasellaDaCoordinate(i+2,j+2)).getCartaContenuta()).setContataPerObiettivoDisposizione(true);
															System.out.print("\tHai completato un obiettivo (per disposizione delle carte)! ");
															System.out.print("Hai ottenuto "+super.getPuntiPerSet());
															if(super.getPuntiPerSet()==1) {
																System.out.println(" punto");
															} else {
																System.out.println(" punti");
															}
													}
											}
									}
							}
						} else if (disposizione.equals("SpigoloBassoSx")) {
							if( !( ((CasellaGiocabile) campo.getCasellaDaCoordinate(i,j)).isEmpty() ) && 
								!( ((CasellaGiocabile) campo.getCasellaDaCoordinate(i+2,j)).isEmpty() ) &&
								!( ((CasellaGiocabile) campo.getCasellaDaCoordinate(i+3,j+1)).isEmpty() ) ) {
									if( !( ((CasellaGiocabile) campo.getCasellaDaCoordinate(i,j)).getCartaContenuta() instanceof CartaIniziale ) && 
										!( ((CasellaGiocabile) campo.getCasellaDaCoordinate(i+2,j)).getCartaContenuta() instanceof CartaIniziale ) &&
										!( ((CasellaGiocabile) campo.getCasellaDaCoordinate(i+3,j+1)).getCartaContenuta() instanceof CartaIniziale ) ) {
											if( !( (((CartaRisorsa) ((CasellaGiocabile) campo.getCasellaDaCoordinate(i,j)).getCartaContenuta()).isContataPerObiettivoDisposizione() ) ) && 
												!( (((CartaRisorsa) ((CasellaGiocabile) campo.getCasellaDaCoordinate(i+2,j)).getCartaContenuta()).isContataPerObiettivoDisposizione() ) ) &&
												!( (((CartaRisorsa) ((CasellaGiocabile) campo.getCasellaDaCoordinate(i+3,j+1)).getCartaContenuta()).isContataPerObiettivoDisposizione() ) ) ) {
													if( (((CartaRisorsa) ((CasellaGiocabile) campo.getCasellaDaCoordinate(i,j)).getCartaContenuta()).getColore() == coloreCarte1 ) && 
														(((CartaRisorsa) ((CasellaGiocabile) campo.getCasellaDaCoordinate(i+2,j)).getCartaContenuta()).getColore() == coloreCarte1 ) &&
														(((CartaRisorsa) ((CasellaGiocabile) campo.getCasellaDaCoordinate(i+3,j+1)).getCartaContenuta()).getColore() == coloreCarte2 ) ) {
															numeroSetCarteDisposteInOrdine++;
															((CartaRisorsa) ((CasellaGiocabile) campo.getCasellaDaCoordinate(i,j)).getCartaContenuta()).setContataPerObiettivoDisposizione(true);
															((CartaRisorsa) ((CasellaGiocabile) campo.getCasellaDaCoordinate(i+2,j)).getCartaContenuta()).setContataPerObiettivoDisposizione(true);
															((CartaRisorsa) ((CasellaGiocabile) campo.getCasellaDaCoordinate(i+3,j+1)).getCartaContenuta()).setContataPerObiettivoDisposizione(true);
															System.out.print("\tHai completato un obiettivo (per disposizione delle carte)! ");
															System.out.print("Hai ottenuto "+super.getPuntiPerSet());
															if(super.getPuntiPerSet()==1) {
																System.out.println(" punto");
															} else {
																System.out.println(" punti");
															}
													}
											}
									}
							}
						} else if (disposizione.equals("SpigoloBassoDx")) {
							if( !( ((CasellaGiocabile) campo.getCasellaDaCoordinate(i,j)).isEmpty() ) && 
								!( ((CasellaGiocabile) campo.getCasellaDaCoordinate(i+2,j)).isEmpty() ) &&
								!( ((CasellaGiocabile) campo.getCasellaDaCoordinate(i+3,j-1)).isEmpty() ) ) {
									if( !( ((CasellaGiocabile) campo.getCasellaDaCoordinate(i,j)).getCartaContenuta() instanceof CartaIniziale ) && 
										!( ((CasellaGiocabile) campo.getCasellaDaCoordinate(i+2,j)).getCartaContenuta() instanceof CartaIniziale ) &&
										!( ((CasellaGiocabile) campo.getCasellaDaCoordinate(i+3,j-1)).getCartaContenuta() instanceof CartaIniziale ) ) {
											if( !( (((CartaRisorsa) ((CasellaGiocabile) campo.getCasellaDaCoordinate(i,j)).getCartaContenuta()).isContataPerObiettivoDisposizione() ) ) && 
												!( (((CartaRisorsa) ((CasellaGiocabile) campo.getCasellaDaCoordinate(i+2,j)).getCartaContenuta()).isContataPerObiettivoDisposizione() ) ) &&
												!( (((CartaRisorsa) ((CasellaGiocabile) campo.getCasellaDaCoordinate(i+3,j-1)).getCartaContenuta()).isContataPerObiettivoDisposizione() ) ) ) {
													if( (((CartaRisorsa) ((CasellaGiocabile) campo.getCasellaDaCoordinate(i,j)).getCartaContenuta()).getColore() == coloreCarte1 ) && 
														(((CartaRisorsa) ((CasellaGiocabile) campo.getCasellaDaCoordinate(i+2,j)).getCartaContenuta()).getColore() == coloreCarte1 ) &&
														(((CartaRisorsa) ((CasellaGiocabile) campo.getCasellaDaCoordinate(i+3,j-1)).getCartaContenuta()).getColore() == coloreCarte2 ) ) {
															numeroSetCarteDisposteInOrdine++;
															((CartaRisorsa) ((CasellaGiocabile) campo.getCasellaDaCoordinate(i,j)).getCartaContenuta()).setContataPerObiettivoDisposizione(true);
															((CartaRisorsa) ((CasellaGiocabile) campo.getCasellaDaCoordinate(i+2,j)).getCartaContenuta()).setContataPerObiettivoDisposizione(true);
															((CartaRisorsa) ((CasellaGiocabile) campo.getCasellaDaCoordinate(i+3,j-1)).getCartaContenuta()).setContataPerObiettivoDisposizione(true);
															System.out.print("\tHai completato un obiettivo (per disposizione delle carte)! ");
															System.out.print("Hai ottenuto "+super.getPuntiPerSet());
															if(super.getPuntiPerSet()==1) {
																System.out.println(" punto");
															} else {
																System.out.println(" punti");
															}
													}
											}
									}
							}
						} else if (disposizione.equals("SpigoloAltoSx")) {
							if( !( ((CasellaGiocabile) campo.getCasellaDaCoordinate(i,j)).isEmpty() ) && 
								!( ((CasellaGiocabile) campo.getCasellaDaCoordinate(i+1,j-1)).isEmpty() ) &&
								!( ((CasellaGiocabile) campo.getCasellaDaCoordinate(i+3,j-1)).isEmpty() ) ) {
									if( !( ((CasellaGiocabile) campo.getCasellaDaCoordinate(i,j)).getCartaContenuta() instanceof CartaIniziale ) && 
										!( ((CasellaGiocabile) campo.getCasellaDaCoordinate(i+1,j-1)).getCartaContenuta() instanceof CartaIniziale ) &&
										!( ((CasellaGiocabile) campo.getCasellaDaCoordinate(i+3,j-1)).getCartaContenuta() instanceof CartaIniziale ) ) {
											if( !( (((CartaRisorsa) ((CasellaGiocabile) campo.getCasellaDaCoordinate(i,j)).getCartaContenuta()).isContataPerObiettivoDisposizione() ) ) && 
												!( (((CartaRisorsa) ((CasellaGiocabile) campo.getCasellaDaCoordinate(i+1,j-1)).getCartaContenuta()).isContataPerObiettivoDisposizione() ) ) &&
												!( (((CartaRisorsa) ((CasellaGiocabile) campo.getCasellaDaCoordinate(i+3,j-1)).getCartaContenuta()).isContataPerObiettivoDisposizione() ) ) ) {
													if( (((CartaRisorsa) ((CasellaGiocabile) campo.getCasellaDaCoordinate(i,j)).getCartaContenuta()).getColore() == coloreCarte2 ) && 
														(((CartaRisorsa) ((CasellaGiocabile) campo.getCasellaDaCoordinate(i+1,j-1)).getCartaContenuta()).getColore() == coloreCarte1 ) &&
														(((CartaRisorsa) ((CasellaGiocabile) campo.getCasellaDaCoordinate(i+3,j-1)).getCartaContenuta()).getColore() == coloreCarte1 ) ) {
															numeroSetCarteDisposteInOrdine++;
															((CartaRisorsa) ((CasellaGiocabile) campo.getCasellaDaCoordinate(i,j)).getCartaContenuta()).setContataPerObiettivoDisposizione(true);
															((CartaRisorsa) ((CasellaGiocabile) campo.getCasellaDaCoordinate(i+1,j-1)).getCartaContenuta()).setContataPerObiettivoDisposizione(true);
															((CartaRisorsa) ((CasellaGiocabile) campo.getCasellaDaCoordinate(i+3,j-1)).getCartaContenuta()).setContataPerObiettivoDisposizione(true);
															System.out.print("\tHai completato un obiettivo (per disposizione delle carte)! ");
															System.out.print("Hai ottenuto "+super.getPuntiPerSet());
															if(super.getPuntiPerSet()==1) {
																System.out.println(" punto");
															} else {
																System.out.println(" punti");
															}
													}
											}
									}
							}
						} else if (disposizione.equals("SpigoloAltoDx")) {
							if( !( ((CasellaGiocabile) campo.getCasellaDaCoordinate(i,j)).isEmpty() ) && 
								!( ((CasellaGiocabile) campo.getCasellaDaCoordinate(i+1,j+1)).isEmpty() ) &&
								!( ((CasellaGiocabile) campo.getCasellaDaCoordinate(i+3,j+1)).isEmpty() ) ) {
									if( !( ((CasellaGiocabile) campo.getCasellaDaCoordinate(i,j)).getCartaContenuta() instanceof CartaIniziale ) && 
										!( ((CasellaGiocabile) campo.getCasellaDaCoordinate(i+1,j+1)).getCartaContenuta() instanceof CartaIniziale ) &&
										!( ((CasellaGiocabile) campo.getCasellaDaCoordinate(i+3,j+1)).getCartaContenuta() instanceof CartaIniziale ) ) {
											if( !( (((CartaRisorsa) ((CasellaGiocabile) campo.getCasellaDaCoordinate(i,j)).getCartaContenuta()).isContataPerObiettivoDisposizione() ) ) && 
												!( (((CartaRisorsa) ((CasellaGiocabile) campo.getCasellaDaCoordinate(i+1,j+1)).getCartaContenuta()).isContataPerObiettivoDisposizione() ) ) &&
												!( (((CartaRisorsa) ((CasellaGiocabile) campo.getCasellaDaCoordinate(i+3,j+1)).getCartaContenuta()).isContataPerObiettivoDisposizione() ) ) ) {
													if( (((CartaRisorsa) ((CasellaGiocabile) campo.getCasellaDaCoordinate(i,j)).getCartaContenuta()).getColore() == coloreCarte2 ) && 
														(((CartaRisorsa) ((CasellaGiocabile) campo.getCasellaDaCoordinate(i+1,j+1)).getCartaContenuta()).getColore() == coloreCarte1 ) &&
														(((CartaRisorsa) ((CasellaGiocabile) campo.getCasellaDaCoordinate(i+3,j+1)).getCartaContenuta()).getColore() == coloreCarte1 ) ) {
															numeroSetCarteDisposteInOrdine++;
															((CartaRisorsa) ((CasellaGiocabile) campo.getCasellaDaCoordinate(i,j)).getCartaContenuta()).setContataPerObiettivoDisposizione(true);
															((CartaRisorsa) ((CasellaGiocabile) campo.getCasellaDaCoordinate(i+1,j+1)).getCartaContenuta()).setContataPerObiettivoDisposizione(true);
															((CartaRisorsa) ((CasellaGiocabile) campo.getCasellaDaCoordinate(i+3,j+1)).getCartaContenuta()).setContataPerObiettivoDisposizione(true);
															System.out.print("\tHai completato un obiettivo (per disposizione delle carte)! ");
															System.out.print("Hai ottenuto "+super.getPuntiPerSet());
															if(super.getPuntiPerSet()==1) {
																System.out.println(" punto");
															} else {
																System.out.println(" punti");
															}
													}
											}
									}
								}
						}
						
					} catch (ArrayIndexOutOfBoundsException e) {
						//Questa eccezione si verifica nel caso si inizi a trovare una disposizione di carte che però finisce al di fuori del campo possibile
						//es: si trova una carta rossa per la disposizione "DiagonaleDecrescente" nell'ultima casella possibile in basso a destra del campo)
						
						//In questo caso non bisogna fare effettivamente nulla, in quanto non è possibile posizionare carte fuori dal campo e quindi completare una disposizione qui
						
						/*
						 * Forse non serve nemmeno gestire questo caso perchè non si arriverà mai a posizionare una carta nell'ultima casella ad un angolo del campo, in quanto
						 * la partita finisce quando finiscono entrambi i mazzi, non quando finiscono entrambi i mazzi E le carte in mano di ogni giocatore
						 */
					}
				}
				
			}
		}
		return numeroSetCarteDisposteInOrdine;
	}
	
	
	/**
	 * Metodo derivante dall'interfaccia campoECaselle.Resetter implementata da questa classe.
	 *  Serve per "resettare" il campo di gioco prima di un nuovo controllo delle disposizioni, dichiarando che nessuna delle carte presenti sul campo va "saltata" perchè già presa in analisi
	 */
	@Override
	public void resetConteggioSimboliOControlloDisposizione(Campo campo) {
		for(int i=0; i<Campo.DIM; i++) {
			for(int j=0; j<Campo.DIM; j++) {
				if(campo.getCasellaDaCoordinate(i,j) instanceof CasellaGiocabile) {
					if(!(((CasellaGiocabile) campo.getCasellaDaCoordinate(i,j)).isEmpty())) {
						((CasellaGiocabile) campo.getCasellaDaCoordinate(i,j)).getCartaContenuta().setContataPerObiettivoDisposizione(false);
					}
				}
			}
		}
		//System.out.println("Campo resettato (disposizione)");
		//(Usato per testing)
	}
	
}
