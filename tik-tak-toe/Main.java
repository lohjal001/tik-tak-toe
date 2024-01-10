import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Random;

public class Main {

    public static boolean seriesOfGamesIsOver = false;
    public static boolean gameIsOver = false;


    public static void main(String[] args){


        /*Makes sure we don´t instance more Randomgenerators than needed*/
        Random rand = new Random();
         char[] xandO = {'X', 'O'};
        /*Hahahaha okay so apparently 1%2 = -1, thought they would use 2*0 as divisor*/

        /*The players active in the game, can be of subclass Computer/HumanPlayer*/
        Player[] playerArray = new Player[2];
        /*When we implement the choice-buttons we neeeeeeeeeeeeeed to change this to 0*/
        GUI graphicalUserInterface = new GUI();
        PlayingField ticTacToeBoard;




        if(graphicalUserInterface.is1PlayerMode()) {
            HumanPlayer player1 = new HumanPlayer(graphicalUserInterface.getPlayerNames()[0]);
            ComputerPlayer player2 = new ComputerPlayer();
            playerArray = new Player[2];
            playerArray[0] = player1;
            playerArray[1] = player2;
            int index = rand.nextInt(0,2);
            playerArray[0].setMark(xandO[index]);
            playerArray[1].setMark(xandO[1-index]);
             ticTacToeBoard = new PlayingField(graphicalUserInterface, playerArray);
            ActionListener buttonListenerMain = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ticTacToeBoard.increaseRoundNumber();
                }
            };
            graphicalUserInterface.setActionListener(ticTacToeBoard.getButtonListener());
            graphicalUserInterface.setActionListener(buttonListenerMain);


        }
        else {
            /*We need to always make sure that the first player in the array has X*/
            HumanPlayer player1 = new HumanPlayer(graphicalUserInterface.getPlayerNames()[0]);
            HumanPlayer player2 = new HumanPlayer(graphicalUserInterface.getPlayerNames()[1]);
            /*Will generate new Random-object each time, see if we can fix that*/

            int index = rand.nextInt(0, 2);
            player1.setMark(xandO[index]);
            player2.setMark(xandO[1 - index]);
            /*Upon activating setting marks we want the x-having player to be first in the array*/
            playerArray = new Player[]{(Player) player1, (Player) player2};

            if (player1.getMark() == 'O') playerArray = new Player[]{(Player) player2, (Player) player1};
            ticTacToeBoard = new PlayingField(graphicalUserInterface, playerArray);
            graphicalUserInterface.setActionListener(ticTacToeBoard.getButtonListener());

        }

        while(!seriesOfGamesIsOver) {


            while (!gameIsOver) {
                /*Upon checking with breakpoints its clear the program never enters the if-statement -> boolean not updated*/

            }

        }
    }

}
