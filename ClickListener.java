import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.*;
public class ClickListener implements ActionListener
{
    //detects clicks on the buttons within the button array and remembers which cell was clicked
    private int x;
    private int y;
    static Location a;
    static Location b;
    static int count = 0;
    static boolean gameState = true;
    /**
     * Initializes the button ClickListener and allows it to remember which button in the grid is clicked.
     * @param x the x coordinate of the button that is in the grid
     * @param y the y coordinate of the button that is in the grid
     */
    public ClickListener(int x, int y){
        super();
        this.x = x;
        this.y = y;
    }
    /**
     * Every two clicks this method generates two Location objects and updates the board with these objects.
     * Incraments how many times all buttons in the grid have been clicked as a whole to keep track of how to move pieces.
     * Checks to see if either the Opponent's King is in Check or CheckMate after every turn
     * @param event default ActionEvent object
     */
    public void actionPerformed(ActionEvent event){
        if(gameState){
            if(count%2 == 0){
                a = new Location(x,y);
                if(Board.getPiece(a) == null){ 
                    count = -1;
                    Main.text.insert("No piece selected			",0);
                }
            }
            else{
                b = new Location(x,y);
                Main.board.update(a,b);
                Piece WKing = Board.idPiece(6, false);
                Piece BKing = Board.idPiece(6, true);
                if(Main.board.getTurn()) WKing.checkmateAssist();
                else BKing.checkmateAssist();
            }
            count++;
        }
        else {
            Main.text.insert("Exiting Game			",0);
            System.exit(0);
        }
    }
}