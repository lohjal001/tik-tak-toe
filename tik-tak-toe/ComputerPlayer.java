import java.util.Random;

public class ComputerPlayer extends Player {

    public ComputerPlayer(){
        /*This instances a player with the name "Dator-spelare"*/
        super("Dator-spelare");





    }

    /**This chooses a possible move generated by the PlayingField at random
     *
     *
     *
     * @param strategicMoves
     */

    @Override
    protected void makeMove(int[] strategicMoves) {
        Random rand = new Random();
        //This will generate what solution we choose
        int solutionIndex = rand.nextInt(0, strategicMoves.length);
        activeCoordinates[][]

    }

    /**This is a getter for the activeCoordinates integer-array
     *
     *
     *
     * @return int[][] activeCoordinates
     */
    public int[][] getActiveCoordinates(){
        return super.activeCoordinates;



    }
}
