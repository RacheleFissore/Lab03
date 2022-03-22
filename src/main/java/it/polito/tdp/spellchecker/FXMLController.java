/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.spellchecker;

import java.net.URL;
import java.util.*;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class FXMLController {
	private Dictionary model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="bntClear"
    private Button bntClear; // Value injected by FXMLLoader

    @FXML // fx:id="bntSpellCheck"
    private Button bntSpellCheck; // Value injected by FXMLLoader

    @FXML // fx:id="cmbLingue"
    private ComboBox<String> cmbLingue; // Value injected by FXMLLoader

    @FXML // fx:id="lblRisultato"
    private Label lblRisultato; // Value injected by FXMLLoader

    @FXML // fx:id="lblTime"
    private Label lblTime; // Value injected by FXMLLoader

    @FXML // fx:id="txtInserimento"
    private TextArea txtInserimento; // Value injected by FXMLLoader

    @FXML // fx:id="txtWrong"
    private TextArea txtWrong; // Value injected by FXMLLoader

    @FXML
    void handleClear(ActionEvent event) {
    	txtWrong.clear();
    	txtInserimento.clear();
    }

    @FXML
    void handleSpellCheck(ActionEvent event) {
    	txtWrong.clear();
    	List<RichWord> listaRichWord = new LinkedList<RichWord>();
    	model.loadDictionary(cmbLingue.getValue());
    	String daControllare = txtInserimento.getText().toLowerCase();
    	daControllare.replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]\"]", " ");
    	String[] arrayParole = daControllare.split(" "); // Vado ad inserire dentro l'array tutte le parole che ottengo andando a slittare la stringa
    													 // dagli spazi
    	List<String> parole = new LinkedList<String>();
    	
    	for(int i = 0; i < arrayParole.length; i++) {
    		parole.add(arrayParole[i]);
    	}
    	
    	for(int i = 0; i < parole.size(); i++) {
    		System.out.println(parole.get(i));
    	}
    	
    	listaRichWord = model.spellCheckText(parole);
    	int cntWrong = 0;
    	
    	for(RichWord r : listaRichWord) {
    		if(r.isCorretta() == false) {
    			txtWrong.appendText(r.getParola() + "\n");
    			cntWrong++;
    		}
    	}
    	
    	lblRisultato.setText("The text contains " + cntWrong + " errors");
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert bntClear != null : "fx:id=\"bntClear\" was not injected: check your FXML file 'Scene.fxml'.";
        assert bntSpellCheck != null : "fx:id=\"bntSpellCheck\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbLingue != null : "fx:id=\"cmbLingue\" was not injected: check your FXML file 'Scene.fxml'.";
        assert lblRisultato != null : "fx:id=\"lblRisultato\" was not injected: check your FXML file 'Scene.fxml'.";
        assert lblTime != null : "fx:id=\"lblTime\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtInserimento != null : "fx:id=\"txtInserimento\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtWrong != null : "fx:id=\"txtWrong\" was not injected: check your FXML file 'Scene.fxml'.";

        cmbLingue.getItems().clear();
        cmbLingue.getItems().add("English");
        cmbLingue.getItems().add("Italian");
    }
    public void setModel(Dictionary model) {
		this.model = model;
		
	}
}


