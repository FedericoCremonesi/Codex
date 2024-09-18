package facceEAngoli;

public class AngoloVisibile extends Angolo {
	
	private String contenuto; //può essere una Risorsa, un Oggetto o nulla, uso String
	private boolean coperto;

	
	public AngoloVisibile(String contenuto) {
		this.contenuto = contenuto;
		this.coperto = false;
	}
}
