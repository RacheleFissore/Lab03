package it.polito.tdp.spellchecker.model;

import java.io.*;
import java.util.*;

public class Dictionary {
	List<String> listaItaliano = new LinkedList<String>();
	List<String> listaInglese = new LinkedList<String>();
	
	String linguaScelta;
	
	public void loadDictionary(String language) {
		linguaScelta = language; // Mi salvo la lingua scelta in una variabile da poter utilizzare anche nel metodo sottostante
		if(language.toLowerCase().compareTo("english") == 0) { // Se la lingua selezionata è inglese salvo il dizionario inglese nella lista 
															   // delle parole inglesi
			try {
				FileReader fr = new FileReader("src/main/resources/English.txt");
				BufferedReader br = new BufferedReader(fr);
				String word;
				while((word = br.readLine()) != null) {
					listaInglese.add(word);
				}
				
				br.close();
			}
			catch(IOException e) {
				System.out.println("Errore nella lettura da file");
			}
		}
		
		if(language.toLowerCase().compareTo("italian") == 0) { // Se la lingua selezionata è italiano salvo il dizionario italiano nella lista 
			   												   // delle parole italiane
			try {
				FileReader fr = new FileReader("src/main/resources/Italian.txt");
				BufferedReader br = new BufferedReader(fr);
				String word;
				while((word = br.readLine()) != null) {
					listaItaliano.add(word);
				}
				
				br.close();
			}
			catch(IOException e) {
				System.out.println("Errore nella lettura da file");
			}
		}	
	
	}
	
	public List<RichWord> spellCheckText(List<String> inputTextList) { // A partire dalla lista delle parole che l'utente ha inserito in ingresso 
																	   // restituisco una lista di oggetti RichWord, ossia di oggetti che contengono
																	   // sia la parola inserita sia un boolean per indicare se è corretta oppure no
		List<RichWord> listaParole = new LinkedList<RichWord>();
		boolean corretta = true;

		if(linguaScelta.toLowerCase().compareTo("italian") == 0) {
			for(String s1 : inputTextList) {
				if(listaItaliano.contains(s1)) { // Controllo se la parola inserita è contenuta nel dizionario italiano
					corretta = true;
				}
				else {
					corretta = false;
				}
				
				RichWord p = new RichWord(s1, corretta);	
				listaParole.add(p); // Aggiungo alla lista di parole da restituire quella appena creata
			}
		}
		
		if(linguaScelta.toLowerCase().compareTo("english") == 0) {
			for(String s1 : inputTextList) {
				
				if(listaInglese.contains(s1)) {
					corretta = true;
				}
				else {
					corretta = false;
				}
					
				RichWord p = new RichWord(s1, corretta);	
				listaParole.add(p);
				
			}
		}
		return listaParole;
	}
	
	public List<RichWord> spellCheckTextDichotomic(List<String> inputTextList) {
		List<RichWord> listaParole = new ArrayList<RichWord>();
		boolean corretta = false;

		if(linguaScelta.toLowerCase().compareTo("italian") == 0) {
			for(String s1 : inputTextList) {
				int pos = this.ricercaBinariaNonRicorsiva(s1);
				if(pos == -1)
					corretta = false;
				else
					corretta = true;
				
				RichWord p = new RichWord(s1, corretta);	
				listaParole.add(p); // Aggiungo alla lista di parole da restituire quella appena creata
			}
		}
		
		if(linguaScelta.toLowerCase().compareTo("english") == 0) {
			for(String s1 : inputTextList) {
				int pos = this.ricercaBinariaNonRicorsiva(s1);
				if(pos == -1)
					corretta = false;
				else
					corretta = true;
					
				RichWord p = new RichWord(s1, corretta);	
				listaParole.add(p);
				
			}
		}
		return listaParole;
	}
	
	/*
	 * @param s la stringa
	 * @return la posizione del valore trovato, -1 se non l'ha trovato
	*/
	public int ricercaBinariaNonRicorsiva(String s)
	{
		int low = 0;
		int high = listaItaliano.size()-1;
		
		// Ogni volta devo confrontare la metà di un elenco, vedere se il mio elemento è nella prima o nella seconda metà dell'elenco e 
		// andare a dividere a metà l'elenco in cui si trova, quindi se la parola è nella prima metà dell'elenco divido a metà questa prima
		// metà e poi confronto con la posizione centrale, se sono diversi allora guardo se la parola è nella prima metà di questo nuovo
		// elenco o nella seconda metà e così via
		while (low <= high) {
			int mid = (low + high)/2;
			System.out.println(listaItaliano.get(mid));
			if(listaItaliano.get(mid).compareTo(s) == 0) {
				return mid; //valore trovato nella posizione mid
		    }
			else if (listaItaliano.get(mid).compareTo(s) < 0) {
				low = mid + 1;
			}
			else {
				high = mid - 1;
			}
		}
		return -1; //non l'ha trovato	
	}

}
