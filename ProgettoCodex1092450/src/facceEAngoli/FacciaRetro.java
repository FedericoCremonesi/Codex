package facceEAngoli;

import carte.ColoreCarta;

public class FacciaRetro extends FacciaFronte {
	
	private Risorsa risorsaRetroCentrale; //solo per le facce retro
	private Risorsa risorsaRetroCentraleAggiuntiva1; //solo per le facce retro di carte iniziali che la hanno
	private Risorsa risorsaRetroCentraleAggiuntiva2; //solo per le facce retro di carte iniziali che la hanno

	
	public Risorsa getRisorsaRetroCentrale() {
		return risorsaRetroCentrale;
	}
	public void setRisorsaRetroCentrale(Risorsa risorsaRetroCentrale) {
		this.risorsaRetroCentrale = risorsaRetroCentrale;
	}

	public Risorsa getRisorsaRetroCentraleAggiuntiva1() {
		return risorsaRetroCentraleAggiuntiva1;
	}
	public void setRisorsaRetroCentraleAggiuntiva1(Risorsa risorsaRetroCentraleAggiuntiva1) {
		this.risorsaRetroCentraleAggiuntiva1 = risorsaRetroCentraleAggiuntiva1;
	}

	public Risorsa getRisorsaRetroCentraleAggiuntiva2() {
		return risorsaRetroCentraleAggiuntiva2;
	}
	public void setRisorsaRetroCentraleAggiuntiva2(Risorsa risorsaRetroCentraleAggiuntiva2) {
		this.risorsaRetroCentraleAggiuntiva2 = risorsaRetroCentraleAggiuntiva2;
	}
	
	
	//Overloading del metodo costruttore
	/**
	 * Costruttore della faccia sul retro delle carte: risorsa e oro
	 */
	public FacciaRetro(Risorsa risorsaRetroCentrale) {
		super(new AngoloVisibile("vuoto"), new AngoloVisibile("vuoto"), new AngoloVisibile("vuoto"), new AngoloVisibile("vuoto"));
		this.risorsaRetroCentrale = risorsaRetroCentrale;
		this.risorsaRetroCentraleAggiuntiva1 = null;
		this.risorsaRetroCentraleAggiuntiva2 = null;
	}
	
	//Overloading del metodo costruttore
	/**
	 * Costruttore della faccia sul retro delle carte: iniziali con 1 risorsa centrale
	 */
	public FacciaRetro(Angolo angoloAltoSx, Angolo angoloAltoDx, Angolo angoloBassoSx, Angolo angoloBassoDx,
			Risorsa risorsaRetroCentrale) {
		super(angoloAltoSx, angoloAltoDx, angoloBassoSx, angoloBassoDx);
		this.risorsaRetroCentrale = risorsaRetroCentrale;
		this.risorsaRetroCentraleAggiuntiva1 = null;
		this.risorsaRetroCentraleAggiuntiva2 = null;
	}
	
	//Overloading del metodo costruttore
	/**
	 * Costruttore della faccia sul retro delle carte: iniziali con 2 risorse centrali
	 */
	public FacciaRetro(Angolo angoloAltoSx, Angolo angoloAltoDx, Angolo angoloBassoSx, Angolo angoloBassoDx,
			Risorsa risorsaRetroCentrale, Risorsa risorsaRetroCentraleAggiuntiva1) {
		super(angoloAltoSx, angoloAltoDx, angoloBassoSx, angoloBassoDx);
		this.risorsaRetroCentrale = risorsaRetroCentrale;
		this.risorsaRetroCentraleAggiuntiva1 = risorsaRetroCentraleAggiuntiva1;
		this.risorsaRetroCentraleAggiuntiva2 = null;
	}
	
	//Overloading del metodo costruttore
	/**
	 * Costruttore della faccia sul retro delle carte: iniziali con 3 risorse centrali
	 */
	public FacciaRetro(Angolo angoloAltoSx, Angolo angoloAltoDx, Angolo angoloBassoSx, Angolo angoloBassoDx,
			Risorsa risorsaRetroCentrale, Risorsa risorsaRetroCentraleAggiuntiva1, Risorsa risorsaRetroCentraleAggiuntiva2) {
		super(angoloAltoSx, angoloAltoDx, angoloBassoSx, angoloBassoDx);
		this.risorsaRetroCentrale = risorsaRetroCentrale;
		this.risorsaRetroCentraleAggiuntiva1 = risorsaRetroCentraleAggiuntiva1;
		this.risorsaRetroCentraleAggiuntiva2 = risorsaRetroCentraleAggiuntiva2;
	}
	
	
	@Override
	public String toString() {
		return "["+super.toString()+" - "+risorsaRetroCentrale+" "+risorsaRetroCentraleAggiuntiva1+" "+risorsaRetroCentraleAggiuntiva2+"]";
	}
	
	
	//sovrascrivo solo i metodi per stampare la parte "media" della faccia, in quanto per la parte "alta" e "bassa" si possono usare gli stessi metodi della faccia frontale
	@Override
	public void printRiga6(String coloreContorno) {
		//stampo la carta: riga6
		printRigaCartaDaRisorsa(risorsaRetroCentrale, coloreContorno);
	}
	
	@Override
	public void printRiga7(String coloreContorno) {
		//stampo la carta: riga7
		printRigaCartaDaRisorsa(risorsaRetroCentraleAggiuntiva1, coloreContorno);
	}
	
	@Override
	public void printRiga8(String coloreContorno) {
		//stampo la carta: riga8
		printRigaCartaDaRisorsa(risorsaRetroCentraleAggiuntiva2, coloreContorno);
	}
	
	
	public void printRigaCartaDaRisorsa(Risorsa risorsa, String coloreContorno) {
		if(!(risorsa == null))
		{
			if(risorsa.toString().equals(Risorsa.FUNGHI.toString())) {
				System.out.println(coloreContorno+"|             "+Risorsa.CODICE_COLORE_RISORSA_FUNGHI+"  FUNGHI  "+ColoreCarta.CODICE_RESET_COLORE+coloreContorno+"             |"+ColoreCarta.CODICE_RESET_COLORE); //uso i codici colore per colorare le scritte delle risorse con i corrispettivi colori
			} else if(risorsa.toString().equals(Risorsa.VEGETALE.toString())) {
				System.out.println(coloreContorno+"|             "+Risorsa.CODICE_COLORE_RISORSA_VEGETALE+" VEGETALE "+ColoreCarta.CODICE_RESET_COLORE+coloreContorno+"             |"+ColoreCarta.CODICE_RESET_COLORE);
			} else if(risorsa.toString().equals(Risorsa.ANIMALE.toString())) {
				System.out.println(coloreContorno+"|             "+Risorsa.CODICE_COLORE_RISORSA_ANIMALE+"  ANIMALE "+ColoreCarta.CODICE_RESET_COLORE+coloreContorno+"             |"+ColoreCarta.CODICE_RESET_COLORE);
			} else if(risorsa.toString().equals(Risorsa.INSETTI.toString())) {
				System.out.println(coloreContorno+"|             "+Risorsa.CODICE_COLORE_RISORSA_INSETTI+"  INSETTI "+ColoreCarta.CODICE_RESET_COLORE+coloreContorno+"             |"+ColoreCarta.CODICE_RESET_COLORE);
			}
		} else {
			System.out.println(coloreContorno+"|                                    |"+ColoreCarta.CODICE_RESET_COLORE);
		}
	}
}
