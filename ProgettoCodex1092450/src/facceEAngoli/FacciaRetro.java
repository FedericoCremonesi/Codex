package facceEAngoli;

public class FacciaRetro extends FacciaFronte {
	
	private Risorsa risorsaRetroCentrale; //solo per le facce retro
	private Risorsa risorsaRetroCentraleAggiuntiva1; //solo per le facce retro di carte iniziali che la hanno
	private Risorsa risorsaRetroCentraleAggiuntiva2; //solo per le facce retro di carte iniziali che la hanno

	
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
	
	
	@Override
	public void printFaccia(String coloreContorno) {
		
	}
}
