package giocatori;

import java.util.List;
import java.util.Scanner;

import campoECaselle.Campo;
import carte.Carta;
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
		this.punti = 0;
		this.conteggioObiettiviCompletati = 0;
	}
	
	
	public void stampaNickname() {
		System.out.println(nickname);
	}
	
	
	public void assegnaColorePedina(Pedina pedinaScelta) {
		colorePedina = pedinaScelta;
		if(pedinaScelta.toString().equals(Pedina.ROSSA.toString())) { //utilizzo i codici dei colori per "colorare" i nickname dei giocatori in base al colore scelto
			nickname = "\u001B[31m"+nickname+"\u001B[0m";
		} else if(pedinaScelta.toString().equals(Pedina.GIALLA.toString())) {
			nickname = "\u001B[33m"+nickname+"\u001B[0m";
		} else if(pedinaScelta.toString().equals(Pedina.VERDE.toString())) {
			nickname = "\u001B[32m"+nickname+"\u001B[0m";
		} else if(pedinaScelta.toString().equals(Pedina.BLU.toString())) {
			nickname = "\u001B[34m"+nickname+"\u001B[0m";
		}
	}
}
