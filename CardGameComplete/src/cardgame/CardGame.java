package cardgame;

/**
 * This is the main class that greets the player and begins the game.
 * 
 * @author Tasos_Chrysopoulos
 * @author Alexandros_Adam
 */
public class CardGame {

    
    public static void main(String[] args) {       
        System.out.println("---------------------------------");
        System.out.println("Welcome to the memory card game!");
        System.out.println("---------------------------------");
        GameIsOn start = new GameIsOn();
        start.gamePlay();
    }  
}
