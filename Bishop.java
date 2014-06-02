import javax.swing.*;
public class Bishop extends Piece{
    public Bishop(int x, int y, boolean team,ImageIcon img){
        super(x,y,"Bishop",team, img, 3);
    }
    // Overwriting for each different piece class on movement behavior
    public boolean getValidMove(int dx , int dy , Piece opponent){
        if(opponent == null || opponent.getTeam() != super.getTeam()){
            int changeX = dx - super.getX();
            int changeY = dy - super.getY();
            if(Math.abs(changeX)==Math.abs(changeY)){
                Piece[][] board = Main.board.getBoard();
                for(int i = 1; i < Math.abs(changeX); i++){
                    if(changeX > 0 && changeY > 0){
                        if(board[dx-i][dy-i] != null) return false;
                    }
                    if(changeX < 0 && changeY > 0){
                        if(board[dx+i][dy-i] != null) return false;
                    }
                    if(changeX > 0 && changeY < 0){
                        if(board[dx-i][dy+i] != null) return false;
                    }
                    if(changeX < 0 && changeY < 0){
                        if(board[dx+i][dy+i] != null) return false;
                    }
                }
                return true; 
            }
            else return false;
        }
        else return false;
    }
}
