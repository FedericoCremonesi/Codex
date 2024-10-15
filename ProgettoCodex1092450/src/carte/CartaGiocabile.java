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
	
	
	//Vari getter e setter degli attributi di questa classe
	
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
	
	
	/**
	 * Metodo che restituisce la faccia di gioco di una carta (quando posizionata sul campo, ovvero quando la sua faccia di gioco è "FRONTE" o "RETRO")
	 * @return Faccia di gioco come oggetto, non viene restituita una stringa recitante "FRONTE" o "RETRO"
	 */
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
	
	
	/**
	 * Metodo costruttore
	 * @param id
	 * @param fronte
	 * @param retro
	 */
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
	public abstract void print(String numeroLineaDaStampare);
	//metodo astratto, scritto solo per fare overriding nelle classi figlie
	
	
	/**
	 * Metodo che posiziona una carta sul campo.
	 *  Non esegue i controlli del caso, semplicemente pone: la carta da giocare come cartaContenuta della casella in cui va posizionata, e pone la casella stessa come casellaDiGioco della carta da posizionare)
	 * @param casellaInCuiPosizionareCarta
	 */
	public void posizionaSuCampo(CasellaGiocabile casellaInCuiPosizionareCarta) {
		casellaInCuiPosizionareCarta.setCartaContenuta(this);
		this.setCasellaDiGioco(casellaInCuiPosizionareCarta);
		System.out.println("Carta giocata!");
	}
	
	
	/**
	 * Aggiunge questa (this) carta giocabile alle carte in mano di un giocatore
	 * @param manoInCuiAggiungereCarta
	 */
	public void aggiungiAMano(Mano manoInCuiAggiungereCarta) {
		manoInCuiAggiungereCarta.aggiungiCartaAMano(this);
	}
	
}
