import javax.swing.*;
public class Board{
    private Piece[][] board = new Piece[8][8];
    private boolean turn;
    private int count = 0;
    boolean movePossible;
    /**
     * Initializes the Board, sets up the pieces in the starting configuration, sets it to White's turn to move, and configures the images.
     */
    public Board(){
        for(int i = 0; i < 8; i++){
            board[i][1] = new Pawn(i,1,true,new ImageIcon(getClass().getResource("BPawn.png")));
        }
        for(int i = 0; i < 8; i++){
            board[i][6] = new Pawn(i,6,false,new ImageIcon(getClass().getResource("WPawn.png")));
        }
        board[0][0] = new Rook(0,0,true,new ImageIcon(getClass().getResource("BRook.png")));
        board[1][0] = new Knight(1,0,true,new ImageIcon(getClass().getResource("BKnight.png")));
        board[2][0] = new Bishop(2,0,true,new ImageIcon(getClass().getResource("BBishop.png")));
        board[4][0] = new King(4,0,true,new ImageIcon(getClass().getResource("BKing.png")));
        board[3][0] = new Queen(3,0,true,new ImageIcon(getClass().getResource("BQueen.png")));
        board[5][0] = new Bishop(5,0,true,new ImageIcon(getClass().getResource("BBishop.png")));
        board[6][0] = new Knight(6,0,true,new ImageIcon(getClass().getResource("BKnight.png")));
        board[7][0] = new Rook(7,0,true,new ImageIcon(getClass().getResource("BRook.png")));

        board[0][7] = new Rook(0,7,false,new ImageIcon(getClass().getResource("WRook.png")));
        board[1][7] = new Knight(1,7,false,new ImageIcon(getClass().getResource("WKnight.png")));
        board[2][7] = new Bishop(2,7,false,new ImageIcon(getClass().getResource("WBishop.png")));
        board[4][7] = new King(4,7,false,new ImageIcon(getClass().getResource("WKing.png")));
        board[3][7] = new Queen(3,7,false,new ImageIcon(getClass().getResource("WQueen.png")));
        board[5][7] = new Bishop(5,7,false,new ImageIcon(getClass().getResource("WBishop.png")));
        board[6][7] = new Knight(6,7,false,new ImageIcon(getClass().getResource("WKnight.png")));
        board[7][7] = new Rook(7,7,false,new ImageIcon(getClass().getResource("WRook.png")));
        turn = true;
        movePossible = false;
    }

    /**
     * Gets the double array that represents the board and is filled with Piece objects
     * @return a double array that is the current state of the chess board and holds all the Piece objects
     */
    public Piece[][] getBoard(){
        return this.board;
    }

    /**
     * Gets the boolean value that represents whose turn it is.
     * @return the boolean value turn that is false if it is whites turn and true if it is blacks turn.
     */
    public boolean getTurn(){
        return this.turn;
    }

