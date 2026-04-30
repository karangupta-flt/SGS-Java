package Game.Reel;

import Game.Round.Round;
import Game.Symbols.Symbol;

public class Reel {


    protected Game.Round.Round Round;
        protected final Symbol[] reel;
        protected final int id;
        protected int stop = -1;
        //public int length;

        public Reel(int index, Symbol[] symbols ) {
            this.id = index;
            this.reel = symbols;
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

        public int stopPos() {
            return stop;
        }

        public Symbol symbolAt(int pos) {

            return reel[pos % reel.length];
        }

        public Symbol[] getReel() {
            return reel;
        }

        public int Spin() {
            stop = (int)(Math.random() * reel.length);
            //System.out.println("inside Reel Stop : "+ stop);
            return stop;
        }


        public void setRoundRef(Round round) {

            this.Round = round;
        }





}
