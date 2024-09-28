package campoECaselle;

import java.util.InputMismatchException;
import java.util.Scanner;

import sviluppoGioco.Partita;

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
	
	
	public void stampaMatriceCampoAQuadratini() {
		System.out.println("\nCampo di gioco:");
		
		int coordinataDaStampare;
		
		System.out.print("    "); //stampo degli spazi per allineare al meglio gli indici delle colonne
		
		for(int j=0; j<DIM; j++) //uso j perchè per convenzione ho indicato con questa lettera le colonne, potevo usare una qualsiasi altra variabile
		{
			/*
			 * Le coordinate della matrice vanno da 0 a 80,
			 * ma per migliorare l'estetica del campo visualizzo le coordinate da -40 a (+)40
			 */
			coordinataDaStampare = (j-40);
			
			/*
			 * Con questa condizione evito di stampare sempre tutta la matrice (81x81, quindi molto grande),
			 * ne stampo invece solo alcune caselle, partendo da quella centrale e aggiungendone una ad ogni round della partita
			 */
			if( j>=(39-Partita.getNumeroRoundGiocati()) && j<=(41+Partita.getNumeroRoundGiocati()) )
			/*
			* Nota: i, come j, si riferisce all'indice effettivo della matrice (va quindi da 0 a 80),
			* non alle coordinate visualizzate (che vanno da -40 a (+)40)
			*/
			{
				//Stampo in alto la coordinata di ogni colonna
				stampaCoordinataConSpaziPrima(coordinataDaStampare);
			}
		}
		
		System.out.println(); //vado a capo per cominciare a stampare la tabella stessa (con a lato le coordinate delle righe)
		
		for(int i=0; i<DIM; i++)
		{
			if( i>=(39-Partita.getNumeroRoundGiocati()) && i<=(41+Partita.getNumeroRoundGiocati()) )
			{
				coordinataDaStampare = (i-40);
				
				//Stampo a lato la coordinata della riga analizzata
				stampaCoordinataConSpaziPrima(coordinataDaStampare);
				//Stampo uno spazio tra ogni coordinata a lato e le caselle della matrice
				System.out.print(" ");
				
				for(int j=0; j<DIM; j++)
				{
					if( j>=(39-Partita.getNumeroRoundGiocati()) && j<=(41+Partita.getNumeroRoundGiocati()) )
					{
						//Stampo sia le caselle giocabili che non giocabili (i rispettivi metodi specificheranno cosa stampare per ognuno dei due casi)
						caselleDelCampo[i][j].stampaCasellaComeQuadratino();
					}
				}
				
				System.out.println(); //vado a capo una volta finita la riga
			}
		}
		
	}
	
	
	public void stampaCoordinataConSpaziPrima(int coordinataDaStampare) {
		if(coordinataDaStampare>=0 && coordinataDaStampare<=9)
		{
			//aggiungo davanti 2 spazi se la coordinata è tra 0 e 9
			System.out.print("  "+coordinataDaStampare);
		} else if((coordinataDaStampare>=-9 && coordinataDaStampare<=-1) || (coordinataDaStampare>=10 && coordinataDaStampare<=40))
		{
			//aggiungo davanti 1 spazio se la coordinata è tra -9 e -1 oppure tra 10 e 40
			System.out.print(" "+coordinataDaStampare);
		} else {
			//non aggiungo alcuno spazio se stampando il numero occupo già 3 spazi (cioè se il numero è tra -40 e -10)
			System.out.print(coordinataDaStampare);
		}
	}
	
	
	public void stampa3x3AttornoACartaInCampo(int[] coordinateCasella) {
		int partenzaI = coordinateCasella[0]-1; //voglio stampare anche la carta nella riga sopra rispetto a quella alle coordinate comunicate, quindi calcolo -1
		int partenzaJ = coordinateCasella[1]-1; //voglio stampare anche la carta nella colonna a sinistra rispetto a quella alle coordinate comunicate, quindi calcolo -1
		System.out.println();
		//Con questo doppio ciclo for stampo una "matrice" 3x3 di caselle (giocabili e non)
		for(int i=partenzaI; i<=(partenzaI+2); i++)
		{
			for(int j=partenzaJ; j<=(partenzaJ+2); j++)
			{
				
				//Con questo for, per ogni casella che sto analizzando della matrice, stampo riga per riga il contenuto
				for(Integer k=0; k<13; k++)
				{
					if(caselleDelCampo[i][j] instanceof CasellaGiocabile) {
						if(((CasellaGiocabile) caselleDelCampo[i][j]).isEmpty()) { //posso eseguire un casting sulla casella considerata perchè nella condizione dell'if precedente ho appena controllato che la casella fosse di tipo "Giocabile"
							System.out.println("[                                     ]"); //TODO, da modificare
						} else {
							((CasellaGiocabile) caselleDelCampo[i][j]).getCartaContenuta().print(k.toString()); //analogamente a prima, posso eseguire un casting per i medesimi motivi
							//per poter convertire il k (intero) in una stringa (cioè ciò che il metodo print riceve in ingresso) devo dichiarare k come Integer ("classe wrapper" di int) e usare il metodo toString()
						}
					} else if(caselleDelCampo[i][j] instanceof CasellaNonGiocabile) {
						((CasellaNonGiocabile) caselleDelCampo[i][j]).stampaRigaVuota(); //anche qui posso eseguire un casting sulla casella perchè se la casella considerata non è "Giocabile", per come è costruita la matrice Campo è sicuramente "NonGiocabile"
					}
				}
				
			}
		}
	}

	
}
