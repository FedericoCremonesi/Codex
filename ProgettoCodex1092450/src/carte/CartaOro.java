package carte;

import campoECaselle.Casella;
import facceEAngoli.Faccia;
import facceEAngoli.Risorsa;

public class CartaOro extends CartaGiocabile{
	
	
	private ColoreCarta colore;
	
	private int punti;
	private String condizionePunti; //non posso usare "Oggetto" perchè alcune carte danno i punti in base agli angoli coperti alla loro giocata, uso String
	
	private Risorsa risorsaNecessaria1;
	private int quantitàRisorsaNecessaria1;
	
	private Risorsa risorsaNecessaria2;
	private int quantitàRisorsaNecessaria2;
	
	
	public CartaOro(int id, String facciaDiGioco, Casella casellaDiGioco, Faccia fronte, Faccia retro, ColoreCarta colore,
			int punti, String condizionePunti, Risorsa risorsaNecessaria1, int quantitàRisorsaNecessaria1,
			Risorsa risorsaNecessaria2, int quantitàRisorsaNecessaria2) {
		super(id, facciaDiGioco, casellaDiGioco, fronte, retro);
		this.colore = colore;
		this.punti = punti;
		this.condizionePunti = condizionePunti;
		this.risorsaNecessaria1 = risorsaNecessaria1;
		this.quantitàRisorsaNecessaria1 = quantitàRisorsaNecessaria1;
		this.risorsaNecessaria2 = risorsaNecessaria2;
		this.quantitàRisorsaNecessaria2 = quantitàRisorsaNecessaria2;
	}
	
}
