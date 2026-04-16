package Game.Round;

import Game.DataDef.FreeSpin;
import Game.DataDef.PlayResponse;
import Game.DataDef.Spin;
import Game.Grid.Grid;
import Game.Symbols.Symbol;
import Game.Grid.Pair;
import java.util.ArrayList;
import java.util.List;
import Game.DataDef.Winning;
import Game.Grid.GridMain;


 public  class Round {

        // References to core systems
        protected PlayResponse playResponse;
        protected GridMain grid;
        public static final long refWinAmount = 0;
        public static int numWS = 0;

        // Game state
        protected int mode;
        protected int spinCount = 0;

        // Constructor
        public Round(PlayResponse playResponse, GridMain grid) {
            this.playResponse = playResponse;
            this.grid = grid;
        }
        // Start round
        public void Play() {}
        public void next(boolean gamble, boolean succeed) {}

        // Base spin execution
        protected int runBaseSpin(long winsSoFar) {
            return 0;
        }
        // Free spin execution
        protected void runFreeSpins(int numBS, long winsSoFar) {
        }

        // Single spin logic
        protected Pair<Long,Integer> Spin(boolean baseSpin, Spin spin, long winsSoFar) {

                return new Pair<>(0L,0);
        }
        // Replace reel symbols
        protected void replaceRSymbol() {}

        // Take grid snapshot
        protected List<List<Symbol>> takeGridSnapshot() {

            return new ArrayList<>();
        }
        protected Pair<Boolean, Float> tryGamble(int numBS) {
            return new Pair<>(false,0.0f);                     // Gamble logic
        }
        protected void collect(PlayResponse playResponse) {} // Collect winnings
        protected void clear() {} // Clear round state
        protected void selectSpecialSym(int numWS, Symbol symRemaining, Symbol specialSym) {}
        protected long getWinningsFromSpecialSymbols(FreeSpin freeSpin, long winsSoFar) {
               return winsSoFar;                                             // Calculate winnings from special symbols
        }

        // Convert grid to symbol vector
        protected void makeSymVector(Symbol symbols) {
        }
}
