package facceEAngoli;

public enum Risorsa {
	
	FUNGHI,		//Codice colore: "\u001B[31m"
	VEGETALE,	//Codice colore: "\u001B[32m"
	ANIMALE,	//Codice colore: "\u001B[34m"
	INSETTI;	//Codice colore: "\u001B[35m"
				//Codice per reset colore: "\u001B[0m"
	
	
	public static final String CODICE_COLORE_RISORSA_FUNGHI = "\u001B[31m";
	public static final String CODICE_COLORE_RISORSA_VEGETALE = "\u001B[32m";
	public static final String CODICE_COLORE_RISORSA_ANIMALE = "\u001B[34m";
	public static final String CODICE_COLORE_RISORSA_INSETTI = "\u001B[35m";
	public static final String codiceResetColore = "\u001B[0m";
}
