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
	
	
	public void printFaccia(String coloreCarta, String tipologiaCarta) { 
		String coloreContorno=null;
		if( coloreCarta.equals(ColoreCarta.ROSSO.toString()) ) { //utilizzo questi codici per colorare le delimitazioni (contorno e lati angoli) delle carte stampare
			coloreContorno = "\u001B[31m";
		} else if( coloreCarta.equals(ColoreCarta.VERDE.toString()) ) {
			coloreContorno = "\u001B[32m";
		} else if( coloreCarta.equals(ColoreCarta.BLU.toString()) ) {
			coloreContorno = "\u001B[34m";
		} else if( coloreCarta.equals(ColoreCarta.VIOLA.toString()) ) {
			coloreContorno = "\u001B[35m";
		} else {
			coloreContorno = "";
			/*
			 * Questo viene eseguito quando si tenta di stampare una qualsiasi faccia di una carta iniziale,
			 * si dovrebbe usare il codice del colore di default, tuttavia questo dipende dall'estetica dell'IDE su cui viene eseguito il programma,
			 * dunque non stampando nulla si risolve il problema
			 */
		}
		
		String coloreLatiAngoli=null;
		switch (tipologiaCarta) {
		case "risorsa":
			coloreLatiAngoli = "\u001B[30m"; //stampo i lati degli angoli delle carte risorsa di colore nero
			break;
		case "oro":
			coloreLatiAngoli = "\u001B[33m"; //stampo i lati degli angoli delle carte oro di colore giallo
			break;
		default:
			coloreLatiAngoli = "";
		}
		
		String resetColore="\u001B[0m";
		
		printRiga1(coloreContorno, resetColore);
		printRiga2(coloreContorno, resetColore, coloreLatiAngoli);
		printRiga3(coloreContorno, resetColore, coloreLatiAngoli);
		printRiga4(coloreContorno, resetColore, coloreLatiAngoli);
		printRiga5(coloreContorno, resetColore, coloreLatiAngoli);
		printRiga6(coloreContorno, resetColore);
		printRiga7(coloreContorno, resetColore);
		printRiga8(coloreContorno, resetColore);
		printRiga9(coloreContorno, resetColore, coloreLatiAngoli);
		printRiga10(coloreContorno, resetColore, coloreLatiAngoli);
		printRiga11(coloreContorno, resetColore, coloreLatiAngoli);
		printRiga12(coloreContorno, resetColore, coloreLatiAngoli);
		printRiga13(coloreContorno, resetColore);
	}
	
	
	public void printRiga1(String coloreContorno, String resetColore) {
		//stampo la carta: riga1
		System.out.println(coloreContorno+"▛------------------------------------▜"+resetColore);
	}
	
	public void printRiga2(String coloreContorno, String resetColore, String coloreLatiAngoli) {
		//stampo la carta: riga2
		if(angoloAltoSx instanceof AngoloVisibile) {
			System.out.print(coloreContorno+"|            "+coloreLatiAngoli+"|"+coloreContorno+"     "+resetColore);
		} else {
			System.out.print(coloreContorno+"|                  "+resetColore);
		}
		if(angoloAltoDx instanceof AngoloVisibile) {
			System.out.println(coloreContorno+"     "+coloreLatiAngoli+"|"+coloreContorno+"            |"+resetColore);
		} else {
			System.out.println(coloreContorno+"                  |"+resetColore);
		}
	}
	
	public void printRiga3(String coloreContorno, String resetColore, String coloreLatiAngoli) {
		//stampo la carta: riga3
		if(angoloAltoSx instanceof AngoloVisibile) {
			System.out.print(coloreContorno+"| "+resetColore+angoloAltoSx.toString()+coloreContorno+" "+coloreLatiAngoli+"|"+coloreContorno+"     "+resetColore);
		} else {
			System.out.print(coloreContorno+"|                  "+resetColore);
		}
		if(angoloAltoDx instanceof AngoloVisibile) {
			System.out.println(coloreContorno+"     "+coloreLatiAngoli+"|"+coloreContorno+" "+resetColore+angoloAltoDx.toString()+coloreContorno+" |"+resetColore);
		} else {
			System.out.println(coloreContorno+"                  |"+resetColore);
		}
	}
	
	public void printRiga4(String coloreContorno, String resetColore, String coloreLatiAngoli) {
		//stampo la carta: riga4
		if(angoloAltoSx instanceof AngoloVisibile) {
			System.out.print(coloreContorno+"|            "+coloreLatiAngoli+"|"+coloreContorno+"     "+resetColore);
		} else {
			System.out.print(coloreContorno+"|                  "+resetColore);
		}
		if(angoloAltoDx instanceof AngoloVisibile) {
			System.out.println(coloreContorno+"     "+coloreLatiAngoli+"|"+coloreContorno+"            |"+resetColore);
		} else {
			System.out.println(coloreContorno+"                  |"+resetColore);
		}
	}
	
	public void printRiga5(String coloreContorno, String resetColore, String coloreLatiAngoli) {
		//stampo la carta: riga5
		if(angoloAltoSx instanceof AngoloVisibile) {
			System.out.print(coloreContorno+"|"+coloreLatiAngoli+"------------▟"+coloreContorno+"     "+resetColore);
		} else {
			System.out.print(coloreContorno+"|                  "+resetColore);
		}
		if(angoloAltoDx instanceof AngoloVisibile) {
			System.out.println(coloreContorno+"     "+coloreLatiAngoli+"▙------------"+coloreContorno+"|"+resetColore);
		} else {
			System.out.println(coloreContorno+"                  |"+resetColore);
		}
	}
	
	public void printRiga6(String coloreContorno, String resetColore) {
		//stampo la carta: riga6
		System.out.println(coloreContorno+"|                                    |"+resetColore);
	}
	
	public void printRiga7(String coloreContorno, String resetColore) {
		//stampo la carta: riga7
		System.out.println(coloreContorno+"|                                    |"+resetColore);
	}
	
	public void printRiga8(String coloreContorno, String resetColore) {
		//stampo la carta: riga8
		System.out.println(coloreContorno+"|                                    |"+resetColore);
	}
	
	public void printRiga9(String coloreContorno, String resetColore, String coloreLatiAngoli) {
		//stampo la carta: riga9
		if(angoloBassoSx instanceof AngoloVisibile) {
			System.out.print(coloreContorno+"|"+coloreLatiAngoli+"------------▜"+coloreContorno+"     "+resetColore);
		} else {
			System.out.print(coloreContorno+"|                  "+resetColore);
		}
		if(angoloBassoDx instanceof AngoloVisibile) {
			System.out.println(coloreContorno+"     "+coloreLatiAngoli+"▛------------"+coloreContorno+"|"+resetColore);
		} else {
			System.out.println(coloreContorno+"                  |"+resetColore);
		}
	}
	
	public void printRiga10(String coloreContorno, String resetColore, String coloreLatiAngoli) {
		//stampo la carta: riga10
		if(angoloBassoSx instanceof AngoloVisibile) {
			System.out.print(coloreContorno+"|            "+coloreLatiAngoli+"|"+coloreContorno+"     "+resetColore);
		} else {
			System.out.print(coloreContorno+"|                  "+resetColore);
		}
		if(angoloBassoDx instanceof AngoloVisibile) {
			System.out.println(coloreContorno+"     "+coloreLatiAngoli+"|"+coloreContorno+"            |"+resetColore);
		} else {
			System.out.println(coloreContorno+"                  |"+resetColore);
		}
	}
	
	public void printRiga11(String coloreContorno, String resetColore, String coloreLatiAngoli) {
		//stampo la carta: riga11
		if(angoloBassoSx instanceof AngoloVisibile) {
			System.out.print(coloreContorno+"| "+resetColore+angoloBassoSx.toString()+coloreContorno+" "+coloreLatiAngoli+"|"+coloreContorno+"     "+resetColore);
		} else {
			System.out.print(coloreContorno+"|                  "+resetColore);
		}
		if(angoloBassoDx instanceof AngoloVisibile) {
			System.out.println(coloreContorno+"     "+coloreLatiAngoli+"|"+coloreContorno+" "+resetColore+angoloBassoDx.toString()+coloreContorno+" |"+resetColore);
		} else {
			System.out.println(coloreContorno+"                  |"+resetColore);
		}
	}
	
	public void printRiga12(String coloreContorno, String resetColore, String coloreLatiAngoli) {
		//stampo la carta: riga12
		if(angoloBassoSx instanceof AngoloVisibile) {
			System.out.print(coloreContorno+"|            "+coloreLatiAngoli+"|"+coloreContorno+"     "+resetColore);
		} else {
			System.out.print(coloreContorno+"|                  "+resetColore);
		}
		if(angoloBassoDx instanceof AngoloVisibile) {
			System.out.println(coloreContorno+"     "+coloreLatiAngoli+"|"+coloreContorno+"            |"+resetColore);
		} else {
			System.out.println(coloreContorno+"                  |"+resetColore);
		}
	}
	
	public void printRiga13(String coloreContorno, String resetColore) {
		//stampo la carta: riga13
		System.out.println(coloreContorno+"▙------------------------------------▟"+resetColore);
	}
}