import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class PlayingField  {
    private final char[] xAndO = {'X', 'O'};
    private GUI graphicalUserInterface;
    private final int[][][][] playingFieldArray;
    private  int currentGameNmbr;
    private  int currentRoundNmbr;
    /*This lets us store the action-listener objects from the GUI -> When the event is triggered actionPerformed is triggered here too*/
    private final  ActionListener buttonListener;

    private boolean gameisOver;
    private final int scoreX = 0;
    private final int scoreO = 0;
    /*The game will never have more than two players so we can declare it as final*/
    private Player[] playerArray = new Player[2];


    public PlayingField(GUI graphicalUserInterfaceIn,Player[] playerArrayIn) {
        playerArray = playerArrayIn;
        /*When we create a PlayingField the board should be laid out*/
        graphicalUserInterface = graphicalUserInterfaceIn;
        /*Once the visual field is generated and the player-selection is done we should add our actionlisteners*/
        JButton[][] buttonArray = graphicalUserInterface.getButtonArray();
        buttonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] coordinatesOfClickedButton = graphicalUserInterface.getCoordinatesOfClickedButton((JButton)e.getSource());


                if((((JButton) e.getSource()).getText().equals("") && !(Main.gameIsOver))){

                    playerArray[(currentRoundNmbr+1) % 2].makeMove(PlayingField.this, coordinatesOfClickedButton);
                    graphicalUserInterface.drawActiveCoordinates(playerArray);
                   if(graphicalUserInterface.announceIfWin(playerArray[(currentRoundNmbr+1) % 2],coordinatesOfClickedButton )) {
                       startNewGame();
                       ;/*This should trigger a method in main*/
                   }
                   else{
                        currentRoundNmbr++;
                    }


                }

            }
        };
        graphicalUserInterfaceIn.setActionListener(buttonListener);
        currentGameNmbr = 1;
        currentRoundNmbr = 1;
        playingFieldArray = new int[3][3][11][11];
        /*I instance the gamesArray with possible rounds, should be one more than possible rounds and
        and a possibility for 10 games which is more than our proposed bestOutOf3GamesMode */
        /*Alrik-implementation of for-loops*/
        for (int j = 0; j < 3; j++)
            for (int k = 0; k < 3; k++)
                for (int h = 0; h < 11; h++)
                    for (int m = 0; m < 11; m++) {
                        playingFieldArray[j][k][h][m] = 0;
                    }




        }







    public GUI getGraphicalUserInterface() {
        return graphicalUserInterface;
    }

        /*Generate moveSet for AI - Implement. Can use activeCoordinates, a playingField should have Players. Implement structure for containing players (acc. to UML)
    If possible time-wise implement GUI-class instead of having them conjoined (just copy paste with some extra steps). A PlayingField doesn´t paint a screen
     */

public void setGraphicalUserInterface(GUI graphicalUserInterfaceIn){

    graphicalUserInterface = graphicalUserInterfaceIn;

}

    public int getCurrentGameNmbr() {
        return currentGameNmbr;
    }

    public int getCurrentRoundNmbr() {
        return currentRoundNmbr;
    }



    public void increaseRoundNumber(){

        currentRoundNmbr++;

    }

    /**Gets the actionlistener to be imposed on the buttonArray inside GUI
     *
     *
     * @return buttonListener
     */
    public ActionListener getButtonListener() {
        return buttonListener;
    }

    public void setPlayerArray(Player[] playerArray) {
        this.playerArray = playerArray;
    }

    /**This method cleans the board and increases game-number
     *
     */
    public void startNewGame(){
    /*This sets the activeCoordinates to zero*/
    playerArray[0].resetActiveCoordinates();
    playerArray[1].resetActiveCoordinates();
    graphicalUserInterface.resetButtonTexts();
    graphicalUserInterface.resetUnableButtons();
    currentRoundNmbr = 1;
    currentGameNmbr++;


    }
}





