package carte;

public class CartaObiettivo extends Carta{

	
	public CartaObiettivo(int id) {
		super(id);
	}

	
	@Override
	public String toString() {
		return "C.Obiettivo: "+super.toString();
	}
}
