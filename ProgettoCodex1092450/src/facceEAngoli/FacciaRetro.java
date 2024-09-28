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
		this.insiemeRigheFaccia = new String[NUMERO_RIGHE_FACCIA];
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
		this.insiemeRigheFaccia = new String[NUMERO_RIGHE_FACCIA];
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
		this.insiemeRigheFaccia = new String[NUMERO_RIGHE_FACCIA];
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
		this.insiemeRigheFaccia = new String[NUMERO_RIGHE_FACCIA];
	}
	
	
	@Override
	public String toString() {
		return "["+super.toString()+" - "+risorsaRetroCentrale+" "+risorsaRetroCentraleAggiuntiva1+" "+risorsaRetroCentraleAggiuntiva2+"]";
	}
	
	
	//questo metodo sarà eseguito quando il metodo "printFaccia" della classe madre chiamerà un metodo "costruisciArrayRigheFaccia" con questa signature
	@Override
	public String[] costruisciArrayRigheFaccia(String coloreContorno, String coloreLatiAngoli) {
		//Prima di tutto uso il costruttore della classe madre (FacciaFronte) per costruire l'array di stringhe contenenti le 13 righe della faccia
		String[] righeFaccia = super.costruisciArrayRigheFaccia(coloreContorno, coloreLatiAngoli);
		
		//poi modifico le 3 righe che differiscono tra la faccia frontale e quella sul retro
		
		//Modifico la riga6 della faccia
		righeFaccia[5] = ottieniRigaCartaDaRisorsa(risorsaRetroCentrale, coloreContorno);
		
		//Modifico la riga7 della faccia
		righeFaccia[6] = ottieniRigaCartaDaRisorsa(risorsaRetroCentraleAggiuntiva1, coloreContorno);
		
		//Modifico la riga8 della faccia
		righeFaccia[7] = ottieniRigaCartaDaRisorsa(risorsaRetroCentraleAggiuntiva2, coloreContorno);
		
		return righeFaccia;
	}
	
	
	public String ottieniRigaCartaDaRisorsa(Risorsa risorsa, String coloreContorno) {
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
