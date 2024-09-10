package Carte;

public class CartaIniziale extends CartaGiocabile{
	
	public CartaIniziale(int id, String facciaDiGioco, String angoloFronteAltoSx, String angoloFronteAltoDx,
			String angoloFronteBassoSx, String angoloFronteBassoDx, String angoloRetroAltoSx, String angoloRetroAltoDx,
			String angoloRetroBassoSx, String angoloRetroBassoDx, String risorsaRetroCentrale,
			String risorsaRetroCentraleAggiuntiva1, String risorsaRetroCentraleAggiuntiva2) {
		super(id, facciaDiGioco, angoloFronteAltoSx, angoloFronteAltoDx, angoloFronteBassoSx, angoloFronteBassoDx,
				angoloRetroAltoSx, angoloRetroAltoDx, angoloRetroBassoSx, angoloRetroBassoDx, risorsaRetroCentrale);
		this.risorsaRetroCentraleAggiuntiva1 = risorsaRetroCentraleAggiuntiva1;
		this.risorsaRetroCentraleAggiuntiva2 = risorsaRetroCentraleAggiuntiva2;
	}
	private String risorsaRetroCentraleAggiuntiva1;
	private String risorsaRetroCentraleAggiuntiva2;

}
