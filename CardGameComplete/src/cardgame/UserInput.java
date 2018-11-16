package cardgame;

import java.util.Scanner;

/**
 *  This class contains the User Interface.
 *  This class takes in the mode selected by the user and later on the card 
 *  the user selects to open , goes through try - catch blocks to detect any
 *  errors and returns the input along with corresponding messages.
 * 
 * @author Tasos_Chrysopoulos
 * @author Alexandros_Adam
 */
public class UserInput {
    //Lets the user select a mode and then checks if it is valid.
    public int modeSelect() {
        Scanner sc = new Scanner(System.in);
        int err;
        int mode = 1;
        //String inp = "";
        do {

            err = 0;
            PrintCards.SelectMode();
            try {
                mode = sc.nextInt();             
            } catch (Exception e) {
                err = 1;
                System.out.println("Error: Mode not available.Try entering a NUMBER");
                sc.next();
            }
            if (mode < 1 || mode > 3){
                System.out.println("Requested mode not available!");
		err = 1;
            }           
        } while (err == 1);
        return mode;
    }
    //Checks if the card number the user chooses is available and if it is actually a number.
    public int inputCheck(char[] array, int size) {

        Scanner sc = new Scanner(System.in);									
        int scan = 0;
        int err;
        do {
            System.out.print("->");
            err = 0;
            try {
                scan = sc.nextInt() - 1;
            } catch (Exception e) {
                err = 1;
                System.out.println("Error: Try entering an available NUMBER");
                sc.next();
            }
            if (scan < 0 || scan >= size) {
                System.out.println("Card number out of bounds,Try an avaiable card");
                err = 1;
            } else if (array[scan] != '-' && err == 0) {
                System.out.println("Card already opened,Try a closed one!");
                err = 1;
            }

        } while (err == 1);
        return scan;
    }
}
