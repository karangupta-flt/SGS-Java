package Game.Reel;

import Game.Round.Round;
import Game.Symbols.Symbol;

public class Reel {

        protected Game.Round.Round Round;
        protected final Symbol[] reel;
        protected final int id;
        protected int stop = -1;
        //protected final RngLib rng;

        public Reel(int index, Symbol[] symbols ) {   //RngLib rng
            this.id = index;
            this.reel = symbols;
            //this.rng = rng;
        }

        public int size() {

            return reel.length;
        }

        public int getId() {

            return id;
        }

        public int setStop(int s) {
            int size = reel.length;
            if (s < 0) {
                stop = size - Math.abs(s) % size;
            } else {
                stop = s % size;
            }
            return stop;
        }

        public int moveBy(int m) {

            return setStop(stop + m);
        }

        public int stopPosition() {

            return stop;
        }

        public Symbol symbolAt(int position) {

            return reel[position % reel.length];
        }

        public Symbol[] getReel() {

            return reel;
        }

//       public int spin() {
//            int rnd = rng.nextInt(reel.size());
//       }
//           return setStop(rnd);
//       // }

        public void setRoundRef(Round round) {

            this.Round = round;
        }


    public int Spin() {
        return 0;
    }

    public int stopPos() {
        return 0;
    }
}
