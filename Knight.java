import javax.swing.*;
public class Knight extends Piece{
    public Knight(int x, int y, boolean team,ImageIcon img){
        super(x,y,"Knight",team, img, 2);
    }
    // Overwriting for each different piece class on movement behavior
    public boolean getValidMove(int dx, int dy, Piece opponent){
        if(opponent == null || opponent.getTeam() != super.getTeam()){
            dx = dx - super.getX();
            dy = dy - super.getY();
            dx = Math.abs(dx);
            dy = Math.abs(dy);
            if(dx == 2 && dy == 1) return true;
            else if(dx == 1 && dy == 2) return true;
            else return false;
        }
        else return false;
    }
}