package Game.Reel;
import java.util.List;
import Game.Symbols.Symbol;

public class ReelMain {


        public Symbol[] reel;
        public int id;
        public int stop;

        public ReelMain(int index, Symbol[] symbols)  {         //RngLib rng
            this.reel = symbols;
            this.id = index;
        }

        public int Spin() {
            int stop = (int)(Math.random() * reel.length);
            return stop;
        }
}
