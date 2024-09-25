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
		coloreContorno = ColoreCarta.ottieniStringCodiceColoreDaStringa(coloreCarta);
		
		String coloreLatiAngoli=null;
		switch (tipologiaCarta) {
		case "risorsa":
			coloreLatiAngoli = ColoreCarta.CODICE_COLORE_CARTA_RISORSA; //stampo i lati degli angoli delle carte risorsa di colore nero
			break;
		case "oro":
			coloreLatiAngoli = ColoreCarta.CODICE_COLORE_CARTA_ORO; //stampo i lati degli angoli delle carte oro di colore giallo
			break;
		default:
			coloreLatiAngoli = "";
		}
		
		//La stampa della carta è "spezzetata" per poter stampare comodamente le carte sul campo
		printRiga1(coloreContorno);
		printRiga2(coloreContorno, coloreLatiAngoli);
		printRiga3(coloreContorno, coloreLatiAngoli);
		printRiga4(coloreContorno, coloreLatiAngoli);
		printRiga5(coloreContorno, coloreLatiAngoli);
		printRiga6(coloreContorno);
		printRiga7(coloreContorno);
		printRiga8(coloreContorno);
		printRiga9(coloreContorno, coloreLatiAngoli);
		printRiga10(coloreContorno, coloreLatiAngoli);
		printRiga11(coloreContorno, coloreLatiAngoli);
		printRiga12(coloreContorno, coloreLatiAngoli);
		printRiga13(coloreContorno);
	}
	
	
	public void printRiga1(String coloreContorno) {
		//stampo la carta: riga1
		System.out.println(coloreContorno+"▛------------------------------------▜"+ColoreCarta.CODICE_RESET_COLORE);
	}
	
	public void printRiga2(String coloreContorno, String coloreLatiAngoli) {
		//stampo la carta: riga2
		if(angoloAltoSx instanceof AngoloVisibile) {
			System.out.print(coloreContorno+"|            "+coloreLatiAngoli+"|"+coloreContorno+"     "+ColoreCarta.CODICE_RESET_COLORE);
		} else {
			System.out.print(coloreContorno+"|                  "+ColoreCarta.CODICE_RESET_COLORE);
		}
		if(angoloAltoDx instanceof AngoloVisibile) {
			System.out.println(coloreContorno+"     "+coloreLatiAngoli+"|"+coloreContorno+"            |"+ColoreCarta.CODICE_RESET_COLORE);
		} else {
			System.out.println(coloreContorno+"                  |"+ColoreCarta.CODICE_RESET_COLORE);
		}
	}
	
	public void printRiga3(String coloreContorno, String coloreLatiAngoli) {
		//stampo la carta: riga3
		if(angoloAltoSx instanceof AngoloVisibile) {
			System.out.print(coloreContorno+"| "+ColoreCarta.CODICE_RESET_COLORE+angoloAltoSx.toString()+coloreContorno+" "+coloreLatiAngoli+"|"+coloreContorno+"     "+ColoreCarta.CODICE_RESET_COLORE);
		} else {
			System.out.print(coloreContorno+"|                  "+ColoreCarta.CODICE_RESET_COLORE);
		}
		if(angoloAltoDx instanceof AngoloVisibile) {
			System.out.println(coloreContorno+"     "+coloreLatiAngoli+"|"+coloreContorno+" "+ColoreCarta.CODICE_RESET_COLORE+angoloAltoDx.toString()+coloreContorno+" |"+ColoreCarta.CODICE_RESET_COLORE);
		} else {
			System.out.println(coloreContorno+"                  |"+ColoreCarta.CODICE_RESET_COLORE);
		}
	}
	
	public void printRiga4(String coloreContorno, String coloreLatiAngoli) {
		//stampo la carta: riga4
		if(angoloAltoSx instanceof AngoloVisibile) {
			System.out.print(coloreContorno+"|            "+coloreLatiAngoli+"|"+coloreContorno+"     "+ColoreCarta.CODICE_RESET_COLORE);
		} else {
			System.out.print(coloreContorno+"|                  "+ColoreCarta.CODICE_RESET_COLORE);
		}
		if(angoloAltoDx instanceof AngoloVisibile) {
			System.out.println(coloreContorno+"     "+coloreLatiAngoli+"|"+coloreContorno+"            |"+ColoreCarta.CODICE_RESET_COLORE);
		} else {
			System.out.println(coloreContorno+"                  |"+ColoreCarta.CODICE_RESET_COLORE);
		}
	}
	
	public void printRiga5(String coloreContorno, String coloreLatiAngoli) {
		//stampo la carta: riga5
		if(angoloAltoSx instanceof AngoloVisibile) {
			System.out.print(coloreContorno+"|"+coloreLatiAngoli+"------------▟"+coloreContorno+"     "+ColoreCarta.CODICE_RESET_COLORE);
		} else {
			System.out.print(coloreContorno+"|                  "+ColoreCarta.CODICE_RESET_COLORE);
		}
		if(angoloAltoDx instanceof AngoloVisibile) {
			System.out.println(coloreContorno+"     "+coloreLatiAngoli+"▙------------"+coloreContorno+"|"+ColoreCarta.CODICE_RESET_COLORE);
		} else {
			System.out.println(coloreContorno+"                  |"+ColoreCarta.CODICE_RESET_COLORE);
		}
	}
	
	public void printRiga6(String coloreContorno) {
		//stampo la carta: riga6
		System.out.println(coloreContorno+"|                                    |"+ColoreCarta.CODICE_RESET_COLORE);
	}
	
	public void printRiga7(String coloreContorno) {
		//stampo la carta: riga7
		System.out.println(coloreContorno+"|                                    |"+ColoreCarta.CODICE_RESET_COLORE);
	}
	
	public void printRiga8(String coloreContorno) {
		//stampo la carta: riga8
		System.out.println(coloreContorno+"|                                    |"+ColoreCarta.CODICE_RESET_COLORE);
	}
	
	public void printRiga9(String coloreContorno, String coloreLatiAngoli) {
		//stampo la carta: riga9
		if(angoloBassoSx instanceof AngoloVisibile) {
			System.out.print(coloreContorno+"|"+coloreLatiAngoli+"------------▜"+coloreContorno+"     "+ColoreCarta.CODICE_RESET_COLORE);
		} else {
			System.out.print(coloreContorno+"|                  "+ColoreCarta.CODICE_RESET_COLORE);
		}
		if(angoloBassoDx instanceof AngoloVisibile) {
			System.out.println(coloreContorno+"     "+coloreLatiAngoli+"▛------------"+coloreContorno+"|"+ColoreCarta.CODICE_RESET_COLORE);
		} else {
			System.out.println(coloreContorno+"                  |"+ColoreCarta.CODICE_RESET_COLORE);
		}
	}
	
	public void printRiga10(String coloreContorno, String coloreLatiAngoli) {
		//stampo la carta: riga10
		if(angoloBassoSx instanceof AngoloVisibile) {
			System.out.print(coloreContorno+"|            "+coloreLatiAngoli+"|"+coloreContorno+"     "+ColoreCarta.CODICE_RESET_COLORE);
		} else {
			System.out.print(coloreContorno+"|                  "+ColoreCarta.CODICE_RESET_COLORE);
		}
		if(angoloBassoDx instanceof AngoloVisibile) {
			System.out.println(coloreContorno+"     "+coloreLatiAngoli+"|"+coloreContorno+"            |"+ColoreCarta.CODICE_RESET_COLORE);
		} else {
			System.out.println(coloreContorno+"                  |"+ColoreCarta.CODICE_RESET_COLORE);
		}
	}
	
	public void printRiga11(String coloreContorno, String coloreLatiAngoli) {
		//stampo la carta: riga11
		if(angoloBassoSx instanceof AngoloVisibile) {
			System.out.print(coloreContorno+"| "+ColoreCarta.CODICE_RESET_COLORE+angoloBassoSx.toString()+coloreContorno+" "+coloreLatiAngoli+"|"+coloreContorno+"     "+ColoreCarta.CODICE_RESET_COLORE);
		} else {
			System.out.print(coloreContorno+"|                  "+ColoreCarta.CODICE_RESET_COLORE);
		}
		if(angoloBassoDx instanceof AngoloVisibile) {
			System.out.println(coloreContorno+"     "+coloreLatiAngoli+"|"+coloreContorno+" "+ColoreCarta.CODICE_RESET_COLORE+angoloBassoDx.toString()+coloreContorno+" |"+ColoreCarta.CODICE_RESET_COLORE);
		} else {
			System.out.println(coloreContorno+"                  |"+ColoreCarta.CODICE_RESET_COLORE);
		}
	}
	
	public void printRiga12(String coloreContorno, String coloreLatiAngoli) {
		//stampo la carta: riga12
		if(angoloBassoSx instanceof AngoloVisibile) {
			System.out.print(coloreContorno+"|            "+coloreLatiAngoli+"|"+coloreContorno+"     "+ColoreCarta.CODICE_RESET_COLORE);
		} else {
			System.out.print(coloreContorno+"|                  "+ColoreCarta.CODICE_RESET_COLORE);
		}
		if(angoloBassoDx instanceof AngoloVisibile) {
			System.out.println(coloreContorno+"     "+coloreLatiAngoli+"|"+coloreContorno+"            |"+ColoreCarta.CODICE_RESET_COLORE);
		} else {
			System.out.println(coloreContorno+"                  |"+ColoreCarta.CODICE_RESET_COLORE);
		}
	}
	
	public void printRiga13(String coloreContorno) {
		//stampo la carta: riga13
		System.out.println(coloreContorno+"▙------------------------------------▟"+ColoreCarta.CODICE_RESET_COLORE);
	}
}