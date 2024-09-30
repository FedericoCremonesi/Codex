package facceEAngoli;

import facceEAngoli.Risorsa;
import facceEAngoli.Oggetto;

public class AngoloVisibile extends Angolo {

	private String contenuto; //può essere una Risorsa, un Oggetto o nulla, uso String
	private boolean coperto;
	
	
	public String getContenuto() {
		return contenuto;
	}
	public void setContenuto(String contenuto) {
		this.contenuto = contenuto;
	}

	public boolean isCoperto() {
		return coperto;
	}
	public void setCoperto(boolean coperto) {
		this.coperto = coperto;
	}

	
	public AngoloVisibile(String contenuto) {
		this.contenuto = contenuto;
		this.coperto = false;
	}
	
	
	@Override
	public String toString()
	{
		if(coperto) {
			return " (coperto)";
		} else {
			if(contenuto.equals(Risorsa.FUNGHI.toString())) {
				return Risorsa.CODICE_COLORE_RISORSA_FUNGHI+"  FUNGHI  "+Risorsa.CODICE_RESET_COLORE; //uso i codici colore per colorare le scritte delle risorse con i corrispettivi colori
			} else if(contenuto.equals(Risorsa.VEGETALE.toString())) {
				return Risorsa.CODICE_COLORE_RISORSA_VEGETALE+" VEGETALE "+Risorsa.CODICE_RESET_COLORE;
			} else if(contenuto.equals(Risorsa.ANIMALE.toString())) {
				return Risorsa.CODICE_COLORE_RISORSA_ANIMALE+"  ANIMALE "+Risorsa.CODICE_RESET_COLORE;
			} else if(contenuto.equals(Risorsa.INSETTI.toString())) {
				return Risorsa.CODICE_COLORE_RISORSA_INSETTI+"  INSETTI "+Risorsa.CODICE_RESET_COLORE;
			} else if(contenuto.equals(Oggetto.PIUMA.toString())) {
				return "   PIUMA  ";
			} else if(contenuto.equals(Oggetto.INCHIOSTRO.toString())) {
				return "INCHIOSTRO";
			} else if(contenuto.equals(Oggetto.PERGAMENA.toString())) {
				return " PERGAMENA";
			} else {
				return "          ";
			}
		}
	}
}
