package de.hft.swp1.pong;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * displays the highscores list on this system after the game
 */
public class Highscores extends JScrollPane

{
    /** Attributes */
    
    private final static Comparator<Highscore> COMP_HIGHSCORES = new Comparator<Highscore>() {
        @Override
        public int compare(Highscore h1, Highscore h2) {
            int scoreDifference = h2.score - h1.score;
            if(scoreDifference == 0){
                return h1.playerName.compareTo(h2.playerName);
            }
            return scoreDifference;
        }
    };
    
    /**
     * 
     */
    private static List<Highscore> highscores;
    
    private static JTable highscoreTable;
    /**
     * Operation Highscores
     *
     * @param player -
     * @param score  -
     * @return
     */
    public Highscores(){
        initComponents();
    }
    
    private void initComponents(){
        highscores = new ArrayList<>();
        highscoreTable = new JTable();
        setViewportView(highscoreTable);
    }
    
    public void addHighscore(Highscore playedHighscore){
        highscores.add(playedHighscore);
        highscores.sort(COMP_HIGHSCORES);
        highscoreTable.setModel( new DefaultTableModel(new String [] {"Score","Player-Name"}, highscores.size()));
        for(int i = 0; i < highscores.size(); i++){
            highscoreTable.getModel().setValueAt(highscores.get(i).score, i, 0);
            highscoreTable.getModel().setValueAt(highscores.get(i).playerName, i, 1);
        }
    }
}
