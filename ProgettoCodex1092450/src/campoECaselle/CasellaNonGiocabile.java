package campoECaselle;

public class CasellaNonGiocabile extends Casella {

	public CasellaNonGiocabile(int x, int y) {
		super(x, y);
	}

	
	public void stampaCasellaComeQuadratino() {
		System.out.print("   ");
	}
}
