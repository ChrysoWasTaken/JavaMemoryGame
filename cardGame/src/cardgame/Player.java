/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import java.util.ArrayList;

/**
 *
 * @author Alex
 */
public class Player {
    boolean isHuman;
    int botDiff;
    
    public Player(boolean isH, int x){
        this.isHuman = isH;
        this.botDiff = x;
    }
    
    public Player(boolean isH){
        this(isH,0);
    }
    public void getNextMove(){
        
        //epilegei mia tuxaia thesi arxika apo autes pou einai dia8esimes, prepei na pernas to state tou paixnidiou(parametro)
        // deuteri epilogh mia random timh apo 0-1 (math random) 8a orizei ena threshhold h duskolia tou bot
        // 8a kerdizei apo 0-0.2 0-0.6 0-1
       // (botDiff + 1) * 0.2 8a orizete to threshhold gia thn duskolia
       // efososn to math random 8a moy bgalei mia timh apo 0 - threshhold to bot 8a epilegei thn swsth epomenh kinhsh alliws 8a epilegei mia random kinhsh
       
    }
}
