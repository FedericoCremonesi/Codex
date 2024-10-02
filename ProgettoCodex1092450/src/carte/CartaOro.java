package carte;

import campoECaselle.Casella;
import facceEAngoli.FacciaFronte;
import facceEAngoli.FacciaRetro;
import facceEAngoli.Risorsa;

public class CartaOro extends CartaGiocabile {
	
	
	private ColoreCarta colore;
	
	private int punti;
	private String condizionePunti; //non posso usare "Oggetto" perchè alcune carte danno i punti in base agli angoli coperti alla loro giocata, uso String
	
	private int quantitàRisorsaNecessaria1;
	
	private Risorsa risorsaNecessaria2;
	private int quantitàRisorsaNecessaria2;
	
	
	private Risorsa risorsaNecessaria1;
	public int getPunti() {
		return punti;
	}
	public void setPunti(int punti) {
		this.punti = punti;
	}
	public String getCondizionePunti() {
		return condizionePunti;
	}
	public void setCondizionePunti(String condizionePunti) {
		this.condizionePunti = condizionePunti;
	}
	public Risorsa getRisorsaNecessaria1() {
		return risorsaNecessaria1;
	}
	public void setRisorsaNecessaria1(Risorsa risorsaNecessaria1) {
		this.risorsaNecessaria1 = risorsaNecessaria1;
	}
	public int getQuantitàRisorsaNecessaria1() {
		return quantitàRisorsaNecessaria1;
	}
	public void setQuantitàRisorsaNecessaria1(int quantitàRisorsaNecessaria1) {
		this.quantitàRisorsaNecessaria1 = quantitàRisorsaNecessaria1;
	}
	public Risorsa getRisorsaNecessaria2() {
		return risorsaNecessaria2;
	}
	public void setRisorsaNecessaria2(Risorsa risorsaNecessaria2) {
		this.risorsaNecessaria2 = risorsaNecessaria2;
	}
	public int getQuantitàRisorsaNecessaria2() {
		return quantitàRisorsaNecessaria2;
	}
	public void setQuantitàRisorsaNecessaria2(int quantitàRisorsaNecessaria2) {
		this.quantitàRisorsaNecessaria2 = quantitàRisorsaNecessaria2;
	}
	
	
	public ColoreCarta getColore() {
		return colore;
	}
	public void setColore(ColoreCarta colore) {
		this.colore = colore;
	}
	
	
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
		if(retro.getRisorsaRetroCentrale().toString().toUpperCase().equals(Risorsa.FUNGHI.toString())) {
			this.colore = ColoreCarta.ROSSO;
		} else if(retro.getRisorsaRetroCentrale().toString().toUpperCase().equals(Risorsa.VEGETALE.toString())) {
			this.colore = ColoreCarta.VERDE;
		} else if(retro.getRisorsaRetroCentrale().toString().toUpperCase().equals(Risorsa.ANIMALE.toString())) {
			this.colore = ColoreCarta.BLU;
		} else if(retro.getRisorsaRetroCentrale().toString().toUpperCase().equals(Risorsa.INSETTI.toString())) {
			this.colore = ColoreCarta.VIOLA;
		}
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
		if(retro.getRisorsaRetroCentrale().toString().toUpperCase().equals(Risorsa.FUNGHI.toString())) {
			this.colore = ColoreCarta.ROSSO;
		} else if(retro.getRisorsaRetroCentrale().toString().toUpperCase().equals(Risorsa.VEGETALE.toString())) {
			this.colore = ColoreCarta.VERDE;
		} else if(retro.getRisorsaRetroCentrale().toString().toUpperCase().equals(Risorsa.ANIMALE.toString())) {
			this.colore = ColoreCarta.BLU;
		} else if(retro.getRisorsaRetroCentrale().toString().toUpperCase().equals(Risorsa.INSETTI.toString())) {
			this.colore = ColoreCarta.VIOLA;
		}
	}
	
	
	@Override
	public String toString() {
		return "C.Oro: "+super.toString()+" "+colore+" "+punti+" "+condizionePunti+" "+risorsaNecessaria1+" "+quantitàRisorsaNecessaria1+" "+risorsaNecessaria2+" "+quantitàRisorsaNecessaria2;
	}
	
	
	@Override
	public void print(String numeroLineaDaStampare) {
		String tipologiaCarta = "oro";
		if (!(super.getFacciaDiGioco().equals("RETRO")))
		{
			super.getFronte().printFaccia(colore.toString(), tipologiaCarta, numeroLineaDaStampare);
		}
		if (!(super.getFacciaDiGioco().equals("FRONTE")))
		{
			super.getRetro().printFaccia(colore.toString(), tipologiaCarta, numeroLineaDaStampare);
		}
		//usando le negazioni delle condizioni negli if è possibile stampare sia il fronte che il retro della carta nel caso in cui non sia ancora stata giocata
		
		
		if (super.getFacciaDiGioco().equals("non ancora giocata")) {
			System.out.print(ColoreCarta.CODICE_COLORE_CARTA_ORO+" ➤ Punti dati dalla carta: "+ColoreCarta.CODICE_RESET_COLORE+punti);
			if(condizionePunti.equals("nessunaCondizione"))
			{
				System.out.println(); //va semplicemente a capo
			} else {
				System.out.print(" per ogni ");
				if(condizionePunti.equals("angoliCoperti")) {
					System.out.println("angolo coperto"); //si intende "angolo coperto con la giocata di questa carta"
				} else {
					System.out.println(condizionePunti+" visibile nel campo"); //si intende "visibile nel campo del giocatore dopo la giocata di questa carta"
				}
			}
			
			System.out.print(ColoreCarta.CODICE_COLORE_CARTA_ORO+" ➤ Condizioni di gioco: "+ColoreCarta.CODICE_RESET_COLORE+quantitàRisorsaNecessaria1+" risorse del regno "+risorsaNecessaria1.toString()); //si intendono "condizioni di gioco per poter giocare questa carta (con la faccia frontale)"
			if(risorsaNecessaria2==null)
			{
				System.out.println(); //va semplicemente a capo
			} else {
				System.out.println(" e "+quantitàRisorsaNecessaria2+" risorse del regno "+risorsaNecessaria2.toString());
			}
		}
		//se la carta non è ancora stata giocata sul campo, stampo anche le informazioni aggiuntive relative ad essa
	}
}
