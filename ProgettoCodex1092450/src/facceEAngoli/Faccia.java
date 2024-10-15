package facceEAngoli;

import carte.ColoreCarta;

public abstract class Faccia {
	
	protected Angolo[] angoliFaccia = new Angolo[4];
	/*
	 * Indice 0: angoloAltoSx
	 * Indice 1: angoloAltoDx
	 * Indice 2: angoloBassoSx
	 * Indice 3: angoloBassoDx
	 */
	
	protected String[] insiemeLineeFaccia;
	public final static int NUMERO_LINEE_FACCIA=13; //in una faccia ci sono 13 linee in totale
	
	
	public Angolo getAngoloDaIndice(int indice) {
		return angoliFaccia[indice];
	}
	
	public String[] getInsiemeLineeFaccia() {
		return insiemeLineeFaccia;
	}
	public void setInsiemeLineeFaccia(String[] lineeFaccia) {
		this.insiemeLineeFaccia = lineeFaccia;
	}
	
	
	public Faccia(Angolo angoloAltoSx, Angolo angoloAltoDx, Angolo angoloBassoSx, Angolo angoloBassoDx) {
		this.angoliFaccia[0] = angoloAltoSx;
		this.angoliFaccia[1] = angoloAltoDx;
		this.angoliFaccia[2] = angoloBassoSx;
		this.angoliFaccia[3] = angoloBassoDx;
	}

	
	public void printFaccia(String coloreCarta, String tipologiaCarta, String numeroLineaDaStampare) { 
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
		
		insiemeLineeFaccia = costruisciArrayLineeFaccia(coloreContorno, coloreLatiAngoli);
		//l'array con le linee della faccia da stampare lo costruisco ogni volta che devo stamparlo, in quanto potrebbero avvenire delle modifiche agli angoli nel corso della partita
				
		if(numeroLineaDaStampare.equals("all")) //se dico di stampare la linea "all" vuol dire che bisogna stampare tutte le linee della carta (cioè tutta la carta)
		{
			for(int k=0; k<NUMERO_LINEE_FACCIA; k++) {
				System.out.println(insiemeLineeFaccia[k]); //qui posso usare println perchè quando viene eseguito questo ciclo non starò stampando delle caselle affiancate, bensì le carte per intero (fronte, retro e informazioni aggiuntive)
			}
		} else {
			int k = (Integer.parseInt(numeroLineaDaStampare)); //converto la stringa in un intero e lo metto nella variabile k
			System.out.print(insiemeLineeFaccia[k]); //NON println, altrimenti risulta sbagliata la stampa delle 3x3 caselle ingrandite
		}
	}
	
	
	//La stampa della carta è "spezzetata" per poter stampare comodamente le carte affiancate sul campo quando sarà necessario
	public String[] costruisciArrayLineeFaccia(String coloreContorno, String coloreLatiAngoli) {
		
		String[] lineeFaccia = new String[NUMERO_LINEE_FACCIA];
		
		//Costruisco la linea1 della faccia
		lineeFaccia[0] = (coloreContorno+"╭------------------------------------╮"+ColoreCarta.CODICE_RESET_COLORE);
		
		//Costruisco la linea2 della faccia
		if(angoliFaccia[0] instanceof AngoloVisibile) {
			lineeFaccia[1] = (coloreContorno+"|            "+coloreLatiAngoli+"|"+coloreContorno+"     "+ColoreCarta.CODICE_RESET_COLORE);
		} else {
			lineeFaccia[1] = (coloreContorno+"|                  "+ColoreCarta.CODICE_RESET_COLORE);
		}
		if(angoliFaccia[1] instanceof AngoloVisibile) {
			lineeFaccia[1] = (lineeFaccia[1] + (coloreContorno+"     "+coloreLatiAngoli+"|"+coloreContorno+"            |"+ColoreCarta.CODICE_RESET_COLORE));
		} else {
			lineeFaccia[1] = (lineeFaccia[1] + (coloreContorno+"                  |"+ColoreCarta.CODICE_RESET_COLORE));
		}
				
		//Costruisco la linea3 della faccia
		if(angoliFaccia[0] instanceof AngoloVisibile) {
			lineeFaccia[2] = (coloreContorno+"| "+ColoreCarta.CODICE_RESET_COLORE+angoliFaccia[0].toString()+coloreContorno+" "+coloreLatiAngoli+"|"+coloreContorno+"     "+ColoreCarta.CODICE_RESET_COLORE);
		} else {
			lineeFaccia[2] = (coloreContorno+"|                  "+ColoreCarta.CODICE_RESET_COLORE);
		}
		if(angoliFaccia[1] instanceof AngoloVisibile) {
			lineeFaccia[2] = (lineeFaccia[2] + (coloreContorno+"     "+coloreLatiAngoli+"|"+coloreContorno+" "+ColoreCarta.CODICE_RESET_COLORE+angoliFaccia[1].toString()+coloreContorno+" |"+ColoreCarta.CODICE_RESET_COLORE));
		} else {
			lineeFaccia[2] = (lineeFaccia[2] + (coloreContorno+"                  |"+ColoreCarta.CODICE_RESET_COLORE));
		}
		
		//Costruisco la linea4 della faccia
		if(angoliFaccia[0] instanceof AngoloVisibile) {
			lineeFaccia[3] = (coloreContorno+"|            "+coloreLatiAngoli+"|"+coloreContorno+"     "+ColoreCarta.CODICE_RESET_COLORE);
		} else {
			lineeFaccia[3] = (coloreContorno+"|                  "+ColoreCarta.CODICE_RESET_COLORE);
		}
		if(angoliFaccia[1] instanceof AngoloVisibile) {
			lineeFaccia[3] = (lineeFaccia[3] + (coloreContorno+"     "+coloreLatiAngoli+"|"+coloreContorno+"            |"+ColoreCarta.CODICE_RESET_COLORE));
		} else {
			lineeFaccia[3] = (lineeFaccia[3] + (coloreContorno+"                  |"+ColoreCarta.CODICE_RESET_COLORE));
		}
		
		//Costruisco la linea5 della faccia
		if(angoliFaccia[0] instanceof AngoloVisibile) {
			lineeFaccia[4] = (coloreContorno+"|"+coloreLatiAngoli+"------------╯"+coloreContorno+"     "+ColoreCarta.CODICE_RESET_COLORE);
		} else {
			lineeFaccia[4] = (coloreContorno+"|                  "+ColoreCarta.CODICE_RESET_COLORE);
		}
		if(angoliFaccia[1] instanceof AngoloVisibile) {
			lineeFaccia[4] = (lineeFaccia[4] + (coloreContorno+"     "+coloreLatiAngoli+"╰------------"+coloreContorno+"|"+ColoreCarta.CODICE_RESET_COLORE));
		} else {
			lineeFaccia[4] = (lineeFaccia[4] + (coloreContorno+"                  |"+ColoreCarta.CODICE_RESET_COLORE));
		}
		
		//Costruisco la linea6 della faccia
		lineeFaccia[5] = (coloreContorno+"|                                    |"+ColoreCarta.CODICE_RESET_COLORE);
		
		//Costruisco la linea7 della faccia
		lineeFaccia[6] = (coloreContorno+"|                                    |"+ColoreCarta.CODICE_RESET_COLORE);
		
		//Costruisco la linea8 della faccia
		lineeFaccia[7] = (coloreContorno+"|                                    |"+ColoreCarta.CODICE_RESET_COLORE);
		
		//Costruisco la linea9 della faccia
		if(angoliFaccia[2] instanceof AngoloVisibile) {
			lineeFaccia[8] = (coloreContorno+"|"+coloreLatiAngoli+"------------╮"+coloreContorno+"     "+ColoreCarta.CODICE_RESET_COLORE);
		} else {
			lineeFaccia[8] = (coloreContorno+"|                  "+ColoreCarta.CODICE_RESET_COLORE);
		}
		if(angoliFaccia[3] instanceof AngoloVisibile) {
			lineeFaccia[8] = (lineeFaccia[8] + (coloreContorno+"     "+coloreLatiAngoli+"╭------------"+coloreContorno+"|"+ColoreCarta.CODICE_RESET_COLORE));
		} else {
			lineeFaccia[8] = (lineeFaccia[8] + (coloreContorno+"                  |"+ColoreCarta.CODICE_RESET_COLORE));
		}
		
		//Costruisco la linea10 della faccia
		if(angoliFaccia[2] instanceof AngoloVisibile) {
			lineeFaccia[9] = (coloreContorno+"|            "+coloreLatiAngoli+"|"+coloreContorno+"     "+ColoreCarta.CODICE_RESET_COLORE);
		} else {
			lineeFaccia[9] = (coloreContorno+"|                  "+ColoreCarta.CODICE_RESET_COLORE);
		}
		if(angoliFaccia[3] instanceof AngoloVisibile) {
			lineeFaccia[9] = (lineeFaccia[9] + (coloreContorno+"     "+coloreLatiAngoli+"|"+coloreContorno+"            |"+ColoreCarta.CODICE_RESET_COLORE));
		} else {
			lineeFaccia[9] = (lineeFaccia[9] + (coloreContorno+"                  |"+ColoreCarta.CODICE_RESET_COLORE));
		}
		
		//Costruisco la linea11 della faccia
		if(angoliFaccia[2] instanceof AngoloVisibile) {
			lineeFaccia[10] = (coloreContorno+"| "+ColoreCarta.CODICE_RESET_COLORE+angoliFaccia[2].toString()+coloreContorno+" "+coloreLatiAngoli+"|"+coloreContorno+"     "+ColoreCarta.CODICE_RESET_COLORE);
		} else {
			lineeFaccia[10] = (coloreContorno+"|                  "+ColoreCarta.CODICE_RESET_COLORE);
		}
		if(angoliFaccia[3] instanceof AngoloVisibile) {
			lineeFaccia[10] = (lineeFaccia[10] + (coloreContorno+"     "+coloreLatiAngoli+"|"+coloreContorno+" "+ColoreCarta.CODICE_RESET_COLORE+angoliFaccia[3].toString()+coloreContorno+" |"+ColoreCarta.CODICE_RESET_COLORE));
		} else {
			lineeFaccia[10] = (lineeFaccia[10] + (coloreContorno+"                  |"+ColoreCarta.CODICE_RESET_COLORE));
		}
		
		//Costruisco la linea12 della faccia
		if(angoliFaccia[2] instanceof AngoloVisibile) {
			lineeFaccia[11] = (coloreContorno+"|            "+coloreLatiAngoli+"|"+coloreContorno+"     "+ColoreCarta.CODICE_RESET_COLORE);
		} else {
			lineeFaccia[11] = (coloreContorno+"|                  "+ColoreCarta.CODICE_RESET_COLORE);
		}
		if(angoliFaccia[3] instanceof AngoloVisibile) {
			lineeFaccia[11] = (lineeFaccia[11] + (coloreContorno+"     "+coloreLatiAngoli+"|"+coloreContorno+"            |"+ColoreCarta.CODICE_RESET_COLORE));
		} else {
			lineeFaccia[11] = (lineeFaccia[11] + (coloreContorno+"                  |"+ColoreCarta.CODICE_RESET_COLORE));
		}
		
		//Costruisco la linea13 della faccia
		lineeFaccia[12] = (coloreContorno+"╰------------------------------------╯"+ColoreCarta.CODICE_RESET_COLORE);
		
		return lineeFaccia;
	}
}
