/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acp.p2;

import java.io.File;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Christian
 */
public class AcpP2 extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

        Scene scene = new Scene(root);

        stage.setTitle("Spell Checker");
        stage.setScene(scene);
        stage.show();

        Set<String> myDictonary = new HashSet<>();

        Scanner scanner = new Scanner(new File("Words.txt")); //seperate by new line

        while (scanner.hasNext()) {
            myDictonary.add(scanner.next()); //add each word into the HashSet
        }
 

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