    /**
     * Updates the board every time a move is made.
     * This method performs all the checks to make sure moves are valid including exception moves such as castling, ascending, check, and checkmate.
     * This method also updates the images of the buttons.
     * Activates every time two buttons are pressed and allows pieces to move.
     * @param a the first Location object that represents the position of the piece selected to move
     * @param b the second Location object that represents the position the user wants the selected piece to move
     */
    public void update(Location a, Location b){
        boolean t;
        if(this.turn) t = false;
        else t = true;
        Piece king = idPiece(6,t);        
        if(this.turn != board[a.getX()][a.getY()].getTeam()){
            movePossible = board[a.getX()][a.getY()].getValidMove(b.getX(),b.getY(),
                board[b.getX()][b.getY()]);
            /*
             * check for if the king is in check and if he isn't moving
             */
            if(king.isInCheck(king.getX(), king.getY())){
                Piece save = board[b.getX()][b.getY()];
                if(movePossible){
                    board[b.getX()][b.getY()] = board[a.getX()][a.getY()];
                    if(king.isInCheck(b.getX(),b.getY())){
                        movePossible = false;
                    }
                }
                board[b.getX()][b.getY()] = save;
            }
            /*
             * Check to see if enpassant is possible.
             */
            if(!movePossible && board[a.getX()][a.getY()].getID() == 1){
                if(board[a.getX()][a.getY()].enpassant(b.getX(),b.getY(),board[a.getX()-1][a.getY()])
                || board[a.getX()][a.getY()].enpassant(b.getX(),b.getY(),board[a.getX()+1][a.getY()])){
                    if(b.getX() == a.getX() + 1) board[a.getX()+1][a.getY()] = null;
                    else board[a.getX()-1][a.getY()] = null;
                    movePossible = true;
                    Main.text.insert("Enpassant			",0);
                }
            }
            /*
             *This if statement moves for regular movement behavior and takes care of taking opponents pieces.
             */
            if(movePossible){
                if(count%2==0) turn = false;
                else turn = true;
                count++;
                board[b.getX()][b.getY()] = null;
                board[b.getX()][b.getY()] = board[a.getX()][a.getY()];

                board[a.getX()][a.getY()] = null;
                board[b.getX()][b.getY()].move(b);
                board[b.getX()][b.getY()].addMove();
                //ascends a pawn to a Queen
                if(board[b.getX()][b.getY()].ascend(b.getY())){
                    if(board[b.getX()][b.getY()].getTeam())board[b.getX()][b.getY()] = new Queen(b.getX(),b.getY(),board[b.getX()][b.getY()].getTeam(),new ImageIcon("BQueen.png"));
                    else board[b.getX()][b.getY()] = new Queen(b.getX(),b.getY(),board[b.getX()][b.getY()].getTeam(),new ImageIcon("WQueen.png"));
                }
            }
            /*
             * This else if statement handles casteling and activates every time the king tries to move irregularly. 
             */
            else if(board[a.getX()][a.getY()].getID()== 6 && board[a.getX()][a.getY()].getNumMoves() == 0 && !board[a.getX()][a.getY()].isInCheck(a.getX(), a.getY())){
                castleAssister(a,b);
            }
            else Main.text.insert("You cannot move there			",0);
        }
        else Main.text.insert("It is not your turn			",0);
        //updates the images on the buttons
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(board[j][i] != null){
                    Main.buttonGrid[j][i].setIcon(board[j][i].getImg());
                }
                else Main.buttonGrid[j][i].setIcon(null);
            }
        }
    }

    /**
     * Simply Asists updater with castling because castling is a lot of code and the updater method was getting too long.
     * You really shouldn't be using this method in any way other than castling.
     * @param a the first Location object that represents the position of the piece selected to move
     * @param b the second Location object that represents the position the user wants the selected piece to move
     */
    public void castleAssister(Location a, Location b){
        if(b.getX()>a.getX()){
            movePossible = board[a.getX()][a.getY()].castle(b.getX(),b.getY(),board[b.getX()+1][b.getY()]);
            if(movePossible){
                if(count%2==0) turn = false;
                else turn = true;
                count++;
                Location c = new Location(b.getX() - 1,b.getY());
                //moves the king
                board[b.getX()][b.getY()] = board[a.getX()][a.getY()];
                board[a.getX()][a.getY()] = null;
                board[b.getX()][b.getY()].move(b);
                board[b.getX()][b.getY()].addMove();
                //moves the rook
                board[c.getX()][c.getY()] = board[b.getX()+1][b.getY()];
                board[b.getX()+1][b.getY()] = null;
                board[c.getX()][c.getY()].move(c);
                board[c.getX()][c.getY()].addMove();
            }
        }
        else{
            movePossible = board[a.getX()][a.getY()].castle(b.getX(),b.getY(),board[b.getX()-2][b.getY()]);
            if(movePossible){
                if(count%2==0) turn = false;
                else turn = true;
                count++;
                board[a.getX()][a.getY()].addMove();
                Location c = new Location(b.getX() + 1,b.getY());
                //moves the king
                board[b.getX()][b.getY()] = board[a.getX()][a.getY()];
                board[a.getX()][a.getY()] = null;
                board[b.getX()][b.getY()].move(b);
                board[b.getX()][b.getY()].addMove();
                //moves the rook
                board[c.getX()][c.getY()] = board[b.getX() - 2][b.getY()];
                board[b.getX()-2][b.getY()] = null;
                board[c.getX()][c.getY()].move(c);
                board[c.getX()][c.getY()].addMove();
            }
        }
    }
    /**
     * Gets the Piece at the Location it is given
     * @param a The Location object needed to locate the piece in the array
     * @return the piece found at the location given
     */
    public static Piece getPiece(Location a){
        return Main.board.getBoard()[a.getX()][a.getY()];
    }
    /**
     * Gets the Piece with the id that identifies it as a certian piece.
     * Note: this is only effective if there is one of these pieces on the board. 
     * It will simply find the first piece it finds if there are more than one of the piece.
     * This method is generally used only to find the King.
     * @param id the id int that is 1-6 depending on what piece it is.
     * @param team the boolean to know which color piece you are looking for.
     * @return the piece found with that id.
     */
    public static Piece idPiece(int id, boolean team){
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(Main.board.getBoard()[i][j] != null){
                    if(Main.board.getBoard()[i][j].getID() == id && Main.board.getBoard()[i][j].getTeam() == team){
                        return Main.board.getBoard()[i][j];
                    }
                }
            }
        }
        return null;
    }
}
