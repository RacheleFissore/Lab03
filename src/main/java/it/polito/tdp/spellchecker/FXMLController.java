package it.polito.tdp.spellchecker;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.Dictionary;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class FXMLController {
	
	private Dictionary model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button bntClear;

    @FXML
    private Button bntSpellCheck;

    @FXML
    private ComboBox<String> cmbLingue;

    @FXML
    private Label lblRisultato;

    @FXML
    private Label lblTime;

    @FXML
    private TextArea txtInserimento;

    @FXML
    private TextArea txtWrong;

    @FXML
    void handleClear(ActionEvent event) {

    }

    @FXML
    void handleSpellCheck(ActionEvent event) {

    }

    @FXML
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


