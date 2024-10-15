package facceEAngoli;

public abstract class Angolo {
	
	/**
	 * Prende in input una stringa (es "nascosto"/"vuoto"/una risorsa/un oggetto) e crea di conseguenza un angolo.
	 *  Se viene inserito "nascosto", crea un AngoloNascosto, altrimenti un AngoloVisibile che come contenuto ha l'input dato a questo metodo
	 * @param input
	 * @return AngoloNascosto o AngoloVisibile
	 */
	public static Angolo creaAngoloInBaseAInput (String input)
	{
		if(input.equals("nascosto"))
		{
			AngoloNascosto angoloNascosto = new AngoloNascosto();
			return angoloNascosto;
		} else {
			AngoloVisibile angoloVisibile = new AngoloVisibile(input);
			return angoloVisibile;
		}
	}

}
