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
	public void printRiga6(String coloreContorno, String resetColore) {
		//stampo la carta: riga6
		if(!(risorsaRetroCentrale == null))
		{
			if(risorsaRetroCentrale.toString().equals(Risorsa.FUNGHI.toString())) {
				System.out.println(coloreContorno+"|             "+"\u001B[31m"+"  FUNGHI  "+"\u001B[0m"+coloreContorno+"             |"+resetColore); //uso i codici colore per colorare le scritte delle risorse con i corrispettivi colori
			} else if(risorsaRetroCentrale.toString().equals(Risorsa.VEGETALE.toString())) {
				System.out.println(coloreContorno+"|             "+"\u001B[32m"+" VEGETALE "+"\u001B[0m"+coloreContorno+"             |"+resetColore);
			} else if(risorsaRetroCentrale.toString().equals(Risorsa.ANIMALE.toString())) {
				System.out.println(coloreContorno+"|             "+"\u001B[34m"+"  ANIMALE "+"\u001B[0m"+coloreContorno+"             |"+resetColore);
			} else if(risorsaRetroCentrale.toString().equals(Risorsa.INSETTI.toString())) {
				System.out.println(coloreContorno+"|             "+"\u001B[35m"+"  INSETTI "+"\u001B[0m"+coloreContorno+"             |"+resetColore);
			}
		} else {
			System.out.println(coloreContorno+"|                                    |"+resetColore);
		}
	}
	
	@Override
	public void printRiga7(String coloreContorno, String resetColore) {
		//stampo la carta: riga7
		if(!(risorsaRetroCentraleAggiuntiva1 == null))
		{
			if(risorsaRetroCentraleAggiuntiva1.toString().equals(Risorsa.FUNGHI.toString())) {
				System.out.println(coloreContorno+"|             "+"\u001B[31m"+"  FUNGHI  "+"\u001B[0m"+coloreContorno+"             |"+resetColore); //uso i codici colore per colorare le scritte delle risorse con i corrispettivi colori
			} else if(risorsaRetroCentraleAggiuntiva1.toString().equals(Risorsa.VEGETALE.toString())) {
				System.out.println(coloreContorno+"|             "+"\u001B[32m"+" VEGETALE "+"\u001B[0m"+coloreContorno+"             |"+resetColore);
			} else if(risorsaRetroCentraleAggiuntiva1.toString().equals(Risorsa.ANIMALE.toString())) {
				System.out.println(coloreContorno+"|             "+"\u001B[34m"+"  ANIMALE "+"\u001B[0m"+coloreContorno+"             |"+resetColore);
			} else if(risorsaRetroCentraleAggiuntiva1.toString().equals(Risorsa.INSETTI.toString())) {
				System.out.println(coloreContorno+"|             "+"\u001B[35m"+"  INSETTI "+"\u001B[0m"+coloreContorno+"             |"+resetColore);
			}
		} else {
			System.out.println(coloreContorno+"|                                    |"+resetColore);
		}
	}
	
	@Override
	public void printRiga8(String coloreContorno, String resetColore) {
		//stampo la carta: riga8
		if(!(risorsaRetroCentraleAggiuntiva2 == null))
		{
			if(risorsaRetroCentraleAggiuntiva2.toString().equals(Risorsa.FUNGHI.toString())) {
				System.out.println(coloreContorno+"|             "+"\u001B[31m"+"  FUNGHI  "+"\u001B[0m"+coloreContorno+"             |"+resetColore); //uso i codici colore per colorare le scritte delle risorse con i corrispettivi colori
			} else if(risorsaRetroCentraleAggiuntiva2.toString().equals(Risorsa.VEGETALE.toString())) {
				System.out.println(coloreContorno+"|             "+"\u001B[32m"+" VEGETALE "+"\u001B[0m"+coloreContorno+"             |"+resetColore);
			} else if(risorsaRetroCentraleAggiuntiva2.toString().equals(Risorsa.ANIMALE.toString())) {
				System.out.println(coloreContorno+"|             "+"\u001B[34m"+"  ANIMALE "+"\u001B[0m"+coloreContorno+"             |"+resetColore);
			} else if(risorsaRetroCentraleAggiuntiva2.toString().equals(Risorsa.INSETTI.toString())) {
				System.out.println(coloreContorno+"|             "+"\u001B[35m"+"  INSETTI "+"\u001B[0m"+coloreContorno+"             |"+resetColore);
			}
		} else {
			System.out.println(coloreContorno+"|                                    |"+resetColore);
		}
	}
}
