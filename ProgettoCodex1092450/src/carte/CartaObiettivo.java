package carte;

import carte.ColoreCarta;
import facceEAngoli.Oggetto;
import facceEAngoli.Risorsa;

public class CartaObiettivo extends Carta{

	private int puntiPerSet;
	private String disposizione;
	private ColoreCarta coloreCarte1;
	private ColoreCarta coloreCarte2;
	private Risorsa risorsa;
	private int quantitàRisorsa;
	private Oggetto oggetto1;
	private int quantitàOggetto1;
	private Oggetto oggetto2;
	private int quantitàOggetto2;
	private Oggetto oggetto3;
	private int quantitàOggetto3;
	
	public final static int NUMERO_LINEE_FACCIA_CARTA=13;
	private String[] insiemeLineeFacciaCarta = {"╭------------------------------------╮",				//linea 1, indiceArray 0
												"|                                    |",				//linea 2, indiceArray 1
												"",	//linea 3, indiceArray 2 (costruisco in costruttori)
												"|                                    |",				//linea 4, indiceArray 3
												"|           per ogni set di          |",				//linea 5, indiceArray 4
												"|                                    |",				//linea 6, indiceArray 5
												"",														//linea 7, indiceArray 6 (costruisco in costruttori)
												"",														//linea 8, indiceArray 7 (costruisco in costruttori)
												"",														//linea 9, indiceArray 8 (costruisco in costruttori)
												"",														//linea 10, indiceArray 9 (costruisco in costruttori)
												"",														//linea 11, indiceArray 10 (costruisco in costruttori)
												"",														//linea 12, indiceArray 11 (costruisco in costruttori)
												"╰------------------------------------╯"};				//linea 13, indiceArray 12
	
	
	/*
	 * Overloading del metodo costruttore
	 * con questo costruisco le carte con id da 87 a 90
	 * cioè le 4 carte che danno punti per ogni set di carte in disposizione "diagonale"
	 */
	public CartaObiettivo(int id, int puntiPerSet, String disposizione, ColoreCarta coloreCarte1) {
		super(id);
		this.puntiPerSet = puntiPerSet;
		this.disposizione = disposizione;
		this.coloreCarte1 = coloreCarte1;
	}
	
	/*
	 * Overloading del metodo costruttore
	 * con questo costruisco le carte con id da 91 a 94
	 * cioè le 4 carte che danno punti per ogni set di carte in disposizione "a spigolo"
	 */
	public CartaObiettivo(int id, int puntiPerSet, String disposizione, ColoreCarta coloreCarte1, ColoreCarta coloreCarte2) {
		super(id);
		this.puntiPerSet = puntiPerSet;
		this.disposizione = disposizione;
		this.coloreCarte1 = coloreCarte1;
		this.coloreCarte2 = coloreCarte2;
	}
	
	/*
	 * Overloading del metodo costruttore
	 * con questo costruisco le carte con id da 95 a 98
	 * cioè le 4 carte che danno punti per ogni set di 3 risorse uguali
	 */
	public CartaObiettivo(int id, int puntiPerSet, Risorsa risorsa, int quantitàRisorsa) {
		super(id);
		this.puntiPerSet = puntiPerSet;
		this.risorsa = risorsa;
		this.quantitàRisorsa = quantitàRisorsa;
	}
	
	/*
	 * Overloading del metodo costruttore
	 * con questo costruisco le carte con id da 99 a 102
	 * cioè le 4 carte che danno punti per ogni set di oggetti (diversi o uguali tra loro)
	 */
	public CartaObiettivo(int id, int puntiPerSet, Oggetto oggetto1, int quantitàOggetto1, Oggetto oggetto2, int quantitàOggetto2, Oggetto oggetto3, int quantitàOggetto3) {
		super(id);
		this.puntiPerSet = puntiPerSet;
		this.oggetto1 = oggetto1;
		this.quantitàOggetto1 = quantitàOggetto1;
		this.oggetto1 = oggetto2;
		this.quantitàOggetto1 = quantitàOggetto2;
		this.oggetto1 = oggetto3;
		this.quantitàOggetto1 = quantitàOggetto3;
	}

	
	@Override
	public String toString() {
		return "C.Obiettivo: "+super.toString();
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
}
