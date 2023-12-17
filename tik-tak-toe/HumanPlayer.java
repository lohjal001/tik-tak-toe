import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HumanPlayer extends Player {
        /*These actionlisteners makes sure that we can wait for action before performing move inside makeMove*/


    public HumanPlayer(String name){
        super(name);

    }


    @Override
    protected void makeMove(PlayingField pf) {
        /*This is supposed to handle the computer logic, where the moves will be generated by the playingField*/
    }

    /**
     * This method lets a HumanPlayer make a move on a PlayingField
     *
     *
     *
     *
     * @param playingField, int[] moveCoordinates = {x-coordinate of move, y-coordinate of move}
     */
    @Override
    protected void makeMove(PlayingField playingField, int[] moveCoordinates) {
        /*On the playingField we make a move specified in move-coordinates*/
           super.setActiveCoordinates(moveCoordinates);




        }




}