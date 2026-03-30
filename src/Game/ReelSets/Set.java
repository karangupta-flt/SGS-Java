package Game.ReelSets;

import Game.Reel.Reel;
import Game.Round.Round;

    public class Set {
        public final int id;
        public final String name;
        public Reel[] reel;

        // protected RngLib rng;

        // Constructor
        public Set(int id, String name, Reel[] reel) {
            this.id = id;
            this.name = name;
            this.reel = reel;
            //Random Rng= rng;
        }


        // Getter for reels
        public Reel[] getReels() {

            return reel;
        }

        // Set Round reference for all reels
        public void setRoundRef(Round Round) {
            for (Reel reel : reel) {    // for each loop.
                reel.setRoundRef(Round);
            }
        }



        //Set[] sets = new Set[8];

        public String getName() {
            return name;
        }
    }


