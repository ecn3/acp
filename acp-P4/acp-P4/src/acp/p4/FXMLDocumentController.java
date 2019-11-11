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
import javafx.scene.Group;
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
    
    public Pane[] panes = new Pane[9];

    //spots
    public int spot0 = 0;
    public int spot1 = 0;
    public int spot2 = 0;
    public int spot3 = 0;
    public int spot4 = 0;
    public int spot5 = 0;
    public int spot6 = 0;
    public int spot7 = 0;
    public int spot8 = 0;
    
    public int spots[] = new int[9];
    
    public int winner = 0;
 

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for(int i = 0; i < 9; i++){
            spots[i] = 0; // set all spots to 0
        }
        panes[0] = pane0;
        panes[1] = pane1;
        panes[2] = pane2;
        panes[3] = pane3;
        panes[4] = pane4;
        panes[5] = pane5;
        panes[6] = pane6;
        panes[7] = pane7;
        panes[8] = pane8;

    }

    @FXML
    private void drawPane0(MouseEvent event) {
        drawCircle(0);;
    }//end pane0draw

    @FXML
    private void drawPane1(MouseEvent event) {
        drawCircle(1);
    }

    @FXML
    private void drawPane2(MouseEvent event) {
        drawCircle(2);
    }

    @FXML
    private void drawPane3(MouseEvent event) {
        drawCircle(3);
    }

    @FXML
    private void drawPane4(MouseEvent event) {
        drawCircle(4);;
    }

    @FXML
    private void drawPane5(MouseEvent event) {
        drawCircle(5);
    }

    @FXML
    private void drawPane6(MouseEvent event) {
        drawCircle(6);
    }

    @FXML
    private void drawPane7(MouseEvent event) {
        drawCircle(7); 
    }

    @FXML
    private void drawPane8(MouseEvent event) {
        drawCircle(8);
    }
    
    public void gameWon(){
         //->
         if((spots[0] == spots[1])&&(spots[1] == spots[2])){
             winner = spots[0];
         } // win option -> top
         else if((spots[3] == spots[4])&&(spots[4] == spots[5])){
             winner = spots[3];
         } // win option -> middle
         else if((spots[6] == spots[7])&&(spots[7] == spots[8])){
             winner = spots[6];
         } // win option -> bottom
         // V
         else if((spots[0] == spots[3])&&(spots[3] == spots[6])){
             winner = spots[0];
         } // win option V col 0
         else if((spots[1] == spots[4])&&(spots[4] == spots[7])){
             winner = spots[1];
         } // win option V col 1
         else if((spots[2] == spots[5])&&(spots[5] == spots[8])){
             winner = spots[2];
         } // win option V col 2
         // \/
         else if((spots[0] == spots[4])&&(spots[4] == spots[8])){
             winner = spots[0];
         } // win option \
         else if((spots[2] == spots[4])&&(spots[4] == spots[5])){
             winner = spots[2];
         } // win option /
         
        if(winner>0){
         System.out.println("Player "+winner+" has won!");   
        }
    }
    
    public void drawCircle(int pane){
        if ((spots[pane] == 0) && (winner == 0)) {
            
            if (currentPlayer == 0) {
                panes[pane].getChildren().add(new Circle(0, 0, 20, player1Color));
                currentPlayer = 1;
                spots[pane] = 1;
            } else {
                panes[pane].getChildren().add(new Circle(0, 0, 20, player2Color));
                currentPlayer = 0;
                spots[pane] = 2;
            }
            panes[pane].setTranslateX(panes[pane].getPrefWidth() / 2);
            panes[pane].setTranslateY(panes[pane].getPrefHeight() / 2);

        } else {
            System.out.print("taken");
        }
        gameWon();
    }
}
