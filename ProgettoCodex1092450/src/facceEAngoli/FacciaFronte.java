package facceEAngoli;

import carte.ColoreCarta;

public class FacciaFronte {
	
	private Angolo angoloAltoSx;
	private Angolo angoloAltoDx;
	private Angolo angoloBassoSx;
	private Angolo angoloBassoDx;
	
	
	/**
	 * Costruttore della faccia frontale delle carte: risorsa, oro e iniziali
	 */
	public FacciaFronte(Angolo angoloAltoSx, Angolo angoloAltoDx, Angolo angoloBassoSx, Angolo angoloBassoDx) {
		this.angoloAltoSx = angoloAltoSx;
		this.angoloAltoDx = angoloAltoDx;
		this.angoloBassoSx = angoloBassoSx;
		this.angoloBassoDx = angoloBassoDx;
	}
	
	
	@Override
	public String toString() {
		return "["+angoloAltoSx+" "+angoloAltoDx+" "+angoloBassoSx+" "+angoloBassoDx+"]";
	}
	
	
	public void printFaccia(String coloreContorno) { //utilizzo questi codici per colorare il colore delle carte stampare
		String codiceColore=null;
		String resetColore="\u001B[0m";
		if( coloreContorno.equals(ColoreCarta.ROSSO.toString()) ) {
			codiceColore = "\u001B[31m";
		} else if( coloreContorno.equals(ColoreCarta.VERDE.toString()) ) {
			codiceColore = "\u001B[32m";
		} else if( coloreContorno.equals(ColoreCarta.BLU.toString()) ) {
			codiceColore = "\u001B[34m";
		} else if( coloreContorno.equals(ColoreCarta.VIOLA.toString()) ) {
			codiceColore = "\u001B[35m";
		}
		
		System.out.println(codiceColore+"▛----------------------------------▜");
		System.out.println("|                                   |");
		System.out.println("|"+resetColore);
	}
}