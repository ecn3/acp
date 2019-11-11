/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acp.p4;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

/**
 *
 * @author Christian
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Pane pane0;
    @FXML
    private Pane pane1;
    @FXML
    private Pane pane2;
    @FXML
    private Pane pane3;
    @FXML
    private Pane pane4;
    @FXML
    private Pane pane5;
    @FXML
    private Pane pane6;
    @FXML
    private Pane pane7;
    @FXML
    private Pane pane8;
    

    public Color player1Color = Color.RED;
    public Color player2Color = Color.BLUE;
    public int currentPlayer = 0;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void drawPane0(MouseEvent event) {     
        if(currentPlayer == 0){
        pane0.getChildren().add(new Circle(0, 0, 20,player1Color));
        currentPlayer = 1;
        } else{
        pane0.getChildren().add(new Circle(0, 0, 20,player2Color));
        currentPlayer = 0;
        }
        pane0.setTranslateX(pane0.getPrefWidth() / 2);
        pane0.setTranslateY(pane0.getPrefHeight() / 2);
        
        } 

    @FXML
    private void drawPane1(MouseEvent event) {
    }
    }

