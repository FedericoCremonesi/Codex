package giocatori;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import campoECaselle.Campo;
import campoECaselle.CasellaGiocabile;
import carte.Carta;
import carte.CartaGiocabile;
import carte.CartaObiettivo;

public class Giocatore {
	
	
	public static final int MIN_GIOCATORI = 2;
	public static final int MAX_GIOCATORI = 4;
	
	public static int numeroGiocatori;
	
	private String nickname;
	private Pedina colorePedina;
	private int punti;
	private Campo campo;
	private Mano mano;
	private CartaObiettivo obiettivoSegreto;
	private int conteggioObiettiviCompletati;
	
	
	public String getNickname() {
		return nickname;
	}


	public void setNickname(String nickname) {
		this.nickname = nickname;
	}


	public Pedina getColorePedina() {
		return colorePedina;
	}


	public void setColorePedina(Pedina colorePedina) {
		this.colorePedina = colorePedina;
	}
	
	
	public int getPunti() {
		return punti;
	}


	public void setPunti(int punti) {
		this.punti = punti;
	}


	public Campo getCampo() {
		return campo;
	}


	public void setCampo(Campo campo) {
		this.campo = campo;
	}


	public Mano getMano() {
		return mano;
	}


	public void setMano(Mano mano) {
		this.mano = mano;
	}


	public CartaObiettivo getObiettivoSegreto() {
		return obiettivoSegreto;
	}


	public void setObiettivoSegreto(CartaObiettivo obiettivoSegreto) {
		this.obiettivoSegreto = obiettivoSegreto;
	}


	public int getConteggioObiettiviCompletati() {
		return conteggioObiettiviCompletati;
	}


	public void setConteggioObiettiviCompletati(int conteggioObiettiviCompletati) {
		this.conteggioObiettiviCompletati = conteggioObiettiviCompletati;
	}
	
	
	public Giocatore(String nickname) {
		System.out.println("Creazione di un giocatore con questo nickname in corso...");
		this.nickname = nickname;
		this.campo = new Campo();
		this.mano = new Mano();
		this.punti = 0;
		this.conteggioObiettiviCompletati = 0;
	}
	
	
	public void stampaNickname() {
		System.out.println(nickname);
	}
	
	
	public void assegnaColorePedina(Pedina pedinaScelta) {
		String codiceColorePedina = Pedina.ottieniStringCodiceColoreDaStringa(pedinaScelta.toString());
		nickname = codiceColorePedina+nickname+Pedina.CODICE_RESET_COLORE;
	}
	
	
	public void scegliFacciaDiGioco(CartaGiocabile cartaDaGiocare) {
		String facciaDiGiocoScelta;
		do {
			System.out.println(nickname+" su quale faccia vuoi giocare la carta?");
			Scanner sc = new Scanner(System.in);
			facciaDiGiocoScelta = sc.nextLine().toUpperCase();
			if(facciaDiGiocoScelta.equals("FRONTE") || facciaDiGiocoScelta.equals("RETRO")) {
				cartaDaGiocare.setFacciaDiGioco(facciaDiGiocoScelta); //setto la faccia di gioco della carta (giocabile) a "FRONTE" o "RETRO"
				cartaDaGiocare.posizionaSuCampo((CasellaGiocabile) campo.getCasellaDaCoordinate(40,40));
			} else {
				System.out.println("Inserimento non valido, scrivere Fronte oppure Retro");
			}
		} while (!( (facciaDiGiocoScelta.equals("FRONTE") || facciaDiGiocoScelta.equals("RETRO")) ));
	}
	
	
	public void giocaCartaDaMano() {
		//Non stampo di nuovo le carte in mano perchè le ho già fatte visualizzare a inizio turno
		
		int indiceCartaScelta = 0; //dato un valore iniziale come esempio, non sarà ovviamente accettato
		do {
			try {
				System.out.println("Quale carta vuoi giocare dalla tua mano?");
				Scanner sc = new Scanner(System.in);
				indiceCartaScelta = sc.nextInt();
				if(!(indiceCartaScelta >= 1 && indiceCartaScelta <= 3))
				{
					System.out.println("Indice inserito non valido, inserire un numero tra 1 e 3");
				}
			} catch(InputMismatchException e) { //gestisco il caso con una eccezione NON controllata
				System.out.println("Inserimento non valido, inserire un numero");
			}
		} while (!(indiceCartaScelta >= 1 && indiceCartaScelta <= 3));
		indiceCartaScelta = indiceCartaScelta-1; //decremento di 1 il valore dell'indice inserito perchè gli indici delle carte nella lista mano vanno da 0 a 2 (non da 1 a 3)
		scegliFacciaDiGioco(mano.getCartaDaMano(indiceCartaScelta));
		
		//...
	}
}
