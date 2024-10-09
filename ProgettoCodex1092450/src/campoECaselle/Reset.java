package campoECaselle;

public interface Reset {
	
	/*
	 * Questa interfaccia è implementata dalle classi che utilizzano un metodo
	 * per contare simboli sul campo oppure verificano una data disposizione di carte.
	 * 
	 * Nell'eseguire questi metodi, vengono infatti messi a true alcuni attributi delle carte giocabili,
	 * che impediscono che queste siano contate di nuovo per lo stesso controllo
	 * (es per giocare una carta oro oppure per completare un obiettivo)
	 * (questo è necessario per rispettare la regola "Per uno stesso Obiettivo, ogni carta Risorsa
	 * o Oro nell'area di gioco, può essere contata una sola volta")
	 * 
	 * Tuttavia, dopo aver finito un controllo sarà probabilmente necessario eseguirne uno nuovo,
	 * e se le carte fossero lasciate con questi attributi a true non verrebbero contate e sorgerebbero
	 * problemi nel codice: qui entra in gioco il metodo di questa interfaccia che, a seconda del controllo eseguito,
	 * alla fine di questo ri-setta (da qui il nome "reset") gli attributii interessati di tutte le carte sul campo.
	 */
	public void resetConteggioSimboliOControlloDisposizione(Campo campo);

}
