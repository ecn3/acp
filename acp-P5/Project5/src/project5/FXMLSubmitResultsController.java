/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project5;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Christian
 */
public class FXMLSubmitResultsController implements Initializable {

    @FXML
    private Text queryResult;
    @FXML
    private Button okBtn;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void okBtnClick(ActionEvent event) {
    Stage stage = (Stage) okBtn.getScene().getWindow();
    stage.close();
    }

    void initData(String query) {
        queryResult.setText(query);
    }
}
