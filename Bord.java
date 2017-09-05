
import edu.princeton.cs.algs4.Bag;

public class Bord {
   private  int N;
   private  int[][] velden;
     
   public Bord(int[][] pvelden) {
    	N = pvelden.length;
    	velden = new int[N][N];

    	for(int i = 0; i < N; i++) {
    		for(int j = 0; j < N; j++) {
                    velden[i][j] = pvelden[i][j];
    		}
    	}
    }         
        
    public int dimension() {
    	return N;
    }
    
    public int hamming() {
                
    	int count = 0;
    	for(int i = 0; i < N; i++) {
    		for(int j = 0; j < N; j++) {
    			if(velden[i][j] != N*i+j+1 && velden[i][j] != 0) {
    				count++;
    			}
    		}
    	}
    	return count;
    }     
    

    public int manhattan() {
    	int manhattanL = 0;
    	for(int i = 0; i < N; i++) {
    		for(int j = 0; j < N; j++) {
    			if(velden[i][j] != 0) {
        	    	if(velden[i][j]%N == 0) {
        	    		manhattanL = manhattanL + Math.abs(velden[i][j]/N-1-i) + Math.abs(N-1-j);
        	    	}
        	    	else {
        	    		manhattanL = manhattanL + Math.abs(velden[i][j]/N-i) + Math.abs(velden[i][j]%N -1-j);
        	    	}	
    			}
    		}
    	}
    	return manhattanL;
    }    
    
// sum of Manhattan distances between blocks and goal
    public boolean isDoelOpstelling() {
    	return this.hamming() == 0;
    } 
    
    // is this board the goal board?
    public Bord twin() {
        int[][] kopiebord = new int[N][N];
       	for(int i = 0; i < N; i++) {
          for(int j = 0; j < N; j++) {
        	kopiebord[i][j] = velden[i][j];
          }
        }
   	
        int i = 0, j = 0;
        if (kopiebord[i][j] != 0 && kopiebord[i][j+1] != 0){
            wissel(kopiebord, i, j, i, j + 1);
        }
        else{
            wissel(kopiebord, i + 1, j, i + 1, j+1);
        }
    return new Bord(kopiebord);
}
 
    public boolean equals(Object y) {
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;

        Bord that = (Bord) y;
        if(this.N != that.N) return false;
        else {
        	for(int i = 0; i < this.N; i++) {
        		for(int j = 0; j < this.N; j++) {
        			if(that.velden[i][j] != this.velden[i][j])
        				return false;
        		}
        	}
        }
        return true;
    }
    
    
    public Iterable<Bord> buren() {
        // find index of the zero
    	int index_x = 0;
    	int index_y = 0;
    	for(int i = 0; i < this.N; i++) {
    		for(int j = 0; j < this.N; j++) {
    			if(velden[i][j]==0) {
    				index_x = i;
    				index_y = j;
    			}
    		}
    	}
        Bag<Bord> buren = new Bag<Bord>();

    	if(index_x > 0) {
    		int[][] temp = new int[N][N];
    		temp = kopieerVelden();
    		temp[index_x][index_y] = temp[index_x - 1][index_y];
    		temp[index_x-1][index_y] = 0;
    		buren.add(new Bord(temp));
    	}
    	if(index_x < N-1) {
    		int[][] temp = new int[N][N];
    		temp = kopieerVelden();
    		temp[index_x][index_y] = temp[index_x + 1][index_y];
    		temp[index_x+1][index_y] = 0;
    		buren.add(new Bord(temp));
    	}
    	if(index_y > 0) {
    		int[][] temp = new int[N][N];
    		temp = kopieerVelden();
    		temp[index_x][index_y] = temp[index_x][index_y - 1];
    		temp[index_x][index_y - 1] = 0;
    		buren.add(new Bord(temp));
    	}
    	if(index_y < N-1) {
    		int[][] temp = new int[N][N];
    		temp = kopieerVelden();
    		temp[index_x][index_y] = temp[index_x][index_y + 1];
    		temp[index_x][index_y + 1] = 0;
    		buren.add(new Bord(temp));
    	}
    	return buren;
    }    
    
    
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", velden[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }              // string representation of this board (in the output format specified below)

  
   
/* helper functions */
    
private void wissel(int[][] blocks, int i1, int j1, int i2, int j2) {
        int t = blocks[i1][j1];
        blocks[i1][j1] = blocks[i2][j2];
        blocks[i2][j2] = t;
}   

private int[][] kopieerVelden() {
    	int[][] copy = new int[N][N];
    	for(int i = 0; i < N; i++) {
    		for(int j = 0; j < N; j++) {
    			copy[i][j] = velden[i][j];
    		}
    	}
    	return copy;
    }

    
}

  