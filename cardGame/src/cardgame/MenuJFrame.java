/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * @author Alex
 * @author Tasos
 */
public class MenuJFrame extends JFrame {
    
	//This class holds the main menu of our game in which the player may change mode / amount of players / bots / bot difficulty...
	
    public int gridMode;
    public int mode_id;
    public int players_id;
    public int duel_memory, duel_num;
    JFrame duel_pop_up;
    Frame callFrame;

    /**
     * Creates new form MenuJFrame
     */
    //checks if the checkbox is checked and proceeds to disable bots and their difficulty.
    public MenuJFrame() {
        initComponents();
        disableBot.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (disableBot.isSelected()) {
                    bot_memory.setEnabled(false);
                    numberOfBots.setEnabled(false);
                } else {
                    bot_memory.setEnabled(true);
                    numberOfBots.setEnabled(true);
                }
            }
        });
        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                mode_id = modes.getSelectedIndex();
                duel_memory = bot_memory.getSelectedIndex();
                if (numberOfBots.isEnabled()) {
                    duel_num = numberOfBots.getSelectedIndex();
                } else {
                    duel_num = -1; // If checkbox is checked , duel num (number of bots) will change to -1 and later in line 127 to 0 (no bots)
                }
                players_id = players.getSelectedIndex();
                if (duel_num >= players_id ) {
                    duel_num = players_id-1;
                }
                gameStart();
            }
        });
         duel_start.addActionListener(new ActionListener() { //Incomplete
             public void actionPerformed(ActionEvent evt) {
             
             duel_memory = 0;
             Object[] duel_options = {"Against Player",
                "Against Easy Bot",
                "Against Medium Bot",
                "Against Hard Bot"
             };
            int duel_choice = JOptionPane.showOptionDialog(duel_pop_up,
                    "Pick your oponent!",
                    "Duel Options",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    duel_options,
                    duel_options[0]);
                if(duel_choice == 0){
                   Frame FrameObj = new Frame(1,2, 1,0); 
               }else if(duel_choice == 1){
                   Frame FrameObj = new Frame(1,2, 1,duel_memory + 1);
               }else if(duel_choice == 2){
                   Frame FrameObj = new Frame(1,2,1,duel_memory + 2);
               }else{
                   Frame FrameObj = new Frame(1,2,1,duel_memory + 3);
               }
        //        callFrame.JFraming();
            }
        });
    }
    //Overloaded constructor in case we want to play with the same settings again.
    public MenuJFrame(int mode, int players, int botPlayers, int botMode) {	//Incomplete
        //write source file moves and wins of a player
        Writer bestSolo = null;
        try {
            bestSolo = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Best_solo.txt"), "utf-8"));            
            bestSolo.write("Something");
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage() + "Error writing file ");
        } finally {
            try {
                bestSolo.close();
            } catch (Exception ex) {/*ignore*/
            }
        }
        
        Frame FrameObj = new Frame(mode, players, botPlayers, botMode);
    //    FrameObj.JFraming();
    }
    
    public void gameStart() {
        Frame FrameObj = new Frame(mode_id + 1, players_id + 1, duel_num + 1, duel_memory + 1); //Starts the game based on our inputs
        setVisible(false);
    //    FrameObj.JFraming();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        modes = new javax.swing.JComboBox<>();
        players = new javax.swing.JComboBox<>();
        start = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        bot_memory = new javax.swing.JComboBox<>();
        numberOfBots = new javax.swing.JComboBox<>();
        disableBot = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        duel_start = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        modes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Simple", "Double", "Triple" }));

        players.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1 players", "2 players", "3 players", "4 players" }));

        start.setText("Start!");

        jLabel1.setText("Modes");

        jLabel2.setText("Number of Players");

        bot_memory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Goldfish", "Kangaroo", "Elephant" }));

        numberOfBots.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1 Bot", "2 Bot", "3 Bot", "4 Bot", " " }));

        disableBot.setText("Disable Bot");

        jLabel3.setText("Bot Memory");

        jLabel4.setText("Number of bots");

        jLabel5.setText("Memory Card Game");

        duel_start.setText("Duel");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(modes, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(players, 0, 130, Short.MAX_VALUE))
                    .addComponent(duel_start, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bot_memory, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                    .addComponent(numberOfBots, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(disableBot))
                .addGap(27, 27, 27))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(136, 136, 136)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(start, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(123, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(modes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bot_memory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(5, 5, 5))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(players, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numberOfBots, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(disableBot)
                    .addComponent(duel_start))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addComponent(start)
                .addGap(39, 39, 39))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws IOException {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MenuJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>          
        /* Create and display the form */
        MenuJFrame mj = new MenuJFrame();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuJFrame().setVisible(true);
            }
        });
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> bot_memory;
    private javax.swing.JCheckBox disableBot;
    private javax.swing.JButton duel_start;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JComboBox<String> modes;
    private javax.swing.JComboBox<String> numberOfBots;
    private javax.swing.JComboBox<String> players;
    private javax.swing.JButton start;
    // End of variables declaration//GEN-END:variables
}
