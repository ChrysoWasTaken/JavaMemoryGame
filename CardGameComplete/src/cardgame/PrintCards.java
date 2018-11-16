package cardgame;

/**
 * This class contains all void methods used to display 
 * information to the user.
 * 
 * @author Tasos_Chrysopoulos
 * @author Alexandros_Adam
 */
public class PrintCards {
    public void printArray(char[] array, int col) {

        for (int i = 0; i < 50; ++i) {
            System.out.println();
        }

        for (int ind = 0; ind <= (array.length - 1); ind++) {
            if ((ind + 1) % col == 0) {

                System.out.println(array[ind] + " ");
            } else {
                System.out.print(array[ind] + " ");
            }
        }
        System.out.println();
    }

    //This method shows the card positions that are not already picked up.
    public void availableNumbers(char[] array) {
        System.out.print("Available numbers:");
        for (int i = 0; i < array.length; i++) {
            if (array[i] == '-') {
                System.out.print(i + 1);
                System.out.print(" ");
            }

        }
        System.out.println();
    }
    public static void SelectMode(){
        System.out.println();
        System.out.println("Available Modes:");
        System.out.println("Type 1 for basic mode  -- 12 pairs of 2 cards");
        System.out.println("Type 2 for double mode   -- 24 pairs of 2 cards");
        System.out.println("Type 3 for triple mode   -- 12 pairs of 3 cards");
        System.out.print("Select Mode:");
    }
}
