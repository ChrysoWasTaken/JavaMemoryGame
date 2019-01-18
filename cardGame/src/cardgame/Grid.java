package cardgame;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import javax.swing.ImageIcon;
/**
 * @author Alex
 * @author Tasos
 */
public class Grid {

    String[] memoryArray;	//main array that holds the paths of the images as strings
    int repeats;
    int columns;
    int size;
    int rows;
    //initializes our Grid based on the mode selected (Single ,Double, Triple)
    public Grid(int mode) {
        int[][] modes = {{2, 6, 24}, {2, 8, 48}, {3, 6, 36}/*,{2,6,24}*/};
        if (mode > 0 && mode < 5) {
            repeats = modes[mode - 1][0];
            columns = modes[mode - 1][1];
            size = modes[mode - 1][2];
            rows = size / columns;
        }
        memoryArray = new String[size];
        createArray(memoryArray, repeats);
        

    }
    //Gives Values to our array
    public String[] createArray(String[] gameArray, int repeats) {
        int j = 0;
        int c = 0;
        File folder = new File("Images");
        File[] listOfFiles = folder.listFiles();
        for (int i = 0; i < gameArray.length; i++) {
            if (j < repeats) {
                gameArray[i] = "Images/" + listOfFiles[c].getName();
                j++;
            } else {
                c++;
                gameArray[i] = "Images/" + listOfFiles[c].getName();
                j = 1;
            }
        }
        return gameArray;
    }
    //This Method Shuffles the array
    public void shuffleArray(String[] array){
        Collections.shuffle(Arrays.asList(array));
    }

}
