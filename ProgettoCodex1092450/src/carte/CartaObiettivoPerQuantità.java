package carte;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import campoECaselle.Campo;
import facceEAngoli.AngoloVisibile;
import facceEAngoli.Oggetto;
import facceEAngoli.Risorsa;

public class CartaObiettivoPerQuantità extends CartaObiettivo {
	
	private Risorsa risorsa;
	private int quantitàRisorsa;
	private Oggetto oggetto1;
	private int quantitàOggetto1;
	private Oggetto oggetto2;
	private int quantitàOggetto2;
	private Oggetto oggetto3;
	private int quantitàOggetto3;
	
	private String[] insiemeLineeFacciaCarta =
		   {ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+"╭------------------------------------╮"+ColoreCarta.CODICE_RESET_COLORE,	//linea 1, indiceArray 0 (uguale in tutte carte obiettivo)
			ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+"|                                    |"+ColoreCarta.CODICE_RESET_COLORE,	//linea 2, indiceArray 1 (uguale in tutte carte obiettivo)
			   										  "",																		//linea 3, indiceArray 2 (costruisco in costruttori)
			ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+"|                                    |"+ColoreCarta.CODICE_RESET_COLORE,	//linea 4, indiceArray 3 (uguale in tutte carte obiettivo)
			ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+"|           per ogni set di          |"+ColoreCarta.CODICE_RESET_COLORE,	//linea 5, indiceArray 4 (uguale in tutte carte obiettivo)
			ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+"|                                    |"+ColoreCarta.CODICE_RESET_COLORE,	//linea 6, indiceArray 5 (uguale in tutte carte obiettivo)
													  "",																		//linea 7, indiceArray 6 (costruisco in costruttori)
													  "",																		//linea 8, indiceArray 7 (costruisco in costruttori)
													  "",																		//linea 9, indiceArray 8 (costruisco in costruttori)
													  "",																		//linea 10, indiceArray 9 (costruisco in costruttori)
													  "",																		//linea 11, indiceArray 10 (costruisco in costruttori)
													  "",																		//linea 12, indiceArray 11 (costruisco in costruttori)
			ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+"╰------------------------------------╯"+ColoreCarta.CODICE_RESET_COLORE};//linea 13, indiceArray 12 (uguale in tutte carte obiettivo)

	
	/*
	 * Overloading del metodo costruttore
	 * con questo costruisco le carte con id da 95 a 98
	 * cioè le 4 carte che danno punti per ogni set di 3 risorse uguali
	 */
	public CartaObiettivoPerQuantità(int id, int puntiPerSet, Risorsa risorsa, int quantitàRisorsa) {
		super(id, puntiPerSet);
		this.risorsa = risorsa;
		this.quantitàRisorsa = quantitàRisorsa;
		
		insiemeLineeFacciaCarta[2] =  ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+"|          Otterrai "+ColoreCarta.CODICE_RESET_COLORE +puntiPerSet+ ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+" punti          |"+ColoreCarta.CODICE_RESET_COLORE;
		
		insiemeLineeFacciaCarta[6] =  ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+"|                                    |"+	ColoreCarta.CODICE_RESET_COLORE;
		insiemeLineeFacciaCarta[7] =  ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+"|         "+								ColoreCarta.CODICE_RESET_COLORE +quantitàRisorsa+ ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+" simboli di tipo:         |"+ColoreCarta.CODICE_RESET_COLORE;
		insiemeLineeFacciaCarta[8] =  ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+"|             "+							ColoreCarta.CODICE_RESET_COLORE +AngoloVisibile.ottieniStringaConSpaziDaStringaSimbolo(risorsa.toString())+ ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+"             |"+ColoreCarta.CODICE_RESET_COLORE; //devo applicare il metodo toString perchè il metodo usato necessita di una Stringa in ingresso
		insiemeLineeFacciaCarta[9] =  ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+"|                                    |"+	ColoreCarta.CODICE_RESET_COLORE;
		insiemeLineeFacciaCarta[10] = ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+"|                                    |"+	ColoreCarta.CODICE_RESET_COLORE;
		insiemeLineeFacciaCarta[11] = ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+"|                                    |"+	ColoreCarta.CODICE_RESET_COLORE;
	}
	
	/*
	 * Overloading del metodo costruttore
	 * con questo costruisco le carte con id 99
	 * cioè le 4 carte che danno punti per ogni set di oggetti (diversi o uguali tra loro)
	 */
	public CartaObiettivoPerQuantità(int id, int puntiPerSet, Oggetto oggetto1, int quantitàOggetto1, Oggetto oggetto2, int quantitàOggetto2, Oggetto oggetto3, int quantitàOggetto3) {
		super(id, puntiPerSet);
		this.oggetto1 = oggetto1;
		this.quantitàOggetto1 = quantitàOggetto1;
		this.oggetto1 = oggetto2;
		this.quantitàOggetto1 = quantitàOggetto2;
		this.oggetto1 = oggetto3;
		this.quantitàOggetto1 = quantitàOggetto3;
		
		insiemeLineeFacciaCarta[2] =  ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+"|          Otterrai "+ColoreCarta.CODICE_RESET_COLORE +puntiPerSet+ ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+" punti          |"+ColoreCarta.CODICE_RESET_COLORE;
		
		insiemeLineeFacciaCarta[6] =  ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+"|         "+ColoreCarta.CODICE_RESET_COLORE +quantitàOggetto1 +ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+" simboli di tipo:         |"+ColoreCarta.CODICE_RESET_COLORE;
		insiemeLineeFacciaCarta[7] =  ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+"|             "+ColoreCarta.CODICE_RESET_COLORE +AngoloVisibile.ottieniStringaConSpaziDaStringaSimbolo(oggetto1.toString())+ ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+"             |"+ColoreCarta.CODICE_RESET_COLORE;
		insiemeLineeFacciaCarta[8] =  ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+"|         "+ColoreCarta.CODICE_RESET_COLORE +quantitàOggetto2 +ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+" simboli di tipo:         |"+ColoreCarta.CODICE_RESET_COLORE;
		insiemeLineeFacciaCarta[9] =  ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+"|             "+ColoreCarta.CODICE_RESET_COLORE +AngoloVisibile.ottieniStringaConSpaziDaStringaSimbolo(oggetto2.toString())+ ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+"             |"+ColoreCarta.CODICE_RESET_COLORE;
		insiemeLineeFacciaCarta[10] = ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+"|         "+ColoreCarta.CODICE_RESET_COLORE +quantitàOggetto3 +ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+" simboli di tipo:         |"+ColoreCarta.CODICE_RESET_COLORE;
		insiemeLineeFacciaCarta[11] = ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+"|             "+ColoreCarta.CODICE_RESET_COLORE +AngoloVisibile.ottieniStringaConSpaziDaStringaSimbolo(oggetto3.toString())+ ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+"             |"+ColoreCarta.CODICE_RESET_COLORE;
	}
	
