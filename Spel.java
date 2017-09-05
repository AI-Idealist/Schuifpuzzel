
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;


public class Spel {
    Bord bord;
    AISpeler aispeler;
    
    public static void main(String[] args) {
              
      String filename = args[0];
      In in = new In(filename);
      int N = in.readInt();

      int[][] opstelling = new int[N][N];
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
           opstelling[i][j] = in.readInt();
        }
      }
      Bord bord = new Bord(opstelling);
      AISpeler aispeler  = new AISpeler();   
      
      aispeler.zoekOplossing(bord);
     
      if (!aispeler.vondOplossing()){
         StdOut.println("No solution possible");}
      else {
        StdOut.println("Minimum number of moves = " + aispeler.zetten());
        for (Bord board : aispeler.Oplossing())
          StdOut.println(board);
    
        }
     }
    }
    
    
    
    
