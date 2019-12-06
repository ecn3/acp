/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project5;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Christian
 */
public class FXMLDocumentController implements Initializable {
    
    ObservableList instrumentTypes = FXCollections.observableArrayList();
    ObservableList instrumentBrands = FXCollections.observableArrayList();
    ObservableList warehouseLocations = FXCollections.observableArrayList(); 
    @FXML
    private AnchorPane submitBtn;
    @FXML
    private ChoiceBox<String> instrumentTypeChoiceBox;
    @FXML
    private ChoiceBox<String> instrumentBrandChoiceBox;
    @FXML
    private ChoiceBox<String> warehouseLocationChoiceBox;
    
    public String instrumentTypeChoiceBoxText;
    public String instrumentBrandChoiceBoxText;
    public String warehouseLocationChoiceBoxText;
    public String maximumCostText;
    public String myQuery;
    @FXML
    private TextField maximumCost;
    
    
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
       
    private String getQueryResult(){
        //get choices that user put in
        instrumentTypeChoiceBoxText = instrumentTypeChoiceBox.getValue();
        instrumentBrandChoiceBoxText = instrumentBrandChoiceBox.getValue();
        warehouseLocationChoiceBoxText = warehouseLocationChoiceBox.getValue();
        maximumCostText = maximumCost.getText();
        
        myQuery = instrumentTypeChoiceBoxText+"     "+instrumentBrandChoiceBoxText+"     "
        +maximumCostText+"  "+warehouseLocationChoiceBoxText;
                
        return myQuery;
    }
    
    private void searchDatabase(){
        
    }

    @FXML
    private void submitBtnClicked(ActionEvent event) {
                try {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("FXMLSubmitResults.fxml"));
        Parent root = loader.load();
  
        Scene scene = new Scene(root);
        
        FXMLSubmitResultsController myQueryController = loader.getController();
        
        //get query
        myQuery = getQueryResult();
        
        // send to text field on second window
        myQueryController.initData(myQuery);
        
        

        Stage stage = new Stage();
        stage.setTitle("Musical Instrument Lookup");
        stage.setScene(scene);
        stage.show();
    } catch (IOException e) {
        Logger logger = Logger.getLogger(getClass().getName());
        logger.log(Level.SEVERE, "Failed to create new Window.", e);
    }
    }
    
    
    
}
