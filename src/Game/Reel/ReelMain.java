package Game.Reel;

import Game.Symbols.Symbol;

public class ReelMain extends Reel{

        public ReelMain(int index, Symbol[] symbols)  {         //RngLib rng
            super (index, symbols);
        }


        @Override
        public int Spin() {
            int stop = (int)(Math.random() * reel.length);
            return stop;
        }
}
