package cardgame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import javax.swing.*;
/**
 * @author Alex
 * @author Tasos
 */
//This class contains the main frame along with the logic of the game

public class Frame extends JFrame implements ActionListener {
    Grid mGame;
    Players[] player;
    JFrame pop_up;
    JSplitPane splitPane;
    //JSplitPane topPanelDuel;
    JPanel topPanel;
    JPanel bottomPanel;
    //JPanel topLeftPanel;
    //JPanel topRightPanel;
    JLabel label1 = new JLabel("<html>Player:<br/>Score:</html>");
    int playerI,i,previousI,previousI2,reps;
    String currentCard;
    String previousCard;
    int noOfButtons;
    String[] cards;
    int keepA,keepB,keepC,keepD;
    JButton cardButtons[];
    ImageIcon closedCard;
    ImageIcon cardIcons[];
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    double width = screenSize.getWidth();
    double height = screenSize.getHeight();

    //Constructor of the game
    public Frame(int obj, int pls,int botNumber,int botDif) {   
        mGame = new Grid(obj);
        cards = new String[mGame.size];
        player = new Players[pls];
        for (int i = 0; i < player.length; i++)
            player[i] = new Players();
        createBots(botNumber,botDif);
        pop_up = new JFrame();
        reps = 1;
        playerI = 0;
        keepForReplay(obj,pls,botNumber,botDif);
        JFraming();
    }
    //Main method of the game that creates our grid &, panel and buttons and assigns images to them based on the main array.
    public void JFraming() {
        splitPane = new JSplitPane();
        splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        splitPane.setDividerLocation((int) (height / 1.1));
        
        //if (keepA!=4){
        	mGame.shuffleArray(mGame.memoryArray);
            for (int i = 0; i < cards.length; i++)
                cards[i] = mGame.memoryArray[i];
            topPanel = new JPanel();
            topPanel.setLayout(new GridLayout(mGame.rows, mGame.columns, 40, 10));
            splitPane.setTopComponent(topPanel);
        //}
        /*else{
        	splitInTwo();
            topPanelDuel = new JSplitPane();
            topPanelDuel.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
            topPanelDuel.setDividerLocation((int)(width/2));
            topLeftPanel = new JPanel();
            topRightPanel = new JPanel();
            topLeftPanel.setLayout(new GridLayout(4,3,20,10));
            topRightPanel.setLayout(new GridLayout(4,3,20,10));
            splitPane.setTopComponent(topPanelDuel);
            topPanelDuel.setLeftComponent(topLeftPanel);
            topPanelDuel.setRightComponent(topRightPanel);
            topPanelDuel.setDividerSize(40);
        }*/
            
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        splitPane.setBottomComponent(bottomPanel);
        bottomPanel.add(label1);
        label1.setText("<html>Player:" + (playerI + 1) + "<br/>Score:" + player[playerI].score + "</html>");

        getContentPane().setSize((int) width, (int) height);
        getContentPane().setLayout(new GridLayout());
        getContentPane().add(splitPane);
    
        closedCard = new ImageIcon("Images/closed.jpg");
        closedCard = resizeIcon(closedCard, (int) (width / mGame.columns), (int) (height / mGame.rows));
        noOfButtons = mGame.memoryArray.length;
        cardButtons = new JButton[noOfButtons];
        cardIcons = new ImageIcon[noOfButtons];

        for (int i = 0; i < noOfButtons; i++) {
            cardButtons[i] = new JButton("");
            cardButtons[i].setIcon(closedCard);
            //if(keepA!=4)
                topPanel.add(cardButtons[i]);
            //else {
            	//if(i<12)
            	//	topLeftPanel.add(cardButtons[i]);
            //	else
            //		topRightPanel.add(cardButtons[i]);
            //}
            cardIcons[i] = new ImageIcon(mGame.memoryArray[i]);
            cardButtons[i].addActionListener(this);
        }

        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    //This method resizes the buttons based on our screen and grid size
    private static ImageIcon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
    //actionPerformed of buttons , resizes the icons and calls the gameLogic method with a timer.
    public void actionPerformed(ActionEvent e) {

        for (i = 0; i < noOfButtons; i++) {
            if (e.getSource() == cardButtons[i]) {
                cardButtons[i]
                        .setIcon(resizeIcon(cardIcons[i], (int) (width / mGame.columns), (int) (height / mGame.rows)));
                cardButtons[i].setDisabledIcon(
                        resizeIcon(cardIcons[i], (int) (width / mGame.columns), (int) (height / mGame.rows)));
                cardButtons[i].setEnabled(false);
                currentCard = cards[i];
                break;
            }
        }
        Timer t = new Timer(250, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameLogic();
            }
        });
        t.setRepeats(false);
        t.start();

    }
    
    //Main logic of the game.
    public void gameLogic() {
        if (reps == 1) {
            previousCard = currentCard;
            previousI = i;
            previousI2 = i;
            reps++;
        } else if (reps == 2) {
            if (currentCard.equals(previousCard)) {
                if (mGame.repeats == 2) {
                    cardButtons[i].setVisible(false);
                    cardButtons[previousI].setVisible(false);
                    reps = 1;
                    player[playerI].score++;
                    label1.setText("<html>Player:" + (playerI + 1) + "<br/>Score:" + player[playerI].score + "</html>");                    
                    isOver();
                } else {
                    previousI2 = i;
                    previousCard = currentCard;
                    reps++;
                }
            } else {

                cardButtons[i].setIcon(closedCard);
                cardButtons[previousI].setIcon(closedCard);

                cardButtons[i].setEnabled(true);
                cardButtons[previousI].setEnabled(true);
                reps = 1;
                countMoves();
                nextPlayer();
                label1.setText("<html>Player:" + (playerI + 1) + "<br/>Score:" + player[playerI].score + "</html>");

            }
        } else if (reps == 3) {
            if (currentCard.equals(previousCard)) {
                cardButtons[i].setVisible(false);
                cardButtons[previousI].setVisible(false);
                cardButtons[previousI2].setVisible(false);
                reps = 1;
                player[playerI].score++;
                label1.setText("<html>Player:" + (playerI + 1) + "<br/>Score:" + player[playerI].score + "</html>");
                isOver();
            } else {
                cardButtons[i].setIcon(closedCard);
                cardButtons[i].setEnabled(true);
                cardButtons[previousI].setIcon(closedCard);
                cardButtons[previousI].setEnabled(true);
                cardButtons[previousI2].setIcon(closedCard);
                cardButtons[previousI2].setEnabled(true);
                reps = 1;
                countMoves();
                nextPlayer();
                label1.setText("<html>Player:" + (playerI + 1) + "<br/>Score:" + player[playerI].score + "</html>");
            }

        }
        for (int k = 0; k < player.length; k++) {
            if (player[k].isBot && player[k].botDifficulty != 1) {
                willRemember(k, i, player[k].botDifficulty);
            }
        }
        if (reps == 1) {
            removeInvisible();
        }
        botClick();

    }

    //Changes the player after our turn is complete (given that the player chose wrong)
    public void nextPlayer() {
        if (playerI + 1 == player.length) {
            playerI = 0;
        } else {
            playerI++;
        }
    }

    //This method is called after our gameLogic and contains the thought process and gameplay of our bots.
    public void botClick() {
        Random rand = new Random();
        int selection, count = 0;
        boolean foundSame = false;
        if (player[playerI].isBot == true) {
            if (player[playerI].botDifficulty == 1) {

                do {
                    selection = rand.nextInt(noOfButtons);
                } while (cardButtons[selection].isEnabled() == false || cardButtons[selection].isVisible() == false);

                cardButtons[selection].doClick();

            } else if (player[playerI].botDifficulty >= 2) {
                if (player[playerI].nextMove.isEmpty()) {

                    if (reps == 1) {
                        for (int i : player[playerI].botRemembers) {
                            for (int j : player[playerI].botRemembers) {
                                if (cards[i].equals(cards[j])) {
                                    count++;
                                }
                            }

                            if (count == mGame.repeats) {
                                foundSame = true;
                                for (int j : player[playerI].botRemembers) {
                                    if (cards[i].equals(cards[j])) {
                                        player[playerI].nextMove.add(j);
                                    }
                                }
                                break;
                            }
                            count = 0;
                        }

                        if (foundSame == false) {
                            do {
                                selection = rand.nextInt(noOfButtons);
                            } while (cardButtons[selection].isEnabled() == false
                                    || cardButtons[selection].isVisible() == false || isInRemember(selection,playerI));
                            player[playerI].nextMove.add(selection);
                        }
                    } else {
                        for (int i : player[playerI].botRemembers) {
                            if (cards[i].equals(currentCard) && i != previousI && i != previousI2) {
                                count++;
                            }
                            if (count == mGame.repeats - (reps - 1)) {
                                foundSame = true;
                                for (int j : player[playerI].botRemembers) {
                                    if (cards[j].equals(currentCard) && j != previousI && j != previousI2) {
                                        player[playerI].nextMove.add(j);
                                    }
                                }
                                break;
                            }
                            count = 0;
                        }
                        if (foundSame == false) {
                            do {
                                selection = rand.nextInt(noOfButtons);
                            } while (cardButtons[selection].isEnabled() == false
                                    || cardButtons[selection].isVisible() == false || isInRemember(selection,playerI));
                            player[playerI].nextMove.add(selection);
                        }
                    }

                }
                doNextMove();

            }
        }
    }

    //This method tells the bot to perform its move
    public void doNextMove() {
        int move;
        move = player[playerI].nextMove.get(0);
        player[playerI].nextMove.remove(0);
        cardButtons[move].doClick();
    }

    
    //Checks if a bot remembers a specific card
    public boolean isInRemember(int checkCard,int playerR) {
        boolean value = false;
        for (int i : player[playerR].botRemembers) {
            if (checkCard == i) {
                value = true;
                break;
            }
        }
        return value;
    }

    //Determins if the bot will remember a card given the fact that it doesnt know its position already
    public void willRemember(int playerK, int index, int mode23) {
        Random r = new Random();
        boolean rememberBool;
        if (mode23 == 2) {
            rememberBool = r.nextBoolean();
        } else {
            rememberBool = true;
        }

        if (rememberBool && !isInRemember(index,playerK)) {
            player[playerK].botRemembers.add(index);
        }

    }

    //Removes the opened cards from our bots' memory
    public void removeInvisible() {
        ArrayList<Integer> toRemove = new ArrayList<Integer>();
        for (int i = 0; i < player.length; i++) {
            if (player[i].isBot) {
                if (!player[i].botRemembers.isEmpty()) {

                    for (int j : player[i].botRemembers) {
                        if (!cardButtons[j].isVisible()) {
                            toRemove.add(j);
                        }
                    }
                    for (int k : toRemove) {
                        player[i].botRemembers.remove(Integer.valueOf(k));
                    }
                    toRemove.clear();
                }
            }

        }

    }
    
    //This method creates our bots and places them last in the player arraylist. Called in constructor.
    public void createBots(int botNo,int botMode){
        for(int i =player.length;i>(player.length-botNo);i--){
            player[i-1].isBot = true;
            player[i-1].botDifficulty = botMode;
        
        }
    }
   
    //Determines if the game is over by checking the total score points given to the players
    //If game is over , proceeds to tell us the winner and asks the player to play again
    public void isOver(){
        String botP="";
        int scoreGlobal= 0;
        int max =player[0].score;
        int maxPlayer = 1;
        for (int i =0;i<player.length;i++)
            scoreGlobal=scoreGlobal+player[i].score;
        for (int i=1;i<player.length;i++){
            if(player[i].score>max){
                max=player[i].score;
                maxPlayer = i+1;
            }
        }
        if (player[maxPlayer-1].isBot)
            botP="(Bot)";
        else
            botP="";
        if(scoreGlobal==(int)cards.length/mGame.repeats){
            player[maxPlayer-1].moves++;
            JOptionPane.showMessageDialog(pop_up,"Winner is"+botP+" player: "+maxPlayer +" with a score of: "+max);
            Object[] options = {"Yes",
                "No",
                "Go to menu"};
            int choice = JOptionPane.showOptionDialog(pop_up,
                    "Do you want to play again?",
                    "Play Again",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[1]);
            if(choice==0){
                setVisible(false);
                new MenuJFrame(keepA,keepB,keepC,keepD).setVisible(true);
            }
            else if(choice==1){              
                System.exit(0); 
            }
            else if(choice==2){
                setVisible(false);
                new MenuJFrame().setVisible(true);
            }
            else{
                System.exit(0);
            }
                
        }      
    }
    public void countMoves(){
        player[playerI].moves++;
    }
    
    //Contains the previous game's settings
    public void keepForReplay(int a,int b,int c,int d){
       keepA= a;
       keepB= b;
       keepC= c;
       keepD= d;
    }
    /*
    public void splitInTwo() {
    		int k =0;
    		String[] cardsA = new String[12];
    		String[] cardsB = new String[12];
    		for(i=0;i<mGame.memoryArray.length;i=i+2) {
    			cardsA[k]=mGame.memoryArray[i];
    			cardsB[k]=mGame.memoryArray[i+1];
    		}
    		shuffleDuelTest(cardsA);
    		shuffleDuelTest(cardsB);
    		for(i=0;i<mGame.memoryArray.length;i++) {
    			if(i<12)
    				cards[i]=cardsA[i];
    			else
    				cards[i]=cardsB[i-12];
    		}
    	    

    		
	    	//if (Arrays.asList(cards).contains(mGame.memoryArray[i])){
	    	//	cards2[k]=mGame.memoryArray[i];
	    	//	k++;
    		//}
    		//else {
    		//	cards[l]=mGame.memoryArray[i];
    		//	l++;
    	//	}

    	
    }
    public void shuffleDuelTest(String[] array) {
    	int index;
		String temp;
	    Random random = new Random();
	    for (int i = array.length - 1; i > 0; i--)
	    {
	        index = random.nextInt(i + 1);
	        temp = array[index];
	       array[index] = array[i];
	        array[i] = temp;
	    }
    	
    }*/
}