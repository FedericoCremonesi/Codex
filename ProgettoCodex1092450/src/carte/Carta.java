package carte;

public abstract class Carta {

	
	private final int id;
	
	
	public Carta(int id) {
		this.id = id;
	}

	
	@Override
	public String toString() {
		return ""+id;
	}
}
