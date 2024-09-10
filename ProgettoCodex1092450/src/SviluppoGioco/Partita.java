package SviluppoGioco;

import java.util.Set;

import Carte.CartaObiettivo;
import Carte.CartaOro;
import Carte.CartaRisorsa;

public class Partita {
	
	public Set<Giocatore> gruppoGiocatori;
	public Set<CartaRisorsa> mazzoCarteRisorsa;
	public Set<CartaOro> mazzoCarteOro;
	public CartaObiettivo[] obiettiviComuni;

}
