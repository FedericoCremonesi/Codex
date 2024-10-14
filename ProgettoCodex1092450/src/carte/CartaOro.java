package carte;

import java.util.HashMap;

import campoECaselle.Campo;
import campoECaselle.Casella;
import facceEAngoli.FacciaFronte;
import facceEAngoli.FacciaRetro;
import facceEAngoli.Oggetto;
import facceEAngoli.Risorsa;
import giocatori.Giocatore;

public class CartaOro extends CartaRisorsa {
	
	
	private String condizionePunti; //non posso usare "Oggetto" perchè alcune carte danno i punti in base agli angoli coperti alla loro giocata, uso String
	
	private Risorsa risorsaNecessaria1;
	private int quantitàRisorsaNecessaria1;
	
	private Risorsa risorsaNecessaria2;
	private int quantitàRisorsaNecessaria2;
	
	
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
	
	
	
	//Overloading del costruttore
	/**
	 * Costruttore delle carte oro con 2 tipi diversi di risorse necessarie per la loro giocata (sulla faccia frontale)
	 */
	public CartaOro(int id, FacciaFronte fronte, FacciaRetro retro, int punti, String condizionePunti, Risorsa risorsaNecessaria1, int quantitàRisorsaNecessaria1,
			Risorsa risorsaNecessaria2, int quantitàRisorsaNecessaria2) {
		super(id, fronte, retro, punti);
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
		super(id, fronte, retro, punti);
		this.condizionePunti = condizionePunti;
		this.risorsaNecessaria1 = risorsaNecessaria1;
		this.quantitàRisorsaNecessaria1 = quantitàRisorsaNecessaria1;
	}
	
	
	@Override
	public void print(String numeroLineaDaStampare) {
		String tipologiaCarta = "oro";
		if(super.getFacciaDiGioco().equals("FRONTE"))
		{
			super.getFronte().printFaccia(super.getColore().toString(), tipologiaCarta, numeroLineaDaStampare);
		}
		if(super.getFacciaDiGioco().equals("RETRO"))
		{
			super.getRetro().printFaccia(super.getColore().toString(), tipologiaCarta, numeroLineaDaStampare);
		}
		//Così stampo le due faccie affiancate (nel caso non sia ancora stata giocata la carta)
		if (super.getFacciaDiGioco().equals("non ancora giocata")) {
			for(Integer k=0; k<FacciaFronte.NUMERO_LINEE_FACCIA; k++) {
				//per poter usare il metodo toString, devo usare il tipo Integer (non int, che è un tipo primitivo e non ha metodi)
				super.getFronte().printFaccia(super.getColore().toString(), tipologiaCarta, k.toString());
				System.out.print(" \t");
				super.getRetro().printFaccia(super.getColore().toString(), tipologiaCarta, k.toString());
				System.out.print("\n");
			}
		}
		
		if (super.getFacciaDiGioco().equals("non ancora giocata")) {
			System.out.print(ColoreCarta.CODICE_COLORE_CARTA_ORO+" ➤ Punti dati dalla carta: "+ColoreCarta.CODICE_RESET_COLORE+super.getPunti());
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
			
			System.out.print(ColoreCarta.CODICE_COLORE_CARTA_ORO+" ➤ Condizioni di gioco: "+ColoreCarta.CODICE_RESET_COLORE+quantitàRisorsaNecessaria1+" risorse del regno "+Risorsa.ottieniStringCodiceColoreDaRisorsa(risorsaNecessaria1.toString())+risorsaNecessaria1.toString()+Risorsa.CODICE_RESET_COLORE); //si intendono "condizioni di gioco per poter giocare questa carta (con la faccia frontale)"
			if(risorsaNecessaria2==null)
			{
				System.out.println(); //va semplicemente a capo
			} else {
				System.out.println(" e "+quantitàRisorsaNecessaria2+" risorse del regno "+Risorsa.ottieniStringCodiceColoreDaRisorsa(risorsaNecessaria2.toString())+risorsaNecessaria2.toString()+Risorsa.CODICE_RESET_COLORE);
			}
		}
		//se la carta non è ancora stata giocata sul campo, stampo anche le informazioni aggiuntive relative ad essa
	}
	
	
	public void assegnaPunti(Giocatore giocatore, int numeroAngoliCopertiConGiocata) {
		if(super.getFacciaDiGioco().equals("FRONTE")) {
			int puntiIniziali = giocatore.getPunti();
			int incremento = 0;
			
			if(condizionePunti.equals("nessunaCondizione")) {		//Caso in cui punti non dipendono da nessuna condizione
				incremento = super.getPunti();
				
				//Stampo a schermo il messaggio per indicare che sono stati assegnati i punti
				System.out.print("\tHai guadagnato "+incremento);
				if(incremento == 1) {
					System.out.println(" punto");
				} else {
					System.out.println(" punti");
				} 
				
			} else if(condizionePunti.equals("angoliCoperti")) {	//Caso in cui punti dati da: numero di angoli coperti alla giocata della carta
				incremento = (super.getPunti())*(numeroAngoliCopertiConGiocata);
				
				//Stampo a schermo il messaggio per indicare che è stata applicata la condizione e sono stati assegnati i punti
				System.out.print("\tCon la giocata di questa carta hai coperto "+numeroAngoliCopertiConGiocata+" ");
				if(numeroAngoliCopertiConGiocata==1) {
					System.out.print("angolo");
				} else {
					System.out.print("angoli");
				}
				System.out.print(", quindi ottieni "+incremento+" ");
				if(incremento==1) {
					System.out.println("punto");
				} else {
					System.out.println("punti");
				}
				
			} else {												//Caso in cui punti dati da: numero di piume/inchiostri/pergamene sul campo
				System.out.println("Contando anche la carta appena giocata,");
				HashMap<String, Integer> conteggioRisorseEOggetti = giocatore.getCampo().contaRisorseEOggettiVisibili(40, 40, true); //Così facendo eseguo lo scorrimento del campo, conto le risorse e gli oggetti, e inoltre salvo il risultato nella variabile locale di questo metodo
				giocatore.getCampo().stampaConteggioRisorseEOggettiVisibili();
				String oggettoCheConferiscePunti = condizionePunti;
				incremento = (super.getPunti())*(conteggioRisorseEOggetti.get(oggettoCheConferiscePunti));
				
				//Stampo a schermo il messaggio per indicare che è stata applicata la condizione e sono stati assegnati i punti
				System.out.print("\tSul campo hai "+conteggioRisorseEOggetti.get(oggettoCheConferiscePunti)+" ");
				if(conteggioRisorseEOggetti.get(oggettoCheConferiscePunti)==1) {
					System.out.print("simbolo");
				} else {
					System.out.print("simboli");
				}
				System.out.print(" di "+oggettoCheConferiscePunti+" ");
				if(conteggioRisorseEOggetti.get(oggettoCheConferiscePunti)==1) {
					System.out.print("visibile");
				} else {
					System.out.print("visibili");
				}
				System.out.print(", quindi ottieni "+incremento+" ");
				if(incremento==1) {
					System.out.println("punto");
				} else {
					System.out.println("punti");
				}
				
			}
			
			giocatore.setPunti(puntiIniziali+incremento);
		}
	}
	
	
	
}
