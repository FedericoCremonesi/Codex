package carte;

public abstract class Carta {

	
	private final int id;
	
	
	public int getId() {
		return id;
	}


	public Carta(int id) {
		this.id = id;
	}

	
	@Override
	public String toString() {
		return ""+id;
	}
	
	
	public abstract void print(String numeroLineaDaStampare); //metodo astratto, scritto solo per fare overriding nelle classi figlie
}
