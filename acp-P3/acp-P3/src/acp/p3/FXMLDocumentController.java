/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acp.p3;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/**
 *
 * @author Christian
 */
public class FXMLDocumentController implements Initializable {
    
    //public String horse1ImageUrl = "C:\\Users\\Christian\\Desktop\\Master Home\\Home Libary\\School\\UWF\\BS\\2019\\Fall 2019\\COP4027 Advanced Computer Programming\\acp\\acp\\acp-P3\\acp-P3\\horse1.jpg";
    //public Image horse1Image = new Image(horse1ImageUrl);
    
    //public String workingDir = System.getProperty("user.dir");//stores uses working director

    @FXML
    private ImageView horse1;
    @FXML
    private ImageView horse2;
    @FXML
    private ImageView horse3;
    @FXML
    private ImageView horse4;
    @FXML
    private ImageView horse5;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void runRace(ActionEvent event) {
        //race length X -100 -> X 450 [550]
        horse2.setLayoutX(horse2.getLayoutX()+5);
        horse2.setLayoutY(38.0);
        
    }

    @FXML
    private void resetRace(ActionEvent event) {
        // Y = -25 -> 160: HorsesY: -10, 20, 60, 100, 140
        //fitHeight="35.0" fitWidth="110.0" layoutX="-1.0" layoutY="38.0"
        horse2.setLayoutX(-1.0);
        horse2.setLayoutY(38.0);
    }

    @FXML
    private void quitProgram(ActionEvent event) {
       System.exit(0); 
    }
    
}
