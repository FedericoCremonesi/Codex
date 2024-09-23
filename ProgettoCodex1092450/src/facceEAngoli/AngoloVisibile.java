package facceEAngoli;

import facceEAngoli.Risorsa;
import facceEAngoli.Oggetto;

public class AngoloVisibile extends Angolo {
	
	private String contenuto; //può essere una Risorsa, un Oggetto o nulla, uso String
	private boolean coperto;

	
	public AngoloVisibile(String contenuto) {
		this.contenuto = contenuto;
		this.coperto = false;
	}
	
	
	@Override
	public String toString()
	{
		return ""+super.toString()+contenuto;
	}
	
	
	public void printAngolo()
	{
		if(contenuto.equals(Risorsa.FUNGHI.toString())) {
			System.out.println("  FUNGHI  ");
		} else if(contenuto.equals(Risorsa.VEGETALE.toString())) {
			System.out.println(" VEGETALE ");
		} else if(contenuto.equals(Risorsa.ANIMALE.toString())) {
			System.out.println("  ANIMALE ");
		} else if(contenuto.equals(Risorsa.INSETTI.toString())) {
			System.out.println("  INSETTI ");
		} else if(contenuto.equals(Oggetto.PIUMA.toString())) {
			System.out.println("   PIUMA  ");
		} else if(contenuto.equals(Oggetto.INCHIOSTRO.toString())) {
			System.out.println("INCHIOSTRO");
		} else if(contenuto.equals(Oggetto.PERGAMENA.toString())) {
			System.out.println(" PERGAMENA");
		} else {
			System.out.println("          ");
		}
		/*
		switch(contenuto)
		{
		case :
			System.out.println();
			break;
		case :
			System.out.println();
			break;
		case :
			System.out.println();
			break;
		case :
			System.out.println();
			break;
		case :
			System.out.println();
			break;
		case :
			System.out.println();
			break;
		case :
			System.out.println();
			break;
		default:
			
		}
		*/
	}
}
