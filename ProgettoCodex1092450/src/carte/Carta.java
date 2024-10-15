package carte;

public abstract class Carta {

	
	private final int id;
	
	
	public int getId() {
		return id;
	}


	/**
	 * Metodo costruttore della Carta
	 * @param id
	 */
	public Carta(int id) {
		this.id = id;
	}

	
	@Override
	public String toString() {
		return ""+id;
	}
	
	
	/**
	 * Metodo per stampare una linea precisa della faccia di gioco di una carta (o l'intera faccia della carta)
	 * @param numeroLineaDaStampare la linea della faccia da stampare, oppure la Stringa "all" in caso si voglia stampare tutta la faccia
	 */
	public abstract void print(String numeroLineaDaStampare);
	//metodo astratto, scritto solo per fare overriding nelle classi figlie
}
