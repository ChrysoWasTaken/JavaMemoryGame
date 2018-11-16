package cardgame;

/**
 * This is the major class that contains the gameplay elements of the project.
 * 
 * @author Tasos_Chrysopoulos
 * @author Alexandros_Adam
 */
public class GameIsOn {

    UserInput ui = new UserInput();
    Grid mGame = new Grid(ui.modeSelect());
    PrintCards pCards = new PrintCards();
    private char saved;
    private int check;
    private int scan;
    private int step = 1;
/*
    This method contains the general logic of the game.Three arrays are taken in
    from the Grid class , one filled with characters and shuffled , one filled 
    only with the '-' character , implying that it's empty , and its clone.
    Basically when the user selects a card to open , the card is copied in the empty
    array and shown to the player through the UserInput and PrintCards classes.
    This is repeated based on the mode selected by the user.When the loop is done , 
    if the user's choice is wrong , the cloned empty array acts as an undo function.
    If the user is correct , then the choice is passed down to the cloned array , so that
    if the user makes a wrong move , the whole array will not be reset , but rather only his 
    current move.When all the cards are picked up, the game congratulates the player,
    shows their total moves and shuts down.
    
  
 */
    public void gamePlay() {
        pCards.printArray(mGame.imageFillerArray, mGame.columns);
        while (!(mGame.isEmpty(mGame.fillerArray))) {
            mGame.imageFillerArray = mGame.fillerArray.clone();
            check = 0;
            saved = '-';
            System.out.println("TURN: " + step);
            for (int i = 0; i < mGame.repeats; i++) {
                pCards.availableNumbers(mGame.imageFillerArray);
                scan = ui.inputCheck(mGame.imageFillerArray, mGame.size);

                mGame.imageFillerArray[scan] = mGame.memoryArray[scan];
                pCards.printArray(mGame.imageFillerArray, mGame.columns);
                if (saved != '-') {
                    if (saved != mGame.imageFillerArray[scan]) {
                        check = 1;

                    }
                }
                saved = mGame.imageFillerArray[scan];
            }
            step++;
            if (check == 0) {
                mGame.pickCards(mGame.imageFillerArray);
                mGame.fillerArray = mGame.imageFillerArray;
                System.out.println("Correct!");
            }
        }
        pCards.printArray(mGame.memoryArray, mGame.columns);
        System.out.println("CONGRATULATIONS!!!!!");
        System.out.println("Total Turns:" + (step - 1));
    }

}
