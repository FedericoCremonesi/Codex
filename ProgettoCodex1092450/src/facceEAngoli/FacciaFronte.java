package facceEAngoli;

import carte.ColoreCarta;

public class FacciaFronte {

	private Angolo angoloAltoSx;
	private Angolo angoloAltoDx;
	private Angolo angoloBassoSx;
	private Angolo angoloBassoDx;
	
	protected String[] insiemeRigheFaccia;
	public final int NUMERO_RIGHE_FACCIA=13; //in una faccia ci sono 13 righe in totali
	
	
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
	
	public String[] getInsiemeRigheFaccia() {
		return insiemeRigheFaccia;
	}
	public void setInsiemeRigheFaccia(String[] righeFaccia) {
		this.insiemeRigheFaccia = righeFaccia;
	}
	
	
	/**
	 * Costruttore della faccia frontale delle carte: risorsa, oro e iniziali
	 */
	public FacciaFronte(Angolo angoloAltoSx, Angolo angoloAltoDx, Angolo angoloBassoSx, Angolo angoloBassoDx) {
		this.angoloAltoSx = angoloAltoSx;
		this.angoloAltoDx = angoloAltoDx;
		this.angoloBassoSx = angoloBassoSx;
		this.angoloBassoDx = angoloBassoDx;
		this.insiemeRigheFaccia = new String[NUMERO_RIGHE_FACCIA];
	}
	
	
	@Override
	public String toString() {
		return "["+angoloAltoSx+" "+angoloAltoDx+" "+angoloBassoSx+" "+angoloBassoDx+"]";
	}
	
	
	public void printFaccia(String coloreCarta, String tipologiaCarta, String numeroRigaDaStampare) { 
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
		
		insiemeRigheFaccia = costruisciArrayRigheFaccia(coloreContorno, coloreLatiAngoli);
				
		if(numeroRigaDaStampare.equals("all")) //se dico di stampare la riga "all" vuol dire che bisogna stampare tutte le righe della carta (cioè tutta la carta)
		{
			for(int k=0; k<NUMERO_RIGHE_FACCIA; k++) {
				System.out.println(insiemeRigheFaccia[k]);
			}
		} else {
			int k = (Integer.parseInt(numeroRigaDaStampare)); //per comodità converto la stringa in un intero e lo metto nella variabile k
			System.out.println(insiemeRigheFaccia[k]);
		}
	}
	
	
	//La stampa della carta è "spezzetata" per poter stampare comodamente le carte affiancate sul campo quando sarà necessario
	public String[] costruisciArrayRigheFaccia(String coloreContorno, String coloreLatiAngoli) {
		
		String[] righeFaccia = new String[NUMERO_RIGHE_FACCIA];
		
		//Costruisco la riga1 della faccia
		righeFaccia[0] = (coloreContorno+"▛------------------------------------▜"+ColoreCarta.CODICE_RESET_COLORE);
		
		//Costruisco la riga2 della faccia
		if(angoloAltoSx instanceof AngoloVisibile) {
			righeFaccia[1] = (coloreContorno+"|            "+coloreLatiAngoli+"|"+coloreContorno+"     "+ColoreCarta.CODICE_RESET_COLORE);
		} else {
			righeFaccia[1] = (coloreContorno+"|                  "+ColoreCarta.CODICE_RESET_COLORE);
		}
		if(angoloAltoDx instanceof AngoloVisibile) {
			righeFaccia[1] = (righeFaccia[1] + (coloreContorno+"     "+coloreLatiAngoli+"|"+coloreContorno+"            |"+ColoreCarta.CODICE_RESET_COLORE));
		} else {
			righeFaccia[1] = (righeFaccia[1] + (coloreContorno+"                  |"+ColoreCarta.CODICE_RESET_COLORE));
		}
				
		//Costruisco la riga3 della faccia
		if(angoloAltoSx instanceof AngoloVisibile) {
			righeFaccia[2] = (coloreContorno+"| "+ColoreCarta.CODICE_RESET_COLORE+angoloAltoSx.toString()+coloreContorno+" "+coloreLatiAngoli+"|"+coloreContorno+"     "+ColoreCarta.CODICE_RESET_COLORE);
		} else {
			righeFaccia[2] = (coloreContorno+"|                  "+ColoreCarta.CODICE_RESET_COLORE);
		}
		if(angoloAltoDx instanceof AngoloVisibile) {
			righeFaccia[2] = (righeFaccia[2] + (coloreContorno+"     "+coloreLatiAngoli+"|"+coloreContorno+" "+ColoreCarta.CODICE_RESET_COLORE+angoloAltoDx.toString()+coloreContorno+" |"+ColoreCarta.CODICE_RESET_COLORE));
		} else {
			righeFaccia[2] = (righeFaccia[2] + (coloreContorno+"                  |"+ColoreCarta.CODICE_RESET_COLORE));
		}
		
		//Costruisco la riga4 della faccia
		if(angoloAltoSx instanceof AngoloVisibile) {
			righeFaccia[3] = (coloreContorno+"|            "+coloreLatiAngoli+"|"+coloreContorno+"     "+ColoreCarta.CODICE_RESET_COLORE);
		} else {
			righeFaccia[3] = (coloreContorno+"|                  "+ColoreCarta.CODICE_RESET_COLORE);
		}
		if(angoloAltoDx instanceof AngoloVisibile) {
			righeFaccia[3] = (righeFaccia[3] + (coloreContorno+"     "+coloreLatiAngoli+"|"+coloreContorno+"            |"+ColoreCarta.CODICE_RESET_COLORE));
		} else {
			righeFaccia[3] = (righeFaccia[3] + (coloreContorno+"                  |"+ColoreCarta.CODICE_RESET_COLORE));
		}
		
		//Costruisco la riga5 della faccia
		if(angoloAltoSx instanceof AngoloVisibile) {
			righeFaccia[4] = (coloreContorno+"|"+coloreLatiAngoli+"------------▟"+coloreContorno+"     "+ColoreCarta.CODICE_RESET_COLORE);
		} else {
			righeFaccia[4] = (coloreContorno+"|                  "+ColoreCarta.CODICE_RESET_COLORE);
		}
		if(angoloAltoDx instanceof AngoloVisibile) {
			righeFaccia[4] = (righeFaccia[4] + (coloreContorno+"     "+coloreLatiAngoli+"▙------------"+coloreContorno+"|"+ColoreCarta.CODICE_RESET_COLORE));
		} else {
			righeFaccia[4] = (righeFaccia[4] + (coloreContorno+"                  |"+ColoreCarta.CODICE_RESET_COLORE));
		}
		
		//Costruisco la riga6 della faccia
		righeFaccia[5] = (coloreContorno+"|                                    |"+ColoreCarta.CODICE_RESET_COLORE);
		
		//Costruisco la riga7 della faccia
		righeFaccia[6] = (coloreContorno+"|                                    |"+ColoreCarta.CODICE_RESET_COLORE);
		
		//Costruisco la riga8 della faccia
		righeFaccia[7] = (coloreContorno+"|                                    |"+ColoreCarta.CODICE_RESET_COLORE);
		
		//Costruisco la riga9 della faccia
		if(angoloBassoSx instanceof AngoloVisibile) {
			righeFaccia[8] = (coloreContorno+"|"+coloreLatiAngoli+"------------▜"+coloreContorno+"     "+ColoreCarta.CODICE_RESET_COLORE);
		} else {
			righeFaccia[8] = (coloreContorno+"|                  "+ColoreCarta.CODICE_RESET_COLORE);
		}
		if(angoloBassoDx instanceof AngoloVisibile) {
			righeFaccia[8] = (righeFaccia[8] + (coloreContorno+"     "+coloreLatiAngoli+"▛------------"+coloreContorno+"|"+ColoreCarta.CODICE_RESET_COLORE));
		} else {
			righeFaccia[8] = (righeFaccia[8] + (coloreContorno+"                  |"+ColoreCarta.CODICE_RESET_COLORE));
		}
		
		//Costruisco la riga10 della faccia
		if(angoloBassoSx instanceof AngoloVisibile) {
			righeFaccia[9] = (coloreContorno+"|            "+coloreLatiAngoli+"|"+coloreContorno+"     "+ColoreCarta.CODICE_RESET_COLORE);
		} else {
			righeFaccia[9] = (coloreContorno+"|                  "+ColoreCarta.CODICE_RESET_COLORE);
		}
		if(angoloBassoDx instanceof AngoloVisibile) {
			righeFaccia[9] = (righeFaccia[9] + (coloreContorno+"     "+coloreLatiAngoli+"|"+coloreContorno+"            |"+ColoreCarta.CODICE_RESET_COLORE));
		} else {
			righeFaccia[9] = (righeFaccia[9] + (coloreContorno+"                  |"+ColoreCarta.CODICE_RESET_COLORE));
		}
		
		//Costruisco la riga11 della faccia
		if(angoloBassoSx instanceof AngoloVisibile) {
			righeFaccia[10] = (coloreContorno+"| "+ColoreCarta.CODICE_RESET_COLORE+angoloBassoSx.toString()+coloreContorno+" "+coloreLatiAngoli+"|"+coloreContorno+"     "+ColoreCarta.CODICE_RESET_COLORE);
		} else {
			righeFaccia[10] = (coloreContorno+"|                  "+ColoreCarta.CODICE_RESET_COLORE);
		}
		if(angoloBassoDx instanceof AngoloVisibile) {
			righeFaccia[10] = (righeFaccia[10] + (coloreContorno+"     "+coloreLatiAngoli+"|"+coloreContorno+" "+ColoreCarta.CODICE_RESET_COLORE+angoloBassoDx.toString()+coloreContorno+" |"+ColoreCarta.CODICE_RESET_COLORE));
		} else {
			righeFaccia[10] = (righeFaccia[10] + (coloreContorno+"                  |"+ColoreCarta.CODICE_RESET_COLORE));
		}
		
		//Costruisco la riga12 della faccia
		if(angoloBassoSx instanceof AngoloVisibile) {
			righeFaccia[11] = (coloreContorno+"|            "+coloreLatiAngoli+"|"+coloreContorno+"     "+ColoreCarta.CODICE_RESET_COLORE);
		} else {
			righeFaccia[11] = (coloreContorno+"|                  "+ColoreCarta.CODICE_RESET_COLORE);
		}
		if(angoloBassoDx instanceof AngoloVisibile) {
			righeFaccia[11] = (righeFaccia[11] + (coloreContorno+"     "+coloreLatiAngoli+"|"+coloreContorno+"            |"+ColoreCarta.CODICE_RESET_COLORE));
		} else {
			righeFaccia[11] = (righeFaccia[11] + (coloreContorno+"                  |"+ColoreCarta.CODICE_RESET_COLORE));
		}
		
		//Costruisco la riga13 della faccia
		righeFaccia[12] = (coloreContorno+"▙------------------------------------▟"+ColoreCarta.CODICE_RESET_COLORE);
		
		return righeFaccia;
	}
}