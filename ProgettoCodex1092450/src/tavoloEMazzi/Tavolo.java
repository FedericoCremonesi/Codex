package tavoloEMazzi;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import campoECaselle.Casella;
import carte.CartaIniziale;
import carte.CartaObiettivo;
import carte.CartaOro;
import carte.CartaRisorsa;
import facceEAngoli.Angolo;
import facceEAngoli.AngoloVisibile;
import facceEAngoli.FacciaFronte;
import facceEAngoli.FacciaRetro;
import facceEAngoli.Risorsa;

public class Tavolo {
	
	
	private Mazzo<CartaRisorsa> mazzoCarteRisorsa;
	private Mazzo<CartaOro> mazzoCarteOro;
	private Mazzo<CartaIniziale> mazzoCarteIniziali;
	private Mazzo<CartaObiettivo> mazzoCarteObiettivo;
	private CartaObiettivo[] obiettiviComuni;
	
	
	public Mazzo<CartaRisorsa> getMazzoCarteRisorsa() {
		return mazzoCarteRisorsa;
	}
	public void setMazzoCarteRisorsa(Mazzo<CartaRisorsa> mazzoCarteRisorsa) {
		this.mazzoCarteRisorsa = mazzoCarteRisorsa;
	}

	public Mazzo<CartaOro> getMazzoCarteOro() {
		return mazzoCarteOro;
	}
	public void setMazzoCarteOro(Mazzo<CartaOro> mazzoCarteOro) {
		this.mazzoCarteOro = mazzoCarteOro;
	}

	public Mazzo<CartaIniziale> getMazzoCarteIniziali() {
		return mazzoCarteIniziali;
	}
	public void setMazzoCarteIniziali(Mazzo<CartaIniziale> mazzoCarteIniziali) {
		this.mazzoCarteIniziali = mazzoCarteIniziali;
	}

	public Mazzo<CartaObiettivo> getMazzoCarteObiettivo() {
		return mazzoCarteObiettivo;
	}
	public void setMazzoCarteObiettivo(Mazzo<CartaObiettivo> mazzoCarteObiettivo) {
		this.mazzoCarteObiettivo = mazzoCarteObiettivo;
	}

