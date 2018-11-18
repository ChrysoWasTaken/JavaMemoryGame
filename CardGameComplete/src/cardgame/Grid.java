package cardgame;

import java.util.Random;

/**
 * This class contains the methods used for initializing and modifying the game arrays.
 * 
 * @author Tasos_Chrysopoulos
 * @author Alexandros_Adam
 */
public class Grid {

    char[] memoryArray;
    char[] fillerArray;
    char[] imageFillerArray;
    int repeats;
    int columns;
    int size;
    
 /*
    The constructor creates the grid of the game based on the mode given by the user.
    Repeats : How many times will the player need to open a card 
              (2 for basic and double , 3 for triple)
    Columns : Grid's columns (Depends on the mode , rows are not needed)
    Size: Number of cards.
 */  
    public Grid(int mode) {
        int[][] modes = {{ 2, 6, 24 }, { 2, 8, 48 }, { 3, 6, 36 } };
        if (mode > 0 && mode < 4) 
        {
            repeats = modes[mode-1][0];
            columns = modes[mode-1][1];
            size = modes[mode-1][2];
        }     
        // Initializing , shuffling and filling the arrays.
        memoryArray = new char[size];
        fillerArray = new char[size];
        imageFillerArray = new char[size];
        createArray(memoryArray, repeats);
        shuffleArray(memoryArray);
        resetArray(fillerArray);
        imageFillerArray = fillerArray.clone();

    }
    // This method fills the array with characters
    public char[] createArray(char[] gameArray, int repeats) {
        int j = 0;
        char c = 'A';
        for (int i = 0; i < gameArray.length; i++) {
            if (j < repeats) {
                gameArray[i] = c;
                j++;
            } 
            else {
                c++;
                gameArray[i] = c;
                j = 1;
            }																													//ÌÅÓÁ ÓÔÇÍ ÅÐÁÍÁËÇØÇ                           )
        }
        return gameArray;
    }
    // This method shuffles the array
    public void shuffleArray(char[] array) {
        int index;
        char temp;
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            index = random.nextInt(i + 1);
            temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }
    // This method fills the filler array with '-' 
    public void resetArray(char[] array) {
        for (int i = 0; i < size; i++) {
            array[i] = '-';
        }
    }
    //This method replaces correct cards with blanks so that they look picked up.
    public char[] pickCards(char[] array) {
        for (int i = 0; i < size; i++) {
            if (array[i] != '-') {
                array[i] = ' ';
            }
        }
        return array;
    }
    //Checks if the array is empty
    public boolean isEmpty(char[] array) {
        boolean check = true;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != ' ') {
                check = false;
                break;
            }

        }
        return check;

    }
}
