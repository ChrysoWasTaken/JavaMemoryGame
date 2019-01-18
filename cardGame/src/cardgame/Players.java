/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import java.util.ArrayList;

/**
 * @author Alex
 * @author Tasos
 */
//This class keeps the information about every player (Players are kept in an arraylist)
public class Players {
    int score;
    int wins;
    int moves;
    boolean isBot;
    int botDifficulty; //Bot difficulty varies from 1 to 3 
    ArrayList<Integer> botRemembers;
    ArrayList<Integer> nextMove;
    
    public Players(){     
	score=0;
    wins=0;
	moves = 0;
	isBot = false;
	botDifficulty = 1;
	botRemembers = new ArrayList<Integer>();
	nextMove = new ArrayList<Integer>();


    }

}