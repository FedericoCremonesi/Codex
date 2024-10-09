package carte;

import campoECaselle.Campo;
import campoECaselle.Casella;
import campoECaselle.CasellaGiocabile;
import facceEAngoli.Faccia;
import facceEAngoli.FacciaFronte;
import facceEAngoli.FacciaRetro;
import giocatori.Giocatore;
import giocatori.Mano;

public abstract class CartaGiocabile extends Carta {
	
	private String facciaDiGioco;
	private Casella casellaDiGioco;
	
	private FacciaFronte fronte;
	private FacciaRetro retro;
	
	/*
	 * Questi due attributi boolean servono per non "contare due volte" le risorse di una carta (o la carta stessa)
	 * durante la verifica dei requisiti delle carte oro o l'assegnazione di punti per gli obiettivi
	 */
	boolean contatiSimboli = false;
	boolean contataPerObiettivoDisposizione = false;
	
	
	public String getFacciaDiGioco() {
		return facciaDiGioco;
	}
	public void setFacciaDiGioco(String facciaDiGioco) {
		this.facciaDiGioco = facciaDiGioco;
	}
	
	public Casella getCasellaDiGioco() {
		return casellaDiGioco;
	}
	public void setCasellaDiGioco(Casella casellaDiGioco) {
		this.casellaDiGioco = casellaDiGioco;
	}

	public FacciaFronte getFronte() {
		return fronte;
	}
	public void setFronte(FacciaFronte fronte) {
		this.fronte = fronte;
	}

	public FacciaRetro getRetro() {
		return retro;
	}
	public void setRetro(FacciaRetro retro) {
		this.retro = retro;
	}
	
	public boolean isContatiSimboli() {
		return contatiSimboli;
	}
	public void setContatiSimboli(boolean contateRisorse) {
		this.contatiSimboli = contateRisorse;
	}
	
	public boolean isContataPerObiettivoDisposizione() {
		return contataPerObiettivoDisposizione;
	}
	public void setContataPerObiettivoDisposizione(boolean contataPerObiettivoDisposizione) {
		this.contataPerObiettivoDisposizione = contataPerObiettivoDisposizione;
	}
	
	
	public Faccia ottieniFacciaSuCuiGiocata() {
		if(facciaDiGioco.equals("FRONTE")) {
			return fronte;
		} else if(facciaDiGioco.equals("RETRO")) {
			return retro;
		} else {
			System.out.println("Questa carta non è ancora stata giocata");
			return null;
		}
	}
	
	
	public CartaGiocabile(int id, FacciaFronte fronte, FacciaRetro retro) {
		super(id);
		this.fronte = fronte;
		this.retro = retro;
		this.facciaDiGioco = "non ancora giocata";
	}

	
	@Override
	public String toString() {
		return ""+super.toString()+" "+fronte.toString()+" "+retro.toString();
	}
	
	
	@Override
	public abstract void print(String numeroLineaDaStampare); //metodo astratto, scritto solo per fare overriding nelle classi figlie
	
	
	
	public void posizionaSuCampo(CasellaGiocabile casellaInCuiPosizionareCarta) {
		casellaInCuiPosizionareCarta.setCartaContenuta(this);
		this.setCasellaDiGioco(casellaInCuiPosizionareCarta);
		System.out.println("Carta giocata!");
	}
	
	
	public void aggiungiAMano(Mano manoInCuiAggiungereCarta) {
		manoInCuiAggiungereCarta.aggiungiCartaAMano(this);
	}
	
}
