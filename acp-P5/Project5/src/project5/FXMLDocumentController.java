/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project5;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

/**
 *
 * @author Christian
 */
public class FXMLDocumentController implements Initializable {
    
    ObservableList instrumentTypes = FXCollections.observableArrayList();
    ObservableList instrumentBrands = FXCollections.observableArrayList();
    ObservableList warehouseLocations = FXCollections.observableArrayList();
     
    @FXML
    private Button submitBtn;
    @FXML
    private ChoiceBox<String> instrumentTypeChoiceBox;
    @FXML
    private ChoiceBox<String> instrumentBrandChoiceBox;
    @FXML
    private ChoiceBox<String> warehouseLocationChoiceBox;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadInstrumentTypes();
        loadInstrumentBrands();
        loadwarehouseLocations();
    }  
    
    private void loadInstrumentTypes(){
        instrumentTypes.removeAll(instrumentTypes); // clear list
        instrumentTypes.addAll("all","guitar","bass","keyboard","drums");
        instrumentTypeChoiceBox.getItems().addAll(instrumentTypes);
    }
    
     private void loadInstrumentBrands(){
        instrumentBrands.removeAll(instrumentBrands); // clear list
        instrumentBrands.addAll("yamaha","gibson","fender","roland","alesis");
        instrumentBrandChoiceBox.getItems().addAll(instrumentBrands);
    }
       private void loadwarehouseLocations(){
        warehouseLocations.removeAll(warehouseLocations); // clear list
        warehouseLocations.addAll("all","PNS","CLT","DFW");
        warehouseLocationChoiceBox.getItems().addAll(warehouseLocations);
    }
    
}
