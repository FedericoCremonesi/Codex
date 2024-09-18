package tavoloEMazzi;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import carte.CartaIniziale;
import carte.CartaObiettivo;
import carte.CartaOro;
import carte.CartaRisorsa;
import facceEAngoli.AngoloVisibile;
import facceEAngoli.Faccia;
import facceEAngoli.Risorsa;

public class Tavolo {
	
	private Mazzo<CartaRisorsa> mazzoCarteRisorsa;
	private Mazzo<CartaOro> mazzoCarteOro;
	private Mazzo<CartaIniziale> mazzoCarteIniziali;
	private Mazzo<CartaObiettivo> mazzoCarteObiettivo;
	private CartaObiettivo[] obiettiviComuni;

	
	public Tavolo() {
		this.mazzoCarteRisorsa = new Mazzo<CartaRisorsa>(new ArrayList<CartaRisorsa>());
		this.mazzoCarteOro = new Mazzo<CartaOro>(new ArrayList<CartaOro>()); //posso mettere "<CartaOro>" dopo "new Mazzo" *
		this.mazzoCarteIniziali = new Mazzo(new ArrayList<CartaIniziale>()); //* ma posso anche evitare di mettere "<CartaIniziale>" dopo "new Mazzo", non è necessario
		this.mazzoCarteObiettivo = new Mazzo(new ArrayList<CartaObiettivo>());
	}
	
	
	public void creaMazzi() {
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
												new Faccia(new AngoloVisibile(splitted[1]),new AngoloVisibile(splitted[2]),new AngoloVisibile(splitted[3]),new AngoloVisibile(splitted[4])),
												new Faccia(Risorsa.valueOf(splitted[5])),
												Integer.parseInt(splitted[6]) ));
				} else
				if(Integer.parseInt(splitted[0]) >= 41 && Integer.parseInt(splitted[0]) <= 80)
				{
					if(Integer.parseInt(splitted[11])!=0)
					{
						//Così costruisco le carte oro con 2 tipi diversi di risorse necessarie per la loro giocata (sulla faccia frontale) -> uso overloading
						mazzoInCuiMettereCarte.aggiungiCartaAMazzo(new CartaOro( Integer.parseInt(splitted[0]),
								new Faccia(new AngoloVisibile(splitted[1]),new AngoloVisibile(splitted[2]),new AngoloVisibile(splitted[3]),new AngoloVisibile(splitted[4])),
								new Faccia(Risorsa.valueOf(splitted[5])),
								Integer.parseInt(splitted[6]), splitted[7],
								Risorsa.valueOf(splitted[8]), Integer.parseInt(splitted[9]),
								Risorsa.valueOf(splitted[10]), Integer.parseInt(splitted[11]) ));
					} else {
						//Così costruisco le carte oro con 1 solo tipo di risorsa necessaria per la loro giocata (sulla faccia frontale) -> uso overloading
						mazzoInCuiMettereCarte.aggiungiCartaAMazzo(new CartaOro( Integer.parseInt(splitted[0]),
								new Faccia(new AngoloVisibile(splitted[1]),new AngoloVisibile(splitted[2]),new AngoloVisibile(splitted[3]),new AngoloVisibile(splitted[4])),
								new Faccia(Risorsa.valueOf(splitted[5])),
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
								new Faccia(new AngoloVisibile(splitted[1]),new AngoloVisibile(splitted[2]),new AngoloVisibile(splitted[3]),new AngoloVisibile(splitted[4])),
								new Faccia(new AngoloVisibile(splitted[5]),new AngoloVisibile(splitted[6]),new AngoloVisibile(splitted[7]),new AngoloVisibile(splitted[8]),Risorsa.valueOf(splitted[9])) ));
					} else
						if(!(splitted[10].equals("null")) && splitted[11].equals("null"))
					{
						//Così creo le carte iniziali con 2 risorse sul retro -> uso overloading
						mazzoInCuiMettereCarte.aggiungiCartaAMazzo(new CartaIniziale( Integer.parseInt(splitted[0]),
								new Faccia(new AngoloVisibile(splitted[1]),new AngoloVisibile(splitted[2]),new AngoloVisibile(splitted[3]),new AngoloVisibile(splitted[4])),
								new Faccia(new AngoloVisibile(splitted[5]),new AngoloVisibile(splitted[6]),new AngoloVisibile(splitted[7]),new AngoloVisibile(splitted[8]),Risorsa.valueOf(splitted[9]),Risorsa.valueOf(splitted[10])) ));
					} else
						if((!(splitted[10].equals("null"))) && (!(splitted[11].equals("null"))))
					{
						//Così creo le carte iniziali con 3 risorse sul retro -> uso overloading
						mazzoInCuiMettereCarte.aggiungiCartaAMazzo(new CartaIniziale( Integer.parseInt(splitted[0]),
								new Faccia(new AngoloVisibile(splitted[1]),new AngoloVisibile(splitted[2]),new AngoloVisibile(splitted[3]),new AngoloVisibile(splitted[4])),
								new Faccia(new AngoloVisibile(splitted[5]),new AngoloVisibile(splitted[6]),new AngoloVisibile(splitted[7]),new AngoloVisibile(splitted[8]),Risorsa.valueOf(splitted[9]),Risorsa.valueOf(splitted[10]),Risorsa.valueOf(splitted[11])) ));
					}
				} else
				if(Integer.parseInt(splitted[0]) >= 87 && Integer.parseInt(splitted[0]) <= 102)
				{
					mazzoInCuiMettereCarte.aggiungiCartaAMazzo(new CartaObiettivo( Integer.parseInt(splitted[0]) ));
				}
				
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("Non è stato possibile trovare il file con le carte");
		}
		
	}
}
