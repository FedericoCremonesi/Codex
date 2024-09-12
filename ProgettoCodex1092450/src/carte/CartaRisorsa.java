package carte;

public class CartaRisorsa extends CartaGiocabile{
	
	public CartaRisorsa(int id, String facciaDiGioco, String angoloFronteAltoSx, String angoloFronteAltoDx,
			String angoloFronteBassoSx, String angoloFronteBassoDx, String angoloRetroAltoSx, String angoloRetroAltoDx,
			String angoloRetroBassoSx, String angoloRetroBassoDx, String risorsaRetroCentrale, int punti) {
		super(id, facciaDiGioco, angoloFronteAltoSx, angoloFronteAltoDx, angoloFronteBassoSx, angoloFronteBassoDx,
				angoloRetroAltoSx, angoloRetroAltoDx, angoloRetroBassoSx, angoloRetroBassoDx, risorsaRetroCentrale);
		this.punti = punti;
	}

	private int punti;

}
