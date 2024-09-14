package campoECaselle;

import carte.Carta;

public class CasellaGiocabile extends Casella {
	
	private Carta cartaContenuta;
	
	public CasellaGiocabile(Coordinata coordinataCasella) {
		super(coordinataCasella);
	}
	
	public boolean isEmpty()
	{
		if(cartaContenuta.equals(null))
		{
			return true;
		} else {
			return false;
		}
	}

}
