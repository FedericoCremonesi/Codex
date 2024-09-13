package carte;

public class CartaOro extends CartaGiocabile{
	
	public CartaOro(int id, String facciaDiGioco, String angoloFronteAltoSx, String angoloFronteAltoDx,
			String angoloFronteBassoSx, String angoloFronteBassoDx, String angoloRetroAltoSx, String angoloRetroAltoDx,
			String angoloRetroBassoSx, String angoloRetroBassoDx, String risorsaRetroCentrale, String colore, int punti,
			String condizionePunti, String risorsaNecessaria1, int quantitàRisorsaNecessaria1,
			String risorsaNecessaria2, int quantitàRisorsaNecessaria2) {
		super(id, facciaDiGioco, angoloFronteAltoSx, angoloFronteAltoDx, angoloFronteBassoSx, angoloFronteBassoDx,
				angoloRetroAltoSx, angoloRetroAltoDx, angoloRetroBassoSx, angoloRetroBassoDx, risorsaRetroCentrale);
		this.colore = colore;
		this.punti = punti;
		this.condizionePunti = condizionePunti;
		this.risorsaNecessaria1 = risorsaNecessaria1;
		this.quantitàRisorsaNecessaria1 = quantitàRisorsaNecessaria1;
		this.risorsaNecessaria2 = risorsaNecessaria2;
		this.quantitàRisorsaNecessaria2 = quantitàRisorsaNecessaria2;
	}
	
	private String colore;
	
	private int punti;
	private String condizionePunti;
	
	private String risorsaNecessaria1;
	private int quantitàRisorsaNecessaria1;
	
	private String risorsaNecessaria2;
	private int quantitàRisorsaNecessaria2;

}
