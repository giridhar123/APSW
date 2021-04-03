package Utils;

import java.util.HashMap;
import java.util.Map;


public class Carrello {
	Map<Integer, Integer> listaProdotti;
	
	public Carrello() {
		listaProdotti = new HashMap<>();
	}
	
	public Map<Integer, Integer> getListaProdotti(){
		return listaProdotti;
	}
	
	public void aggiungiQuantita(int codProdotto, int quantita) {
		if (listaProdotti.containsKey(codProdotto)) {
			int oldQuantita = listaProdotti.get(codProdotto);
			listaProdotti.replace(codProdotto, oldQuantita + quantita);
		}
		else listaProdotti.put(codProdotto, quantita);
	}
	
	public void rimuoviProdotto(int codProdotto) {
		listaProdotti.remove(codProdotto);		
	}
}
