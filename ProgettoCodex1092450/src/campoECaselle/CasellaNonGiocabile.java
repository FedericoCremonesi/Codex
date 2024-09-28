package campoECaselle;

public class CasellaNonGiocabile extends Casella {

	public CasellaNonGiocabile(int x, int y) {
		super(x, y);
	}

	
	public void stampaCasellaComeQuadratino() {
		System.out.print("   ");
	}
	
	
	public void stampaRigaVuota()
	//Questo metodo serve per quando si vuole stampare una riga vuota della stessa lunghezza di una carta
	{
		System.out.println("(                                     )"); //TODO, da modificare
	}
}
