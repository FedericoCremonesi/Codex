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

		
	public CasellaNonGiocabile(int x, int y) {
		super(x, y);
	}

	
	//Questo metodo serve per stampare il campo come tabella di quadratini
	@Override
	public void stampaCasellaComeQuadratino() {
		System.out.print("   ");
	}
	
	
	//Questo metodo serve per stampare un'area 3x3 di caselle "ingrandite"
	@Override
	public void stampaLineaCasellaIngrandita(Integer numeroLineaDaStampare)
	{
		System.out.print(INSIEME_LINEE_CASELLA_NON_GIOCABILE[numeroLineaDaStampare]); //NON println, altrimenti risulta sbagliata la stampa delle 3x3 caselle ingrandite
	}
}
