package it.polito.tdp.spellchecker.model;

import java.io.*;
import java.util.*;

public class Dictionary {
	List<String> listaItaliano = new LinkedList<String>();
	List<String> listaInglese = new LinkedList<String>();
	
	String linguaScelta;
	
	public void loadDictionary(String language) {
		linguaScelta = language;
		if(language.toLowerCase().compareTo("english") == 0) {
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
		
		if(language.toLowerCase().compareTo("italian") == 0) {
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
	
	public List<RichWord> spellCheckText(List<String> inputTextList) {
		List<RichWord> listaParole = new LinkedList<RichWord>();
		boolean corretta = true;
		//boolean trovata = false;
		if(linguaScelta.toLowerCase().compareTo("italian") == 0) {
			for(String s1 : inputTextList) {
				if(listaItaliano.contains(s1)) {
					corretta = true;
				}
				else {
					corretta = false;
				}
				
				RichWord p = new RichWord(s1, corretta);	
				listaParole.add(p);
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
}
