package carte;

public class CartaObiettivo extends Carta{

	
	public CartaObiettivo(int id) {
		super(id);
	}

	
	@Override
	public String toString() {
		return "C.Obiettivo: "+super.toString();
	}
	
	
	@Override
	public void print(String numeroLineaDaStampare)
	/*
	 * Ho messo questa String in ingresso per evitare che fosse eseguito il metodo (astratto) della superclasse Carta nel caso di una chiamata, da parte di un oggetto carta obiettivo, di un metodo print passante in ingresso una String
	 * 
	 * Infatti, se questo metodo print non avesse ricevuto in ingresso nulla,
	 * durante la fase di compilazione (per overloading, "scelta della segnatura") si sarebbe eseguito il metodo print della superclasse Carta, che prende in ingresso una String
	 * 
	 * Mettendo in ingresso una String anche in questo metodo print, per overriding il metodo eseguito (nel caso della chiamata descritta precedentemente) sarà questo
	 */
	{
		System.out.println("Questa è una carta obiettivo, id: "+super.getId());
	}
}
