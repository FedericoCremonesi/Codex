package campoECaselle;

public class CasellaNonGiocabile extends Casella {
	
	//Questa costante (array di stringhe) serve per stampare una casella non giocabile
		private static final String[] INSIEME_LINEE_CASELLA_NON_GIOCABILE ={"                                      ",
																			"                                      ",
																			"                                      ",
																			"                                      ",
																			"                                      ",
																			"                                      ",
																			"                                      ",
																			"                                      ",
																			"                                      ",
																			"                                      ",
																			"                                      ",
																			"                                      ",
																			"                                      "};

	
	/**
	 * Metodo costruttore, richiama il metodo costruttore della classe madre (astratta) con super
	 * @param x
	 * @param y
	 */
	public CasellaNonGiocabile(int x, int y) {
		super(x, y);
	}

	
	@Override
	public void stampaCasellaComeQuadratino() {
		System.out.print("   ");
	}
	
	
	@Override
	public void stampaLineaCasellaIngrandita(Integer numeroLineaDaStampare)
	{
		System.out.print(INSIEME_LINEE_CASELLA_NON_GIOCABILE[numeroLineaDaStampare]); //NON println, altrimenti risulta sbagliata la stampa delle 3x3 caselle ingrandite
	}
}
