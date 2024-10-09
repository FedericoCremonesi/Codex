package carte;

import campoECaselle.Campo;

public class CartaObiettivoPerDisposizione extends CartaObiettivo {
	
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

	
	/*
	 * Overloading del metodo costruttore
	 * con questo costruisco le carte con id da 87 a 90
	 * cioè le 4 carte che danno punti per ogni set di carte in disposizione "diagonale"
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
	
	/*
	 * Overloading del metodo costruttore
	 * con questo costruisco le carte con id da 91 a 94
	 * cioè le 4 carte che danno punti per ogni set di carte in disposizione "a spigolo"
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
	
	
	
	public int controllaObiettivo() {
		for(int i=0; i<Campo.DIM; i++) {
			for(int j=0; j<Campo.DIM; j++) {
				
			}
		}
		return 0;
	}
}
