package carte;

import campoECaselle.Casella;
import facceEAngoli.FacciaFronte;
import facceEAngoli.FacciaRetro;
import facceEAngoli.Risorsa;

public class CartaOro extends CartaGiocabile implements Giocabile {
	
	
	private ColoreCarta colore;
	
	private int punti;
	private String condizionePunti; //non posso usare "Oggetto" perchè alcune carte danno i punti in base agli angoli coperti alla loro giocata, uso String
	
	private Risorsa risorsaNecessaria1;
	private int quantitàRisorsaNecessaria1;
	
	private Risorsa risorsaNecessaria2;
	private int quantitàRisorsaNecessaria2;
	
	
	//Overloading del costruttore
	/**
	 * Costruttore delle carte oro con 2 tipi diversi di risorse necessarie per la loro giocata (sulla faccia frontale)
	 */
	public CartaOro(int id, FacciaFronte fronte, FacciaRetro retro, int punti, String condizionePunti, Risorsa risorsaNecessaria1, int quantitàRisorsaNecessaria1,
			Risorsa risorsaNecessaria2, int quantitàRisorsaNecessaria2) {
		super(id, fronte, retro);
		this.punti = punti;
		this.condizionePunti = condizionePunti;
		this.risorsaNecessaria1 = risorsaNecessaria1;
		this.quantitàRisorsaNecessaria1 = quantitàRisorsaNecessaria1;
		this.risorsaNecessaria2 = risorsaNecessaria2;
		this.quantitàRisorsaNecessaria2 = quantitàRisorsaNecessaria2;
	}
	
	//Overloading del costruttore
	/**
	 * Costruttore delle carte oro con 1 solo tipo di risorsa necessaria per la loro giocata (sulla faccia frontale)
	 */
	public CartaOro(int id, FacciaFronte fronte, FacciaRetro retro, int punti, String condizionePunti, Risorsa risorsaNecessaria1, int quantitàRisorsaNecessaria1) {
		super(id, fronte, retro);
		this.punti = punti;
		this.condizionePunti = condizionePunti;
		this.risorsaNecessaria1 = risorsaNecessaria1;
		this.quantitàRisorsaNecessaria1 = quantitàRisorsaNecessaria1;
	}
	
	
	@Override
	public String toString() {
		return "C.Oro: "+super.toString()+" "+colore+" "+punti+" "+condizionePunti+" "+risorsaNecessaria1+" "+quantitàRisorsaNecessaria1+" "+risorsaNecessaria2+" "+quantitàRisorsaNecessaria2;
	}
	
	
	@Override
	public void print() {
		
	}
}