	/*
	 * Overloading del metodo costruttore
	 * con questo costruisco le carte con id da 100 a 102
	 * cioè le 4 carte che danno punti per ogni set di oggetti (diversi o uguali tra loro)
	 */
	public CartaObiettivoPerQuantità(int id, int puntiPerSet, Oggetto oggetto1, int quantitàOggetto1) {
		super(id, puntiPerSet);
		this.oggetto1 = oggetto1;
		this.quantitàOggetto1 = quantitàOggetto1;
		
		insiemeLineeFacciaCarta[2] =  ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+"|          Otterrai "+ColoreCarta.CODICE_RESET_COLORE +puntiPerSet+ ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+" punti          |"+ColoreCarta.CODICE_RESET_COLORE;
		
		insiemeLineeFacciaCarta[6] =  ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+"|                                    |"+ColoreCarta.CODICE_RESET_COLORE;
		insiemeLineeFacciaCarta[7] =  ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+"|         "+ColoreCarta.CODICE_RESET_COLORE +quantitàOggetto1+ ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+" simboli di tipo:         |"+ColoreCarta.CODICE_RESET_COLORE;
		insiemeLineeFacciaCarta[8] =  ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+"|             "+ColoreCarta.CODICE_RESET_COLORE +AngoloVisibile.ottieniStringaConSpaziDaStringaSimbolo(oggetto1.toString())+ ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+"             |"+ColoreCarta.CODICE_RESET_COLORE;
		insiemeLineeFacciaCarta[9] =  ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+"|                                    |"+ColoreCarta.CODICE_RESET_COLORE;
		insiemeLineeFacciaCarta[10] = ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+"|                                    |"+ColoreCarta.CODICE_RESET_COLORE;
		insiemeLineeFacciaCarta[11] = ColoreCarta.CODICE_COLORE_CARTA_OBIETTIVO+"|                                    |"+ColoreCarta.CODICE_RESET_COLORE;
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
	
	
	public int controllaObiettivo(Campo campo) {
		double numeroSetSimboli = 0;
		HashMap<String, Integer> conteggioRisorseEOggetti = campo.contaRisorseEOggettiVisibili(40,40,true);
		
		if(oggetto2 != null && oggetto3 != null) {
			//Controllo la carta con id: 99
			
			//Per trovare quanti "set di oggetti diversi" sono presenti sul campo, basta confrontare i conteggi dei 3 oggetti tra loro e prendere il minore
			
			//Voglio trovare il minore tra i 3 conteggi, senza necessariamente ricordarmi anche quale oggetto compare più spesso,
			//dunque metto in questa lista di interi le quantità dei tre rispettivi oggetti
			List<Integer> conteggiTreOggetti = new ArrayList<>();
			
			conteggiTreOggetti.add(conteggioRisorseEOggetti.get(oggetto1.toString())); //nella lista dei conteggi metto quante volte compare oggetto1
			conteggiTreOggetti.add(conteggioRisorseEOggetti.get(oggetto2.toString())); //nella lista dei conteggi metto quante volte compare oggetto2
			conteggiTreOggetti.add(conteggioRisorseEOggetti.get(oggetto3.toString())); //nella lista dei conteggi metto quante volte compare oggetto3
			
			Collections.sort(conteggiTreOggetti); //ordino (in ordine crescente) la lista contenente i tre Integer indicanti le quantità di ognuno dei 3 oggetti
			numeroSetSimboli = conteggiTreOggetti.get(0); //l'indice 0 indica il conteggio in posizione 1, ovvero il minore (poichè sono in ordine crescente dopo aver usato sort)
		} else {
			if(risorsa != null) {
				//Controllo le carte con id: 95,96,97,98
				numeroSetSimboli = ( conteggioRisorseEOggetti.get(risorsa.toString()) / quantitàRisorsa );
			} else if(oggetto1 != null){
				//Controllo le carte con id: 100,101,102
				numeroSetSimboli = ( conteggioRisorseEOggetti.get(oggetto1.toString()) / quantitàOggetto1 );
			}
			
			Math.floor(numeroSetSimboli); //Con questo metodo floor, approssimo per difetto il numero di set di simboli	
		}
		
		return (int) numeroSetSimboli; //posso castare a intero perchè qualsiasi valore contenuto in numeroSetSimboli sarà un intero o un double arrotondato per difetto
	}
}
