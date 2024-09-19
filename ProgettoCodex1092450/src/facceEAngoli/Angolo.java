package facceEAngoli;

public abstract class Angolo {
	
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

	
	@Override
	public String toString()
	{
		return "";
	}
}
