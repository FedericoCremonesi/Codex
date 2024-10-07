package facceEAngoli;

import carte.ColoreCarta;

public class FacciaFronte extends Faccia {
	
	/**
	 * Costruttore della faccia frontale delle carte: risorsa, oro e iniziali
	 */
	public FacciaFronte(Angolo angoloAltoSx, Angolo angoloAltoDx, Angolo angoloBassoSx, Angolo angoloBassoDx) {
		super(angoloAltoSx, angoloAltoDx, angoloBassoSx, angoloBassoDx);
		this.insiemeLineeFaccia = new String[NUMERO_LINEE_FACCIA];
	}
	
	
	@Override
	public String toString() {
		return "["+super.getAngoloAltoSx()+" "+super.getAngoloAltoDx()+" "+super.getAngoloBassoSx()+" "+super.getAngoloBassoDx()+"]";
	}
	
}