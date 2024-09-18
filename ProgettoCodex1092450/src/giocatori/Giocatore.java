package giocatori;

import java.util.List;
import java.util.Scanner;

import campoECaselle.Campo;
import carte.Carta;
import carte.CartaObiettivo;

public class Giocatore {
	
	private static final int MIN_GIOCATORI = 2;
	private static final int MAX_GIOCATORI = 4;
	
	public static int numeroGiocatori;
	
	private String nickname;
	private Pedina colorePedina;
	private int punti;
	private Campo campo;
	private Mano mano;
	private CartaObiettivo obiettivoSegreto;
	private int conteggioObiettiviCompletati;
	
}
