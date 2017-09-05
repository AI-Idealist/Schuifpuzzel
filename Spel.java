
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;


public class Spel {
    Bord bord;
    AISpeler aispeler = new AISpeler(bord);
    
    public static void main(String[] args) {
              
      String filename = args[0];
      In in = new In(filename);
      int N = in.readInt();

      int[][] tiles = new int[N][N];
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
           tiles[i][j] = in.readInt();
        }
      }
      Bord initial = new Bord(tiles);
          
      // solve the puzzle
      AISpeler speler = new AISpeler(initial);
     
      if (!speler.vondOplossing()){
         StdOut.println("No solution possible");}
      else {
        StdOut.println("Minimum number of moves = " + speler.zetten());
        for (Bord board : speler.Oplossing())
          StdOut.println(board);
    
        }
     }
    }// unit tests (not graded)
    
    
    
    
