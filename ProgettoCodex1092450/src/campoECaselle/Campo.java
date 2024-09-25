package campoECaselle;

public class Campo {
	
	public final int DIM = 81;
	/*
	 * DIM è la dimensione di ognuno dei due array componenti la matrice campo
	 * la dimensione degli array è 81, ovvero la quantità delle caselle totali presenti in ognuno di questi
	 * le coordinate delle caselle che verranno visualizzate vanno da -40 a 40 e sono quindi 81 (considerando lo 0)
	 */
	
	private Casella[][] caselleDelCampo;
	
	
	public Casella getCasellaDaCoordinate(int i, int j) {
		return caselleDelCampo[i][j];
	}
	
	
	public Campo() {
		caselleDelCampo = new Casella[DIM][DIM]; //creo la matrice (doppio vettore) vuota
		
		for(int i=0; i<DIM; i++) //i indica la riga
								 //gli indici vanno da 0 a 80 (verranno visualizzate coordinate da -40 a 40)
		{
			for(int j=0; j<DIM; j++) //j indica la colonna
									 //gli indici vanno da 0 a 80 (verranno visualizzate coordinate da -40 a 40)
			{
				if( (i%2==0 && j%2==0) || (i%2!=0 && j%2!=0) ) { //se entrambe le coordinate sono pari o entrambe sono dispari, la casella che creo è giocabile
					caselleDelCampo[i][j] = new CasellaGiocabile(i,j);
				} else {
					caselleDelCampo[i][j] = new CasellaNonGiocabile(i,j);
				}
			}
		}
	}
	
	
	public void stampaMatriceCampo() {
		//continua da qui
	}

}
