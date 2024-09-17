package carte;

import campoECaselle.Casella;
import facceEAngoli.Faccia;

public abstract class CartaGiocabile extends Carta {

	
	private String facciaDiGioco;
	private Casella casellaDiGioco;
	
	private Faccia fronte;
	private Faccia retro;
	
	
	public CartaGiocabile(int id, String facciaDiGioco, Casella casellaDiGioco, Faccia fronte, Faccia retro) {
		super(id);
		this.facciaDiGioco = facciaDiGioco;
		this.casellaDiGioco = casellaDiGioco;
		this.fronte = fronte;
		this.retro = retro;
	}

}
