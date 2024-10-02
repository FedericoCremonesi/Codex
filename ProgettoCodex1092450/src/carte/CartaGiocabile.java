package carte;

import campoECaselle.Casella;
import campoECaselle.CasellaGiocabile;
import facceEAngoli.FacciaFronte;
import facceEAngoli.FacciaRetro;
import giocatori.Mano;

public abstract class CartaGiocabile extends Carta {
	
	private String facciaDiGioco;
	private Casella casellaDiGioco;
	
	private FacciaFronte fronte;
	private FacciaRetro retro;
	
	
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
	
	
	public boolean posizionaSuCampo(CasellaGiocabile casellaInCuiPosizionareCarta) {
		/*
		if(condizione1) {
			System.out.println("...");
			return false;
		} else if(condizione2) {
			System.out.println("...");
			return false;
		} else if(condizione3) {
			System.out.println("...");
			return false;
		} else if(condizione4) {
			System.out.println("...");
			return false;
		} else {
			casellaInCuiPosizionareCarta.setCartaContenuta(this);
			this.setCasellaDiGioco(casellaInCuiPosizionareCarta);
			System.out.println("Carta giocata!");
			return true;
		}
		*/
		return false;
	}
	
	
	public void aggiungiAMano(Mano manoInCuiAggiungereCarta) {
		manoInCuiAggiungereCarta.aggiungiCartaAMano(this);
	}
}