	public CartaObiettivo[] getObiettiviComuni() {
		return obiettiviComuni;
	}
	public void setObiettiviComuni(CartaObiettivo[] obiettiviComuni) {
		this.obiettiviComuni = obiettiviComuni;
	}

	
	public Tavolo() {
		this.mazzoCarteRisorsa = new Mazzo<CartaRisorsa>(new ArrayList<CartaRisorsa>());
		this.mazzoCarteOro = new Mazzo<CartaOro>(new ArrayList<CartaOro>()); //posso mettere "<CartaOro>" dopo "new Mazzo" *
		this.mazzoCarteIniziali = new Mazzo(new ArrayList<CartaIniziale>()); //* ma posso anche evitare di mettere "<CartaIniziale>" dopo "new Mazzo", non è necessario
		this.mazzoCarteObiettivo = new Mazzo(new ArrayList<CartaObiettivo>());
		
		obiettiviComuni = new CartaObiettivo[2]; //creo il vettore degli obiettivi comuni vuoto
	}
	
	
	public void creaMazzi() {
		System.out.println("Creando i 4 mazzi di carte...");
		creaMazzoDaFile(mazzoCarteRisorsa, new File("fileCarteRisorsa.csv"));
		creaMazzoDaFile(mazzoCarteOro, new File("fileCarteOro.csv"));
		creaMazzoDaFile(mazzoCarteIniziali, new File("fileCarteIniziali.csv"));
		creaMazzoDaFile(mazzoCarteObiettivo, new File("fileCarteObiettivo.csv"));
	}
	
	
	public void creaMazzoDaFile(Mazzo mazzoInCuiMettereCarte, File fileDaCuiLeggere) {
		
		Scanner sc;
		try {
			
			sc = new Scanner(fileDaCuiLeggere);
			while (sc.hasNext())
			{
				String line = sc.nextLine();
				String[] splitted=line.split(",");
				
				if(Integer.parseInt(splitted[0]) >= 1 && Integer.parseInt(splitted[0]) <= 40)
				{
					mazzoInCuiMettereCarte.aggiungiCartaAMazzo(new CartaRisorsa( Integer.parseInt(splitted[0]),
								new FacciaFronte(Angolo.creaAngoloInBaseAInput(splitted[1]),Angolo.creaAngoloInBaseAInput(splitted[2]),Angolo.creaAngoloInBaseAInput(splitted[3]),Angolo.creaAngoloInBaseAInput(splitted[4])),
								new FacciaRetro(Risorsa.valueOf(splitted[5])),
												Integer.parseInt(splitted[6]) ));
				} else
				if(Integer.parseInt(splitted[0]) >= 41 && Integer.parseInt(splitted[0]) <= 80)
				{
					if(Integer.parseInt(splitted[11])!=0)
					{
						//Così costruisco le carte oro con 2 tipi diversi di risorse necessarie per la loro giocata (sulla faccia frontale) -> uso overloading
						mazzoInCuiMettereCarte.aggiungiCartaAMazzo(new CartaOro( Integer.parseInt(splitted[0]),
								new FacciaFronte(Angolo.creaAngoloInBaseAInput(splitted[1]),Angolo.creaAngoloInBaseAInput(splitted[2]),Angolo.creaAngoloInBaseAInput(splitted[3]),Angolo.creaAngoloInBaseAInput(splitted[4])),
								new FacciaRetro(Risorsa.valueOf(splitted[5])),
								Integer.parseInt(splitted[6]), splitted[7],
								Risorsa.valueOf(splitted[8]), Integer.parseInt(splitted[9]),
								Risorsa.valueOf(splitted[10]), Integer.parseInt(splitted[11]) ));
					} else {
						//Così costruisco le carte oro con 1 solo tipo di risorsa necessaria per la loro giocata (sulla faccia frontale) -> uso overloading
						mazzoInCuiMettereCarte.aggiungiCartaAMazzo(new CartaOro( Integer.parseInt(splitted[0]),
								new FacciaFronte(Angolo.creaAngoloInBaseAInput(splitted[1]),Angolo.creaAngoloInBaseAInput(splitted[2]),Angolo.creaAngoloInBaseAInput(splitted[3]),Angolo.creaAngoloInBaseAInput(splitted[4])),
								new FacciaRetro(Risorsa.valueOf(splitted[5])),
								Integer.parseInt(splitted[6]), splitted[7],
								Risorsa.valueOf(splitted[8]), Integer.parseInt(splitted[9]) ));
					}
				} else
				if(Integer.parseInt(splitted[0]) >= 81 && Integer.parseInt(splitted[0]) <= 86)
				{
					if(splitted[10].equals("null") && splitted[11].equals("null"))
					{
						//Così creo le carte iniziali con 1 risorsa sul retro -> uso overloading
						mazzoInCuiMettereCarte.aggiungiCartaAMazzo(new CartaIniziale( Integer.parseInt(splitted[0]),
								new FacciaFronte(Angolo.creaAngoloInBaseAInput(splitted[1]),Angolo.creaAngoloInBaseAInput(splitted[2]),Angolo.creaAngoloInBaseAInput(splitted[3]),Angolo.creaAngoloInBaseAInput(splitted[4])),
								new FacciaRetro(Angolo.creaAngoloInBaseAInput(splitted[5]),Angolo.creaAngoloInBaseAInput(splitted[6]),Angolo.creaAngoloInBaseAInput(splitted[7]),Angolo.creaAngoloInBaseAInput(splitted[8]),Risorsa.valueOf(splitted[9])) ));
					} else
						if(!(splitted[10].equals("null")) && splitted[11].equals("null"))
					{
						//Così creo le carte iniziali con 2 risorse sul retro -> uso overloading
						mazzoInCuiMettereCarte.aggiungiCartaAMazzo(new CartaIniziale( Integer.parseInt(splitted[0]),
								new FacciaFronte(Angolo.creaAngoloInBaseAInput(splitted[1]),Angolo.creaAngoloInBaseAInput(splitted[2]),Angolo.creaAngoloInBaseAInput(splitted[3]),Angolo.creaAngoloInBaseAInput(splitted[4])),
								new FacciaRetro(Angolo.creaAngoloInBaseAInput(splitted[5]),Angolo.creaAngoloInBaseAInput(splitted[6]),Angolo.creaAngoloInBaseAInput(splitted[7]),Angolo.creaAngoloInBaseAInput(splitted[8]),Risorsa.valueOf(splitted[9]),Risorsa.valueOf(splitted[10])) ));
					} else
						if((!(splitted[10].equals("null"))) && (!(splitted[11].equals("null"))))
					{
						//Così creo le carte iniziali con 3 risorse sul retro -> uso overloading
						mazzoInCuiMettereCarte.aggiungiCartaAMazzo(new CartaIniziale( Integer.parseInt(splitted[0]),
								new FacciaFronte(Angolo.creaAngoloInBaseAInput(splitted[1]),Angolo.creaAngoloInBaseAInput(splitted[2]),Angolo.creaAngoloInBaseAInput(splitted[3]),Angolo.creaAngoloInBaseAInput(splitted[4])),
								new FacciaRetro(Angolo.creaAngoloInBaseAInput(splitted[5]),Angolo.creaAngoloInBaseAInput(splitted[6]),Angolo.creaAngoloInBaseAInput(splitted[7]),Angolo.creaAngoloInBaseAInput(splitted[8]),Risorsa.valueOf(splitted[9]),Risorsa.valueOf(splitted[10]),Risorsa.valueOf(splitted[11])) ));
					}
				} else
				if(Integer.parseInt(splitted[0]) >= 87 && Integer.parseInt(splitted[0]) <= 102)
				{
					mazzoInCuiMettereCarte.aggiungiCartaAMazzo(new CartaObiettivo( Integer.parseInt(splitted[0]) ));
				}
				
			}
			
		} catch (FileNotFoundException e) { //gestisco il caso con una eccezione controllata
			System.out.println("Non è stato possibile trovare il file con le carte");
			System.exit(0);
		}
		
	}
	
	
	public void stampaMazzi() {
		System.out.println("Stampo i 4 mazzi di carte:");
		System.out.println("Mazzo carte risorsa:");
		mazzoCarteRisorsa.stampaMazzo();
		System.out.println("Mazzo carte oro:");
		mazzoCarteOro.stampaMazzo();
		System.out.println("Mazzo carte iniziali:");
		mazzoCarteIniziali.stampaMazzo();
		System.out.println("Mazzo carte obiettivo:");
		mazzoCarteObiettivo.stampaMazzo();
	}
	
	
	public void mescolaMazzi() {
		System.out.println("Mescolando i 4 mazzi di carte...");
		mazzoCarteRisorsa.mescolaMazzo();
		mazzoCarteOro.mescolaMazzo();
		mazzoCarteIniziali.mescolaMazzo();
		mazzoCarteObiettivo.mescolaMazzo();
	}
	
	
	public void aggiungiObiettivoComune(int indice, CartaObiettivo carta) {
		obiettiviComuni[indice] = carta;
		System.out.println("Carta aggiunta agli obiettivi comuni");
	}
}
