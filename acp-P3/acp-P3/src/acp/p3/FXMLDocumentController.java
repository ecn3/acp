/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acp.p3;

import java.net.URL;
import java.util.Date;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 *
 * @author Christian
 */
public class FXMLDocumentController implements Initializable {

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
    @FXML
    private Label winnerLabel;

    public int winner = 0;

    public boolean keepRacing = true;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void runRace(ActionEvent event) throws InterruptedException {
        //race length X -100 -> X 450 [550]
        Timer timer = new Timer();
        TimerTask runTheHorses = new TimerTask() {
            public void run() {
                advanceHorses();
            }
        };


        timer.scheduleAtFixedRate(runTheHorses, 0, 200l); //run task every .5 seconds
    
        if (keepRacing == false) {
            timer.cancel();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Winner");
            alert.setHeaderText(null);
            alert.setContentText("Winner is Horse: " + winner);
            alert.showAndWait();
        }
    }

    @FXML
    private void resetRace(ActionEvent event) {
        winner = 0;

        horse1.setLayoutX(-1.0);
        horse1.setLayoutY(0);

        horse2.setLayoutX(-1.0);
        horse2.setLayoutY(38.0);

        horse3.setLayoutX(-1.0);
        horse3.setLayoutY(76.0);

        horse4.setLayoutX(-1.0);
        horse4.setLayoutY(114.0);

        horse5.setLayoutX(-1.0);
        horse5.setLayoutY(152.0);
    }

    @FXML
    private void quitProgram(ActionEvent event) {
        System.exit(0);
    }

    private void advanceHorses() {
        Random r = new Random();
        int speed;

        if (horse1.getLayoutX() >= 550) {
            winner = 1;
        } else if (horse2.getLayoutX() >= 550) {
            winner = 2;
        } else if (horse3.getLayoutX() >= 550) {
            winner = 3;
        } else if (horse4.getLayoutX() >= 550) {
            winner = 4;
        } else if (horse5.getLayoutX() >= 550) {
            winner = 5;
        } else {
            speed = r.nextInt(20) + 5; // random number between 5 - 20
            horse1.setLayoutX(horse1.getLayoutX() + speed);

            speed = r.nextInt(20) + 5; // random number between 5 - 20
            horse2.setLayoutX(horse2.getLayoutX() + speed);

            speed = r.nextInt(20) + 5; // random number between 5 - 20
            horse3.setLayoutX(horse3.getLayoutX() + speed);

            speed = r.nextInt(20) + 5; // random number between 5 - 20
            horse4.setLayoutX(horse4.getLayoutX() + speed);

            speed = r.nextInt(20) + 5; // random number between 5 - 20
            horse5.setLayoutX(horse5.getLayoutX() + speed);
        }
        if (winner > 0) {
            keepRacing = false;
        }// end race 
    }
}
