import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class Player {

    protected int wins;

    protected int losses;
    protected int[][] activeCoordinates;
    protected String name;

    /*Realizing this is not technically needed, we infer X or O in GUI with roundNumber*/
    protected char mark;

    protected Player(String nameIn) {
        this.name = nameIn;
        this.activeCoordinates = new int[3][3];
        wins = 0;
        losses = 0;

        for (int i = 0; i < 3; i++) {

            for (int k = 0; k < 3; k++) {
                activeCoordinates[i][k] = 0;

            }

        }
    }


    protected void setMark(char markIn) {
        mark = markIn;

    }

    /**
     * Gets mark of player 'X' || 'O'
     *
     * @return markOfPlayer
     */
    public char getMark(){

        return mark;

    }

    /*Needs to be an inParameter because a player is aggregated inside PlayingField so no access otherwise*/
    protected abstract void makeMove(PlayingField pf);

    /**This method returns the players current board configuration as a 2D array of integers
     *
     *
     * @return int[][] activeCoordinates = (1 (if occupied) || 0 (if not occupied)) ; activeCoordinates[x][y]
     *
     */
    public int[][] getActiveCoordinates() {
        return activeCoordinates;
    }

    /**Sets the array at the specified coordinates to 1
     *
     *
     * @param moveCoordinates = {x-coordinate of move, y-coordinate of move}
     */
    public void setActiveCoordinates(int[] moveCoordinates) {
        activeCoordinates[moveCoordinates[0]][moveCoordinates[1]] = 1;
    }

    protected abstract void makeMove(PlayingField playingField, int[] moveCoordinates);

    protected void addLossOrWin(boolean ifWin){

        if(ifWin) wins++;
        else losses++;

    }

    public int getLosses() {
        return losses;
    }

    public int getWins() {
        return wins;
    }

    /**This method sets the activeCoordinatearray for the player to zero across the board
     *
     */
    public void resetActiveCoordinates(){
        for(int i = 0 ; i < activeCoordinates.length ; i++) for(int k = 0 ; k < activeCoordinates.length ; k++){
            activeCoordinates[i][k] = 0;

        }

    }
}