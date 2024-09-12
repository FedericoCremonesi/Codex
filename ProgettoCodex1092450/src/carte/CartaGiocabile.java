package carte;

public abstract class CartaGiocabile extends Carta{
	
	public CartaGiocabile(int id, String facciaDiGioco, String angoloFronteAltoSx, String angoloFronteAltoDx,
			String angoloFronteBassoSx, String angoloFronteBassoDx, String angoloRetroAltoSx, String angoloRetroAltoDx,
			String angoloRetroBassoSx, String angoloRetroBassoDx, String risorsaRetroCentrale) {
		super(id);
		this.facciaDiGioco = facciaDiGioco;
		this.angoloFronteAltoSx = angoloFronteAltoSx;
		this.angoloFronteAltoDx = angoloFronteAltoDx;
		this.angoloFronteBassoSx = angoloFronteBassoSx;
		this.angoloFronteBassoDx = angoloFronteBassoDx;
		this.angoloRetroAltoSx = angoloRetroAltoSx;
		this.angoloRetroAltoDx = angoloRetroAltoDx;
		this.angoloRetroBassoSx = angoloRetroBassoSx;
		this.angoloRetroBassoDx = angoloRetroBassoDx;
		this.risorsaRetroCentrale = risorsaRetroCentrale;
	}

	private String facciaDiGioco;
	
	private String angoloFronteAltoSx;
	private String angoloFronteAltoDx;
	private String angoloFronteBassoSx;
	private String angoloFronteBassoDx;
	
	private String angoloRetroAltoSx;
	private String angoloRetroAltoDx;
	private String angoloRetroBassoSx;
	private String angoloRetroBassoDx;
	
	private String risorsaRetroCentrale;

}
