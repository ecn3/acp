/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acp.p2;

import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Window;

/**
 *
 * @author Christian
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private Label nameOfFile;
    @FXML
    private TextArea fileText;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void closeApp(javafx.event.ActionEvent event) {
        System.out.println("You choose Exit");
        System.exit(0);
    }

    @FXML
    private void openFile(javafx.event.ActionEvent event) throws FileNotFoundException {
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File("C:\\Users\\Christian\\Desktop\\Master Home\\Home Libary\\School\\UWF\\2019\\Fall 2019\\COP4027 Advanced Computer Programming\\acp\\acp\\acp-P2\\acp-p2"));
        //the above needs to be changed
        File selectedFile = fc.showOpenDialog(null);
        if (selectedFile != null) {
            nameOfFile.setText(selectedFile.getName());
        } else {
            System.out.println("no file");
        }

        try {
            Scanner s = new Scanner(new File(selectedFile.getName())).useDelimiter("\\s+");
            while (s.hasNext()) {
                if (s.hasNextInt()) { // check if next token is an int
                    fileText.appendText(s.nextInt() + " "); // if we have an integer it puts it in
                } else {
                    fileText.appendText(s.next() + " "); // eotherwise the next token is put in
                }
            }
        } catch (FileNotFoundException ex) {
            System.err.println(ex);
        }
    }

    @FXML
    private void saveFile(javafx.event.ActionEvent event) throws IOException {
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File("C:\\Users\\Christian\\Desktop\\Master Home\\Home Libary\\School\\UWF\\2019\\Fall 2019\\COP4027 Advanced Computer Programming\\acp\\acp\\acp-P2\\acp-p2"));
        File selectedFile = fc.showSaveDialog(null);
        FileWriter writer = new FileWriter(selectedFile.getName());
	writer.write(fileText.getText().toString());
	writer.close();
    }

}

//https://stackoverflow.com/questions/27222205/javafx-read-from-text-file-and-display-in-textarea citation for scanner