import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;

public class GUI implements ActionListener {
    /*Upon drawing active coordinates we complete a round so we can use that method to update a counter for roundNmbr
    This means we can figure out whether to place an X or O (roundNmbr % 2 -> means O must be at index 0 ; check if first round) from a final char array and not need Playingfield to
    handle that for the GUI -> Better logical structure, GUI paints the values generated by the PlayingField and
    the players actions thereupon

     */
    private int roundNmbr = 1;
    /*Values reflected in name, O at index 0 and x at index 1*/
    private final char[] oAndX = {'O','X'};
    private final JFrame framePlayingField;
    private final JPanel panelOuterPlayingField;
    private final JPanel panelInnerPlayingField;
    private final JLabel labelOuterPlayingFieldNorth;
    private final JLabel labelOuterPlayingFieldSouth;
    private final JButton[][] buttonArray = new JButton[3][3];
    private JButton b = new JButton();
    /*An action-listener array we can send to the playing-field to trigger on action-performed on GUI*/
    private ActionListener[][] buttonListeners = new ActionListener[3][3];
    private boolean is1PlayerMode;
    /*Let´s us use the inputs from the player(s)*/
    private String[] playerNames = new String[2];

    public GUI() {


        framePlayingField = new JFrame("TicTacToe");
        framePlayingField.setSize(600, 600);
        framePlayingField.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        panelOuterPlayingField = new JPanel();
        framePlayingField.add(panelOuterPlayingField);
        panelOuterPlayingField.setLayout(new BorderLayout());

        labelOuterPlayingFieldNorth = new JLabel("Player One and Player Two");
        panelOuterPlayingField.add(labelOuterPlayingFieldNorth, BorderLayout.NORTH);

        labelOuterPlayingFieldSouth = new JLabel("Score and Wins");
        panelOuterPlayingField.add(labelOuterPlayingFieldSouth, BorderLayout.SOUTH);

        panelInnerPlayingField = new JPanel();
        panelOuterPlayingField.add(panelInnerPlayingField, BorderLayout.CENTER);
        panelInnerPlayingField.setLayout(new GridLayout(3, 3));

        JPanel inputPanel = new JPanel();

        //initialize the buttonArray into our game board
        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 3; k++) {
                buttonArray[i][k] = new JButton();
                buttonArray[i][k].addActionListener(this);
                panelInnerPlayingField.add(buttonArray[i][k]);
            }
        }
        ImageIcon optionIcon = new ImageIcon("529185.png");
        String[] selectionTexts = {"1 Spelare", "2 spelare"};
//        JOptionPane singleOrMultiplayerPane = new JOptionPane(selectionTexts,JOptionPane.QUESTION_MESSAGE,JOptionPane.YES_NO_OPTION,optionIcon);
//        singleOrMultiplayerPane.setSize(200,200);
//        panelOuterPlayingField.add(singleOrMultiplayerPane);


        framePlayingField.setVisible(true);
        String[] options = {"1 Spelare", "2 Spelare"};
        String n = (String) JOptionPane.showInputDialog(null, "Vänligen välj antal spelare",
                "Tic-tac-toe", JOptionPane.QUESTION_MESSAGE, optionIcon, options, options[1]);
        is1PlayerMode = (n.equals("1 Spelare")) ? true : false;
        if (is1PlayerMode) {

            playerNames[0] = JOptionPane.showInputDialog(null, "Vänligen ange spelarnamn:", "Tic-tac-toe", JOptionPane.QUESTION_MESSAGE);
            playerNames[1] = "Dator-spelare";

        }
        else{
            playerNames[0] = JOptionPane.showInputDialog(null, "Vänligen ange spelarnamn:", "Spelare 1", JOptionPane.QUESTION_MESSAGE);
            playerNames[1] = JOptionPane.showInputDialog(null, "Vänligen ange spelarnamn:", "Spelare 2", JOptionPane.QUESTION_MESSAGE);


        }
        labelOuterPlayingFieldNorth.setText(playerNames[0]+" | vs | "+playerNames[1]);
        labelOuterPlayingFieldSouth.setText(playerNames[0]+" W : L\n "+playerNames[1]+" W : L");



    }

    /**
     * This updates the GUI according to the latest move
     *
     * @param playerArray
     */
    public void drawActiveCoordinates(Player[] playerArray) {
        /*Since this means a move has been made once it is called we can use this for roundcounting inside the GUI*/
        roundNmbr++;
        for (int playerIndex = 0; playerIndex < 2; playerIndex++)
            for (int row = 0; row < 3; row++)
                for (int col = 0; col < 3; col++) {
                    String s = (playerArray[playerIndex].activeCoordinates[col][row] == 1) ? String.valueOf(playerArray[playerIndex].getMark()) : "";
                    buttonArray[col][row].setText(s);

                }
           framePlayingField.invalidate();
            framePlayingField.validate();
            framePlayingField.repaint();

        //for(int i = 0; i < 2 ; i++)playerArray[i];


    }

    /**Takes a button and returns the coordinates where it is placed
     *
     *
     * @param b
     * @return int[] returnArray = {x-coordinate,y-coordinate} ; origin at top left
     */
    public int[] getCoordinatesOfClickedButton(JButton b) {
        int[] returnArray = new int[2];
        /*Alrik-implemented for-loop structure, fair enough, upon refactoring I get the appeal*/
        for (int i = 0; i < 3; i++)
            for (int k = 0; k < 3; k++) {
                if (buttonArray[i][k] == b) {
                    returnArray[0] = i;
                    returnArray[1] = k;


                    return returnArray;
                }
            }
        return returnArray;
    }
    public JButton getB(){

        return b;

    }

    /**
     * This method returns the result of the player-mode-selection
     *
     * @return true if singleplayer-mode is selected, false otherwise
     */
    public boolean is1PlayerMode() {
        return is1PlayerMode;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /*Gets the instance of the specified button affected by action performed and sets it to variable we can work with*/
        b = (JButton) e.getSource();
        /*On the first move we know it´s always X and it´s always empty everwhere*/
        if(roundNmbr == 1){
            b.setText(String.valueOf(oAndX[1]));
            /*First round done*/
            roundNmbr++;
        }
        /*May return null or maybe get some fuckery if "" =(nothing) isnt in the ASCII-table.... well, we´ll see*/
        if(b.getText().equalsIgnoreCase("")){
            b.setText(String.valueOf(oAndX[roundNmbr%2]));



        }
    }

    /**
     * Returns an array with the player-names from the user-input at the start of running
     *
     * @return playerNamesIn
     */
    public String[] getPlayerNames() {
        return playerNames;
    }

    public ActionListener[][] getButtonListeners() {
        return buttonListeners;
    }

    public JButton[][] getButtonArray() {
        return buttonArray;
    }
}