package campoECaselle;

public abstract class Casella {
	
	private final int x; //x è la coordinata delle righe
	private final int y; //y è la coordinata delle colonne
	
	public Casella(int x, int y) {
		this.x = x;
		this.y = y;
	}
}