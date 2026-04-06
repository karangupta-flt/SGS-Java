package Game.Round;

import Game.DataDef.FreeSpin;
import Game.DataDef.PlayResponse;
import Game.DataDef.Spin;
import Game.Grid.Grid;
import Game.Symbols.Symbol;

import java.util.ArrayList;
import java.util.List;

public  class Round {

        // References to core systems
        public PlayResponse playResponse;
        public Grid grid;
        //protected RngLib rng;
        public static final long refWinAmount = 0;
        public static int numWS = 0;

        // Game state
        protected int mode;
        protected int spinCount = 0;

        // Constructor
        public Round(PlayResponse playResponse, Grid grid) {  //RngLib rng
            this.playResponse = playResponse;
            this.grid = grid;
           // this.rng = rng;
        }

        // Start round
        public void play() {}
        public void next(boolean gamble, boolean succeed) {}

        // Base spin execution
        protected int runBaseSpin(long winsSoFar) {
            return 0;
        }
        // Free spin execution
        protected void runFreeSpins(int numBS, long winsSoFar) {
        }

        // Single spin logic
        protected SpinResult spin(boolean baseSpin, Spin spin, long winsSoFar) {

                return new SpinResult(0L, 0);
        }


    public record SpinResult(long winAmount, int nextState) {

        public static long refWinAmount = 0;
    }




    // Replace reel symbols
        protected void replaceRSymbol() {
        }

        // Take grid snapshot
        protected List<List<Symbol>> takeGridSnapshot() {

            return new ArrayList<>();
        }

        // Gamble logic
        protected GambleResult tryGamble(int numBS) {

            return new GambleResult(false, 0.0f);
        }

        public record GambleResult(boolean success, float multiplier) {
            public static final boolean succeeded = false;
            public static final double prob = 0.0f;
        }

        // Collect winnings
        protected void collect(PlayResponse playResponse) {
        }

        // Clear round state
        protected void clear() {
        }

        // Select special symbols (overridable)
        protected void selectSpecialSym(int numWS, Symbol symRemaining, Symbol specialSym) {
        }

        // Calculate winnings from special symbols
        protected long getWinningsFromSpecialSymbols(FreeSpin freeSpin, long winsSoFar) {

            return winsSoFar;
        }

        // Convert grid to symbol vector
        protected void makeSymVector() {
        }
}
