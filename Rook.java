import javax.swing.*;
public class Rook extends Piece{
    public Rook(int x, int y, boolean team, ImageIcon img){
        super(x,y,"Rook",team,img, 4);
    }
    // Overwriting for each different piece class on movement behavior
    public boolean getValidMove(int dx, int dy, Piece opponent){
        if(opponent == null || opponent.getTeam() != super.getTeam()){
            Piece[][] board = Main.board.getBoard();
            if(dx != super.getX() && dy == super.getY()){
                for(int i = 1; i < Math.abs(dx-super.getX()); i++){
                   if(dx-super.getX() < 0){
                       if(board[dx+i][super.getY()] != null) return false;
                   }
                   else{
                       if(board[dx-i][super.getY()] != null) return false;
                   }
                }
                return true;
            }
            else if(dx == super.getX() && dy != super.getY()){
                for(int i = 1; i < Math.abs(dy-super.getY()); i++){
                   if(dy-super.getY() < 0){
                       if(board[super.getX()][dy+i] != null) return false;
                   }
                   else{
                       if(board[super.getX()][dy-i] != null) return false;
                   }
                }
                return true;
            }
            else return false;
        }
        else return false;
    }
}
