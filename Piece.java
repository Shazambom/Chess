import javax.swing.*;
public abstract class Piece{
    private int x;
    private int y;
    private String name;
    private boolean team;
    private int numMoves;
    private ImageIcon img;
    private int id;
    /**
     * This method initalizes all the different kinds of pieces.
     * @param x position on the board
     * @param y position on the board
     * @param name of the kind of piece
     * @param team a boolean that is false if the piece is white and true if the piece is black
     * @param img the image of the piece 
     */
    public Piece(int x, int y, String name, boolean team, ImageIcon img, int id)
    {
        this.x = x;
        this.y = y;
        this.name = name;
        this.team = team;
        this.numMoves = 0;
        this.img = img;
        this.id = id;
    }
    /**
     * Gets the id of the piece, an integer number that identifies what kind of piece it is.
     * @return the int id
     */
    public int getID(){
        return this.id;
    }
    /**
     * Gets the image of the piece
     * @return the ImageIcon object
     */
    public ImageIcon getImg(){
        return this.img;
    }
    /**
     * Gets the number of moves the piece has taken
     * @return the int value of numMoves
     */
    public int getNumMoves(){
        return this.numMoves;
    }
    /**
     * Increments the number of moves the piece has taken by one. 
     */
    public void addMove(){
        this.numMoves++;
    }
    /**
     * Gets the X coordinate of the piece.
     * @return the int value of the X coordinate
     */
    public int getX(){
        return this.x;
    }
    /**
     * Gets the Y coordinate of the piece.
     * @return the int value of the Y coordinate 
     */
    public int getY(){
        return this.y;
    }
    /**
     * Gets the name of the piece.
     * @return name of the piece which is related to what kind of piece it is
     */
    public String getName(){
        return this.name;
    }
    /**
     * Gets the color of the piece.
     * @return the team the piece is on: False for White and True for Black.
     */
    public boolean getTeam(){
        return this.team;
    }
    /**
     * Moves the piece to a location.
     * @param a A Location object which represents a location on the board
     */
    public void move(Location a){
        this.x = a.getX();
        this.y = a.getY();
    }
    /**
     * This method should only be overwritten in the King class.
     * Checks to see if the king is in check. 
     * @param dx the x coordinate on the board
     * @param dy the y coordinate on the board
     * @return a true value if the King is in check or a false value if the King is not in check
     */
    public boolean isInCheck(int dx, int dy){
        return false;
    }
    /**
     * This method should only be overwritten in the King class.
     * Checks to see if a king is in checkmate.
     * @return a true value if the King is in checkmate or a flase value if the King is not in checkmate.
     */
    public boolean isInCheckmate(){
        return false;
    }
    /**
     * Checks if the move chosen is valid.
     * @param dx the x coordinate where the piece is moving to.
     * @param dy the y coordinate where the piece is moving to.
     * @param opponent the occupant of that space.
     * @return a true value if the the move chosen is possible for the piece chosen
     */
    public abstract boolean getValidMove(int dx, int dy, Piece opponent);
    /**
     * This method is only should be overwritten in the King class.
     * This method checks if the King and respective Rook are capable of castling.
     * @param a the x value where the king is moving.
     * @param b the y value where the king is moving.
     * @param rook that is going to be moved along with the king.
     * @return a true value if it is possible to castle.
     */
    public boolean castle(int a, int b, Piece rook){return false;}
    /**
     * This method should only be overwritten in the Pawn class.
     * This method ascends a Pawn to a queen. 
     * @param y coordinate on the board
     * @return a true value if it is possible for a pawn to ascend
     */
    public boolean ascend(int y){return false;}
    /**
     * This method should only be overwritten in the King class.
     * This method assists with checking to see if a King is in check or checkmate.
     * It checks to see if the King truely is in check or Checkmate.
     * Also checks to see if the player who is in checkmate has any possible move to get out
     * and if so determines that it is not checkmate.
     */
    public void checkmateAssist(){}
    /**
     * This method should only be overwritten in the Pawn class.
     * This method checks to see if a pawn can perform an enpassant.
     * @param a the x value where the pawn is to move to.
     * @param b the y value where the pawn is to move to.
     * @param opp the Piece object to the right or left of the pawn.
     */
    public boolean enpassant(int a, int b, Piece opp){return false;}
}

