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
		this.insiemeLineeFaccia = new String[NUMERO_LINEE_FACCIA];
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
		this.insiemeLineeFaccia = new String[NUMERO_LINEE_FACCIA];
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
		this.insiemeLineeFaccia = new String[NUMERO_LINEE_FACCIA];
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
		this.insiemeLineeFaccia = new String[NUMERO_LINEE_FACCIA];
	}
	
	
	@Override
	public String toString() {
		return "["+super.toString()+" - "+risorsaRetroCentrale+" "+risorsaRetroCentraleAggiuntiva1+" "+risorsaRetroCentraleAggiuntiva2+"]";
	}
	
	
	//questo metodo sarà eseguito quando il metodo "printFaccia" della classe madre chiamerà un metodo "costruisciArrayLineeFaccia" con questa signature
	@Override
	public String[] costruisciArrayLineeFaccia(String coloreContorno, String coloreLatiAngoli) {
		//Prima di tutto uso il costruttore della classe madre (FacciaFronte) per costruire l'array di stringhe contenenti le 13 linee della faccia
		String[] lineeFaccia = super.costruisciArrayLineeFaccia(coloreContorno, coloreLatiAngoli);
		
		//poi modifico le 3 linee che differiscono tra la faccia frontale e quella sul retro
		
		//Modifico la linea6 della faccia
		lineeFaccia[5] = ottieniLineaCartaDaRisorsa(risorsaRetroCentrale, coloreContorno);
		
		//Modifico la linea7 della faccia
		lineeFaccia[6] = ottieniLineaCartaDaRisorsa(risorsaRetroCentraleAggiuntiva1, coloreContorno);
		
		//Modifico la linea8 della faccia
		lineeFaccia[7] = ottieniLineaCartaDaRisorsa(risorsaRetroCentraleAggiuntiva2, coloreContorno);
		
		return lineeFaccia;
	}
	
	
	public String ottieniLineaCartaDaRisorsa(Risorsa risorsa, String coloreContorno) {
		if(!(risorsa == null))
		{
			if(risorsa.toString().equals(Risorsa.FUNGHI.toString())) {
				return (coloreContorno+"|             "+Risorsa.CODICE_COLORE_RISORSA_FUNGHI+"  FUNGHI  "+ColoreCarta.CODICE_RESET_COLORE+coloreContorno+"             |"+ColoreCarta.CODICE_RESET_COLORE); //uso i codici colore per colorare le scritte delle risorse con i corrispettivi colori
			} else if(risorsa.toString().equals(Risorsa.VEGETALE.toString())) {
				return (coloreContorno+"|             "+Risorsa.CODICE_COLORE_RISORSA_VEGETALE+" VEGETALE "+ColoreCarta.CODICE_RESET_COLORE+coloreContorno+"             |"+ColoreCarta.CODICE_RESET_COLORE);
			} else if(risorsa.toString().equals(Risorsa.ANIMALE.toString())) {
				return (coloreContorno+"|             "+Risorsa.CODICE_COLORE_RISORSA_ANIMALE+"  ANIMALE "+ColoreCarta.CODICE_RESET_COLORE+coloreContorno+"             |"+ColoreCarta.CODICE_RESET_COLORE);
			} else if(risorsa.toString().equals(Risorsa.INSETTI.toString())) {
				return (coloreContorno+"|             "+Risorsa.CODICE_COLORE_RISORSA_INSETTI+"  INSETTI "+ColoreCarta.CODICE_RESET_COLORE+coloreContorno+"             |"+ColoreCarta.CODICE_RESET_COLORE);
			}
		} else {
			return (coloreContorno+"|                                    |"+ColoreCarta.CODICE_RESET_COLORE);
		}
		return null;
	}
}
