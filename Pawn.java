import javax.swing.*;
public class Pawn extends Piece{
    public Pawn(int x, int y, boolean team,ImageIcon img){
        super(x,y,"Pawn",team,img,1);
    }

    public boolean getValidMove(int dx , int dy , Piece opponent){
        Piece[][] board = Main.board.getBoard();
        if(super.getTeam()){
            if(opponent == null){
                if(super.getX() == dx && super.getY() == dy - 1) return true;
                else if(super.getX() == dx && super.getY() == dy - 2 
                && super.getNumMoves() == 0 && board[super.getX()][super.getY() + 1] == null) return true;
                else return false;
            }
            else if(opponent.getTeam() != super.getTeam()){
                if(opponent.getX() == super.getX() + 1 && opponent.getY() == super.getY() + 1) return true;
                else if(opponent.getX() == super.getX() - 1 && opponent.getY() == super.getY() + 1) return true;
                else if(opponent.getX() == dx && opponent.getY() == dy) return false;
                else if(super.getX() == dx && super.getY() == dy - 1) return true;
                else return false;
            }

            else{
                if(super.getX() == dx && super.getY() == dy - 1 && opponent.getX() != dx && opponent.getY() != dy ) return true;
                else return false;
            }

        }
        else{
            if(opponent == null){
                if(super.getX() == dx && super.getY() == dy + 1) return true;
                else if(super.getX() == dx && super.getY() == dy + 2 
                && super.getNumMoves() == 0 && board[super.getX()][super.getY() - 1] == null) return true;
                else return false;
            }
            else if(opponent.getTeam() != super.getTeam()){
                if(opponent.getX() == super.getX() + 1 && opponent.getY() == super.getY() - 1) return true;
                else if(opponent.getX() == super.getX() - 1 && opponent.getY() == super.getY() - 1) return true;
                else if(opponent.getX() == dx && opponent.getY() == dy ) return false;
                else if(super.getX() == dx && super.getY() == dy + 1) return true;
                else return false;
            }

            else{
                if(super.getX() == dx && super.getY() == dy + 1 && opponent.getX() != dx && opponent.getY() != dy ) return true;
                else return false;
            }

        }
    }

    public boolean ascend(int dy){
        if(super.getTeam()&& dy == 7)return true;
        else if(dy == 0) return true;
        else return false;
    }

    public boolean enpassant(int dx , int dy , Piece opp){
        if(super.getTeam()){
            if(opp != null && opp.getNumMoves() == 1 && opp.getID() == 1 && opp.getX() == dx && opp.getY() == dy - 1){
                if(opp.getX() + 1 == super.getX() || opp.getX() - 1 == super.getX()){
                    if( dy == super.getY() + 1){
                        if( dx == super.getX() + 1 || dx == super.getX() - 1) return true;
                        else return false;
                    }
                    else return false;
                }
                else return false;
            }
            else return false;
        }
        else{
            if(opp != null && opp.getNumMoves() == 1 && opp.getID() == 1 && opp.getX() == dx && opp.getY() == dy + 1){
                if(opp.getX() + 1 == super.getX() || opp.getX() - 1 == super.getX()){
                    if( dy == super.getY() - 1){
                        if(dx == super.getX() + 1 || dx == super.getX() - 1) return true;
                        else return false;
                    }
                    else return false;
                }
                else return false;
            }
            else return false;
        }
    }
}
