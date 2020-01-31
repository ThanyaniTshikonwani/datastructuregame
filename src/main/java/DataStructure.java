import java.util.ArrayList;
import java.util.Arrays;

public class DataStructure {

    int w ;
    int h;
    int [][] board;

    public DataStructure(int w , int h) {
        this.w = w;
        this.h = h;
        this.board =new int[w][h];
    }

    public  void showBoard(){

        for (int y=0; y<h;y++){
            StringBuilder line = new StringBuilder ( "/" );
            for (int x =0;x<w;x++){
                if (this.board[x][y]==0){
                    line.append ( "+" );//Dead Cell
                }else {
                    line.append ( "#" );// Alive Cell
                }
            }
            System.out.println (line);
        }
    }

    public void deadCell(int x,int y){
        this.board[x][y] =0;
    }
    public void aliveCell(int x,int y){
        this.board[x][y] =1;
    }


    public static void main(String[] args) {
        DataStructure dataStructure = new DataStructure ( 6,3 );

        System.out.println ("  Input");
        dataStructure.aliveCell ( 2,0 );
        dataStructure.aliveCell ( 2,1 );
        dataStructure.aliveCell ( 3,1 );
        dataStructure.showBoard ();
    }
}
