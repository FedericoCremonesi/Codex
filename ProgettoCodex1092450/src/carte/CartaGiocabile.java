package carte;

import campoECaselle.Casella;
import facceEAngoli.FacciaFronte;
import facceEAngoli.FacciaRetro;

public abstract class CartaGiocabile extends Carta {

	
	private String facciaDiGioco;
	private Casella casellaDiGioco;
	
	private FacciaFronte fronte;
	private FacciaRetro retro;
	
	
	public CartaGiocabile(int id, FacciaFronte fronte, FacciaRetro retro) {
		super(id);
		this.fronte = fronte;
		this.retro = retro;
	}

	
	@Override
	public String toString() {
		return ""+super.toString()+" "+fronte.toString()+" "+retro.toString();
	}
	
}
