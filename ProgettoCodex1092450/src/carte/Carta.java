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
	
	
	public void print() {
		System.out.println("Questo non dovrebbe essere stampato");
	}
}
