package campoECaselle;

public abstract class Casella {

	private final int x; //x è la coordinata delle righe
	private final int y; //y è la coordinata delle colonne
	
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
	
	/**
	 * Metodo costruttore della casella
	 * @param x
	 * @param y
	 */
	public Casella(int x, int y) {
		this.x = x;
		this.y = y;
	}
	//(verrà richiamato sicuramente da classi figlie di Casella, in quanto questa è astratta)
	
	
	/**
	 * Metodo per stampare il campo come tabella di quadratini, nello specifico questo metodo stampa una singola casella
	 */
	public abstract void stampaCasellaComeQuadratino();
	//(sarà implementato da classi figlie di Casella poichè è un metodo astratto)
	
	/**
	 * Metodo per stampare l'area 5x5 di caselle "ingrandite", nello specifico questo metodo stampa una singola "linea" della faccia della carta da stampare
	 * @param numeroLineaDaStampare
	 */
	public abstract void stampaLineaCasellaIngrandita(Integer numeroLineaDaStampare);
	//(sarà implementato da classi figlie di Casella poichè è un metodo astratto)
	
}