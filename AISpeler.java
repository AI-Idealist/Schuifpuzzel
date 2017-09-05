
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

public class AISpeler {
	private boolean isOpgelost = true;
	private int aantalzetten = -1;
	private  Knoop knoop, knoopkopie;
	private  Stack<Bord> padNaarDeOplossing = new Stack<Bord>();

        
	private class Knoop implements Comparable<Knoop>{
		private  Bord bord;
		private  int zetN;
		private  int prioriteit;
		private  Knoop Ouder;
		
		public Knoop(Bord pbord, int zetN, Knoop ouder) {
			this.bord = pbord;
			this.zetN = zetN;
			this.prioriteit = pbord.manhattan() + zetN;
			this.Ouder = ouder;
		}
		public boolean isDoelOpstelling() {
			return bord.isDoelOpstelling();
		}
		
		public int compareTo(Knoop that) {
		   return this.prioriteit - that.prioriteit;
		}
	}

	// find a solution to the initial bord (using the A* algorithm)
    public void zoekOplossing(Bord iniopstelling) {
    	MinPQ<Knoop> open = new MinPQ<Knoop>();

    	if (iniopstelling == null) throw new java.lang.IllegalArgumentException();
    	knoop = new Knoop(iniopstelling, 0, null);
    	knoopkopie = new Knoop(iniopstelling.twin(), 0, null);
    	open.insert(knoop);
    	open.insert(knoopkopie);
    	knoop = open.delMin();
    	
        while(!knoop.isDoelOpstelling()) {
    		for(Bord buur: knoop.bord.buren()) {
    			Bord ReedsBezochtBord = null;
    			if(knoop.Ouder != null) {
                            ReedsBezochtBord = knoop.Ouder.bord;
                        }    
    			if(!buur.equals(ReedsBezochtBord)) {
    				Knoop vlgknoop = new Knoop(buur, knoop.zetN + 1, knoop);
    				open.insert(vlgknoop);
    			}
    		}
    		knoop = open.delMin();
    	}
    	Knoop startknoop = knoop;
	padNaarDeOplossing.push(startknoop.bord);
    	
        while(startknoop.Ouder != null) {
    		startknoop = startknoop.Ouder;
    		padNaarDeOplossing.push(startknoop.bord);
    	}
    	
        if(startknoop.bord.equals(knoopkopie.bord)) {
    		isOpgelost = false;
    		aantalzetten = -1;
    	}
    	else 
    		aantalzetten = knoop.zetN;
    	
    }          
   
     // is the initial bord solvable?
    public boolean vondOplossing() {
    	return isOpgelost;
    }          
    
    // min number of zetten to solve initial bord; -1 if unsolvable
    public int zetten() {
    	return aantalzetten;
    }                     
   
    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Bord> Oplossing() {
    	if(this.isOpgelost)
    	   return this.padNaarDeOplossing;
    	else 
    	   return null;
    }     
}