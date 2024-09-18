package carte;

import campoECaselle.Casella;
import facceEAngoli.Faccia;

public abstract class CartaGiocabile extends Carta {

	
	private String facciaDiGioco;
	private Casella casellaDiGioco;
	
	private Faccia fronte;
	private Faccia retro;
	
	
	public CartaGiocabile(int id, Faccia fronte, Faccia retro) {
		super(id);
		this.fronte = fronte;
		this.retro = retro;
	}

}
