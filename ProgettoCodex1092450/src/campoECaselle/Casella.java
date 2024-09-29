package campoECaselle;

public abstract class Casella {
	
	private final int x; //x è la coordinata delle righe
	private final int y; //y è la coordinata delle colonne
	
	public Casella(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	
	//Questo metodo serve per stampare il campo come tabella di quadratini
	public abstract void stampaCasellaComeQuadratino();
	
	//Questo metodo serve per stampare un'area 3x3 di caselle "ingrandite"
	public abstract void stampaLineaCasellaIngrandita(Integer numeroLineaDaStampare);
	
}