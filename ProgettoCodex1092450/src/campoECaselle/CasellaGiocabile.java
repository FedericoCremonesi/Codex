package campoECaselle;

import carte.Carta;
import carte.CartaOro;
import carte.CartaRisorsa;
import carte.ColoreCarta;

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

	
	public void stampaCasellaComeQuadratino() {
		String coloreCarta = null;
		if(cartaContenuta instanceof CartaRisorsa) {
			coloreCarta = ((CartaRisorsa) cartaContenuta).getColore().toString();
		} else if(cartaContenuta instanceof CartaOro) {
			coloreCarta = ((CartaOro) cartaContenuta).getColore().toString();
		}
		
		String codiceColoreDaColoreCarta = ColoreCarta.ottieniStringCodiceColoreDaStringa(coloreCarta);
		
		System.out.println(codiceColoreDaColoreCarta+"■"+ColoreCarta.CODICE_RESET_COLORE);
	}
}
