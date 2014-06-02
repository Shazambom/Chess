import javax.swing.*;
public class King extends Piece{
    public King(int x, int y, boolean team,ImageIcon img){
        super(x,y,"King",team,img,6);
    }
    // Overwriting for each different piece class on movement behavior
    public boolean getValidMove(int dx, int dy, Piece opponent){
        boolean helper = true;
        if(!this.isInCheck(dx,dy)){
            if(opponent == null || opponent.getTeam() != super.getTeam()){
                if(dx - 1 == super.getX() && dy == super.getY()){
                    return true;
                }
                else if(dx + 1 == super.getX() && dy == super.getY()){
                    return true;
                }
                else if(dx == super.getX() && dy - 1 == super.getY()){
                    return true;
                }
                else if(dx == super.getX() && dy + 1 == super.getY()){
                    return true;
                }
                else if(dx - 1 == super.getX() && dy - 1 == super.getY()){
                    return true;
                }
                else if(dx + 1 == super.getX() && dy - 1 == super.getY()){
                    return true;
                }
                else if(dx - 1 == super.getX() && dy + 1 == super.getY()){
                    return true;
                }
                else if(dx + 1 == super.getX() && dy + 1 == super.getY()){
                    return true;
                }
                else return false;
            }
            else return false;
        }
        else return false;
    }

    public boolean isInCheck(int dx, int dy){
        Piece[][] board = Main.board.getBoard();
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                boolean moveToLoc;
                if(i == dx && j == dy){ 
                    moveToLoc = false;
                }
                else moveToLoc = true;
                if(board[i][j] != null && board[i][j].getID() != 6 &&  board[i][j].getTeam() != super.getTeam() && moveToLoc){
                    if(dx != super.getX() || dy != super.getY()){
                        Location loc = new Location(super.getX(), super.getY());
                        super.move(new Location(dx,dy));
                        if(board[i][j].getValidMove(dx,dy,this)){
                            super.move(loc);
                            return true;
                        }
                    }
                    else{
                        if(board[i][j].getValidMove(super.getX(),super.getY(),this)){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean isInCheckmate(){
        Piece[][] board = Main.board.getBoard();
        if(this.isInCheck(super.getX(),super.getY())){
            for(int i = 0; i < 8; i++){
                int dx;
                int dy;
                if(i < 2) {
                    dx = super.getX();
                    if(i%2==0) dy = super.getY() + 1;
                    else dy = super.getY() - 1;
                }
                else if(i < 4){
                    dy = super.getY();
                    if(i%2==0) dx = super.getX() + 1;
                    else dx = super.getX() - 1;
                }
                else if(i < 6){
                    dx = super.getX() + 1;
                    if(i%2==0) dy = super.getY() + 1;
                    else dy = super.getY() - 1;
                }
                else{
                    dx = super.getX() - 1;
                    if(i%2==0) dy = super.getY() + 1;
                    else dy = super.getY() - 1;
                }
                if(dx >= 0 && dx <= 7 && dy >= 0 && dy <= 7){
                    if(this.getValidMove(dx,dy,board[dx][dy]))return false;
                }
            }
            return true;
        }
        else return false;
    }

    public void checkmateAssist(){
        Piece[][] board = Main.board.getBoard();
        if(this.isInCheckmate()){
            boolean makesure = true;
            for(int i = 0; i < 8; i++){
                for(int j = 0; j < 8; j++){
                    if(board[i][j] != null && board[i][j].getTeam() == super.getTeam()){
                        for(int dx = 0; dx < 8; dx++){
                            for(int dy = 0; dy < 8; dy++){
                                if(board[i][j].getID() != 6 && board[i][j].getValidMove(dx,dy,board[dx][dy])){
                                    Piece save = board[dx][dy]; 
                                    Location loc = new Location(board[i][j].getX(), board[i][j].getY());
                                    board[i][j].move(new Location(dx,dy));
                                    board[dx][dy] = board[i][j];
                                    if(!this.isInCheck(super.getX(), super.getY())){
                                        makesure = false;
                                    }
                                    board[dx][dy] = save;
                                    board[i][j].move(loc);
                                }
                            }
                        }
                    }
                }
            }
            if(makesure){
                if(super.getTeam()){
                    System.out.println("Checkmate: White Wins");
                    ClickListener.gameState = false;
                }
                else{
                    System.out.println("Checkmate: Black Wins");
                    ClickListener.gameState = false;
                }
            }
            else System.out.println("Check");
        }
        else if(this.isInCheck(super.getX(), super.getY())){
            System.out.println("Check");
        }
    }

    public boolean castle(int dx, int dy, Piece rook){
        Piece[][] board = Main.board.getBoard();
        if(!this.isInCheck(dx,dy)){
            if(super.getNumMoves() == 0 && rook.getNumMoves() == 0){
                if(dy == 0 && dx == 6){
                    if(board[5][0] == null && board[6][0] == null)return true;
                    else return false;
                }
                else if(dy == 0 && dx == 2){
                    if(board[3][0] == null && board[2][0] == null && board[1][0] == null)return true;
                    else return false;
                }  
                else if(dy == 7 && dx == 2){
                    if(board[3][7] == null && board[2][7] == null && board[1][7] == null)return true;
                    else return false;
                }  
                else if(dy == 7 && dx == 6){
                    if(board[5][7] == null && board[6][7] == null)return true;
                    else return false;                
                }
                else return false;
            }
            else return false;
        }
        else return false;
    }
}
