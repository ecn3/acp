package acp.p2;

import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    public String workingDir = System.getProperty("user.dir");//stores uses working director
    @FXML
    private Label label;
    @FXML
    private Label nameOfFile;
    @FXML
    private TextArea fileText;
    @FXML
    private TextArea suggestions;

    public Set<String> myDictonary = new HashSet<>();

    public boolean correctlySpelled;

    public String possibleCorrect;

    public Set<String> myDictonary() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("Words.txt")); //seperate by new line
        while (scanner.hasNext()) {
            myDictonary.add(scanner.next()); //add each word into the HashSet
        }
        return myDictonary;
    }

    public boolean correctlySpelledWord(String myWord) {
        myWord = myWord.replaceAll("[^a-zA-Z]", ""); // gets rid of special chars
        if (myDictonary.contains(myWord)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            myDictonary();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void closeApp(javafx.event.ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void openFile(javafx.event.ActionEvent event) throws FileNotFoundException {
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File(workingDir));

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
        fc.setInitialDirectory(new File(workingDir));
        File selectedFile = fc.showSaveDialog(null);
        FileWriter writer = new FileWriter(selectedFile.getName());
        writer.write(fileText.getText().toString());
        writer.close();
    }

    @FXML
    private void checkSpelling(javafx.event.ActionEvent event) {
        String myWords = fileText.getText(); // pull words from textArea
        String[] words = myWords.split("\\s+"); // split up each word
        ArrayList<String> tempWords;
        String tempWord;
        int numberOfSuggestions = 0;

        for (int i = 0; i < words.length; i++) {
            correctlySpelled = correctlySpelledWord(words[i].toLowerCase()); // check if is spelled right
            if (correctlySpelled == true) {
                System.out.println(words[i] + ": is correctly spelled");
            } else {
                System.out.println(words[i] + ":  is incorrectly spelled");
                tempWord = words[i]; // set word to tempWord
                suggestions.setText(tempWord + "\n");
                suggestions.appendText("Suggested Corrections:" + "\n");
                //One letter missing. test
                tempWords = hasExtraLetter(tempWord);
                for (int j = 0; j < tempWords.size(); j++) {
                    suggestions.appendText(tempWords.get(0) + "\n");
                    numberOfSuggestions++;
                }
                //one letter added
                tempWords = oneLetterMissing(tempWord);
                for (int j = 0; j < tempWords.size(); j++) {
                    suggestions.appendText(tempWords.get(0) + "\n");
                    numberOfSuggestions++;
                }
                //one letter swapped
                tempWords = isSwapped(tempWord);
                for (int j = 0; j < tempWords.size(); j++) {
                    suggestions.appendText(tempWords.get(0) + "\n");
                    numberOfSuggestions++;
                }
                if (numberOfSuggestions == 0) {
                    suggestions.appendText("We cant find any suggestions");
                }
                i = words.length; /// get us out of the loop
            }
        }

    }//end check spelling
    //one letter is missing

    public ArrayList<String> hasExtraLetter(String word) {
        ArrayList<String> suggestedCorrect = new ArrayList(); // create a list of words

        int lengthOfWord = word.length() - 1;
        //remove char from the front of the dictornary
        if (correctlySpelledWord(word.substring(1).toLowerCase())) {
            suggestedCorrect.add(word.substring(1));
        }
        for (int i = 1; i < lengthOfWord; i++) {
            //try removing each char between (not including) the first and last
            String temp = word.substring(0, i);
            temp = temp.concat(word.substring((i + 1), word.length()));
            if (correctlySpelledWord(temp)) {
                suggestedCorrect.add(temp);
            }
        }
        if (correctlySpelledWord(word.substring(0, lengthOfWord))) {
            suggestedCorrect.add(word.substring(0, lengthOfWord));
        }
        return suggestedCorrect;
    }

    //one letter added
    public ArrayList<String> oneLetterMissing(String word) {
        ArrayList<String> suggestedCorrect = new ArrayList(); // create a list of words
        String temp; // create a list of words
        //break the string into an array of chars
        for (int i = 0; i <= word.length(); ++i) {
            for (char c = 'a'; c <= 'z'; ++c) {
                temp = (word.substring(0, i) + String.valueOf(c) + word.substring(i));
                if (correctlySpelledWord(temp.toLowerCase())) {
                    suggestedCorrect.add(temp);
                }
            }
        }
        return suggestedCorrect;
    }

    //one letter swapped
    public ArrayList<String> isSwapped(String word) {
        ArrayList<String> suggestedCorrect = new ArrayList();

        for (int i = 0; i < word.length() - 1; i++) {
            String temp = word.substring(0, i);
            temp = temp + word.charAt(i + 1);
            temp = temp + word.charAt(i);
            temp = temp.concat(word.substring((i + 2)));
            if (correctlySpelledWord(temp)) {
                suggestedCorrect.add(temp);
            }
        }
        return suggestedCorrect;
    }
}// end doccont
//https://stackoverflow.com/questions/27222205/javafx-read-from-text-file-and-display-in-textarea citation for scanner
