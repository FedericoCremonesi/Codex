package campoECaselle;

import carte.Carta;
import carte.CartaGiocabile;
import carte.CartaIniziale;
import carte.CartaOro;
import carte.CartaRisorsa;
import carte.ColoreCarta;

public class CasellaGiocabile extends Casella {

	
	private CartaGiocabile cartaContenuta;
	
	
	public CartaGiocabile getCartaContenuta() {
		return cartaContenuta;
	}

	public void setCartaContenuta(CartaGiocabile cartaContenuta) {
		this.cartaContenuta = cartaContenuta;
	}
	
	
	public CasellaGiocabile(int x, int y) {
		super(x, y);
	}
	
	
	public boolean isEmpty()
	{
		if(cartaContenuta == null)
		{
			return true;
		} else {
			return false;
		}
	}

	
	public void stampaCasellaComeQuadratino() {
		if(cartaContenuta instanceof CartaIniziale) {
			System.out.print(ColoreCarta.CODICE_COLORE_CARTA_RISORSA+"■■■"+ColoreCarta.CODICE_RESET_COLORE);
					//uso impropriamente la costante con il codice colore nero per le carte risorsa per colorare la carta iniziale quando stampata nella matrice
			return;
		} else {
			String coloreCarta = null;
			if(cartaContenuta instanceof CartaRisorsa) {
				coloreCarta = ((CartaRisorsa) cartaContenuta).getColore().toString();
			} else if(cartaContenuta instanceof CartaOro) {
				coloreCarta = ((CartaOro) cartaContenuta).getColore().toString();
			} else if(isEmpty()){
				coloreCarta = "";
			}
			
			String codiceColoreDaColoreCarta = ColoreCarta.ottieniStringCodiceColoreDaStringa(coloreCarta);
			
			System.out.print(codiceColoreDaColoreCarta+"■■■"+ColoreCarta.CODICE_RESET_COLORE);
			return;
		}
	}
	
}
