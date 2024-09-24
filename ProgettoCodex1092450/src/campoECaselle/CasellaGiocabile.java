package campoECaselle;

import carte.Carta;

public class CasellaGiocabile extends Casella {

	
	private Carta cartaContenuta;
	
	
	public Carta getCartaContenuta() {
		return cartaContenuta;
	}

	public void setCartaContenuta(Carta cartaContenuta) {
		this.cartaContenuta = cartaContenuta;
	}
	
	
	public CasellaGiocabile(int x, int y) {
		super(x, y);
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
