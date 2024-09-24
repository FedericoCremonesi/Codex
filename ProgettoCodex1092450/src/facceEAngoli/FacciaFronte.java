package facceEAngoli;

import carte.ColoreCarta;

public class FacciaFronte {

	private Angolo angoloAltoSx;
	private Angolo angoloAltoDx;
	private Angolo angoloBassoSx;
	private Angolo angoloBassoDx;
	
	
	public Angolo getAngoloAltoSx() {
		return angoloAltoSx;
	}
	public void setAngoloAltoSx(Angolo angoloAltoSx) {
		this.angoloAltoSx = angoloAltoSx;
	}

	public Angolo getAngoloAltoDx() {
		return angoloAltoDx;
	}
	public void setAngoloAltoDx(Angolo angoloAltoDx) {
		this.angoloAltoDx = angoloAltoDx;
	}

	public Angolo getAngoloBassoSx() {
		return angoloBassoSx;
	}
	public void setAngoloBassoSx(Angolo angoloBassoSx) {
		this.angoloBassoSx = angoloBassoSx;
	}

	public Angolo getAngoloBassoDx() {
		return angoloBassoDx;
	}
	public void setAngoloBassoDx(Angolo angoloBassoDx) {
		this.angoloBassoDx = angoloBassoDx;
	}
	
	
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
	
	
	public void printFaccia(String coloreCarta) { 
		String codiceColore=null;
		String resetColore="\u001B[0m";
		if( coloreCarta.equals(ColoreCarta.ROSSO.toString()) ) { //utilizzo questi codici per colorare le delimitazioni (contorno e lati angoli) delle carte stampare
			codiceColore = "\u001B[31m";
		} else if( coloreCarta.equals(ColoreCarta.VERDE.toString()) ) {
			codiceColore = "\u001B[32m";
		} else if( coloreCarta.equals(ColoreCarta.BLU.toString()) ) {
			codiceColore = "\u001B[34m";
		} else if( coloreCarta.equals(ColoreCarta.VIOLA.toString()) ) {
			codiceColore = "\u001B[35m";
		} else {
			codiceColore = "";
			/*
			 * Questo viene eseguito quando si tenta di stampare una qualsiasi faccia di una carta iniziale,
			 * si dovrebbe usare il codice del colore di default, tuttavia questo dipende dall'estetica dell'IDE su cui viene eseguito il programma,
			 * dunque non stampando nulla si risolve il problema
			 */
		}
		
		printFacciaAlta(codiceColore);
		printFacciaMedia(codiceColore);
		printFacciaBassa(codiceColore, resetColore);
	}
	
	
	public void printFacciaAlta(String codiceColore) {
		//stampo la carta: riga1
		System.out.println(codiceColore+"▛------------------------------------▜");
		
		//stampo la carta: riga2
		if(angoloAltoSx instanceof AngoloVisibile) {
			System.out.print("|            |     ");
		} else {
			System.out.print("|                  ");
		}
		if(angoloAltoDx instanceof AngoloVisibile) {
			System.out.println("     |            |");
		} else {
			System.out.println("                  |");
		}
		
		//stampo la carta: riga3
		if(angoloAltoSx instanceof AngoloVisibile) {
			System.out.print("| "+angoloAltoSx.toString()+codiceColore+" |     ");
		} else {
			System.out.print("|                  ");
		}
		if(angoloAltoDx instanceof AngoloVisibile) {
			System.out.println("     | "+angoloAltoDx.toString()+codiceColore+" |");
		} else {
			System.out.println("                  |");
		}
		
		//stampo la carta: riga4
		if(angoloAltoSx instanceof AngoloVisibile) {
			System.out.print("|            |     ");
		} else {
			System.out.print("|                  ");
		}
		if(angoloAltoDx instanceof AngoloVisibile) {
			System.out.println("     |            |");
		} else {
			System.out.println("                  |");
		}
		
		//stampo la carta: riga5
		if(angoloAltoSx instanceof AngoloVisibile) {
			System.out.print("|------------▟     ");
		} else {
			System.out.print("|                  ");
		}
		if(angoloAltoDx instanceof AngoloVisibile) {
			System.out.println("     ▙------------|");
		} else {
			System.out.println("                  |");
		}
	}
	public void printFacciaMedia(String codiceColore) {
		//stampo la carta: riga6
		System.out.println("|                                    |");
		
		//stampo la carta: riga7
		System.out.println("|                                    |");
		
		//stampo la carta: riga8
		System.out.println("|                                    |");
	}
	public void printFacciaBassa(String codiceColore, String resetColore) {
		//stampo la carta: riga9
		if(angoloBassoSx instanceof AngoloVisibile) {
			System.out.print("|------------▜     ");
		} else {
			System.out.print("|                  ");
		}
		if(angoloBassoDx instanceof AngoloVisibile) {
			System.out.println("     ▛------------|");
		} else {
			System.out.println("                  |");
		}
		
		//stampo la carta: riga10
		if(angoloBassoSx instanceof AngoloVisibile) {
			System.out.print("|            |     ");
		} else {
			System.out.print("|                  ");
		}
		if(angoloBassoDx instanceof AngoloVisibile) {
			System.out.println("     |            |");
		} else {
			System.out.println("                  |");
		}
		
		//stampo la carta: riga11
		if(angoloBassoSx instanceof AngoloVisibile) {
			System.out.print("| "+angoloBassoSx.toString()+codiceColore+" |     ");
		} else {
			System.out.print("|                  ");
		}
		if(angoloBassoDx instanceof AngoloVisibile) {
			System.out.println("     | "+angoloBassoDx.toString()+codiceColore+" |");
		} else {
			System.out.println("                  |");
		}
		
		//stampo la carta: riga12
		if(angoloBassoSx instanceof AngoloVisibile) {
			System.out.print("|            |     ");
		} else {
			System.out.print("|                  ");
		}
		if(angoloBassoDx instanceof AngoloVisibile) {
			System.out.println("     |            |");
		} else {
			System.out.println("                  |");
		}
		
		//stampo la carta: riga13
		System.out.println("▙------------------------------------▟"+resetColore);
	}
}